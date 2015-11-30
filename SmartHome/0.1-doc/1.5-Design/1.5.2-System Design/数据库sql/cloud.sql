-----------------------------------------------------
-- Export file for user CLOUD                      --
-- Created by Administrator on 2014/11/20, 9:53:53 --
-----------------------------------------------------

spool cloud.log

prompt
prompt Creating table ACT_RE_DEPLOYMENT
prompt ================================
prompt
create table ACT_RE_DEPLOYMENT
(
  id_          NVARCHAR2(64) not null,
  name_        NVARCHAR2(255),
  deploy_time_ TIMESTAMP(6)
)
;
alter table ACT_RE_DEPLOYMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_GE_BYTEARRAY
prompt ===============================
prompt
create table ACT_GE_BYTEARRAY
(
  id_            NVARCHAR2(64) not null,
  rev_           INTEGER,
  name_          NVARCHAR2(255),
  deployment_id_ NVARCHAR2(64),
  bytes_         BLOB,
  generated_     NUMBER(1)
)
;
create index ACT_IDX_BYTEAR_DEPL on ACT_GE_BYTEARRAY (DEPLOYMENT_ID_);
alter table ACT_GE_BYTEARRAY
  add primary key (ID_);
alter table ACT_GE_BYTEARRAY
  add constraint ACT_FK_BYTEARR_DEPL foreign key (DEPLOYMENT_ID_)
  references ACT_RE_DEPLOYMENT (ID_);
alter table ACT_GE_BYTEARRAY
  add check (GENERATED_ IN (1,0));

prompt
prompt Creating table ACT_GE_PROPERTY
prompt ==============================
prompt
create table ACT_GE_PROPERTY
(
  name_  NVARCHAR2(64) not null,
  value_ NVARCHAR2(300),
  rev_   INTEGER
)
;
alter table ACT_GE_PROPERTY
  add primary key (NAME_);

prompt
prompt Creating table ACT_HI_ACTINST
prompt =============================
prompt
create table ACT_HI_ACTINST
(
  id_           NVARCHAR2(64) not null,
  proc_def_id_  NVARCHAR2(64) not null,
  proc_inst_id_ NVARCHAR2(64) not null,
  execution_id_ NVARCHAR2(64) not null,
  act_id_       NVARCHAR2(255) not null,
  act_name_     NVARCHAR2(255),
  act_type_     NVARCHAR2(255) not null,
  assignee_     NVARCHAR2(64),
  start_time_   TIMESTAMP(6) not null,
  end_time_     TIMESTAMP(6),
  duration_     NUMBER(19)
)
;
create index ACT_IDX_HI_ACT_INST_END on ACT_HI_ACTINST (END_TIME_);
create index ACT_IDX_HI_ACT_INST_START on ACT_HI_ACTINST (START_TIME_);
alter table ACT_HI_ACTINST
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_ATTACHMENT
prompt ================================
prompt
create table ACT_HI_ATTACHMENT
(
  id_           NVARCHAR2(64) not null,
  rev_          INTEGER,
  user_id_      NVARCHAR2(255),
  name_         NVARCHAR2(255),
  description_  NVARCHAR2(2000),
  type_         NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  url_          NVARCHAR2(2000),
  content_id_   NVARCHAR2(64)
)
;
alter table ACT_HI_ATTACHMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_COMMENT
prompt =============================
prompt
create table ACT_HI_COMMENT
(
  id_           NVARCHAR2(64) not null,
  type_         NVARCHAR2(255),
  time_         TIMESTAMP(6) not null,
  user_id_      NVARCHAR2(255),
  task_id_      NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  action_       NVARCHAR2(255),
  message_      NVARCHAR2(2000),
  full_msg_     BLOB
)
;
alter table ACT_HI_COMMENT
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_DETAIL
prompt ============================
prompt
create table ACT_HI_DETAIL
(
  id_           VARCHAR2(64) not null,
  type_         NVARCHAR2(255) not null,
  proc_inst_id_ NVARCHAR2(64) not null,
  execution_id_ NVARCHAR2(64) not null,
  task_id_      NVARCHAR2(64),
  act_inst_id_  NVARCHAR2(64),
  name_         NVARCHAR2(255) not null,
  var_type_     NVARCHAR2(64),
  rev_          INTEGER,
  time_         TIMESTAMP(6) not null,
  bytearray_id_ NVARCHAR2(64),
  double_       NUMBER(*,10),
  long_         NUMBER(19),
  text_         NVARCHAR2(2000),
  text2_        NVARCHAR2(2000)
)
;
create index ACT_IDX_HI_DETAIL_ACT_INST on ACT_HI_DETAIL (ACT_INST_ID_);
create index ACT_IDX_HI_DETAIL_NAME on ACT_HI_DETAIL (NAME_);
create index ACT_IDX_HI_DETAIL_PROC_INST on ACT_HI_DETAIL (PROC_INST_ID_);
create index ACT_IDX_HI_DETAIL_TIME on ACT_HI_DETAIL (TIME_);
alter table ACT_HI_DETAIL
  add primary key (ID_);

prompt
prompt Creating table ACT_HI_PROCINST
prompt ==============================
prompt
create table ACT_HI_PROCINST
(
  id_                        NVARCHAR2(64) not null,
  proc_inst_id_              NVARCHAR2(64) not null,
  business_key_              NVARCHAR2(255),
  proc_def_id_               NVARCHAR2(64) not null,
  start_time_                TIMESTAMP(6) not null,
  end_time_                  TIMESTAMP(6),
  duration_                  NUMBER(19),
  start_user_id_             NVARCHAR2(255),
  start_act_id_              NVARCHAR2(255),
  end_act_id_                NVARCHAR2(255),
  super_process_instance_id_ NVARCHAR2(64),
  delete_reason_             NVARCHAR2(2000)
)
;
create index ACT_IDX_HI_PRO_INST_END on ACT_HI_PROCINST (END_TIME_);
create index ACT_IDX_HI_PRO_I_BUSKEY on ACT_HI_PROCINST (BUSINESS_KEY_);
create unique index ACT_UNIQ_HI_BUS_KEY on ACT_HI_PROCINST (CASE  WHEN BUSINESS_KEY_ IS NULL THEN NULL ELSE PROC_DEF_ID_ END, CASE  WHEN BUSINESS_KEY_ IS NULL THEN NULL ELSE BUSINESS_KEY_ END);
alter table ACT_HI_PROCINST
  add primary key (ID_);
alter table ACT_HI_PROCINST
  add unique (PROC_INST_ID_);

prompt
prompt Creating table ACT_HI_TASKINST
prompt ==============================
prompt
create table ACT_HI_TASKINST
(
  id_             NVARCHAR2(64) not null,
  proc_def_id_    NVARCHAR2(64),
  task_def_key_   NVARCHAR2(255),
  proc_inst_id_   NVARCHAR2(64),
  execution_id_   NVARCHAR2(64),
  parent_task_id_ NVARCHAR2(64),
  name_           NVARCHAR2(255),
  description_    NVARCHAR2(2000),
  owner_          NVARCHAR2(64),
  assignee_       NVARCHAR2(64),
  start_time_     TIMESTAMP(6) not null,
  end_time_       TIMESTAMP(6),
  duration_       NUMBER(19),
  delete_reason_  NVARCHAR2(2000),
  priority_       INTEGER,
  due_date_       TIMESTAMP(6)
)
;
alter table ACT_HI_TASKINST
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_GROUP
prompt ===========================
prompt
create table ACT_ID_GROUP
(
  id_   NVARCHAR2(64) not null,
  rev_  INTEGER,
  name_ NVARCHAR2(255),
  type_ NVARCHAR2(255)
)
;
alter table ACT_ID_GROUP
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_INFO
prompt ==========================
prompt
create table ACT_ID_INFO
(
  id_        NVARCHAR2(64) not null,
  rev_       INTEGER,
  user_id_   NVARCHAR2(64),
  type_      NVARCHAR2(64),
  key_       NVARCHAR2(255),
  value_     NVARCHAR2(255),
  password_  BLOB,
  parent_id_ NVARCHAR2(255)
)
;
alter table ACT_ID_INFO
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_USER
prompt ==========================
prompt
create table ACT_ID_USER
(
  id_         NVARCHAR2(64) not null,
  rev_        INTEGER,
  first_      NVARCHAR2(255),
  last_       NVARCHAR2(255),
  email_      NVARCHAR2(255),
  pwd_        NVARCHAR2(255),
  picture_id_ NVARCHAR2(64)
)
;
alter table ACT_ID_USER
  add primary key (ID_);

prompt
prompt Creating table ACT_ID_MEMBERSHIP
prompt ================================
prompt
create table ACT_ID_MEMBERSHIP
(
  user_id_  NVARCHAR2(64) not null,
  group_id_ NVARCHAR2(64) not null
)
;
create index ACT_IDX_MEMB_GROUP on ACT_ID_MEMBERSHIP (GROUP_ID_);
create index ACT_IDX_MEMB_USER on ACT_ID_MEMBERSHIP (USER_ID_);
alter table ACT_ID_MEMBERSHIP
  add primary key (USER_ID_, GROUP_ID_);
alter table ACT_ID_MEMBERSHIP
  add constraint ACT_FK_MEMB_GROUP foreign key (GROUP_ID_)
  references ACT_ID_GROUP (ID_);
alter table ACT_ID_MEMBERSHIP
  add constraint ACT_FK_MEMB_USER foreign key (USER_ID_)
  references ACT_ID_USER (ID_);

prompt
prompt Creating table ACT_RE_PROCDEF
prompt =============================
prompt
create table ACT_RE_PROCDEF
(
  id_                 NVARCHAR2(64) not null,
  rev_                INTEGER,
  category_           NVARCHAR2(255),
  name_               NVARCHAR2(255),
  key_                NVARCHAR2(255),
  version_            INTEGER,
  deployment_id_      NVARCHAR2(64),
  resource_name_      NVARCHAR2(2000),
  dgrm_resource_name_ VARCHAR2(4000),
  has_start_form_key_ NUMBER(1),
  suspension_state_   INTEGER
)
;
alter table ACT_RE_PROCDEF
  add primary key (ID_);
alter table ACT_RE_PROCDEF
  add check (HAS_START_FORM_KEY_ IN (1,0));

