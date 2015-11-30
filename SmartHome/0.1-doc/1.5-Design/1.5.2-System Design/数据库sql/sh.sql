-----------------------------------------------------
-- Export file for user SH                         --
-- Created by Administrator on 2014/11/20, 9:44:39 --
-----------------------------------------------------

spool sh.log

prompt
prompt Creating table A001
prompt ===================
prompt
create table A001
(
  ma001 VARCHAR2(60) not null,
  ma002 NUMBER(12,4),
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(60),
  ma005 DATE,
  ma006 VARCHAR2(60),
  ma007 VARCHAR2(60),
  ma008 VARCHAR2(60)
)
;
comment on table A001
  is '��ʷ������Ϣ';
comment on column A001.ma001
  is '��ʷ������ʶ';
comment on column A001.ma002
  is 'ֵ';
comment on column A001.ma003
  is '�豸״̬';
comment on column A001.ma004
  is '�豸����';
comment on column A001.ma005
  is '����ʱ��';
comment on column A001.ma006
  is '�豸ID(WIFIģ��ID)';
comment on column A001.ma007
  is '�Ƿ�������(10������00δ����)';
comment on column A001.ma008
  is '��ע';
alter table A001
  add constraint PK_A001 primary key (MA001);

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
prompt Creating table APN_USER
prompt =======================
prompt
create table APN_USER
(
  id           NUMBER(19) not null,
  created_date DATE,
  email        VARCHAR2(64),
  name         VARCHAR2(64),
  password     VARCHAR2(64),
  test         DATE,
  updated_date DATE,
  username     VARCHAR2(64) not null
)
;

prompt
prompt Creating table B001
prompt ===================
prompt
create table B001
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(50) not null,
  ma004 VARCHAR2(50) not null,
  ma005 VARCHAR2(2000),
  ma006 VARCHAR2(40),
  ma007 VARCHAR2(40) not null,
  ma008 VARCHAR2(40) not null,
  ma009 VARCHAR2(40) not null,
  ma010 VARCHAR2(30),
  ma011 VARCHAR2(100),
  ma012 DATE,
  ma013 VARCHAR2(100),
  ma014 NUMBER(19,2),
  ma015 NUMBER(19,2),
  ma016 NUMBER(19,2),
  ma017 NUMBER(19,2),
  ma018 VARCHAR2(200),
  ma019 NUMBER(2),
  ma020 VARCHAR2(20),
  ma021 VARCHAR2(100),
  ma022 VARCHAR2(30),
  ma023 VARCHAR2(20),
  ma024 VARCHAR2(20),
  ma025 NUMBER(19,2),
  ma026 VARCHAR2(20),
  ma027 VARCHAR2(20),
  ma028 VARCHAR2(40) not null,
  ma029 VARCHAR2(20),
  ma030 VARCHAR2(20),
  ma031 DATE default sysdate,
  ma032 DATE,
  ma033 VARCHAR2(100),
  ma034 VARCHAR2(20),
  ma035 VARCHAR2(50),
  ma036 VARCHAR2(50),
  ma037 DATE,
  ma038 NUMBER(2),
  ma039 VARCHAR2(40) not null,
  ma040 VARCHAR2(100),
  ma041 DATE,
  ma042 VARCHAR2(200),
  ma043 NUMBER(2),
  ma044 VARCHAR2(200),
  ma045 VARCHAR2(200),
  ma046 VARCHAR2(200),
  ma047 VARCHAR2(20),
  ma048 VARCHAR2(20),
  ma049 VARCHAR2(20),
  ma050 VARCHAR2(20),
  ma051 VARCHAR2(20),
  ma052 VARCHAR2(20),
  ma053 VARCHAR2(20),
  ma054 VARCHAR2(40),
  ma055 VARCHAR2(40),
  ma056 VARCHAR2(40),
  ma057 VARCHAR2(40),
  ma058 VARCHAR2(40),
  ma059 NUMBER(10),
  ma060 NUMBER(10),
  ma061 NUMBER(10),
  ma062 NUMBER(10),
  ma063 NUMBER(10),
  ma064 VARCHAR2(20),
  ma065 VARCHAR2(20),
  ma066 VARCHAR2(200),
  ma067 VARCHAR2(200),
  ma068 VARCHAR2(200),
  ma069 VARCHAR2(40),
  ma070 VARCHAR2(100),
  ma071 NUMBER(2) default 0,
  ma080 VARCHAR2(40) default 0,
  ma081 VARCHAR2(40),
  ma082 VARCHAR2(40),
  ma083 VARCHAR2(40),
  ma084 VARCHAR2(40),
  ma085 VARCHAR2(40),
  ma086 VARCHAR2(40),
  ma087 VARCHAR2(40),
  ma088 VARCHAR2(40),
  ma089 VARCHAR2(40),
  ma100 VARCHAR2(40)
)
;
comment on table B001
  is '��ҵ��b001';