prompt
prompt Creating table ACT_RU_EXECUTION
prompt ===============================
prompt
create table ACT_RU_EXECUTION
(
  id_               NVARCHAR2(64) not null,
  rev_              INTEGER,
  proc_inst_id_     NVARCHAR2(64),
  business_key_     NVARCHAR2(255),
  parent_id_        NVARCHAR2(64),
  proc_def_id_      NVARCHAR2(64),
  super_exec_       NVARCHAR2(64),
  act_id_           NVARCHAR2(255),
  is_active_        NUMBER(1),
  is_concurrent_    NUMBER(1),
  is_scope_         NUMBER(1),
  is_event_scope_   NUMBER(1),
  suspension_state_ INTEGER
)
;
create index ACT_IDX_EXEC_BUSKEY on ACT_RU_EXECUTION (BUSINESS_KEY_);
create index ACT_IDX_EXE_PARENT on ACT_RU_EXECUTION (PARENT_ID_);
create index ACT_IDX_EXE_PROCINST on ACT_RU_EXECUTION (PROC_INST_ID_);
create index ACT_IDX_EXE_SUPER on ACT_RU_EXECUTION (SUPER_EXEC_);
create unique index ACT_UNIQ_RU_BUS_KEY on ACT_RU_EXECUTION (CASE  WHEN BUSINESS_KEY_ IS NULL THEN NULL ELSE PROC_DEF_ID_ END, CASE  WHEN BUSINESS_KEY_ IS NULL THEN NULL ELSE BUSINESS_KEY_ END);
alter table ACT_RU_EXECUTION
  add primary key (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PARENT foreign key (PARENT_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add constraint ACT_FK_EXE_SUPER foreign key (SUPER_EXEC_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_EXECUTION
  add check (IS_ACTIVE_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_CONCURRENT_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_SCOPE_ IN (1,0));
alter table ACT_RU_EXECUTION
  add check (IS_EVENT_SCOPE_ IN (1,0));

prompt
prompt Creating table ACT_RU_EVENT_SUBSCR
prompt ==================================
prompt
create table ACT_RU_EVENT_SUBSCR
(
  id_            NVARCHAR2(64) not null,
  rev_           INTEGER,
  event_type_    NVARCHAR2(255) not null,
  event_name_    NVARCHAR2(255),
  execution_id_  NVARCHAR2(64),
  proc_inst_id_  NVARCHAR2(64),
  activity_id_   NVARCHAR2(64),
  configuration_ NVARCHAR2(255),
  created_       TIMESTAMP(6) not null
)
;
create index ACT_IDX_EVENT_SUBSCR on ACT_RU_EVENT_SUBSCR (EXECUTION_ID_);
create index ACT_IDX_EVENT_SUBSCR_CONFIG_ on ACT_RU_EVENT_SUBSCR (CONFIGURATION_);
alter table ACT_RU_EVENT_SUBSCR
  add primary key (ID_);
alter table ACT_RU_EVENT_SUBSCR
  add constraint ACT_FK_EVENT_EXEC foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table ACT_RU_TASK
prompt ==========================
prompt
create table ACT_RU_TASK
(
  id_             NVARCHAR2(64) not null,
  rev_            INTEGER,
  execution_id_   NVARCHAR2(64),
  proc_inst_id_   NVARCHAR2(64),
  proc_def_id_    NVARCHAR2(64),
  name_           NVARCHAR2(255),
  parent_task_id_ NVARCHAR2(64),
  description_    NVARCHAR2(2000),
  task_def_key_   NVARCHAR2(255),
  owner_          NVARCHAR2(64),
  assignee_       NVARCHAR2(64),
  delegation_     NVARCHAR2(64),
  priority_       INTEGER,
  create_time_    TIMESTAMP(6),
  due_date_       TIMESTAMP(6)
)
;
create index ACT_IDX_TASK_CREATE on ACT_RU_TASK (CREATE_TIME_);
create index ACT_IDX_TASK_EXEC on ACT_RU_TASK (EXECUTION_ID_);
create index ACT_IDX_TASK_PROCDEF on ACT_RU_TASK (PROC_DEF_ID_);
create index ACT_IDX_TASK_PROCINST on ACT_RU_TASK (PROC_INST_ID_);
alter table ACT_RU_TASK
  add primary key (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCDEF foreign key (PROC_DEF_ID_)
  references ACT_RE_PROCDEF (ID_);
alter table ACT_RU_TASK
  add constraint ACT_FK_TASK_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table ACT_RU_IDENTITYLINK
prompt ==================================
prompt
create table ACT_RU_IDENTITYLINK
(
  id_       NVARCHAR2(64) not null,
  rev_      INTEGER,
  group_id_ NVARCHAR2(64),
  type_     NVARCHAR2(255),
  user_id_  NVARCHAR2(64),
  task_id_  NVARCHAR2(64)
)
;
create index ACT_IDX_IDENT_LNK_GROUP on ACT_RU_IDENTITYLINK (GROUP_ID_);
create index ACT_IDX_IDENT_LNK_USER on ACT_RU_IDENTITYLINK (USER_ID_);
create index ACT_IDX_TSKASS_TASK on ACT_RU_IDENTITYLINK (TASK_ID_);
alter table ACT_RU_IDENTITYLINK
  add primary key (ID_);
alter table ACT_RU_IDENTITYLINK
  add constraint ACT_FK_TSKASS_TASK foreign key (TASK_ID_)
  references ACT_RU_TASK (ID_);

prompt
prompt Creating table ACT_RU_JOB
prompt =========================
prompt
create table ACT_RU_JOB
(
  id_                  NVARCHAR2(64) not null,
  rev_                 INTEGER,
  type_                NVARCHAR2(255) not null,
  lock_exp_time_       TIMESTAMP(6),
  lock_owner_          NVARCHAR2(255),
  exclusive_           NUMBER(1),
  execution_id_        NVARCHAR2(64),
  process_instance_id_ NVARCHAR2(64),
  retries_             INTEGER,
  exception_stack_id_  NVARCHAR2(64),
  exception_msg_       NVARCHAR2(2000),
  duedate_             TIMESTAMP(6),
  repeat_              NVARCHAR2(255),
  handler_type_        NVARCHAR2(255),
  handler_cfg_         NVARCHAR2(2000)
)
;
create index ACT_IDX_JOB_EXCEPTION on ACT_RU_JOB (EXCEPTION_STACK_ID_);
alter table ACT_RU_JOB
  add primary key (ID_);
alter table ACT_RU_JOB
  add constraint ACT_FK_JOB_EXCEPTION foreign key (EXCEPTION_STACK_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_RU_JOB
  add check (EXCLUSIVE_ IN (1,0));

prompt
prompt Creating table ACT_RU_VARIABLE
prompt ==============================
prompt
create table ACT_RU_VARIABLE
(
  id_           NVARCHAR2(64) not null,
  rev_          INTEGER,
  type_         NVARCHAR2(255) not null,
  name_         NVARCHAR2(255) not null,
  execution_id_ NVARCHAR2(64),
  proc_inst_id_ NVARCHAR2(64),
  task_id_      NVARCHAR2(64),
  bytearray_id_ NVARCHAR2(64),
  double_       NUMBER(*,10),
  long_         NUMBER(19),
  text_         NVARCHAR2(2000),
  text2_        NVARCHAR2(2000)
)
;
create index ACT_IDX_VAR_BYTEARRAY on ACT_RU_VARIABLE (BYTEARRAY_ID_);
create index ACT_IDX_VAR_EXE on ACT_RU_VARIABLE (EXECUTION_ID_);
create index ACT_IDX_VAR_PROCINST on ACT_RU_VARIABLE (PROC_INST_ID_);
alter table ACT_RU_VARIABLE
  add primary key (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_BYTEARRAY foreign key (BYTEARRAY_ID_)
  references ACT_GE_BYTEARRAY (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_EXE foreign key (EXECUTION_ID_)
  references ACT_RU_EXECUTION (ID_);
alter table ACT_RU_VARIABLE
  add constraint ACT_FK_VAR_PROCINST foreign key (PROC_INST_ID_)
  references ACT_RU_EXECUTION (ID_);

prompt
prompt Creating table API
prompt ==================
prompt
create table API
(
  api_id      VARCHAR2(40) not null,
  service_id  VARCHAR2(40),
  api_name    VARCHAR2(100),
  api_url     VARCHAR2(200),
  api_type    VARCHAR2(20),
  api_memo    VARCHAR2(100),
  api_result  VARCHAR2(50),
  api_format  VARCHAR2(20),
  create_user VARCHAR2(40),
  create_time DATE default sysdate,
  del_flag    NUMBER(1) default 0
)
;
comment on table API
  is 'API';
comment on column API.api_id
  is 'API ID';
comment on column API.service_id
  is '����ID';
comment on column API.api_name
  is 'API����';
comment on column API.api_url
  is 'API��ַ';
comment on column API.api_type
  is 'API����';
comment on column API.api_memo
  is 'API˵��';
comment on column API.api_result
  is '���ؽ��';
comment on column API.api_format
  is '���ظ�ʽ';
comment on column API.create_user
  is '������';
comment on column API.create_time
  is '����ʱ��';
comment on column API.del_flag
  is 'ɾ�����';
alter table API
  add constraint PK_API primary key (API_ID);

prompt
prompt Creating table C001
prompt ===================
prompt
create table C001
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(60) not null,
  ma003 VARCHAR2(20) not null,
  ma004 VARCHAR2(36),
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(20),
  ma007 VARCHAR2(30),
  ma008 VARCHAR2(2) not null,
  ma009 NUMBER(4) default 0 not null,
  ma010 VARCHAR2(400),
  ma011 VARCHAR2(40) not null,
  ma012 DATE not null,
  ma013 VARCHAR2(40),
  ma014 DATE,
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(8),
  ma017 VARCHAR2(1),
  ma018 VARCHAR2(20),
  ma019 VARCHAR2(20)
)
;
comment on table C001
  is '��˾��';
comment on column C001.ma001
  is '����';
comment on column C001.ma002
  is '��ҵ����
';
comment on column C001.ma003
  is '��֯��������';
comment on column C001.ma004
  is '��ȫ�����������';
comment on column C001.ma005
  is '��ҵ��ϵ�绰(�̻�)
';
comment on column C001.ma006
  is '�ֻ���
';
comment on column C001.ma007
  is '����';
comment on column C001.ma008
  is '�Ƿ���Ч
��Ч��00����Ч��10
';
comment on column C001.ma009
  is 'ͬʱ��������
';
comment on column C001.ma010
  is '��ע';
comment on column C001.ma011
  is '������
';
comment on column C001.ma012
  is '��������
';
comment on column C001.ma013
  is '�޸���
';
comment on column C001.ma014
  is '�޸�����
';
comment on column C001.ma015
  is '������������';
comment on column C001.ma016
  is 'ʡֱϽ�к��ּ��';
comment on column C001.ma017
  is 'ɾ����� 1����Ч��0��ɾ��';
comment on column C001.ma018
  is '�����ֶ�1';
comment on column C001.ma019
  is '�����ֶ�2';
alter table C001
  add constraint PK_C001 primary key (MA001);

prompt
prompt Creating table C002
prompt ===================
prompt
create table C002
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(20) not null,
  ma003 VARCHAR2(50) not null,
  ma004 VARCHAR2(36) not null,
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(20),
  ma007 VARCHAR2(30) not null,
  ma008 VARCHAR2(2) not null,
  ma009 VARCHAR2(300),
  ma010 VARCHAR2(40) not null,
  ma011 DATE not null,
  ma012 VARCHAR2(40),
  ma013 DATE,
  ma014 NUMBER(4) default 0,
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(8),
  ma017 VARCHAR2(1),
  ma018 VARCHAR2(20),
  ma019 VARCHAR2(20)
)
;
comment on table C002
  is '������λ��';
comment on column C002.ma001
  is 'ID';
comment on column C002.ma002
  is '��֯��������';
comment on column C002.ma003
  is '������λ����
';
comment on column C002.ma004
  is '�ϼ����ܵ�λ����
';
comment on column C002.ma005
  is '��ϵ�绰(�̻�)
';
comment on column C002.ma006
  is '�ֻ���
';
comment on column C002.ma007
  is '����
';
comment on column C002.ma008
  is '�Ƿ���Ч ��Ч��00����Ч��10"';
comment on column C002.ma009
  is '��ע�ֶ�
';
comment on column C002.ma010
  is '������
';
comment on column C002.ma011
  is '��������
';
comment on column C002.ma012
  is '�޸���
';
comment on column C002.ma013
  is '�޸�����
';
comment on column C002.ma014
  is 'ͬʱ��������';
comment on column C002.ma015
  is '������������';
comment on column C002.ma016
  is 'ʡֱϽ�к��ּ��';
comment on column C002.ma017
  is 'ɾ����� 1����Ч��0��ɾ��';
comment on column C002.ma018
  is '�����ֶ�1';
comment on column C002.ma019
  is '�����ֶ�2';
alter table C002
  add constraint PK_C002 primary key (MA001);

prompt
prompt Creating table C004
prompt ===================
prompt
create table C004
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(200) not null,
  ma005 VARCHAR2(200),
  ma006 VARCHAR2(200),
  ma007 VARCHAR2(20),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(40),
  ma010 DATE not null
)
;
comment on table C004
  is '�����洢';
comment on column C004.ma001
  is '����';
comment on column C004.ma002
  is '��������';
comment on column C004.ma003
  is '������';
comment on column C004.ma004
  is '����·��1';
comment on column C004.ma005
  is '����·��2';
comment on column C004.ma006
  is '����·��3';
comment on column C004.ma007
  is '��������';
comment on column C004.ma008
  is '����pojo��';
comment on column C004.ma009
  is '������';
comment on column C004.ma010
  is '��������';
alter table C004
  add constraint C004_PK primary key (MA001);

prompt
prompt Creating table C004_TEMP
prompt ========================
prompt
create table C004_TEMP
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(200) not null,
  ma005 VARCHAR2(200),
  ma006 VARCHAR2(200),
  ma007 VARCHAR2(20),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(40),
  ma010 DATE not null
)
;
comment on table C004_TEMP
  is '��������ʱ��¼��ű�';
alter table C004_TEMP
  add constraint C004_TEMP_PK primary key (MA001);

prompt
prompt Creating table CLOUD_ACCESSORY
prompt ==============================
prompt
create table CLOUD_ACCESSORY
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(300),
  ma004 VARCHAR2(300),
  ma005 VARCHAR2(30),
  ma006 INTEGER,
  ma007 DATE,
  ma008 VARCHAR2(200),
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100),
  ma011 VARCHAR2(100)
)
;
comment on table CLOUD_ACCESSORY
  is '������';
comment on column CLOUD_ACCESSORY.ma001
  is '����id';
comment on column CLOUD_ACCESSORY.ma002
  is '��������';
comment on column CLOUD_ACCESSORY.ma003
  is '������ַ';
comment on column CLOUD_ACCESSORY.ma004
  is '�����ַ';
comment on column CLOUD_ACCESSORY.ma005
  is '��������';
comment on column CLOUD_ACCESSORY.ma006
  is '����';
comment on column CLOUD_ACCESSORY.ma007
  is '����ʱ��';
comment on column CLOUD_ACCESSORY.ma008
  is 'ͼƬ����';
comment on column CLOUD_ACCESSORY.ma009
  is '�����ֶ�1';
comment on column CLOUD_ACCESSORY.ma010
  is '�����ֶ�2';
comment on column CLOUD_ACCESSORY.ma011
  is '�����ֶ�3';
alter table CLOUD_ACCESSORY
  add constraint PK_CLOUD_ACCESSORY primary key (MA001);

prompt
prompt Creating table CLOUD_CUSTOMERCASE
prompt =================================
prompt
create table CLOUD_CUSTOMERCASE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(200),
  ma004 VARCHAR2(200),
  ma005 CLOB,
  ma006 DATE,
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100)
)
;
comment on table CLOUD_CUSTOMERCASE
  is '�ͻ���������';
comment on column CLOUD_CUSTOMERCASE.ma001
  is '����id';
comment on column CLOUD_CUSTOMERCASE.ma002
  is '����id';
comment on column CLOUD_CUSTOMERCASE.ma003
  is '�ͻ�����';
comment on column CLOUD_CUSTOMERCASE.ma004
  is '����';
comment on column CLOUD_CUSTOMERCASE.ma005
  is '����';
comment on column CLOUD_CUSTOMERCASE.ma006
  is '����ʱ��';
comment on column CLOUD_CUSTOMERCASE.ma007
  is '�����ֶ�1';
comment on column CLOUD_CUSTOMERCASE.ma008
  is '�����ֶ�2';
comment on column CLOUD_CUSTOMERCASE.ma009
  is '�����ֶ�3';
alter table CLOUD_CUSTOMERCASE
  add constraint PK_CLOUD_CUSTOMERCASE primary key (MA001);

prompt
prompt Creating table CLOUD_FAQ
prompt ========================
prompt
create table CLOUD_FAQ
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(200),
  ma004 CLOB,
  ma005 DATE,
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100)
)
;
comment on table CLOUD_FAQ
  is '������������';
comment on column CLOUD_FAQ.ma001
  is '����id';
comment on column CLOUD_FAQ.ma002
  is '����id';
comment on column CLOUD_FAQ.ma003
  is '����';
comment on column CLOUD_FAQ.ma004
  is '����';
comment on column CLOUD_FAQ.ma005
  is '����ʱ��';
comment on column CLOUD_FAQ.ma006
  is '�����ֶ�1';
comment on column CLOUD_FAQ.ma007
  is '�����ֶ�2';
comment on column CLOUD_FAQ.ma008
  is '�����ֶ�3';
alter table CLOUD_FAQ
  add constraint PK_CLOUD_FAQ primary key (MA001);

prompt
prompt Creating table CLOUD_MENU
prompt =========================
prompt
create table CLOUD_MENU
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(100),
  ma004 VARCHAR2(300),
  ma005 VARCHAR2(50),
  ma006 INTEGER,
  ma007 DATE,
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100)
)
;
comment on table CLOUD_MENU
  is '��ҳ�˵���';
comment on column CLOUD_MENU.ma001
  is '����id';
comment on column CLOUD_MENU.ma002
  is '�˵�����';
comment on column CLOUD_MENU.ma003
  is '�˵�����';
comment on column CLOUD_MENU.ma004
  is '�˵����ӵ�ַ';
comment on column CLOUD_MENU.ma005
  is '�˵�����';
comment on column CLOUD_MENU.ma006
  is '����';
comment on column CLOUD_MENU.ma007
  is '����ʱ��';
comment on column CLOUD_MENU.ma008
  is '�����ֶ�1';
comment on column CLOUD_MENU.ma009
  is '�����ֶ�2';
comment on column CLOUD_MENU.ma010
  is '�����ֶ�3';
alter table CLOUD_MENU
  add constraint PK_CLOUD_MENU primary key (MA001);

prompt
prompt Creating table CLOUD_MOBILEAPP
prompt ==============================
prompt
create table CLOUD_MOBILEAPP
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(200),
  ma004 CLOB,
  ma005 VARCHAR2(36),
  ma006 VARCHAR2(36),
  ma007 VARCHAR2(36),
  ma008 DATE,
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100),
  ma011 VARCHAR2(100)
)
;
comment on table CLOUD_MOBILEAPP
  is '�ƶ�Ӧ������';
comment on column CLOUD_MOBILEAPP.ma001
  is '����id';
comment on column CLOUD_MOBILEAPP.ma002
  is '����id';
comment on column CLOUD_MOBILEAPP.ma003
  is '����';
comment on column CLOUD_MOBILEAPP.ma004
  is '����';
comment on column CLOUD_MOBILEAPP.ma005
  is 'logoͼ��,����ڸ�����';
comment on column CLOUD_MOBILEAPP.ma006
  is '�õ�Ƭid,����ڻõ�Ƭ����';
comment on column CLOUD_MOBILEAPP.ma007
  is '����id,����,�ֻ�app�ļ�';
comment on column CLOUD_MOBILEAPP.ma008
  is '����ʱ��';
comment on column CLOUD_MOBILEAPP.ma009
  is '�����ֶ�1';
comment on column CLOUD_MOBILEAPP.ma010
  is '�����ֶ�2';
comment on column CLOUD_MOBILEAPP.ma011
  is '�����ֶ�3';
alter table CLOUD_MOBILEAPP
  add constraint PK_CLOUD_MOBILEAPP primary key (MA001);

prompt
prompt Creating table CLOUD_ONLINEAPP
prompt ==============================
prompt
create table CLOUD_ONLINEAPP
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(200),
  ma004 VARCHAR2(200),
  ma005 VARCHAR2(500),
  ma006 DATE,
  ma007 INTEGER,
  ma008 VARCHAR2(20),
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100),
  ma011 VARCHAR2(100)
)
;
comment on table CLOUD_ONLINEAPP
  is '��Ӧ������';
comment on column CLOUD_ONLINEAPP.ma001
  is '����';
comment on column CLOUD_ONLINEAPP.ma002
  is '����id';
comment on column CLOUD_ONLINEAPP.ma003
  is '���/��ɫ';
comment on column CLOUD_ONLINEAPP.ma004
  is '����';
comment on column CLOUD_ONLINEAPP.ma005
  is '����';
comment on column CLOUD_ONLINEAPP.ma006
  is 'ʱ��';
comment on column CLOUD_ONLINEAPP.ma007
  is '����';
comment on column CLOUD_ONLINEAPP.ma008
  is '����';
comment on column CLOUD_ONLINEAPP.ma009
  is '�����ֶ�1';
comment on column CLOUD_ONLINEAPP.ma010
  is '�����ֶ�2';
comment on column CLOUD_ONLINEAPP.ma011
  is '�����ֶ�3';
alter table CLOUD_ONLINEAPP
  add constraint PK_CLOUD_ONLINEAPP primary key (MA001);

prompt
prompt Creating table CLOUD_PERARTINGGUIDE
prompt ===================================
prompt
create table CLOUD_PERARTINGGUIDE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(200),
  ma004 CLOB,
  ma005 DATE,
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100)
)
;
comment on table CLOUD_PERARTINGGUIDE
  is 'ʹ��ָ������';
comment on column CLOUD_PERARTINGGUIDE.ma001
  is '����id';
comment on column CLOUD_PERARTINGGUIDE.ma002
  is '����id';
comment on column CLOUD_PERARTINGGUIDE.ma003
  is '����';
comment on column CLOUD_PERARTINGGUIDE.ma004
  is '����';
comment on column CLOUD_PERARTINGGUIDE.ma005
  is '����ʱ��';
comment on column CLOUD_PERARTINGGUIDE.ma006
  is '�����ֶ�1';
comment on column CLOUD_PERARTINGGUIDE.ma007
  is '�����ֶ�2';
comment on column CLOUD_PERARTINGGUIDE.ma008
  is '�����ֶ�3';
alter table CLOUD_PERARTINGGUIDE
  add constraint PK_CLOUD_PERARTINGGUIDE primary key (MA001);

prompt
prompt Creating table CLOUD_PRODUCTADVANTAGE
prompt =====================================
prompt
create table CLOUD_PRODUCTADVANTAGE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 CLOB,
  ma004 DATE,
  ma005 VARCHAR2(100),
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100)
)
;
comment on table CLOUD_PRODUCTADVANTAGE
  is '��Ʒ����,��Ʒ�ص�,��ϸ����';
comment on column CLOUD_PRODUCTADVANTAGE.ma001
  is '����id';
comment on column CLOUD_PRODUCTADVANTAGE.ma002
  is '����id';
comment on column CLOUD_PRODUCTADVANTAGE.ma003
  is '����';
comment on column CLOUD_PRODUCTADVANTAGE.ma004
  is '����ʱ��';
comment on column CLOUD_PRODUCTADVANTAGE.ma005
  is '�����ֶ�1';
comment on column CLOUD_PRODUCTADVANTAGE.ma006
  is '�����ֶ�2';
comment on column CLOUD_PRODUCTADVANTAGE.ma007
  is '�����ֶ�3';
alter table CLOUD_PRODUCTADVANTAGE
  add constraint PK_CLOUD_PRODUCTADVANTAGE primary key (MA001);

prompt
prompt Creating table CLOUD_PRODUCTFEATURE
prompt ===================================
prompt
create table CLOUD_PRODUCTFEATURE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(40),
  ma003 CLOB,
  ma004 DATE,
  ma005 VARCHAR2(100),
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100)
)
;
comment on table CLOUD_PRODUCTFEATURE
  is '��Ʒ��ɫ����';
comment on column CLOUD_PRODUCTFEATURE.ma001
  is '����';
comment on column CLOUD_PRODUCTFEATURE.ma002
  is '����id';
comment on column CLOUD_PRODUCTFEATURE.ma003
  is '����';
comment on column CLOUD_PRODUCTFEATURE.ma004
  is '����ʱ��';
comment on column CLOUD_PRODUCTFEATURE.ma005
  is '�����ֶ�1';
comment on column CLOUD_PRODUCTFEATURE.ma006
  is '�����ֶ�2';
comment on column CLOUD_PRODUCTFEATURE.ma007
  is '�����ֶ�3';
alter table CLOUD_PRODUCTFEATURE
  add constraint PK_CLOUD_PRODUCTFEATURE primary key (MA001);

prompt
prompt Creating table CLOUD_PRODUCTSERVICE
prompt ===================================
prompt
create table CLOUD_PRODUCTSERVICE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(1000),
  ma004 FLOAT,
  ma005 INTEGER,
  ma006 DATE,
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100)
)
;
comment on table CLOUD_PRODUCTSERVICE
  is '��Ʒ�����';
comment on column CLOUD_PRODUCTSERVICE.ma001
  is '����';
comment on column CLOUD_PRODUCTSERVICE.ma002
  is '����';
comment on column CLOUD_PRODUCTSERVICE.ma003
  is '��������';
comment on column CLOUD_PRODUCTSERVICE.ma004
  is '�۸�';
comment on column CLOUD_PRODUCTSERVICE.ma005
  is '����';
comment on column CLOUD_PRODUCTSERVICE.ma006
  is '����ʱ��';
comment on column CLOUD_PRODUCTSERVICE.ma007
  is '�����ֶ�1';
comment on column CLOUD_PRODUCTSERVICE.ma008
  is '�����ֶ�2';
comment on column CLOUD_PRODUCTSERVICE.ma009
  is '�����ֶ�3';
comment on column CLOUD_PRODUCTSERVICE.ma010
  is '�����ֶ�4';
alter table CLOUD_PRODUCTSERVICE
  add constraint PK_CLOUD_PRODUCTSERVICE primary key (MA001);

prompt
prompt Creating table CLOUD_SLIDE
prompt ==========================
prompt
create table CLOUD_SLIDE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(36),
  ma003 VARCHAR2(200),
  ma004 VARCHAR2(500),
  ma005 VARCHAR2(200),
  ma006 VARCHAR2(200),
  ma007 VARCHAR2(200),
  ma008 DATE
)
;
comment on table CLOUD_SLIDE
  is '��Żõ�Ƭ��';
comment on column CLOUD_SLIDE.ma001
  is '����id';
comment on column CLOUD_SLIDE.ma002
  is 'Ӧ��id';
comment on column CLOUD_SLIDE.ma003
  is '�õ�Ƭ����';
comment on column CLOUD_SLIDE.ma004
  is '����';
comment on column CLOUD_SLIDE.ma005
  is '����·��';
comment on column CLOUD_SLIDE.ma006
  is 'ͼƬ·��';
comment on column CLOUD_SLIDE.ma007
  is '�����ַ';
comment on column CLOUD_SLIDE.ma008
  is '����ʱ��';
alter table CLOUD_SLIDE
  add constraint PK_CLOUD_SLIDE primary key (MA001);
alter table CLOUD_SLIDE
  add constraint FK_MOBILE_SLIDE foreign key (MA002)
  references CLOUD_MOBILEAPP (MA001);

prompt
prompt Creating table CLOUD_SOLUTION
prompt =============================
prompt
create table CLOUD_SOLUTION
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(200),
  ma003 VARCHAR2(1000),
  ma004 INTEGER,
  ma005 DATE,
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100)
)
;
comment on table CLOUD_SOLUTION
  is '���������';
comment on column CLOUD_SOLUTION.ma001
  is '����';
comment on column CLOUD_SOLUTION.ma002
  is '����';
comment on column CLOUD_SOLUTION.ma003
  is '����';
comment on column CLOUD_SOLUTION.ma004
  is '����';
comment on column CLOUD_SOLUTION.ma005
  is '����ʱ��';
comment on column CLOUD_SOLUTION.ma006
  is '�����ֶ�1';