comment on column B001.ma001
  is 'Ψһ��־';
comment on column B001.ma002
  is '��ҵ���';
comment on column B001.ma003
  is '��֯��������';
comment on column B001.ma004
  is '���˵�λ����';
comment on column B001.ma005
  is '״̬�����¼';
comment on column B001.ma006
  is '����id  δ�õ�';
comment on column B001.ma007
  is 'ʡ��id';
comment on column B001.ma008
  is '����id';
comment on column B001.ma009
  is '����id';
comment on column B001.ma010
  is '��������(ע������)()';
comment on column B001.ma011
  is '��ҵ����';
comment on column B001.ma012
  is '����ʱ��';
comment on column B001.ma013
  is '�������ܲ���';
comment on column B001.ma014
  is 'ְ������';
comment on column B001.ma015
  is '�̶��ʲ���ֵ';
comment on column B001.ma016
  is '��������';
comment on column B001.ma017
  is '������';
comment on column B001.ma018
  is '��Ҫ��Ʒ';
comment on column B001.ma019
  is '�Ƿ�������ҵ';
comment on column B001.ma020
  is '��ҵemail��ַ';
comment on column B001.ma021
  is '��������-�����ֶ�';
comment on column B001.ma022
  is '�����ʺ�-�����ֶ�';
comment on column B001.ma023
  is '˰��-�����ֶ�';
comment on column B001.ma024
  is '�绰-�����ֶ�';
comment on column B001.ma025
  is 'ռ�����';
comment on column B001.ma026
  is '���λ�������������������ƣ�';
comment on column B001.ma027
  is '���λ�����˵绰';
comment on column B001.ma028
  is '���Id';
comment on column B001.ma029
  is '���������';
comment on column B001.ma030
  is '����˵绰';
comment on column B001.ma031
  is '�������';
comment on column B001.ma032
  is '�޸�ʱ��';
comment on column B001.ma033
  is 'ͨѶ��ַ';
comment on column B001.ma034
  is '��������';
comment on column B001.ma035
  is '����';
comment on column B001.ma036
  is 'γ��';
comment on column B001.ma037
  is '�ϱ�ʱ��';
comment on column B001.ma038
  is '���״̬ 1��ͨ����2ͨ��  ��ʱû��';
comment on column B001.ma039
  is '�����Id';
comment on column B001.ma040
  is '�����';
comment on column B001.ma041
  is '���ʱ��';
comment on column B001.ma042
  is '������';
comment on column B001.ma043
  is '��� 0-����;1��ҵ';
comment on column B001.ma044
  is '��ע';
comment on column B001.ma045
  is '���˵�λע���ַ';
comment on column B001.ma046
  is '���ڻ���԰';
comment on column B001.ma047
  is '����������';
comment on column B001.ma048
  is '��ȫ����������';
comment on column B001.ma049
  is '��ȫ������������ϵ�绰';
comment on column B001.ma050
  is '����';
comment on column B001.ma051
  is '��ҵ���ͣ�ҵ�����ͣ�1-Σ��Ʒ��ҵ,
2-�̻�������ҵ,
3-��ú��ɽ��ҵ,
4-������ҵ';
comment on column B001.ma052
  is '�����ش�Σ��Դ: 0-��,1-��';
comment on column B001.ma053
  is '������ϵ   1-����,2-����������λ,3-ʡ����ҵ,4-ʡ��������λ,5-������ҵ,6-����';
comment on column B001.ma054
  is 'Ӫҵִ��ע���';
comment on column B001.ma055
  is '��ȫ�������֤���';
comment on column B001.ma056
  is 'Σ�ջ�ѧƷ�Ǽ�֤���';
comment on column B001.ma057
  is '��ȫ������׼��֤����';