comment on column CLOUD_SOLUTION.ma007
  is '�����ֶ�2';
comment on column CLOUD_SOLUTION.ma008
  is '�����ֶ�3';
comment on column CLOUD_SOLUTION.ma009
  is '�����ֶ�4';
alter table CLOUD_SOLUTION
  add constraint PK_CLOUD_SOLUTION primary key (MA001);

prompt
prompt Creating table CLOUD_SUCCESSFULCASE
prompt ===================================
prompt
create table CLOUD_SUCCESSFULCASE
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(1000),
  ma004 INTEGER,
  ma005 DATE,
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100)
)
;
comment on table CLOUD_SUCCESSFULCASE
  is '�ɹ�������';
comment on column CLOUD_SUCCESSFULCASE.ma001
  is '����';
comment on column CLOUD_SUCCESSFULCASE.ma002
  is '����';
comment on column CLOUD_SUCCESSFULCASE.ma003
  is '��������';
comment on column CLOUD_SUCCESSFULCASE.ma004
  is '����';
comment on column CLOUD_SUCCESSFULCASE.ma005
  is '����ʱ��';
comment on column CLOUD_SUCCESSFULCASE.ma006
  is '�����ֶ�1';
comment on column CLOUD_SUCCESSFULCASE.ma007
  is '�����ֶ�2';
comment on column CLOUD_SUCCESSFULCASE.ma008
  is '�����ֶ�3';
comment on column CLOUD_SUCCESSFULCASE.ma009
  is '�����ֶ�4';
alter table CLOUD_SUCCESSFULCASE
  add constraint PK_CLOUD_SUCCESSFULCASE primary key (MA001);

prompt
prompt Creating table COLLECTCONFIG
prompt ============================
prompt
create table COLLECTCONFIG
(
  collect_id   VARCHAR2(40) not null,
  apply_id     VARCHAR2(40),
  collect_name VARCHAR2(20),
  config_host  VARCHAR2(40),
  config_port  VARCHAR2(20),
  protocol_id  VARCHAR2(40),
  state        NUMBER,
  create_time  DATE default sysdate,
  create_user  VARCHAR2(40),
  del_flag     NUMBER(1) default 0
)
;
comment on table COLLECTCONFIG
  is '�ɼ�����';
comment on column COLLECTCONFIG.collect_id
  is '�ɼ�����ID';
comment on column COLLECTCONFIG.apply_id
  is '����ID';
comment on column COLLECTCONFIG.collect_name
  is '�ɼ�����';
comment on column COLLECTCONFIG.config_host
  is '���÷�����';
comment on column COLLECTCONFIG.config_port
  is '���ö˿�';
comment on column COLLECTCONFIG.protocol_id
  is 'Э��ID';
comment on column COLLECTCONFIG.state
  is '����״̬';
comment on column COLLECTCONFIG.create_time
  is '����ʱ��';
comment on column COLLECTCONFIG.create_user
  is '������';
comment on column COLLECTCONFIG.del_flag
  is 'ɾ�����';
alter table COLLECTCONFIG
  add constraint PK_COLLECTCONFIG primary key (COLLECT_ID);

prompt
prompt Creating table COLLECTSTORAGEMAP
prompt ================================
prompt
create table COLLECTSTORAGEMAP
(
  cs_map_id   VARCHAR2(40) not null,
  apply_id    VARCHAR2(40),
  collect_id  VARCHAR2(40),
  protocol_id VARCHAR2(40),
  storage_id  VARCHAR2(40),
  del_flag    NUMBER(1) default 0
)
;
comment on table COLLECTSTORAGEMAP
  is '�ɼ��洢��ϵ��';
comment on column COLLECTSTORAGEMAP.cs_map_id
  is '�ɼ��洢��ϵID';
comment on column COLLECTSTORAGEMAP.apply_id
  is '����ID';
comment on column COLLECTSTORAGEMAP.collect_id
  is '�ɼ�����ID';
comment on column COLLECTSTORAGEMAP.protocol_id
  is 'Э��ID';
comment on column COLLECTSTORAGEMAP.storage_id
  is '�洢ID';
comment on column COLLECTSTORAGEMAP.del_flag
  is 'ɾ�����';
alter table COLLECTSTORAGEMAP
  add constraint PK_COLLECTSTORAGEMAP primary key (CS_MAP_ID);

prompt
prompt Creating table COMPORT
prompt ======================
prompt
create table COMPORT
(
  com_id    VARCHAR2(40) not null,
  link_id   VARCHAR2(40),
  com_name  VARCHAR2(100),
  baud_rage INTEGER,
  parity    VARCHAR2(50),
  data_bits INTEGER,
  stop_bits INTEGER,
  del_flag  NUMBER(1) default 0
)
;
comment on table COMPORT
  is '����';
comment on column COMPORT.com_id
  is '����ID';
comment on column COMPORT.link_id
  is '��·ID';
comment on column COMPORT.com_name
  is '��������';
comment on column COMPORT.baud_rage
  is '������';
comment on column COMPORT.parity
  is '��żУ��';
comment on column COMPORT.data_bits
  is '����λ';
comment on column COMPORT.stop_bits
  is 'ֹͣλ';
comment on column COMPORT.del_flag
  is 'ɾ�����';
alter table COMPORT
  add constraint PK_COMPORT primary key (COM_ID);

prompt
prompt Creating table DEVICE
prompt =====================
prompt
create table DEVICE
(
  device_id   VARCHAR2(40) not null,
  gprs_id     VARCHAR2(40),
  address     VARCHAR2(40),
  type        VARCHAR2(40),
  media       VARCHAR2(100),
  unit        VARCHAR2(100),
  create_time DATE default sysdate,
  update_time DATE,
  del_flag    NUMBER(1) default 0
)
;
comment on table DEVICE
  is '�豸';
comment on column DEVICE.device_id
  is '�豸ID';
comment on column DEVICE.gprs_id
  is 'GPRS ID';
comment on column DEVICE.address
  is '�豸��ַ';
comment on column DEVICE.type
  is '�豸����';
comment on column DEVICE.media
  is '̽�����';
comment on column DEVICE.unit
  is '̽����ʵ�λ';
comment on column DEVICE.create_time
  is '����ʱ��';
comment on column DEVICE.update_time
  is '����ʱ��';
comment on column DEVICE.del_flag
  is 'ɾ�����';
create index DSGFSDAFSDAFSDAF on DEVICE (GPRS_ID);
alter table DEVICE
  add constraint PK_DEVICE primary key (DEVICE_ID);

prompt
prompt Creating table DEVICE_SYS
prompt =========================
prompt
create table DEVICE_SYS
(
  id        VARCHAR2(40) not null,
  device_id VARCHAR2(40),
  name      VARCHAR2(40),
  jc        VARCHAR2(40),
  type      VARCHAR2(40),
  url       VARCHAR2(400),
  rev1      VARCHAR2(40),
  rev2      VARCHAR2(40)
)
;
comment on table DEVICE_SYS
  is '�豸��ҵ��ϵͳ��ϵ��';
comment on column DEVICE_SYS.id
  is '����';
comment on column DEVICE_SYS.device_id
  is '�豸id';
comment on column DEVICE_SYS.name
  is 'ҵ��ϵͳ����';
comment on column DEVICE_SYS.jc
  is 'ҵ��ϵͳ���';
comment on column DEVICE_SYS.type
  is 'webservice����';
comment on column DEVICE_SYS.url
  is 'webservice��ַ';
comment on column DEVICE_SYS.rev1
  is '����1';
comment on column DEVICE_SYS.rev2
  is '����2';
create index DEVICE_SYS_DEVICEID_INDEX on DEVICE_SYS (DEVICE_ID);
alter table DEVICE_SYS
  add constraint DEVICE_SYS_PK primary key (ID);

prompt
prompt Creating table GPRSMODULE
prompt =========================
prompt
create table GPRSMODULE
(
  gprs_id      VARCHAR2(40) not null,
  link_id      VARCHAR2(40),
  ccid         VARCHAR2(40),
  mobile       VARCHAR2(20),
  apn_name     VARCHAR2(100),
  apn_user     VARCHAR2(100),
  apn_password VARCHAR2(100),
  x            NUMBER,
  y            NUMBER,
  ip           VARCHAR2(50),
  port         VARCHAR2(50),
  create_user  VARCHAR2(40),
  create_time  DATE default sysdate,
  update_time  DATE,
  del_flag     NUMBER(1) default 0
)
;
comment on table GPRSMODULE
  is 'GPRS';
comment on column GPRSMODULE.gprs_id
  is 'GPRS ID';
comment on column GPRSMODULE.link_id
  is '��·ID';
comment on column GPRSMODULE.ccid
  is 'CCID';
comment on column GPRSMODULE.mobile
  is '�ֻ���';
comment on column GPRSMODULE.apn_name
  is 'APN����';
comment on column GPRSMODULE.apn_user
  is 'APN�û�';
comment on column GPRSMODULE.apn_password
  is 'APN����';
comment on column GPRSMODULE.x
  is 'X����';
comment on column GPRSMODULE.y
  is 'Y����';
comment on column GPRSMODULE.ip
  is 'IP��ַ';
comment on column GPRSMODULE.port
  is '�˿�';
comment on column GPRSMODULE.create_user
  is '������';
comment on column GPRSMODULE.create_time
  is '����ʱ��';
comment on column GPRSMODULE.update_time
  is '����ʱ��';
comment on column GPRSMODULE.del_flag
  is 'ɾ�����';
alter table GPRSMODULE
  add constraint PK_GPRSMODULE primary key (GPRS_ID);

prompt
prompt Creating table LINKMODULE
prompt =========================
prompt
create table LINKMODULE
(
  link_id       VARCHAR2(40) not null,
  collect_id    VARCHAR2(40),
  link_name     VARCHAR2(100),
  link_type     VARCHAR2(30),
  link_ip       VARCHAR2(50),
  link_port     VARCHAR2(50),
  redirect_ip   VARCHAR2(50),
  redirect_port VARCHAR2(50),
  time_gap      INTEGER,
  idle_gap      INTEGER,
  create_user   VARCHAR2(40),
  create_time   DATE default sysdate,
  del_flag      NUMBER(1) default 0
)
;
comment on table LINKMODULE
  is 'ͨѶ��·';
comment on column LINKMODULE.link_id
  is '��·ID';
comment on column LINKMODULE.collect_id
  is '�ɼ�����ID';
comment on column LINKMODULE.link_name
  is '��·����';
comment on column LINKMODULE.link_type
  is '��·����';
comment on column LINKMODULE.link_ip
  is '����IP';
comment on column LINKMODULE.link_port
  is '�����˿�';
comment on column LINKMODULE.redirect_ip
  is '�ض�������IP';
comment on column LINKMODULE.redirect_port
  is '�ض�������TCP�˿�';
comment on column LINKMODULE.time_gap
  is '�ϴ�ʱ����';
comment on column LINKMODULE.idle_gap
  is '���м��';
comment on column LINKMODULE.create_user
  is '������';
comment on column LINKMODULE.create_time
  is '����ʱ��';
comment on column LINKMODULE.del_flag
  is 'ɾ�����';
alter table LINKMODULE
  add constraint PK_LINKMODULE primary key (LINK_ID);

prompt
prompt Creating table M114
prompt ===================
prompt
create table M114
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 DATE,
  ma004 VARCHAR2(2000),
  ma005 VARCHAR2(40),
  ma006 VARCHAR2(1),
  ma007 VARCHAR2(40),
  ma008 VARCHAR2(40),
  ma009 VARCHAR2(40),
  ma010 VARCHAR2(40)
)
;
alter table M114
  add constraint PK_M114 primary key (MA001);

prompt
prompt Creating table M119
prompt ===================
prompt
create table M119
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(400),
  ma003 VARCHAR2(400),
  ma004 VARCHAR2(400),
  ma005 VARCHAR2(40),
  ma006 DATE,
  ma007 VARCHAR2(40),
  ma008 VARCHAR2(1),
  ma009 VARCHAR2(40),
  ma010 VARCHAR2(40)
)
;
comment on table M119
  is 'ҵ����Ϣ����';
comment on column M119.ma001
  is '����';
comment on column M119.ma002
  is '����';
comment on column M119.ma003
  is '����';
comment on column M119.ma004
  is '��������';
comment on column M119.ma005
  is '��ҵid';
comment on column M119.ma006
  is '����ʱ��';
comment on column M119.ma007
  is '������';
comment on column M119.ma008
  is '����  1�ʼ�  2����  3����';
comment on column M119.ma009
  is '������';
comment on column M119.ma010
  is '����1';
alter table M119
  add constraint PK_M119 primary key (MA001);

prompt
prompt Creating table PARAMETERLIST
prompt ============================
prompt
create table PARAMETERLIST
(
  para_id      VARCHAR2(40) not null,
  api_id       VARCHAR2(40),
  para_name    VARCHAR2(100),
  para_type    VARCHAR2(20),
  para_length  INTEGER,
  para_limit   VARCHAR2(100),
  para_example VARCHAR2(100),
  is_necessary NUMBER(1),
  default_val  VARCHAR2(50),
  para_memo    VARCHAR2(300),
  memo         VARCHAR2(300),
  del_flag     NUMBER(1) default 0
)
;
comment on table PARAMETERLIST
  is 'API����';