comment on column B001.ma058
  is '���¼�����ҵ�϶�֤���';
comment on column B001.ma059
  is '��Ҫ����������';
comment on column B001.ma060
  is '�ֹܸ����˺Ͱ�ȫ������Ա����';
comment on column B001.ma061
  is '������ҵ����';
comment on column B001.ma062
  is '������ҵ����';
comment on column B001.ma063
  is 'ũ������';
comment on column B001.ma064
  is '�Ƿ�����ش��¹����� 0-��1-��';
comment on column B001.ma065
  is '�Ƿ���ר��ְӦ����Ԯ���� 0-��1-��';
comment on column B001.ma066
  is '����ƽ��ͼ';
comment on column B001.ma067
  is '����ͼ';
comment on column B001.ma068
  is '�ش�Σ��ԴͼƬ';
comment on column B001.ma069
  is 'ƴ������';
comment on column B001.ma070
  is 'Ӣ������';
comment on column B001.ma071
  is '�Ƿ��ע��1��ע����0δ��ע';
comment on column B001.ma080
  is '�Ƿ��ϱ�  0δ�ϱ� 1��ҵ�ϱ������� 2�����ϱ����а����';
comment on column B001.ma081
  is '����2';
comment on column B001.ma082
  is '����3';
comment on column B001.ma083
  is '����4';
comment on column B001.ma084
  is '����5';
comment on column B001.ma085
  is '����6';
comment on column B001.ma086
  is '����7';
comment on column B001.ma087
  is '����8';
comment on column B001.ma088
  is '����9';
comment on column B001.ma089
  is '����10';
alter table B001
  add constraint PK_B001 primary key (MA001);

prompt
prompt Creating table B002
prompt ===================
prompt
create table B002
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(50) not null,
  ma005 NUMBER(19,2) not null,
  ma006 NUMBER(4) not null,
  ma007 NUMBER(4) not null,
  ma008 NUMBER(4),
  ma009 VARCHAR2(20),
  ma010 VARCHAR2(30),
  ma011 VARCHAR2(20),
  ma012 VARCHAR2(20),
  ma013 VARCHAR2(20),
  ma014 VARCHAR2(20),
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(20),
  ma017 NUMBER(4),
  ma018 NUMBER(4),
  ma019 VARCHAR2(20) not null,
  ma020 VARCHAR2(20) not null,
  ma021 NUMBER(2),
  ma022 DATE default sysdate not null,
  ma023 DATE default sysdate not null,
  ma024 VARCHAR2(200),
  ma025 VARCHAR2(40) not null
)
;
comment on table B002
  is '������Ϣ�� b002';
comment on column B002.ma001
  is 'Ψһ��־';
comment on column B002.ma002
  is '��ȫ����������';
comment on column B002.ma003
  is '��ҵid';
comment on column B002.ma004
  is '��ȫ�����������';
comment on column B002.ma005
  is '��ȫ�����ʽ�Ͷ��';
comment on column B002.ma006
  is '��ҵ����';
comment on column B002.ma007
  is '��ҵ�������ܱ���λ��ȫ��ѵ����';
comment on column B002.ma008
  is '��ҵ�����ɽ���Ṥ�˱�������';
comment on column B002.ma009
  is '����';
comment on column B002.ma010
  is '�칫�ҵ绰';
comment on column B002.ma011
  is '�ֻ�';
comment on column B002.ma012
  is '����';
comment on column B002.ma013
  is '����';
comment on column B002.ma014
  is '�칫�ҵ绰';
comment on column B002.ma015
  is '�ֻ�';
comment on column B002.ma016
  is '����';
comment on column B002.ma017
  is '������Ա��';
comment on column B002.ma018
  is '��ȫ������Ա��';
comment on column B002.ma019
  is 'Ӧ��Ԥ���ƶ����';
comment on column B002.ma020
  is '��ȫ�������';
comment on column B002.ma021
  is '״̬  0δ�ϱ� 1�ϱ�';
comment on column B002.ma022
  is '�Ǽ�ʱ��';
comment on column B002.ma023
  is '����ʱ��';
comment on column B002.ma024
  is '��ע';
comment on column B002.ma025
  is '�����';
create index CIX_B002_MA003 on B002 (MA003);
create index IX_B002_MA002 on B002 (MA002);
create index IX_B002_MA022 on B002 (MA022);
create index IX_B002_MA023 on B002 (MA023);
alter table B002
  add constraint PK_B002_MA001 primary key (MA001);

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
prompt Creating table C007
prompt ===================
prompt
create table C007
(
  id       VARCHAR2(40) not null,
  key      VARCHAR2(60) not null,
  value    VARCHAR2(20) not null,
  display  VARCHAR2(60) not null,
  describe VARCHAR2(300),
  ordernum NUMBER(4),
  parentid VARCHAR2(40) not null,
  levelnum NUMBER(2),
  del_flag VARCHAR2(1) not null
)
;
comment on table C007
  is '���񾭼���ҵ�����(����)';
comment on column C007.id
  is '����
';
comment on column C007.key
  is '����
';
comment on column C007.value
  is 'ֵ
';
comment on column C007.display
  is '��ʾ����
';
comment on column C007.describe
  is '����
';
comment on column C007.ordernum
  is '����
';
comment on column C007.parentid
  is '���ڵ�
';
comment on column C007.levelnum
  is '�㼶��
';
comment on column C007.del_flag
  is 'ɾ����� 1����Ч��0��ɾ��
';
alter table C007
  add constraint PK_C007 primary key (ID);

prompt
prompt Creating table D001
prompt ===================
prompt
create table D001
(
  ma001 VARCHAR2(32) not null,
  ma002 VARCHAR2(256),
  ma003 DATE,
  ma004 VARCHAR2(64),
  ma005 VARCHAR2(64)
)
;
comment on table D001
  is '�豸���ͱ�';
comment on column D001.ma001
  is '�豸���ͱ�ʶ';
comment on column D001.ma002
  is '�����������';
comment on column D001.ma003
  is '����ʱ��';
comment on column D001.ma004
  is '����1';
comment on column D001.ma005
  is '����2';
alter table D001
  add constraint PK_D001 primary key (MA001);

prompt
prompt Creating table D002
prompt ===================
prompt
create table D002
(
  ma001 VARCHAR2(60) not null,
  ma002 VARCHAR2(60) not null,
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(60),
  ma005 DATE,
  ma006 VARCHAR2(64),
  ma007 VARCHAR2(64),
  ma008 VARCHAR2(200)
)
;
comment on table D002
  is '�û��豸��ϸ��Ϣ';
comment on column D002.ma001
  is '�û��豸��ϸ��ʶ';
comment on column D002.ma002
  is '�û���Ϣ��ʶ';
comment on column D002.ma003
  is '�豸���ͱ�ʶ';
comment on column D002.ma004
  is '�豸��ʶ';
comment on column D002.ma005
  is '����ʱ��';
comment on column D002.ma006
  is '����1';
comment on column D002.ma007
  is '����2';
comment on column D002.ma008
  is '�豸����';
alter table D002
  add constraint PK_D002 primary key (MA001);

prompt
prompt Creating table D003
prompt ===================
prompt
create table D003
(
  ma001 VARCHAR2(32) not null,
  ma002 VARCHAR2(32),
  ma003 VARCHAR2(256),
  ma004 DATE,
  ma005 VARCHAR2(64),
  ma006 VARCHAR2(64)
)
;
comment on table D003
  is '������������Ϣ';
comment on column D003.ma001
  is '������������ʶ';
comment on column D003.ma002
  is '�û���ʶ';
comment on column D003.ma003
  is '��������';
comment on column D003.ma004
  is '����ʱ��';
comment on column D003.ma005
  is '����1';
comment on column D003.ma006
  is '����2';
alter table D003
  add constraint PK_D003 primary key (MA001);

prompt
prompt Creating table D004
prompt ===================
prompt
create table D004
(
  ma001 VARCHAR2(32) not null,
  ma002 NUMBER(12,4),
  ma003 NUMBER(12,4),
  ma004 VARCHAR2(128),
  ma005 VARCHAR2(1),
  ma006 VARCHAR2(32),
  ma007 DATE,
  ma008 VARCHAR2(64),
  ma009 VARCHAR2(64)
)
;
comment on table D004
  is '��������ֵ��ϸ��Ϣ';
comment on column D004.ma001
  is '��������ֵ��ϸ��ʶ';
comment on column D004.ma002
  is '����ֵ��С';