comment on column PARAMETERLIST.para_id
  is '����ID';
comment on column PARAMETERLIST.api_id
  is 'API ID';
comment on column PARAMETERLIST.para_name
  is '��������';
comment on column PARAMETERLIST.para_type
  is '��������';
comment on column PARAMETERLIST.para_length
  is '��������';
comment on column PARAMETERLIST.para_limit
  is '��������';
comment on column PARAMETERLIST.para_example
  is 'ʾ��';
comment on column PARAMETERLIST.is_necessary
  is '�Ƿ����';
comment on column PARAMETERLIST.default_val
  is 'Ĭ��ֵ';
comment on column PARAMETERLIST.para_memo
  is '����˵��';
comment on column PARAMETERLIST.memo
  is '��ע';
comment on column PARAMETERLIST.del_flag
  is 'ɾ�����';
alter table PARAMETERLIST
  add constraint PK_PARAMETERLIST primary key (PARA_ID);

prompt
prompt Creating table PROTOCOFORRMAT
prompt =============================
prompt
create table PROTOCOFORRMAT
(
  pformat_id    VARCHAR2(40) not null,
  protocol_id   VARCHAR2(40),
  serial_number VARCHAR2(20),
  define        VARCHAR2(100),
  length        INTEGER,
  format_memo   VARCHAR2(300),
  memo          VARCHAR2(300),
  create_user   VARCHAR2(40),
  create_time   DATE default sysdate,
  del_flag      NUMBER(1) default 0
)
;
comment on table PROTOCOFORRMAT
  is 'Э���ʽ';
comment on column PROTOCOFORRMAT.pformat_id
  is 'Э���ʽID';
comment on column PROTOCOFORRMAT.protocol_id
  is 'Э��ID';
comment on column PROTOCOFORRMAT.serial_number
  is '���';
comment on column PROTOCOFORRMAT.define
  is '����';
comment on column PROTOCOFORRMAT.length
  is '����';
comment on column PROTOCOFORRMAT.format_memo
  is '˵��';
comment on column PROTOCOFORRMAT.memo
  is '��ע';
comment on column PROTOCOFORRMAT.create_user
  is '������';
comment on column PROTOCOFORRMAT.create_time
  is '����ʱ��';
comment on column PROTOCOFORRMAT.del_flag
  is 'ɾ�����';
alter table PROTOCOFORRMAT
  add constraint PK_PROTOCOFORRMAT primary key (PFORMAT_ID);

prompt
prompt Creating table PROTOCOL
prompt =======================
prompt
create table PROTOCOL
(
  protocol_id   VARCHAR2(40) not null,
  protocol_name VARCHAR2(100),
  protocol_type VARCHAR2(20),
  create_user   VARCHAR2(40),
  create_time   DATE default sysdate,
  del_flag      NUMBER(1) default 0
)
;
comment on table PROTOCOL
  is 'Э��';
comment on column PROTOCOL.protocol_id
  is 'Э��ID';
comment on column PROTOCOL.protocol_name
  is 'Э������';
comment on column PROTOCOL.protocol_type
  is 'Э������';
comment on column PROTOCOL.create_user
  is '������';
comment on column PROTOCOL.create_time
  is '����ʱ��';
comment on column PROTOCOL.del_flag
  is 'ɾ�����';
alter table PROTOCOL
  add constraint PK_PROTOCOL primary key (PROTOCOL_ID);

prompt
prompt Creating table PROTOCOLSTORAGEMAP
prompt =================================
prompt
create table PROTOCOLSTORAGEMAP
(
  map_id      VARCHAR2(40) not null,
  protocol_id VARCHAR2(40),
  pformat_id  VARCHAR2(40),
  storage_id  VARCHAR2(40),
  sformat_id  VARCHAR2(40) not null,
  del_flag    NUMBER(1) default 0
)
;
comment on table PROTOCOLSTORAGEMAP
  is 'Э��洢��ʽ��ϵ��';
comment on column PROTOCOLSTORAGEMAP.map_id
  is '��ʽ��ϵID';
comment on column PROTOCOLSTORAGEMAP.protocol_id
  is 'Э��ID';
comment on column PROTOCOLSTORAGEMAP.pformat_id
  is 'Э���ʽID';
comment on column PROTOCOLSTORAGEMAP.storage_id
  is '�洢ID';
comment on column PROTOCOLSTORAGEMAP.sformat_id
  is '�洢��ʽID';
comment on column PROTOCOLSTORAGEMAP.del_flag
  is 'ɾ�����';
alter table PROTOCOLSTORAGEMAP
  add constraint PK_PROTOCOLSTORAGEMAP primary key (MAP_ID, SFORMAT_ID);

prompt
prompt Creating table PROTOCOL_FIELD
prompt =============================
prompt
create table PROTOCOL_FIELD
(
  id       VARCHAR2(40) not null,
  protocol VARCHAR2(40),
  name     VARCHAR2(40),
  name_zn  VARCHAR2(40),
  sort     NUMBER
)
;
comment on table PROTOCOL_FIELD
  is 'Э��������';

prompt
prompt Creating table PROTOCOL_SYS
prompt ===========================
prompt
create table PROTOCOL_SYS
(
  id        VARCHAR2(40) not null,
  device_id VARCHAR2(40),
  name      VARCHAR2(40),
  jc        VARCHAR2(40),
  type      VARCHAR2(40),
  url       VARCHAR2(400),
  rev1      VARCHAR2(40),
  rev2      VARCHAR2(40)
)
;
comment on table PROTOCOL_SYS
  is '�豸��ҵ��ϵͳ��ϵ��';
comment on column PROTOCOL_SYS.id
  is '����';
comment on column PROTOCOL_SYS.device_id
  is 'Э��id';
comment on column PROTOCOL_SYS.name
  is 'ҵ��ϵͳ����';
comment on column PROTOCOL_SYS.jc
  is 'ҵ��ϵͳ���';
comment on column PROTOCOL_SYS.type
  is 'webservice����';
comment on column PROTOCOL_SYS.url
  is 'webservice��ַ';
comment on column PROTOCOL_SYS.rev1
  is '����1';
comment on column PROTOCOL_SYS.rev2
  is '����2';

prompt
prompt Creating table PUSHLET_MSG
prompt ==========================
prompt
create table PUSHLET_MSG
(
  id      VARCHAR2(36) not null,
  user_id VARCHAR2(36) not null,
  msg     VARCHAR2(1500) not null,
  url     VARCHAR2(200)
)
;
comment on table PUSHLET_MSG
  is 'pushlet��Ϣ��';
comment on column PUSHLET_MSG.id
  is '����';
comment on column PUSHLET_MSG.user_id
  is '�û�ID';
comment on column PUSHLET_MSG.msg
  is '��Ϣ';
comment on column PUSHLET_MSG.url
  is '����url';
alter table PUSHLET_MSG
  add constraint PK_PUSHLET_MSG_ID primary key (ID);