comment on column D004.ma003
  is '����ֵ���';
comment on column D004.ma004
  is '��Ϣ';
comment on column D004.ma005
  is '�Ƿ�����';
comment on column D004.ma006
  is '�豸����ID';
comment on column D004.ma007
  is '����ʱ��';
comment on column D004.ma008
  is '����1';
comment on column D004.ma009
  is '����2';
alter table D004
  add constraint PK_D004 primary key (MA001);

prompt
prompt Creating table D005
prompt ===================
prompt
create table D005
(
  ma001 VARCHAR2(60) not null,
  ma002 VARCHAR2(60),
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(60),
  ma005 CLOB,
  ma006 VARCHAR2(60),
  ma007 VARCHAR2(60),
  ma008 VARCHAR2(60),
  ma009 VARCHAR2(60),
  ma010 VARCHAR2(2048),
  ma011 VARCHAR2(10)
)
;
comment on table D005
  is 'APP������־';
comment on column D005.ma001
  is '����';
comment on column D005.ma002
  is '����ϵͳ(ios,android,����)';
comment on column D005.ma003
  is '����ϵͳ�汾��';
comment on column D005.ma004
  is 'APPӦ������';
comment on column D005.ma005
  is '�쳣��־����';
comment on column D005.ma006
  is '�ƶ��豸����(�ֻ���ƽ��)';
comment on column D005.ma007
  is '�ƶ��豸�ͺ�';
comment on column D005.ma008
  is '������(�ֻ���)';
comment on column D005.ma009
  is '����ʱ��';
comment on column D005.ma010
  is '��־����(ȫ������)';
comment on column D005.ma011
  is '��־����(10��¼��־��00�쳣��־)';
alter table D005
  add constraint PK_D005 primary key (MA001);

prompt
prompt Creating table K001
prompt ===================
prompt
create table K001
(
  ma001 VARCHAR2(60) not null,
  ma002 VARCHAR2(100),
  ma003 VARCHAR2(4000),
  ma004 VARCHAR2(100),
  ma005 DATE,
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100)
)
;
comment on table K001
  is '����֪ʶ��Ϣ';
comment on column K001.ma001
  is 'ID';
comment on column K001.ma002
  is '��Ŀ';
comment on column K001.ma003
  is '����';
comment on column K001.ma004
  is '�ؼ���';
comment on column K001.ma005
  is '����ʱ��';
comment on column K001.ma006
  is '��ע1';
comment on column K001.ma007
  is '��ע2';
alter table K001
  add constraint PK_K001 primary key (MA001);

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
prompt Creating table P001
prompt ===================
prompt
create table P001
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(8) not null,
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(60),
  ma005 VARCHAR2(60),
  ma006 TIMESTAMP(6)
)
;
comment on column P001.ma001
  is '����';
comment on column P001.ma002
  is '�豸id';
comment on column P001.ma003
  is '����';
comment on column P001.ma004
  is '����';
comment on column P001.ma005
  is '����';
comment on column P001.ma006
  is '��������';
alter table P001
  add constraint P001_PK primary key (MA001);

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
prompt Creating table RANKING
prompt ======================
prompt
create table RANKING
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 INTEGER,
  ma004 INTEGER,
  ma005 NUMBER(*,2),
  ma006 INTEGER,
  ma007 DATE,
  ma008 VARCHAR2(20),
  ma009 VARCHAR2(20)
)
;
comment on column RANKING.ma002
  is '�û�id';
comment on column RANKING.ma003
  is '��������';
comment on column RANKING.ma004
  is '״̬';
comment on column RANKING.ma006
  is 'ȫ������';
comment on column RANKING.ma007
  is '��������ʱ��';
comment on column RANKING.ma008
  is '��ע';
comment on column RANKING.ma009
  is '��ע';
alter table RANKING
  add constraint RANKING_PK primary key (MA001);

prompt
prompt Creating table S001
prompt ===================
prompt
create table S001
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(64),
  ma003 VARCHAR2(4000),
  ma004 DATE,
  ma005 VARCHAR2(64),
  ma006 VARCHAR2(64)
)
;
comment on table S001
  is '�������⼰�����Ϣ';
comment on column S001.ma001
  is '���������ʶ';
comment on column S001.ma002
  is '����';
comment on column S001.ma003
  is '�������';
comment on column S001.ma004
  is '�������';
comment on column S001.ma005
  is '��ע1';
comment on column S001.ma006
  is '��ע2';
alter table S001
  add constraint PK_S001 primary key (MA001);

prompt
prompt Creating table S002
prompt ===================
prompt
create table S002
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(64),
  ma003 VARCHAR2(40),
  ma004 DATE,
  ma005 VARCHAR2(512),
  ma006 VARCHAR2(800),
  ma007 VARCHAR2(64)
)
;
comment on table S002
  is '���ͼ�¼��Ϣ';
comment on column S002.ma001
  is '���ͼ�¼��ʶ';
comment on column S002.ma002
  is '������Ŀ';
comment on column S002.ma003
  is '��¼��';
comment on column S002.ma004
  is '����ʱ��';
comment on column S002.ma005
  is '��������';
comment on column S002.ma006
  is '�Ƹ�����';
comment on column S002.ma007
  is '����״̬';
alter table S002
  add constraint PK_S002 primary key (MA001);

prompt
prompt Creating table S003
prompt ===================
prompt
create table S003
(
  ma001 VARCHAR2(32) not null,
  ma002 VARCHAR2(32),
  ma003 VARCHAR2(64),
  ma004 VARCHAR2(1024),
  ma005 DATE,
  ma006 DATE,
  ma007 DATE,
  ma008 VARCHAR2(64),
  ma009 VARCHAR2(64)
)
;
comment on table S003
  is 'ԤԼ������Ϣ';
comment on column S003.ma001
  is 'ԤԼ�����ʶ';
comment on column S003.ma002
  is '�û���ʶ';
comment on column S003.ma003
  is '��Ŀ';
comment on column S003.ma004
  is '����';
comment on column S003.ma005
  is 'ԤԼʱ��';
comment on column S003.ma006
  is '��¼���ʱ��';
comment on column S003.ma007
  is 'ȷ��ʱ��';
comment on column S003.ma008
  is '��ע2';
comment on column S003.ma009
  is '��ע1';
alter table S003
  add constraint PK_S003 primary key (MA001);

prompt
prompt Creating table S004
prompt ===================
prompt
create table S004
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(512),
  ma003 VARCHAR2(512),
  ma004 DATE,
  ma005 VARCHAR2(40),
  ma006 VARCHAR2(64),
  ma007 VARCHAR2(64)
)
;
comment on table S004
  is '��������Ϣ';
comment on column S004.ma001
  is '��������ʶ';
comment on column S004.ma002
  is '����';
comment on column S004.ma003
  is '����';
comment on column S004.ma004
  is '����ʱ��';
comment on column S004.ma005
  is '�û�id';
comment on column S004.ma006
  is '��ע1';
comment on column S004.ma007
  is '��ע2';
alter table S004
  add constraint PK_S004 primary key (MA001);

prompt
prompt Creating table S005
prompt ===================
prompt
create table S005
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(512),
  ma004 DATE,
  ma005 VARCHAR2(40),
  ma006 VARCHAR2(64),
  ma007 VARCHAR2(64)
)
;
comment on table S005
  is '�û�������Ϣ';
comment on column S005.ma001
  is '�û����Ա�ʶ';
comment on column S005.ma002
  is '������ID';
comment on column S005.ma003
  is '�ظ�����';
comment on column S005.ma004
  is '�ظ�ʱ��';
comment on column S005.ma005
  is '�û�ID';
comment on column S005.ma006
  is '��ע1';
comment on column S005.ma007
  is '��ע2';
alter table S005
  add constraint PK_S005 primary key (MA001);

prompt
prompt Creating table S006
prompt ===================
prompt
create table S006
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(256),
  ma003 DATE,
  ma004 VARCHAR2(40),
  ma005 VARCHAR2(512),
  ma006 VARCHAR2(8) default 0,
  ma007 DATE
)
;
comment on table S006
  is '�û�����';
comment on column S006.ma001
  is '���԰��ʶ';
comment on column S006.ma002
  is '����';
comment on column S006.ma003
  is '����ʱ��';
comment on column S006.ma004
  is '�û�id';
comment on column S006.ma005
  is '�ظ�����';
comment on column S006.ma006
  is '�ظ�״̬��0δ�ظ���1�ѻظ�';
comment on column S006.ma007
  is '�ظ�ʱ��';