prompt
prompt Creating table QRTZ_JOB_DETAILS
prompt ===============================
prompt
create table QRTZ_JOB_DETAILS
(
  job_name          VARCHAR2(200) not null,
  job_group         VARCHAR2(200) not null,
  description       VARCHAR2(250),
  job_class_name    VARCHAR2(250) not null,
  is_durable        VARCHAR2(1) not null,
  is_volatile       VARCHAR2(1) not null,
  is_stateful       VARCHAR2(1) not null,
  requests_recovery VARCHAR2(1) not null,
  job_data          BLOB
)
;
create index IDX_QRTZ_J_REQ_RECOVERY on QRTZ_JOB_DETAILS (REQUESTS_RECOVERY);
alter table QRTZ_JOB_DETAILS
  add primary key (JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_TRIGGERS
prompt ============================
prompt
create table QRTZ_TRIGGERS
(
  trigger_name   VARCHAR2(200) not null,
  trigger_group  VARCHAR2(200) not null,
  job_name       VARCHAR2(200) not null,
  job_group      VARCHAR2(200) not null,
  is_volatile    VARCHAR2(1) not null,
  description    VARCHAR2(250),
  next_fire_time NUMBER(13),
  prev_fire_time NUMBER(13),
  priority       NUMBER(13),
  trigger_state  VARCHAR2(16) not null,
  trigger_type   VARCHAR2(8) not null,
  start_time     NUMBER(13) not null,
  end_time       NUMBER(13),
  calendar_name  VARCHAR2(200),
  misfire_instr  NUMBER(2),
  job_data       BLOB
)
;
create index IDX_QRTZ_T_NEXT_FIRE_TIME on QRTZ_TRIGGERS (NEXT_FIRE_TIME);
create index IDX_QRTZ_T_NFT_ST on QRTZ_TRIGGERS (NEXT_FIRE_TIME, TRIGGER_STATE);
create index IDX_QRTZ_T_STATE on QRTZ_TRIGGERS (TRIGGER_STATE);
create index IDX_QRTZ_T_VOLATILE on QRTZ_TRIGGERS (IS_VOLATILE);
alter table QRTZ_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_TRIGGERS
  add foreign key (JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_BLOB_TRIGGERS
prompt =================================
prompt
create table QRTZ_BLOB_TRIGGERS
(
  trigger_name  VARCHAR2(200) not null,
  trigger_group VARCHAR2(200) not null,
  blob_data     BLOB
)
;
alter table QRTZ_BLOB_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_BLOB_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_CALENDARS
prompt =============================
prompt
create table QRTZ_CALENDARS
(
  calendar_name VARCHAR2(200) not null,
  calendar      BLOB not null
)
;
alter table QRTZ_CALENDARS
  add primary key (CALENDAR_NAME);

prompt
prompt Creating table QRTZ_CRON_TRIGGERS
prompt =================================
prompt
create table QRTZ_CRON_TRIGGERS
(
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  cron_expression VARCHAR2(120) not null,
  time_zone_id    VARCHAR2(80)
)
;
alter table QRTZ_CRON_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_CRON_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_FIRED_TRIGGERS
prompt ==================================
prompt
create table QRTZ_FIRED_TRIGGERS
(
  entry_id          VARCHAR2(95) not null,
  trigger_name      VARCHAR2(200) not null,
  trigger_group     VARCHAR2(200) not null,
  is_volatile       VARCHAR2(1) not null,
  instance_name     VARCHAR2(200) not null,
  fired_time        NUMBER(13) not null,
  priority          NUMBER(13) not null,
  state             VARCHAR2(16) not null,
  job_name          VARCHAR2(200),
  job_group         VARCHAR2(200),
  is_stateful       VARCHAR2(1),
  requests_recovery VARCHAR2(1)
)
;
create index IDX_QRTZ_FT_JOB_GROUP on QRTZ_FIRED_TRIGGERS (JOB_GROUP);
create index IDX_QRTZ_FT_JOB_NAME on QRTZ_FIRED_TRIGGERS (JOB_NAME);
create index IDX_QRTZ_FT_JOB_REQ_RECOVERY on QRTZ_FIRED_TRIGGERS (REQUESTS_RECOVERY);
create index IDX_QRTZ_FT_JOB_STATEFUL on QRTZ_FIRED_TRIGGERS (IS_STATEFUL);
create index IDX_QRTZ_FT_TRIG_GROUP on QRTZ_FIRED_TRIGGERS (TRIGGER_GROUP);
create index IDX_QRTZ_FT_TRIG_INST_NAME on QRTZ_FIRED_TRIGGERS (INSTANCE_NAME);
create index IDX_QRTZ_FT_TRIG_NAME on QRTZ_FIRED_TRIGGERS (TRIGGER_NAME);
create index IDX_QRTZ_FT_TRIG_NM_GP on QRTZ_FIRED_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);
create index IDX_QRTZ_FT_TRIG_VOLATILE on QRTZ_FIRED_TRIGGERS (IS_VOLATILE);
alter table QRTZ_FIRED_TRIGGERS
  add primary key (ENTRY_ID);

prompt
prompt Creating table QRTZ_JOB_LISTENERS
prompt =================================
prompt
create table QRTZ_JOB_LISTENERS
(
  job_name     VARCHAR2(200) not null,
  job_group    VARCHAR2(200) not null,
  job_listener VARCHAR2(200) not null
)
;
alter table QRTZ_JOB_LISTENERS
  add primary key (JOB_NAME, JOB_GROUP, JOB_LISTENER);
alter table QRTZ_JOB_LISTENERS
  add foreign key (JOB_NAME, JOB_GROUP)
  references QRTZ_JOB_DETAILS (JOB_NAME, JOB_GROUP);

prompt
prompt Creating table QRTZ_LOCKS
prompt =========================
prompt
create table QRTZ_LOCKS
(
  lock_name VARCHAR2(40) not null
)
;
alter table QRTZ_LOCKS
  add primary key (LOCK_NAME);

prompt
prompt Creating table QRTZ_PAUSED_TRIGGER_GRPS
prompt =======================================
prompt
create table QRTZ_PAUSED_TRIGGER_GRPS
(
  trigger_group VARCHAR2(200) not null
)
;
alter table QRTZ_PAUSED_TRIGGER_GRPS
  add primary key (TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_SCHEDULER_STATE
prompt ===================================
prompt
create table QRTZ_SCHEDULER_STATE
(
  instance_name     VARCHAR2(200) not null,
  last_checkin_time NUMBER(13) not null,
  checkin_interval  NUMBER(13) not null
)
;
alter table QRTZ_SCHEDULER_STATE
  add primary key (INSTANCE_NAME);

prompt
prompt Creating table QRTZ_SIMPLE_TRIGGERS
prompt ===================================
prompt
create table QRTZ_SIMPLE_TRIGGERS
(
  trigger_name    VARCHAR2(200) not null,
  trigger_group   VARCHAR2(200) not null,
  repeat_count    NUMBER(7) not null,
  repeat_interval NUMBER(12) not null,
  times_triggered NUMBER(10) not null
)
;
alter table QRTZ_SIMPLE_TRIGGERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP);
alter table QRTZ_SIMPLE_TRIGGERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table QRTZ_TRIGGER_LISTENERS
prompt =====================================
prompt
create table QRTZ_TRIGGER_LISTENERS
(
  trigger_name     VARCHAR2(200) not null,
  trigger_group    VARCHAR2(200) not null,
  trigger_listener VARCHAR2(200) not null
)
;
alter table QRTZ_TRIGGER_LISTENERS
  add primary key (TRIGGER_NAME, TRIGGER_GROUP, TRIGGER_LISTENER);
alter table QRTZ_TRIGGER_LISTENERS
  add foreign key (TRIGGER_NAME, TRIGGER_GROUP)
  references QRTZ_TRIGGERS (TRIGGER_NAME, TRIGGER_GROUP);

prompt
prompt Creating table SERVICE
prompt ======================
prompt
create table SERVICE
(
  service_id           VARCHAR2(40) not null,
  service_name         VARCHAR2(100),
  service_type         VARCHAR2(40),
  web_url              VARCHAR2(100),
  admin_url            VARCHAR2(100),
  service_memo         VARCHAR2(300),
  service_provider     VARCHAR2(40),
  version              VARCHAR2(10),
  regist_time          DATE,
  realization_way      VARCHAR2(20),
  call_way             VARCHAR2(20),
  start_time           DATE,
  stop_time            DATE,
  safety_require       VARCHAR2(40),
  safety_certification VARCHAR2(40),
  is_audit             NUMBER(1),
  is_publish           NUMBER(1),
  update_time          DATE,
  create_user          VARCHAR2(40),
  create_time          DATE default sysdate,
  del_flag             NUMBER(1) default 0
)
;
comment on table SERVICE
  is '�����';
comment on column SERVICE.service_id
  is '����ID';
comment on column SERVICE.service_name
  is '��������';
comment on column SERVICE.service_type
  is '��������';
comment on column SERVICE.web_url
  is 'web��ַ';
comment on column SERVICE.admin_url
  is '�����ַ';
comment on column SERVICE.service_memo
  is '��������';
comment on column SERVICE.service_provider
  is '�����ṩ��';
comment on column SERVICE.version
  is '�汾��';
comment on column SERVICE.regist_time
  is 'ע��ʱ��';
comment on column SERVICE.realization_way
  is 'ʵ�ַ�ʽ';
comment on column SERVICE.call_way
  is '���÷�ʽ';
comment on column SERVICE.start_time
  is '��������ʱ��';
comment on column SERVICE.stop_time
  is '����ͣ��ʱ��';
comment on column SERVICE.safety_require
  is '��ȫҪ��';
comment on column SERVICE.safety_certification
  is '��ȫ��֤';
comment on column SERVICE.is_audit
  is '�Ƿ����';
comment on column SERVICE.is_publish
  is '�Ƿ񷢲�';
comment on column SERVICE.update_time
  is '����ʱ��';
comment on column SERVICE.create_user
  is '������';
comment on column SERVICE.create_time
  is '����ʱ��';
comment on column SERVICE.del_flag
  is 'ɾ�����';
alter table SERVICE
  add constraint PK_SERVICE primary key (SERVICE_ID);

prompt
prompt Creating table SERVICEAPPLY
prompt ===========================
prompt
create table SERVICEAPPLY
(
  apply_id         VARCHAR2(40) not null,
  apply_name       VARCHAR2(100),
  apply_user       VARCHAR2(40),
  service_id       VARCHAR2(40),
  service_name     VARCHAR2(100),
  apply_service_id VARCHAR2(40),
  is_audit         NUMBER(1),
  del_flag         NUMBER(1) default 0
)
;
comment on table SERVICEAPPLY
  is '���������';
comment on column SERVICEAPPLY.apply_id
  is '����ID';
comment on column SERVICEAPPLY.apply_name
  is '��������';
comment on column SERVICEAPPLY.apply_user
  is '�����û�';
comment on column SERVICEAPPLY.service_id
  is '����ID';
comment on column SERVICEAPPLY.service_name
  is '��������';
comment on column SERVICEAPPLY.apply_service_id
  is '����ҵ�����ID';
comment on column SERVICEAPPLY.is_audit
  is '�Ƿ����';
comment on column SERVICEAPPLY.del_flag
  is 'ɾ�����';
alter table SERVICEAPPLY
  add constraint PK_SERVICEAPPLY primary key (APPLY_ID);

prompt
prompt Creating table SERVICECONSUME
prompt =============================
prompt
create table SERVICECONSUME
(
  consume_id       VARCHAR2(40) not null,
  service_id       VARCHAR2(40),
  service_consumer VARCHAR2(40),
  del_flag         NUMBER(1) default 0
)
;
comment on table SERVICECONSUME
  is '�������ѹ�ϵ��';
comment on column SERVICECONSUME.consume_id
  is '��������ID';
comment on column SERVICECONSUME.service_id
  is '����ID';
comment on column SERVICECONSUME.service_consumer
  is '����������';
comment on column SERVICECONSUME.del_flag
  is 'ɾ�����';
alter table SERVICECONSUME
  add constraint PK_SERVICECONSUME primary key (CONSUME_ID);

prompt
prompt Creating table SERVICERELY
prompt ==========================
prompt
create table SERVICERELY
(
  rely_id         VARCHAR2(40) not null,
  service_id      VARCHAR2(40),
  rely_service_id VARCHAR2(40),
  del_flag        NUMBER(1) default 0
)
;
comment on table SERVICERELY
  is '����������ϵ';
comment on column SERVICERELY.rely_id
  is '����ID';
comment on column SERVICERELY.service_id
  is '����ID';
comment on column SERVICERELY.rely_service_id
  is '�����ķ���ID';
comment on column SERVICERELY.del_flag
  is 'ɾ�����';
alter table SERVICERELY
  add constraint PK_SERVICERELY primary key (RELY_ID);

prompt
prompt Creating table SOTRAGE
prompt ======================
prompt
create table SOTRAGE
(
  storage_id       VARCHAR2(40) not null,
  storage_name     VARCHAR2(100),
  storage_type     VARCHAR2(20),
  storage_host     VARCHAR2(30),
  storage_port     VARCHAR2(30),
  storage_user     VARCHAR2(40),
  storage_password VARCHAR2(40),
  storage_location VARCHAR2(100),
  storage_db       VARCHAR2(100),
  storage_table    VARCHAR2(100),
  create_user      VARCHAR2(40),
  create_time      DATE default sysdate,
  del_flag         NUMBER(1) default 0,
  protocol_id      VARCHAR2(40)
)
;
comment on table SOTRAGE
  is '�洢����';
comment on column SOTRAGE.storage_id
  is '�洢ID';
comment on column SOTRAGE.storage_name
  is '�洢����';
comment on column SOTRAGE.storage_type
  is '�洢����';
comment on column SOTRAGE.storage_host
  is '�洢������';
comment on column SOTRAGE.storage_port
  is '�洢�˿�';
comment on column SOTRAGE.storage_user
  is '�洢�û���';
comment on column SOTRAGE.storage_password
  is '�洢����';
comment on column SOTRAGE.storage_location
  is '�洢λ��';
comment on column SOTRAGE.storage_db
  is '�洢����';
comment on column SOTRAGE.storage_table
  is '�洢����';
comment on column SOTRAGE.create_user
  is '������';
comment on column SOTRAGE.create_time
  is '����ʱ��';
comment on column SOTRAGE.del_flag
  is 'ɾ�����';
alter table SOTRAGE
  add constraint PK_SOTRAGE primary key (STORAGE_ID);

prompt
prompt Creating table STORAGEFORMAT
prompt ============================
prompt
create table STORAGEFORMAT
(
  sformat_id   VARCHAR2(40) not null,
  storage_id   VARCHAR2(40),
  field_name   VARCHAR2(50),
  field_type   VARCHAR2(20),
  field_length VARCHAR2(20),
  field_memo   VARCHAR2(300),
  memo         VARCHAR2(300),
  create_user  VARCHAR2(40),
  create_time  DATE default sysdate,
  del_flag     NUMBER(1) default 0
)
;
comment on table STORAGEFORMAT
  is '�洢��ʽ';
comment on column STORAGEFORMAT.sformat_id
  is '�洢��ʽID';
comment on column STORAGEFORMAT.storage_id
  is '�洢ID';
comment on column STORAGEFORMAT.field_name
  is '�ֶ�����';
comment on column STORAGEFORMAT.field_type
  is '�ֶ�����';
comment on column STORAGEFORMAT.field_length
  is '�ֶγ���';
comment on column STORAGEFORMAT.field_memo
  is '�ֶ�˵��';
comment on column STORAGEFORMAT.memo
  is '��ע';
comment on column STORAGEFORMAT.create_user
  is '������';
comment on column STORAGEFORMAT.create_time
  is '����ʱ��';
comment on column STORAGEFORMAT.del_flag
  is 'ɾ�����';
alter table STORAGEFORMAT
  add constraint PK_STORAGEFORMAT primary key (SFORMAT_ID);

prompt
prompt Creating table SYS_CODE_SEQUENCE
prompt ================================
prompt
create table SYS_CODE_SEQUENCE
(
  code     VARCHAR2(40) not null,
  sequence NUMBER(10) not null
)
;
comment on table SYS_CODE_SEQUENCE
  is '�������б�';
comment on column SYS_CODE_SEQUENCE.code
  is '����';
comment on column SYS_CODE_SEQUENCE.sequence
  is '����';
alter table SYS_CODE_SEQUENCE
  add constraint PK_CODE_SEQUENCE_CODE primary key (CODE);

prompt
prompt Creating table SYS_DATE
prompt =======================
prompt
create table SYS_DATE
(
  data_id        VARCHAR2(20),
  data_desc      VARCHAR2(30),
  data_type      VARCHAR2(10),
  data_type_desc VARCHAR2(10)
)
;
comment on column SYS_DATE.data_id
  is 'ʱ��ID';
comment on column SYS_DATE.data_desc
  is 'ʱ������';
comment on column SYS_DATE.data_type
  is 'ʱ������';
comment on column SYS_DATE.data_type_desc
  is 'ʱ����������';

prompt
prompt Creating table SYS_DICTIONARY
prompt =============================
prompt
create table SYS_DICTIONARY
(
  id       VARCHAR2(36) not null,
  key      VARCHAR2(60) not null,
  value    VARCHAR2(20) not null,
  display  VARCHAR2(60) not null,
  describe VARCHAR2(300),
  ordernum NUMBER(4),
  parentid VARCHAR2(36) not null,
  levelnum NUMBER(2),
  del_flag VARCHAR2(1)
)
;
comment on table SYS_DICTIONARY
  is 'ϵͳ�ֵ�';
comment on column SYS_DICTIONARY.id
  is '����';
comment on column SYS_DICTIONARY.key
  is '����';
comment on column SYS_DICTIONARY.value
  is 'ֵ';
comment on column SYS_DICTIONARY.display
  is '��ʾ����';
comment on column SYS_DICTIONARY.describe
  is '����';
comment on column SYS_DICTIONARY.ordernum
  is '����';
comment on column SYS_DICTIONARY.parentid
  is '���ڵ�';
comment on column SYS_DICTIONARY.levelnum
  is '�㼶��';
comment on column SYS_DICTIONARY.del_flag
  is 'ɾ����� 1����Ч��0��ɾ��';
alter table SYS_DICTIONARY
  add constraint PK_DICTIONARY_ID primary key (ID);
alter table SYS_DICTIONARY
  add constraint UQ_DICTIONARY_KEY unique (KEY);

prompt
prompt Creating table SYS_EXCEPTION_LOG
prompt ================================
prompt
create table SYS_EXCEPTION_LOG
(
  id             NVARCHAR2(36) not null,
  code           NVARCHAR2(20),
  type           NVARCHAR2(20),
  operator_id    NVARCHAR2(36),
  operator_name  NVARCHAR2(50),
  recording_time DATE,
  msg            NVARCHAR2(1000),
  content        CLOB,
  ip             NVARCHAR2(15)
)
;
comment on column SYS_EXCEPTION_LOG.id
  is 'ID';
comment on column SYS_EXCEPTION_LOG.code
  is '���';
comment on column SYS_EXCEPTION_LOG.type
  is '����';
comment on column SYS_EXCEPTION_LOG.operator_id
  is '������ID';
comment on column SYS_EXCEPTION_LOG.operator_name
  is '����������';
comment on column SYS_EXCEPTION_LOG.recording_time
  is '��¼ʱ��';
comment on column SYS_EXCEPTION_LOG.msg
  is '�쳣��Ϣ';
comment on column SYS_EXCEPTION_LOG.content
  is '�쳣����';
comment on column SYS_EXCEPTION_LOG.ip
  is 'IP';
alter table SYS_EXCEPTION_LOG
  add constraint EXCEPTION_LOG_ID primary key (ID);

prompt
prompt Creating table SYS_FINDPWD
prompt ==========================
prompt
create table SYS_FINDPWD
(
  id           VARCHAR2(36) not null,
  login_name   VARCHAR2(20) not null,
  type         VARCHAR2(10) not null,
  dynamic_code VARCHAR2(6),
  create_date  TIMESTAMP(6) not null,
  valid_date   TIMESTAMP(6) not null,
  key          VARCHAR2(40) not null,
  col1         VARCHAR2(40),
  col2         VARCHAR2(40),
  col3         VARCHAR2(40)
)
;
comment on table SYS_FINDPWD
  is '�����һ���ʱ���ݱ�';
comment on column SYS_FINDPWD.id
  is '����';
comment on column SYS_FINDPWD.login_name
  is '�û��˺�';
comment on column SYS_FINDPWD.type
  is '�һ����뷽ʽ��SMS���ţ�EMAIL�ʼ�';
comment on column SYS_FINDPWD.dynamic_code
  is '���ɵĶ�̬������֤�룬��Чʱ��60s';
comment on column SYS_FINDPWD.key
  is '�����ַ�����LOGIN_NAME+TYPE+VALID_DATE��';
alter table SYS_FINDPWD
  add constraint PK_SYS_FINDPWD primary key (ID);

prompt
prompt Creating table SYS_LOGIN_LOG
prompt ============================
prompt
create table SYS_LOGIN_LOG
(
  id             NVARCHAR2(36) not null,
  type           NUMBER(2) not null,
  recording_time DATE not null,
  user_id        NVARCHAR2(36),
  user_name      NVARCHAR2(50),
  ip             NVARCHAR2(15),
  gov_id         NVARCHAR2(36),
  corp_id        NVARCHAR2(36),
  remarks        NVARCHAR2(200)
)
;
comment on table SYS_LOGIN_LOG
  is '��¼��־��';
comment on column SYS_LOGIN_LOG.id
  is '��־��ʶ';
comment on column SYS_LOGIN_LOG.type
  is '��־����(1:������¼; 2:�����ǳ�)';
comment on column SYS_LOGIN_LOG.recording_time
  is '��¼ʱ��';
comment on column SYS_LOGIN_LOG.user_id
  is '�û���ʶ';
comment on column SYS_LOGIN_LOG.user_name
  is '�û���';
comment on column SYS_LOGIN_LOG.ip
  is 'IP';
comment on column SYS_LOGIN_LOG.gov_id
  is '������ʶ';
comment on column SYS_LOGIN_LOG.corp_id
  is '��ҵ��ʶ';
comment on column SYS_LOGIN_LOG.remarks
  is '��ע';
create index DSAFADSF on SYS_LOGIN_LOG (RECORDING_TIME, TYPE, USER_ID, USER_NAME, IP, GOV_ID, CORP_ID, REMARKS, ID);
alter table SYS_LOGIN_LOG
  add constraint SYS_LOGIN_LOG_PK primary key (ID);

prompt
prompt Creating table SYS_OPER_LOG
prompt ===========================
prompt
create table SYS_OPER_LOG
(
  id             NVARCHAR2(36) not null,
  code           NVARCHAR2(20),
  type           NVARCHAR2(20),
  operator_id    NVARCHAR2(36),
  operator_name  NVARCHAR2(50),
  recording_time DATE,
  class_name     NVARCHAR2(50),
  method_name    NVARCHAR2(50),
  content        NVARCHAR2(500),
  ip             NVARCHAR2(15)
)
;
comment on column SYS_OPER_LOG.id
  is 'ID';
comment on column SYS_OPER_LOG.code
  is '���';
comment on column SYS_OPER_LOG.type
  is '����';
comment on column SYS_OPER_LOG.operator_id
  is '������ID';
comment on column SYS_OPER_LOG.operator_name
  is '����������';
comment on column SYS_OPER_LOG.recording_time
  is '��¼ʱ��';
comment on column SYS_OPER_LOG.class_name
  is '����';
comment on column SYS_OPER_LOG.method_name
  is '������';
comment on column SYS_OPER_LOG.content
  is '��־����';
comment on column SYS_OPER_LOG.ip
  is 'IP';
alter table SYS_OPER_LOG
  add constraint OPER_LOG_ID primary key (ID);

prompt
prompt Creating table SYS_PERMISSION_DEL
prompt =================================
prompt
create table SYS_PERMISSION_DEL
(
  id          VARCHAR2(36) not null,
  parentid    VARCHAR2(36) not null,
  name        VARCHAR2(64) not null,
  url         VARCHAR2(50),
  image       VARCHAR2(100),
  available   NUMBER(1) default 1 not null,
  admin       NUMBER(1) default 0 not null,
  ordernum    NUMBER(4) default 0 not null,
  description VARCHAR2(200),
  isshow      NUMBER(1) default 1 not null,
  module_code VARCHAR2(60)
)
;
comment on table SYS_PERMISSION_DEL
  is '����Ȩ�ޱ�';
comment on column SYS_PERMISSION_DEL.id
  is 'ID';
comment on column SYS_PERMISSION_DEL.parentid
  is '�ϼ�ID�����Ϊ�������ܣ��ϼ�ID��ID��ͬ';
comment on column SYS_PERMISSION_DEL.name
  is '����';
comment on column SYS_PERMISSION_DEL.url
  is 'URL';
comment on column SYS_PERMISSION_DEL.image
  is 'ͼƬ';
comment on column SYS_PERMISSION_DEL.available
  is '�Ƿ����,0_������,1_����.';
comment on column SYS_PERMISSION_DEL.admin
  is '�Ƿ����ԱȨ��:0_��1_��';
comment on column SYS_PERMISSION_DEL.ordernum
  is '˳���';
comment on column SYS_PERMISSION_DEL.description
  is '����';
comment on column SYS_PERMISSION_DEL.isshow
  is '�Ƿ����ʾ��Ĭ��Ϊ1������ʾ��0Ϊ������ʾ';
comment on column SYS_PERMISSION_DEL.module_code
  is 'ģ�����';
alter table SYS_PERMISSION_DEL
  add constraint UQ_SYS_PERMISSION_ID unique (ID);

prompt
prompt Creating table SYS_RECORD
prompt =========================
prompt
create table SYS_RECORD
(
  ma001 VARCHAR2(100) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(100),
  ma004 VARCHAR2(100),
  ma005 VARCHAR2(100),
  ma006 VARCHAR2(100),
  ma007 DATE,
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(100),
  ma010 VARCHAR2(100),
  ma011 VARCHAR2(100),
  ma012 VARCHAR2(100)
)
;
comment on table SYS_RECORD
  is '�޸ļ�¼��';
comment on column SYS_RECORD.ma001
  is '����';
comment on column SYS_RECORD.ma002
  is '����';
comment on column SYS_RECORD.ma003
  is '����������';
comment on column SYS_RECORD.ma004
  is '����';
comment on column SYS_RECORD.ma005
  is '�ɵ�ֵ';
comment on column SYS_RECORD.ma006
  is '�µ�ֵ';
comment on column SYS_RECORD.ma007
  is '�޸�����';
comment on column SYS_RECORD.ma008
  is '�޸���';
comment on column SYS_RECORD.ma009
  is 'ͬ�α�־ �������һ���ͺ�';
comment on column SYS_RECORD.ma010
  is '����1';
comment on column SYS_RECORD.ma011
  is '����2';
comment on column SYS_RECORD.ma012
  is '����3';
alter table SYS_RECORD
  add constraint SYS_RECORD_PK primary key (MA001);

prompt
prompt Creating table S_BUSI_MODULE
prompt ============================
prompt
create table S_BUSI_MODULE
(
  uuid          VARCHAR2(40) not null,
  menu_name     VARCHAR2(30),
  menu_code     VARCHAR2(60),
  parent_uuid   VARCHAR2(40),
  url           VARCHAR2(200),
  user_type     VARCHAR2(3),
  is_valid      VARCHAR2(2),
  menu_type     VARCHAR2(2),
  remark        VARCHAR2(200),
  ordernum      NUMBER(4),
  creator       VARCHAR2(40),
  create_date   DATE,
  editor        VARCHAR2(40),
  modified_date DATE,
  is_show       VARCHAR2(2)
)
;
comment on table S_BUSI_MODULE
  is 'ҵ��˵��� s_busi_module';
comment on column S_BUSI_MODULE.uuid
  is '����';
comment on column S_BUSI_MODULE.menu_name
  is '�˵�����';
comment on column S_BUSI_MODULE.menu_code
  is 'ģ�����';
comment on column S_BUSI_MODULE.parent_uuid
  is '�ϼ�ģ������';
comment on column S_BUSI_MODULE.url
  is 'URL����';
comment on column S_BUSI_MODULE.user_type
  is '�û����� ��������
�ݶ�����ҵ��ENT������:GOV��ϵͳ:SYS';
comment on column S_BUSI_MODULE.is_valid
  is '�Ƿ���Ч �ֵ䶨��
�ݶ�����Ч��00����Ч��10';
comment on column S_BUSI_MODULE.menu_type
  is 'Ȩ�����_�����ֵ�';
comment on column S_BUSI_MODULE.remark
  is '��ע';
comment on column S_BUSI_MODULE.ordernum
  is '˳���';
comment on column S_BUSI_MODULE.creator
  is '������';
comment on column S_BUSI_MODULE.create_date
  is '��������';
comment on column S_BUSI_MODULE.editor
  is '�޸���';
comment on column S_BUSI_MODULE.modified_date
  is '�޸�����';
comment on column S_BUSI_MODULE.is_show
  is '�Ƿ���ʾ��10��ʾ,00����ʾ';
alter table S_BUSI_MODULE
  add constraint PK_S_BUSI_MODULE primary key (UUID);

prompt
prompt Creating table S_BUSI_OPERATION
prompt ===============================
prompt
create table S_BUSI_OPERATION
(
  uuid        VARCHAR2(40) not null,
  action_name VARCHAR2(40),
  action_code VARCHAR2(10),
  style_name  VARCHAR2(30),
  url         VARCHAR2(200),
  is_valid    VARCHAR2(2),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE,
  menu_uuid   VARCHAR2(40),
  bind_func   VARCHAR2(200),
  ordernum    NUMBER(4)
)
;
comment on table S_BUSI_OPERATION
  is 'ҵ������� s_busi_operation';
comment on column S_BUSI_OPERATION.uuid
  is 'ҵ���������';
comment on column S_BUSI_OPERATION.action_name
  is 'ҵ���������';
comment on column S_BUSI_OPERATION.action_code
  is '��������';
comment on column S_BUSI_OPERATION.style_name
  is '��ʽ����';
comment on column S_BUSI_OPERATION.url
  is 'ҵ���������';
comment on column S_BUSI_OPERATION.is_valid
  is '�Ƿ���Ч �ֵ䶨��
�ݶ�����Ч��00����Ч��10';
comment on column S_BUSI_OPERATION.remark
  is '��ע';
comment on column S_BUSI_OPERATION.creator
  is '������';
comment on column S_BUSI_OPERATION.create_date
  is '��������';
comment on column S_BUSI_OPERATION.menu_uuid
  is 'ҵ��ģ������';
comment on column S_BUSI_OPERATION.bind_func
  is '�󶨺���';
comment on column S_BUSI_OPERATION.ordernum
  is '˳���';
alter table S_BUSI_OPERATION
  add constraint PK_S_BUSI_OPERATION primary key (UUID);

prompt
prompt Creating table S_BUSI_RESOURCE
prompt ==============================
prompt
create table S_BUSI_RESOURCE
(
  uuid              VARCHAR2(40) not null,
  resource_name     VARCHAR2(40),
  resource_type     VARCHAR2(2),
  module_opera_uuid VARCHAR2(40),
  url               VARCHAR2(200),
  is_default        VARCHAR2(1),
  creator           VARCHAR2(40),
  create_date       DATE,
  bind_func         VARCHAR2(200)
)
;
comment on table S_BUSI_RESOURCE
  is 'ҵ����Դ�� s_busi_resource';
comment on column S_BUSI_RESOURCE.uuid
  is 'ҵ����Դ����';
comment on column S_BUSI_RESOURCE.resource_name
  is 'ҵ����Դ����';
comment on column S_BUSI_RESOURCE.resource_type
  is 'ҵ����Դ����';
comment on column S_BUSI_RESOURCE.module_opera_uuid
  is 'ҵ��˵�/ҵ���������';
comment on column S_BUSI_RESOURCE.url
  is '��Դ';
comment on column S_BUSI_RESOURCE.is_default
  is '�Ƿ�Ĭ����Դ
0��Ĭ�ϣ�1���Զ���';
comment on column S_BUSI_RESOURCE.creator
  is '������';
comment on column S_BUSI_RESOURCE.create_date
  is '��������';
comment on column S_BUSI_RESOURCE.bind_func
  is '�󶨺���';
alter table S_BUSI_RESOURCE
  add constraint PK_S_BUSI_RESOURCE primary key (UUID);

prompt
prompt Creating table S_DEPARTMENT
prompt ===========================
prompt
create table S_DEPARTMENT
(
  uuid        VARCHAR2(40) not null,
  depart_name VARCHAR2(30),
  depart_code VARCHAR2(20),
  parent_uuid VARCHAR2(40),
  organ_uuid  VARCHAR2(40),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE
)
;
comment on table S_DEPARTMENT
  is '���ű� s_department';
comment on column S_DEPARTMENT.uuid
  is '��������';
comment on column S_DEPARTMENT.depart_name
  is '��������';
comment on column S_DEPARTMENT.depart_code
  is '���ű���';
comment on column S_DEPARTMENT.parent_uuid
  is '�ϼ���������(���ڵ�)';
comment on column S_DEPARTMENT.organ_uuid
  is '��ҵ/������������';
comment on column S_DEPARTMENT.remark
  is '��ע';
comment on column S_DEPARTMENT.creator
  is '������';
comment on column S_DEPARTMENT.create_date
  is '��������';
alter table S_DEPARTMENT
  add constraint PK_S_DEPARTMENT_UUID primary key (UUID);

prompt
prompt Creating table S_DEPT_ROLE
prompt ==========================
prompt
create table S_DEPT_ROLE
(
  uuid        VARCHAR2(40) not null,
  depart_uuid VARCHAR2(40),
  role_uuid   VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;
comment on table S_DEPT_ROLE
  is '���ų�ʼ����ɫ s_dept_role';
comment on column S_DEPT_ROLE.uuid
  is '����';
comment on column S_DEPT_ROLE.depart_uuid
  is '��������';
comment on column S_DEPT_ROLE.role_uuid
  is '��ɫ����';
comment on column S_DEPT_ROLE.creator
  is '������';
comment on column S_DEPT_ROLE.create_date
  is '��������';
alter table S_DEPT_ROLE
  add constraint PK_S_DEPT_ROLE_UUID primary key (UUID);

prompt
prompt Creating table S_ROLE
prompt =====================
prompt
create table S_ROLE
(
  uuid        VARCHAR2(40) not null,
  role_name   VARCHAR2(60),
  role_code   VARCHAR2(30),
  user_type   VARCHAR2(3),
  organ_uuid  VARCHAR2(40),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE
)
;
comment on table S_ROLE
  is '��ɫ�� s_role';
comment on column S_ROLE.uuid
  is '����';
comment on column S_ROLE.role_name
  is '��ɫ����';
comment on column S_ROLE.role_code
  is '��ɫ����';
comment on column S_ROLE.user_type
  is '�û����� ��������
�ݶ�����ҵ��ENT������:GOV��ϵͳ:SYS';
comment on column S_ROLE.organ_uuid
  is '��ҵ��������������';
comment on column S_ROLE.remark
  is '��ע';
comment on column S_ROLE.creator
  is '������';
comment on column S_ROLE.create_date
  is '��������';
alter table S_ROLE
  add constraint PK_S_ROLE_UUID primary key (UUID);

prompt
prompt Creating table S_ROLE_PERM
prompt ==========================
prompt
create table S_ROLE_PERM
(
  uuid        VARCHAR2(40) not null,
  role_uuid   VARCHAR2(40),
  module_uuid VARCHAR2(40),
  opera_uuid  VARCHAR2(40),
  organ_uuid  VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;
comment on table S_ROLE_PERM
  is 'ҵ��ģ����������� s_role_perm';
comment on column S_ROLE_PERM.uuid
  is '����';
comment on column S_ROLE_PERM.role_uuid
  is '��ɫID';
comment on column S_ROLE_PERM.module_uuid
  is 'ҵ��ģ��ID';
comment on column S_ROLE_PERM.opera_uuid
  is 'ҵ��ģ�����ID';
comment on column S_ROLE_PERM.organ_uuid
  is '��ҵ��������λ����';
comment on column S_ROLE_PERM.creator
  is '������';
comment on column S_ROLE_PERM.create_date
  is '��������';
alter table S_ROLE_PERM
  add constraint PK_S_ROLE_PERM_UUID primary key (UUID);

prompt
prompt Creating table S_USER
prompt =====================
prompt
create table S_USER
(
  uuid                 VARCHAR2(40) not null,
  login_name           VARCHAR2(20),
  password             VARCHAR2(64),
  real_name            VARCHAR2(20),
  age                  NUMBER(2),
  sex                  VARCHAR2(1),
  mobile_number        VARCHAR2(20),
  email                VARCHAR2(30),
  id_card              VARCHAR2(25),
  user_type            VARCHAR2(3),
  is_admin             VARCHAR2(1),
  depart_uuid          VARCHAR2(40),
  depart_code          VARCHAR2(40),
  gov_uuid             VARCHAR2(40),
  user_status          VARCHAR2(2),
  employee_num         VARCHAR2(40),
  last_access_ip       VARCHAR2(39),
  last_access_datetime DATE,
  creator              VARCHAR2(40),
  create_date          DATE,
  editor               VARCHAR2(40),
  modified_date        DATE,
  organ_uuid           VARCHAR2(40),
  mobile_device_access VARCHAR2(1),
  outer_net_access     VARCHAR2(1),
  ext1                 VARCHAR2(20),
  ext2                 VARCHAR2(20),
  ext3                 VARCHAR2(20)
)
;
comment on table S_USER
  is '�û��� s_user';
comment on column S_USER.uuid
  is '����';
comment on column S_USER.login_name
  is '�˻�����';
comment on column S_USER.password
  is '����';
comment on column S_USER.real_name
  is '��ʵ����';
comment on column S_USER.age
  is '����';
comment on column S_USER.sex
  is '�Ա� �ֵ����
�У�M��Ů��F';
comment on column S_USER.mobile_number
  is '�ֻ�';
comment on column S_USER.email
  is '����';
comment on column S_USER.id_card
  is '���֤����';
comment on column S_USER.user_type
  is '�û����� ��������
��ҵ��ENT������:GOV��ϵͳ:SYS';
comment on column S_USER.is_admin
  is '�Ƿ����Ա';
comment on column S_USER.depart_uuid
  is '���ű��';
comment on column S_USER.depart_code
  is '���Ŵ���';
comment on column S_USER.gov_uuid
  is '�����ֶ�';
comment on column S_USER.user_status
  is '״̬ �ֵ����
00:δ��ˣ�10�����ã�20����ͨ';
comment on column S_USER.employee_num
  is 'Ա�����';
comment on column S_USER.last_access_ip
  is '���һ�η���ϵͳIP';
comment on column S_USER.last_access_datetime
  is '���һ�η���ϵͳʱ��';
comment on column S_USER.creator
  is '������';
comment on column S_USER.create_date
  is '��������';
comment on column S_USER.editor
  is '�޸���';
comment on column S_USER.modified_date
  is '�޸�����';
comment on column S_USER.organ_uuid
  is '��ҵ/��������UUID';
comment on column S_USER.mobile_device_access
  is '�ƶ��豸���� 1������0����ֹ';
comment on column S_USER.outer_net_access
  is '�������� 1������0����ֹ';
comment on column S_USER.ext1
  is '��չ�ֶ�1';
comment on column S_USER.ext2
  is '��չ�ֶ�2';
comment on column S_USER.ext3
  is '��չ�ֶ�3';
alter table S_USER
  add constraint PK_S_USER_UUID primary key (UUID);

prompt
prompt Creating table S_USER_ROLE
prompt ==========================
prompt
create table S_USER_ROLE
(
  uuid        VARCHAR2(40) not null,
  user_uuid   VARCHAR2(40),
  role_uuid   VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;
comment on table S_USER_ROLE
  is '�û���ɫ������ s_user_role';
comment on column S_USER_ROLE.uuid
  is '����';
comment on column S_USER_ROLE.user_uuid
  is '�û�ID';
comment on column S_USER_ROLE.role_uuid
  is '��ɫID';
comment on column S_USER_ROLE.creator
  is '������';
comment on column S_USER_ROLE.create_date
  is '��������';
alter table S_USER_ROLE
  add constraint PK_S_USER_ROLE_UUID primary key (UUID);

prompt
prompt Creating table T001
prompt ===================
prompt
create table T001
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(20) not null,
  ma003 VARCHAR2(50) not null,
  ma004 VARCHAR2(36),
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(20),
  ma007 VARCHAR2(30),
  ma008 VARCHAR2(2) not null,
  ma009 VARCHAR2(1000),
  ma010 VARCHAR2(40) not null,
  ma011 DATE not null,
  ma012 VARCHAR2(40),
  ma013 DATE,
  ma014 VARCHAR2(1) not null,
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(20)
)
;
comment on table T001
  is 'test table by lixn  ����������Ϣ��';
comment on column T001.ma001
  is 'ID';
comment on column T001.ma002
  is '��������';
comment on column T001.ma003
  is '��������';
comment on column T001.ma004
  is '�ϼ�����ID';
comment on column T001.ma005
  is '�̶��绰';
comment on column T001.ma006
  is '����';
comment on column T001.ma007
  is '����';
comment on column T001.ma008
  is '��־λ,�Ƿ���Ч, 00 ��Ч, 10 ��Ч';
comment on column T001.ma009
  is '��ע��Ϣ';
comment on column T001.ma010
  is '������';
comment on column T001.ma011
  is '��������';
comment on column T001.ma012
  is '�޸���';
comment on column T001.ma013
  is '�޸�����';
comment on column T001.ma014
  is 'ɾ����� 1����Ч��0��ɾ��';
comment on column T001.ma015
  is '�����ֶ�1';
comment on column T001.ma016
  is '�����ֶ�2';

prompt
prompt Creating table TCODEVALUE
prompt =========================
prompt
create table TCODEVALUE
(
  type   VARCHAR2(50),
  name   VARCHAR2(100),
  value  VARCHAR2(100),
  parent VARCHAR2(50),
  findex VARCHAR2(50)
)
;
comment on column TCODEVALUE.type
  is '���';
comment on column TCODEVALUE.name
  is '����';
comment on column TCODEVALUE.value
  is 'ֵ';
comment on column TCODEVALUE.parent
  is '��';
comment on column TCODEVALUE.findex
  is '����';

prompt
prompt Creating table TOKEN_SYS
prompt ========================
prompt
create table TOKEN_SYS
(
  id       VARCHAR2(40) not null,
  protocol VARCHAR2(40),
  name     VARCHAR2(40),
  name_zn  VARCHAR2(40),
  sort     NUMBER
)
;
comment on table TOKEN_SYS
  is '�������Ա�';
create index TOKEN_SYS_INDEX_TOKEN on TOKEN_SYS (PROTOCOL);
alter table TOKEN_SYS
  add constraint TOKEN_SYS_PK primary key (ID);

prompt
prompt Creating table TZONE
prompt ====================
prompt
create table TZONE
(
  objid     VARCHAR2(40) not null,
  code      VARCHAR2(50),
  name      VARCHAR2(100),
  kind      NUMBER(2),
  status    NUMBER(2),
  statuslog VARCHAR2(2000),
  flevel    NUMBER(2),
  findex    NUMBER(2),
  parentid  VARCHAR2(40),
  areaid    VARCHAR2(40),
  provid    VARCHAR2(40),
  cityid    VARCHAR2(40),
  remark    VARCHAR2(200)
)
;
comment on table TZONE
  is '��ʡ�б�TZONE';
comment on column TZONE.objid
  is 'Ψһ��־  5 6 10 11 12�ݲ�ʹ��';
comment on column TZONE.code
  is '���';
comment on column TZONE.name
  is '����';
comment on column TZONE.kind
  is '����';
comment on column TZONE.status
  is '״̬';
comment on column TZONE.statuslog
  is '״̬�����¼';
comment on column TZONE.flevel
  is '���';
comment on column TZONE.findex
  is '���';
comment on column TZONE.parentid
  is '���ڵ�';
comment on column TZONE.areaid
  is '����';
comment on column TZONE.provid
  is 'ʡ��';
comment on column TZONE.cityid
  is '��';
comment on column TZONE.remark
  is '��ע';
create index IX_TZONE_STATUS on TZONE (STATUS);
alter table TZONE
  add constraint PK_TZONE_OBJID primary key (OBJID);


spool off