alter table S006
  add constraint PK_S006 primary key (MA001);

prompt
prompt Creating table S007
prompt ===================
prompt
create table S007
(
  ma001 VARCHAR2(32) not null,
  ma002 VARCHAR2(32),
  ma003 VARCHAR2(1024),
  ma004 VARCHAR2(64),
  ma005 DATE,
  ma006 VARCHAR2(64),
  ma007 VARCHAR2(64)
)
;
comment on table S007
  is 'Ӧ����ʩ����';
comment on column S007.ma001
  is 'Ӧ��id';
comment on column S007.ma002
  is '��ֵ��Ϣ';
comment on column S007.ma003
  is '��ʩ';
comment on column S007.ma004
  is '���ʶ�';
comment on column S007.ma005
  is '�������';
comment on column S007.ma006
  is '��ע1';
comment on column S007.ma007
  is '��ע2';
alter table S007
  add constraint PK_S007 primary key (MA001);
alter table S007
  add constraint FK_S007_REFERENCE_B002 foreign key (MA002)
  references B002 (MA001);

prompt
prompt Creating table S008
prompt ===================
prompt
create table S008
(
  ma001 VARCHAR2(40) not null,
  ma002 INTEGER,
  ma003 INTEGER,
  ma004 VARCHAR2(200),
  ma005 VARCHAR2(4000),
  ma006 INTEGER default 1,
  ma007 INTEGER default 0,
  ma008 VARCHAR2(20),
  ma009 VARCHAR2(20)
)
;
comment on column S008.ma002
  is '��������';
comment on column S008.ma003
  is '״̬';
comment on column S008.ma004
  is '����';
comment on column S008.ma005
  is '����';
comment on column S008.ma006
  is '�Ƿ�����   1 ����  0 �ر�';
comment on column S008.ma007
  is '�ʺ��·�   0 ��ʾ���ʺ�';
comment on column S008.ma008
  is '��ע';
comment on column S008.ma009
  is '��ע';
alter table S008
  add constraint S008_PK primary key (MA001);

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

prompt
prompt Creating table U001
prompt ===================
prompt
create table U001
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(32),
  ma003 VARCHAR2(32),
  ma004 VARCHAR2(256),
  ma005 VARCHAR2(32),
  ma006 VARCHAR2(11),
  ma007 DATE,
  ma008 VARCHAR2(32),
  ma009 VARCHAR2(64),
  ma010 VARCHAR2(64),
  ma011 VARCHAR2(64),
  ma012 VARCHAR2(364),
  ma013 VARCHAR2(200),
  ma014 VARCHAR2(200),
  ma015 VARCHAR2(200),
  ma016 VARCHAR2(200),
  ma018 VARCHAR2(200),
  ma019 VARCHAR2(200),
  ma020 VARCHAR2(200),
  ma017 BLOB
)
;
comment on table U001
  is '�û���Ϣ';
comment on column U001.ma001
  is '�û���Ϣ��ʶ';
comment on column U001.ma002
  is 'ʡ';
comment on column U001.ma003
  is '��';
comment on column U001.ma004
  is '��ַ';
comment on column U001.ma005
  is 'EMAIL';
comment on column U001.ma006
  is '�˺���(PHONE)';
comment on column U001.ma007
  is '���ʱ��';
comment on column U001.ma008
  is '�ǳ�';
comment on column U001.ma009
  is '����';
comment on column U001.ma010
  is '����';
comment on column U001.ma011
  is '��֤��';
comment on column U001.ma012
  is 'ͷ��';
comment on column U001.ma013
  is 'ͷ���ļ���';
comment on column U001.ma014
  is '����';
comment on column U001.ma015
  is 'γ��';
comment on column U001.ma016
  is '��ϸλ��';
comment on column U001.ma018
  is 'ios token';
comment on column U001.ma019
  is '��ǰ��¼���豸����:android ios';
comment on column U001.ma020
  is '����5';
comment on column U001.ma017
  is 'ͷ����';
alter table U001
  add constraint PK_U001 primary key (MA001);

prompt
prompt Creating sequence HIBERNATE_SEQUENCE
prompt ====================================
prompt
create sequence HIBERNATE_SEQUENCE
minvalue 1
maxvalue 9999999999999999999999999999
start with 6841
increment by 1
cache 20;


spool off
