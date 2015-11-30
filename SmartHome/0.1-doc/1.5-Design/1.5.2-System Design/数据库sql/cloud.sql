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
  is '服务ID';
comment on column API.api_name
  is 'API名称';
comment on column API.api_url
  is 'API地址';
comment on column API.api_type
  is 'API类型';
comment on column API.api_memo
  is 'API说明';
comment on column API.api_result
  is '返回结果';
comment on column API.api_format
  is '返回格式';
comment on column API.create_user
  is '创建人';
comment on column API.create_time
  is '创建时间';
comment on column API.del_flag
  is '删除标记';
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
  is '公司表';
comment on column C001.ma001
  is '主键';
comment on column C001.ma002
  is '企业名称
';
comment on column C001.ma003
  is '组织机构代码';
comment on column C001.ma004
  is '安全管理机构主键';
comment on column C001.ma005
  is '企业联系电话(固话)
';
comment on column C001.ma006
  is '手机号
';
comment on column C001.ma007
  is '邮箱';
comment on column C001.ma008
  is '是否有效
无效：00，有效：10
';
comment on column C001.ma009
  is '同时在线人数
';
comment on column C001.ma010
  is '备注';
comment on column C001.ma011
  is '创建人
';
comment on column C001.ma012
  is '创建日期
';
comment on column C001.ma013
  is '修改人
';
comment on column C001.ma014
  is '修改日期
';
comment on column C001.ma015
  is '行政区划代码';
comment on column C001.ma016
  is '省直辖市汉字简称';
comment on column C001.ma017
  is '删除标记 1：有效；0：删除';
comment on column C001.ma018
  is '备用字段1';
comment on column C001.ma019
  is '备用字段2';
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
  is '政府单位表';
comment on column C002.ma001
  is 'ID';
comment on column C002.ma002
  is '组织机构代码';
comment on column C002.ma003
  is '政府单位名称
';
comment on column C002.ma004
  is '上级主管单位主键
';
comment on column C002.ma005
  is '联系电话(固话)
';
comment on column C002.ma006
  is '手机号
';
comment on column C002.ma007
  is '邮箱
';
comment on column C002.ma008
  is '是否有效 无效：00，有效：10"';
comment on column C002.ma009
  is '备注字段
';
comment on column C002.ma010
  is '创建人
';
comment on column C002.ma011
  is '创建日期
';
comment on column C002.ma012
  is '修改人
';
comment on column C002.ma013
  is '修改日期
';
comment on column C002.ma014
  is '同时在线人数';
comment on column C002.ma015
  is '行政区划代码';
comment on column C002.ma016
  is '省直辖市汉字简称';
comment on column C002.ma017
  is '删除标记 1：有效；0：删除';
comment on column C002.ma018
  is '备用字段1';
comment on column C002.ma019
  is '备用字段2';
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
  is '附件存储';
comment on column C004.ma001
  is '主键';
comment on column C004.ma002
  is '主表主键';
comment on column C004.ma003
  is '附件名';
comment on column C004.ma004
  is '附件路径1';
comment on column C004.ma005
  is '附件路径2';
comment on column C004.ma006
  is '附件路径3';
comment on column C004.ma007
  is '附件类型';
comment on column C004.ma008
  is '主表pojo名';
comment on column C004.ma009
  is '创建人';
comment on column C004.ma010
  is '创建日期';
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
  is '附件的临时记录存放表';
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
  is '附件表';
comment on column CLOUD_ACCESSORY.ma001
  is '主键id';
comment on column CLOUD_ACCESSORY.ma002
  is '附件名称';
comment on column CLOUD_ACCESSORY.ma003
  is '附件地址';
comment on column CLOUD_ACCESSORY.ma004
  is '物理地址';
comment on column CLOUD_ACCESSORY.ma005
  is '附件类型';
comment on column CLOUD_ACCESSORY.ma006
  is '排序';
comment on column CLOUD_ACCESSORY.ma007
  is '创建时间';
comment on column CLOUD_ACCESSORY.ma008
  is '图片描述';
comment on column CLOUD_ACCESSORY.ma009
  is '备用字段1';
comment on column CLOUD_ACCESSORY.ma010
  is '备用字段2';
comment on column CLOUD_ACCESSORY.ma011
  is '备用字段3';
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
  is '客户案例详情';
comment on column CLOUD_CUSTOMERCASE.ma001
  is '主键id';
comment on column CLOUD_CUSTOMERCASE.ma002
  is '服务id';
comment on column CLOUD_CUSTOMERCASE.ma003
  is '客户名称';
comment on column CLOUD_CUSTOMERCASE.ma004
  is '标题';
comment on column CLOUD_CUSTOMERCASE.ma005
  is '内容';
comment on column CLOUD_CUSTOMERCASE.ma006
  is '创建时间';
comment on column CLOUD_CUSTOMERCASE.ma007
  is '备用字段1';
comment on column CLOUD_CUSTOMERCASE.ma008
  is '备用字段2';
comment on column CLOUD_CUSTOMERCASE.ma009
  is '备用字段3';
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
  is '常见问题详情';
comment on column CLOUD_FAQ.ma001
  is '主键id';
comment on column CLOUD_FAQ.ma002
  is '服务id';
comment on column CLOUD_FAQ.ma003
  is '标题';
comment on column CLOUD_FAQ.ma004
  is '内容';
comment on column CLOUD_FAQ.ma005
  is '创建时间';
comment on column CLOUD_FAQ.ma006
  is '备用字段1';
comment on column CLOUD_FAQ.ma007
  is '备用字段2';
comment on column CLOUD_FAQ.ma008
  is '备用字段3';
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
  is '首页菜单项';
comment on column CLOUD_MENU.ma001
  is '主键id';
comment on column CLOUD_MENU.ma002
  is '菜单名称';
comment on column CLOUD_MENU.ma003
  is '菜单描述';
comment on column CLOUD_MENU.ma004
  is '菜单链接地址';
comment on column CLOUD_MENU.ma005
  is '菜单类型';
comment on column CLOUD_MENU.ma006
  is '排序';
comment on column CLOUD_MENU.ma007
  is '创建时间';
comment on column CLOUD_MENU.ma008
  is '备用字段1';
comment on column CLOUD_MENU.ma009
  is '备用字段2';
comment on column CLOUD_MENU.ma010
  is '备用字段3';
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
  is '移动应用详情';
comment on column CLOUD_MOBILEAPP.ma001
  is '主键id';
comment on column CLOUD_MOBILEAPP.ma002
  is '服务id';
comment on column CLOUD_MOBILEAPP.ma003
  is '标题';
comment on column CLOUD_MOBILEAPP.ma004
  is '内容';
comment on column CLOUD_MOBILEAPP.ma005
  is 'logo图标,存放在附件中';
comment on column CLOUD_MOBILEAPP.ma006
  is '幻灯片id,存放在幻灯片表中';
comment on column CLOUD_MOBILEAPP.ma007
  is '附件id,例如,手机app文件';
comment on column CLOUD_MOBILEAPP.ma008
  is '创建时间';
comment on column CLOUD_MOBILEAPP.ma009
  is '备用字段1';
comment on column CLOUD_MOBILEAPP.ma010
  is '备用字段2';
comment on column CLOUD_MOBILEAPP.ma011
  is '备用字段3';
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
  is '云应用详情';
comment on column CLOUD_ONLINEAPP.ma001
  is '主键';
comment on column CLOUD_ONLINEAPP.ma002
  is '服务id';
comment on column CLOUD_ONLINEAPP.ma003
  is '简介/特色';
comment on column CLOUD_ONLINEAPP.ma004
  is '标题';
comment on column CLOUD_ONLINEAPP.ma005
  is '内容';
comment on column CLOUD_ONLINEAPP.ma006
  is '时间';
comment on column CLOUD_ONLINEAPP.ma007
  is '排序';
comment on column CLOUD_ONLINEAPP.ma008
  is '类型';
comment on column CLOUD_ONLINEAPP.ma009
  is '备用字段1';
comment on column CLOUD_ONLINEAPP.ma010
  is '备用字段2';
comment on column CLOUD_ONLINEAPP.ma011
  is '备用字段3';
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
  is '使用指南详情';
comment on column CLOUD_PERARTINGGUIDE.ma001
  is '主键id';
comment on column CLOUD_PERARTINGGUIDE.ma002
  is '服务id';
comment on column CLOUD_PERARTINGGUIDE.ma003
  is '标题';
comment on column CLOUD_PERARTINGGUIDE.ma004
  is '内容';
comment on column CLOUD_PERARTINGGUIDE.ma005
  is '创建时间';
comment on column CLOUD_PERARTINGGUIDE.ma006
  is '备用字段1';
comment on column CLOUD_PERARTINGGUIDE.ma007
  is '备用字段2';
comment on column CLOUD_PERARTINGGUIDE.ma008
  is '备用字段3';
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
  is '产品优势,产品特点,详细介绍';
comment on column CLOUD_PRODUCTADVANTAGE.ma001
  is '主键id';
comment on column CLOUD_PRODUCTADVANTAGE.ma002
  is '服务id';
comment on column CLOUD_PRODUCTADVANTAGE.ma003
  is '内容';
comment on column CLOUD_PRODUCTADVANTAGE.ma004
  is '创建时间';
comment on column CLOUD_PRODUCTADVANTAGE.ma005
  is '备用字段1';
comment on column CLOUD_PRODUCTADVANTAGE.ma006
  is '备用字段2';
comment on column CLOUD_PRODUCTADVANTAGE.ma007
  is '备用字段3';
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
  is '产品特色详情';
comment on column CLOUD_PRODUCTFEATURE.ma001
  is '主键';
comment on column CLOUD_PRODUCTFEATURE.ma002
  is '服务id';
comment on column CLOUD_PRODUCTFEATURE.ma003
  is '内容';
comment on column CLOUD_PRODUCTFEATURE.ma004
  is '创建时间';
comment on column CLOUD_PRODUCTFEATURE.ma005
  is '备用字段1';
comment on column CLOUD_PRODUCTFEATURE.ma006
  is '备用字段2';
comment on column CLOUD_PRODUCTFEATURE.ma007
  is '备用字段3';
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
  is '产品与服务';
comment on column CLOUD_PRODUCTSERVICE.ma001
  is '主键';
comment on column CLOUD_PRODUCTSERVICE.ma002
  is '名称';
comment on column CLOUD_PRODUCTSERVICE.ma003
  is '功能描述';
comment on column CLOUD_PRODUCTSERVICE.ma004
  is '价格';
comment on column CLOUD_PRODUCTSERVICE.ma005
  is '排序';
comment on column CLOUD_PRODUCTSERVICE.ma006
  is '创建时间';
comment on column CLOUD_PRODUCTSERVICE.ma007
  is '备用字段1';
comment on column CLOUD_PRODUCTSERVICE.ma008
  is '备用字段2';
comment on column CLOUD_PRODUCTSERVICE.ma009
  is '备用字段3';
comment on column CLOUD_PRODUCTSERVICE.ma010
  is '备用字段4';
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
  is '存放幻灯片表';
comment on column CLOUD_SLIDE.ma001
  is '主键id';
comment on column CLOUD_SLIDE.ma002
  is '应用id';
comment on column CLOUD_SLIDE.ma003
  is '幻灯片名称';
comment on column CLOUD_SLIDE.ma004
  is '介绍';
comment on column CLOUD_SLIDE.ma005
  is '访问路径';
comment on column CLOUD_SLIDE.ma006
  is '图片路径';
comment on column CLOUD_SLIDE.ma007
  is '物理地址';
comment on column CLOUD_SLIDE.ma008
  is '创建时间';
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
  is '解决方案表';
comment on column CLOUD_SOLUTION.ma001
  is '主键';
comment on column CLOUD_SOLUTION.ma002
  is '名称';
comment on column CLOUD_SOLUTION.ma003
  is '描述';
comment on column CLOUD_SOLUTION.ma004
  is '排序';
comment on column CLOUD_SOLUTION.ma005
  is '创建时间';
comment on column CLOUD_SOLUTION.ma006
  is '备用字段1';
comment on column CLOUD_SOLUTION.ma007
  is '备用字段2';
comment on column CLOUD_SOLUTION.ma008
  is '备用字段3';
comment on column CLOUD_SOLUTION.ma009
  is '备用字段4';
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
  is '成功案例表';
comment on column CLOUD_SUCCESSFULCASE.ma001
  is '主键';
comment on column CLOUD_SUCCESSFULCASE.ma002
  is '名称';
comment on column CLOUD_SUCCESSFULCASE.ma003
  is '方案描述';
comment on column CLOUD_SUCCESSFULCASE.ma004
  is '排序';
comment on column CLOUD_SUCCESSFULCASE.ma005
  is '创建时间';
comment on column CLOUD_SUCCESSFULCASE.ma006
  is '备用字段1';
comment on column CLOUD_SUCCESSFULCASE.ma007
  is '备用字段2';
comment on column CLOUD_SUCCESSFULCASE.ma008
  is '备用字段3';
comment on column CLOUD_SUCCESSFULCASE.ma009
  is '备用字段4';
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
  is '采集配置';
comment on column COLLECTCONFIG.collect_id
  is '采集配置ID';
comment on column COLLECTCONFIG.apply_id
  is '申请ID';
comment on column COLLECTCONFIG.collect_name
  is '采集名称';
comment on column COLLECTCONFIG.config_host
  is '配置服务器';
comment on column COLLECTCONFIG.config_port
  is '配置端口';
comment on column COLLECTCONFIG.protocol_id
  is '协议ID';
comment on column COLLECTCONFIG.state
  is '运行状态';
comment on column COLLECTCONFIG.create_time
  is '创建时间';
comment on column COLLECTCONFIG.create_user
  is '创建人';
comment on column COLLECTCONFIG.del_flag
  is '删除标记';
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
  is '采集存储关系表';
comment on column COLLECTSTORAGEMAP.cs_map_id
  is '采集存储关系ID';
comment on column COLLECTSTORAGEMAP.apply_id
  is '申请ID';
comment on column COLLECTSTORAGEMAP.collect_id
  is '采集配置ID';
comment on column COLLECTSTORAGEMAP.protocol_id
  is '协议ID';
comment on column COLLECTSTORAGEMAP.storage_id
  is '存储ID';
comment on column COLLECTSTORAGEMAP.del_flag
  is '删除标记';
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
  is '串口';
comment on column COMPORT.com_id
  is '串口ID';
comment on column COMPORT.link_id
  is '链路ID';
comment on column COMPORT.com_name
  is '串口名称';
comment on column COMPORT.baud_rage
  is '波特率';
comment on column COMPORT.parity
  is '奇偶校验';
comment on column COMPORT.data_bits
  is '数据位';
comment on column COMPORT.stop_bits
  is '停止位';
comment on column COMPORT.del_flag
  is '删除标记';
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
  is '设备';
comment on column DEVICE.device_id
  is '设备ID';
comment on column DEVICE.gprs_id
  is 'GPRS ID';
comment on column DEVICE.address
  is '设备地址';
comment on column DEVICE.type
  is '设备类型';
comment on column DEVICE.media
  is '探测介质';
comment on column DEVICE.unit
  is '探测介质单位';
comment on column DEVICE.create_time
  is '创建时间';
comment on column DEVICE.update_time
  is '更新时间';
comment on column DEVICE.del_flag
  is '删除标记';
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
  is '设备和业务系统关系表';
comment on column DEVICE_SYS.id
  is '主键';
comment on column DEVICE_SYS.device_id
  is '设备id';
comment on column DEVICE_SYS.name
  is '业务系统名称';
comment on column DEVICE_SYS.jc
  is '业务系统简称';
comment on column DEVICE_SYS.type
  is 'webservice类型';
comment on column DEVICE_SYS.url
  is 'webservice地址';
comment on column DEVICE_SYS.rev1
  is '备用1';
comment on column DEVICE_SYS.rev2
  is '备用2';
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
  is '链路ID';
comment on column GPRSMODULE.ccid
  is 'CCID';
comment on column GPRSMODULE.mobile
  is '手机号';
comment on column GPRSMODULE.apn_name
  is 'APN名称';
comment on column GPRSMODULE.apn_user
  is 'APN用户';
comment on column GPRSMODULE.apn_password
  is 'APN密码';
comment on column GPRSMODULE.x
  is 'X坐标';
comment on column GPRSMODULE.y
  is 'Y坐标';
comment on column GPRSMODULE.ip
  is 'IP地址';
comment on column GPRSMODULE.port
  is '端口';
comment on column GPRSMODULE.create_user
  is '创建人';
comment on column GPRSMODULE.create_time
  is '创建时间';
comment on column GPRSMODULE.update_time
  is '更新时间';
comment on column GPRSMODULE.del_flag
  is '删除标记';
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
  is '通讯链路';
comment on column LINKMODULE.link_id
  is '链路ID';
comment on column LINKMODULE.collect_id
  is '采集配置ID';
comment on column LINKMODULE.link_name
  is '链路名称';
comment on column LINKMODULE.link_type
  is '链路类型';
comment on column LINKMODULE.link_ip
  is '监听IP';
comment on column LINKMODULE.link_port
  is '监听端口';
comment on column LINKMODULE.redirect_ip
  is '重定向主机IP';
comment on column LINKMODULE.redirect_port
  is '重定向主机TCP端口';
comment on column LINKMODULE.time_gap
  is '上传时间间隔';
comment on column LINKMODULE.idle_gap
  is '空闲间隔';
comment on column LINKMODULE.create_user
  is '创建人';
comment on column LINKMODULE.create_time
  is '创建时间';
comment on column LINKMODULE.del_flag
  is '删除标记';
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
  is '业务消息提醒';
comment on column M119.ma001
  is '主键';
comment on column M119.ma002
  is '标题';
comment on column M119.ma003
  is '内容';
comment on column M119.ma004
  is '内容连接';
comment on column M119.ma005
  is '企业id';
comment on column M119.ma006
  is '发送时间';
comment on column M119.ma007
  is '发送人';
comment on column M119.ma008
  is '类型  1邮件  2短信  3其他';
comment on column M119.ma009
  is '接收人';
comment on column M119.ma010
  is '备用1';
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
  is 'API参数';
comment on column PARAMETERLIST.para_id
  is '参数ID';
comment on column PARAMETERLIST.api_id
  is 'API ID';
comment on column PARAMETERLIST.para_name
  is '参数名称';
comment on column PARAMETERLIST.para_type
  is '参数类型';
comment on column PARAMETERLIST.para_length
  is '参数长度';
comment on column PARAMETERLIST.para_limit
  is '参数限制';
comment on column PARAMETERLIST.para_example
  is '示例';
comment on column PARAMETERLIST.is_necessary
  is '是否必需';
comment on column PARAMETERLIST.default_val
  is '默认值';
comment on column PARAMETERLIST.para_memo
  is '参数说明';
comment on column PARAMETERLIST.memo
  is '备注';
comment on column PARAMETERLIST.del_flag
  is '删除标记';
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
  is '协议格式';
comment on column PROTOCOFORRMAT.pformat_id
  is '协议格式ID';
comment on column PROTOCOFORRMAT.protocol_id
  is '协议ID';
comment on column PROTOCOFORRMAT.serial_number
  is '序号';
comment on column PROTOCOFORRMAT.define
  is '定义';
comment on column PROTOCOFORRMAT.length
  is '长度';
comment on column PROTOCOFORRMAT.format_memo
  is '说明';
comment on column PROTOCOFORRMAT.memo
  is '备注';
comment on column PROTOCOFORRMAT.create_user
  is '创建人';
comment on column PROTOCOFORRMAT.create_time
  is '创建时间';
comment on column PROTOCOFORRMAT.del_flag
  is '删除标记';
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
  is '协议';
comment on column PROTOCOL.protocol_id
  is '协议ID';
comment on column PROTOCOL.protocol_name
  is '协议名称';
comment on column PROTOCOL.protocol_type
  is '协议类型';
comment on column PROTOCOL.create_user
  is '创建人';
comment on column PROTOCOL.create_time
  is '创建时间';
comment on column PROTOCOL.del_flag
  is '删除标记';
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
  is '协议存储格式关系表';
comment on column PROTOCOLSTORAGEMAP.map_id
  is '格式关系ID';
comment on column PROTOCOLSTORAGEMAP.protocol_id
  is '协议ID';
comment on column PROTOCOLSTORAGEMAP.pformat_id
  is '协议格式ID';
comment on column PROTOCOLSTORAGEMAP.storage_id
  is '存储ID';
comment on column PROTOCOLSTORAGEMAP.sformat_id
  is '存储格式ID';
comment on column PROTOCOLSTORAGEMAP.del_flag
  is '删除标记';
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
  is '协议主键表';

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
  is '设备和业务系统关系表';
comment on column PROTOCOL_SYS.id
  is '主键';
comment on column PROTOCOL_SYS.device_id
  is '协议id';
comment on column PROTOCOL_SYS.name
  is '业务系统名称';
comment on column PROTOCOL_SYS.jc
  is '业务系统简称';
comment on column PROTOCOL_SYS.type
  is 'webservice类型';
comment on column PROTOCOL_SYS.url
  is 'webservice地址';
comment on column PROTOCOL_SYS.rev1
  is '备用1';
comment on column PROTOCOL_SYS.rev2
  is '备用2';

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
  is 'pushlet消息表';
comment on column PUSHLET_MSG.id
  is '主键';
comment on column PUSHLET_MSG.user_id
  is '用户ID';
comment on column PUSHLET_MSG.msg
  is '消息';
comment on column PUSHLET_MSG.url
  is '访问url';
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
  is '服务表';
comment on column SERVICE.service_id
  is '服务ID';
comment on column SERVICE.service_name
  is '服务名称';
comment on column SERVICE.service_type
  is '服务类型';
comment on column SERVICE.web_url
  is 'web地址';
comment on column SERVICE.admin_url
  is '管理地址';
comment on column SERVICE.service_memo
  is '服务描述';
comment on column SERVICE.service_provider
  is '服务提供者';
comment on column SERVICE.version
  is '版本号';
comment on column SERVICE.regist_time
  is '注册时间';
comment on column SERVICE.realization_way
  is '实现方式';
comment on column SERVICE.call_way
  is '调用方式';
comment on column SERVICE.start_time
  is '服务启用时间';
comment on column SERVICE.stop_time
  is '服务停用时间';
comment on column SERVICE.safety_require
  is '安全要求';
comment on column SERVICE.safety_certification
  is '安全认证';
comment on column SERVICE.is_audit
  is '是否审核';
comment on column SERVICE.is_publish
  is '是否发布';
comment on column SERVICE.update_time
  is '更新时间';
comment on column SERVICE.create_user
  is '创建人';
comment on column SERVICE.create_time
  is '创建时间';
comment on column SERVICE.del_flag
  is '删除标记';
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
  is '服务申请表';
comment on column SERVICEAPPLY.apply_id
  is '申请ID';
comment on column SERVICEAPPLY.apply_name
  is '申请名称';
comment on column SERVICEAPPLY.apply_user
  is '申请用户';
comment on column SERVICEAPPLY.service_id
  is '服务ID';
comment on column SERVICEAPPLY.service_name
  is '服务名称';
comment on column SERVICEAPPLY.apply_service_id
  is '所属业务服务ID';
comment on column SERVICEAPPLY.is_audit
  is '是否审核';
comment on column SERVICEAPPLY.del_flag
  is '删除标记';
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
  is '服务消费关系表';
comment on column SERVICECONSUME.consume_id
  is '服务消费ID';
comment on column SERVICECONSUME.service_id
  is '服务ID';
comment on column SERVICECONSUME.service_consumer
  is '服务消费者';
comment on column SERVICECONSUME.del_flag
  is '删除标记';
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
  is '服务依赖关系';
comment on column SERVICERELY.rely_id
  is '依赖ID';
comment on column SERVICERELY.service_id
  is '服务ID';
comment on column SERVICERELY.rely_service_id
  is '依赖的服务ID';
comment on column SERVICERELY.del_flag
  is '删除标记';
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
  is '存储配置';
comment on column SOTRAGE.storage_id
  is '存储ID';
comment on column SOTRAGE.storage_name
  is '存储名称';
comment on column SOTRAGE.storage_type
  is '存储类型';
comment on column SOTRAGE.storage_host
  is '存储服务器';
comment on column SOTRAGE.storage_port
  is '存储端口';
comment on column SOTRAGE.storage_user
  is '存储用户名';
comment on column SOTRAGE.storage_password
  is '存储密码';
comment on column SOTRAGE.storage_location
  is '存储位置';
comment on column SOTRAGE.storage_db
  is '存储库名';
comment on column SOTRAGE.storage_table
  is '存储表名';
comment on column SOTRAGE.create_user
  is '创建人';
comment on column SOTRAGE.create_time
  is '创建时间';
comment on column SOTRAGE.del_flag
  is '删除标记';
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
  is '存储格式';
comment on column STORAGEFORMAT.sformat_id
  is '存储格式ID';
comment on column STORAGEFORMAT.storage_id
  is '存储ID';
comment on column STORAGEFORMAT.field_name
  is '字段名称';
comment on column STORAGEFORMAT.field_type
  is '字段类型';
comment on column STORAGEFORMAT.field_length
  is '字段长度';
comment on column STORAGEFORMAT.field_memo
  is '字段说明';
comment on column STORAGEFORMAT.memo
  is '备注';
comment on column STORAGEFORMAT.create_user
  is '创建人';
comment on column STORAGEFORMAT.create_time
  is '创建时间';
comment on column STORAGEFORMAT.del_flag
  is '删除标记';
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
  is '代码序列表';
comment on column SYS_CODE_SEQUENCE.code
  is '代码';
comment on column SYS_CODE_SEQUENCE.sequence
  is '序列';
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
  is '时间ID';
comment on column SYS_DATE.data_desc
  is '时间描述';
comment on column SYS_DATE.data_type
  is '时间类型';
comment on column SYS_DATE.data_type_desc
  is '时间类型描述';

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
  is '系统字典';
comment on column SYS_DICTIONARY.id
  is '主键';
comment on column SYS_DICTIONARY.key
  is '编码';
comment on column SYS_DICTIONARY.value
  is '值';
comment on column SYS_DICTIONARY.display
  is '显示内容';
comment on column SYS_DICTIONARY.describe
  is '描述';
comment on column SYS_DICTIONARY.ordernum
  is '排序';
comment on column SYS_DICTIONARY.parentid
  is '父节点';
comment on column SYS_DICTIONARY.levelnum
  is '层级数';
comment on column SYS_DICTIONARY.del_flag
  is '删除标记 1：有效；0：删除';
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
  is '编号';
comment on column SYS_EXCEPTION_LOG.type
  is '类型';
comment on column SYS_EXCEPTION_LOG.operator_id
  is '操作人ID';
comment on column SYS_EXCEPTION_LOG.operator_name
  is '操作人姓名';
comment on column SYS_EXCEPTION_LOG.recording_time
  is '记录时间';
comment on column SYS_EXCEPTION_LOG.msg
  is '异常信息';
comment on column SYS_EXCEPTION_LOG.content
  is '异常内容';
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
  is '密码找回临时数据表';
comment on column SYS_FINDPWD.id
  is '主键';
comment on column SYS_FINDPWD.login_name
  is '用户账号';
comment on column SYS_FINDPWD.type
  is '找回密码方式：SMS短信，EMAIL邮件';
comment on column SYS_FINDPWD.dynamic_code
  is '生成的动态短信验证码，有效时间60s';
comment on column SYS_FINDPWD.key
  is '加密字符串（LOGIN_NAME+TYPE+VALID_DATE）';
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
  is '登录日志表';
comment on column SYS_LOGIN_LOG.id
  is '日志标识';
comment on column SYS_LOGIN_LOG.type
  is '日志类型(1:正常登录; 2:正常登出)';
comment on column SYS_LOGIN_LOG.recording_time
  is '记录时间';
comment on column SYS_LOGIN_LOG.user_id
  is '用户标识';
comment on column SYS_LOGIN_LOG.user_name
  is '用户名';
comment on column SYS_LOGIN_LOG.ip
  is 'IP';
comment on column SYS_LOGIN_LOG.gov_id
  is '政府标识';
comment on column SYS_LOGIN_LOG.corp_id
  is '企业标识';
comment on column SYS_LOGIN_LOG.remarks
  is '备注';
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
  is '编号';
comment on column SYS_OPER_LOG.type
  is '类型';
comment on column SYS_OPER_LOG.operator_id
  is '操作人ID';
comment on column SYS_OPER_LOG.operator_name
  is '操作人姓名';
comment on column SYS_OPER_LOG.recording_time
  is '记录时间';
comment on column SYS_OPER_LOG.class_name
  is '类名';
comment on column SYS_OPER_LOG.method_name
  is '方法名';
comment on column SYS_OPER_LOG.content
  is '日志内容';
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
  is '功能权限表';
comment on column SYS_PERMISSION_DEL.id
  is 'ID';
comment on column SYS_PERMISSION_DEL.parentid
  is '上级ID，如果为顶级功能，上级ID和ID相同';
comment on column SYS_PERMISSION_DEL.name
  is '名称';
comment on column SYS_PERMISSION_DEL.url
  is 'URL';
comment on column SYS_PERMISSION_DEL.image
  is '图片';
comment on column SYS_PERMISSION_DEL.available
  is '是否可用,0_不可用,1_可用.';
comment on column SYS_PERMISSION_DEL.admin
  is '是否管理员权限:0_否1_是';
comment on column SYS_PERMISSION_DEL.ordernum
  is '顺序号';
comment on column SYS_PERMISSION_DEL.description
  is '描述';
comment on column SYS_PERMISSION_DEL.isshow
  is '是否可显示，默认为1，可显示，0为不可显示';
comment on column SYS_PERMISSION_DEL.module_code
  is '模块代码';
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
  is '修改记录表';
comment on column SYS_RECORD.ma001
  is '主键';
comment on column SYS_RECORD.ma002
  is '表名';
comment on column SYS_RECORD.ma003
  is '关联的主键';
comment on column SYS_RECORD.ma004
  is '列名';
comment on column SYS_RECORD.ma005
  is '旧的值';
comment on column SYS_RECORD.ma006
  is '新的值';
comment on column SYS_RECORD.ma007
  is '修改日期';
comment on column SYS_RECORD.ma008
  is '修改人';
comment on column SYS_RECORD.ma009
  is '同次标志 随机生成一个就好';
comment on column SYS_RECORD.ma010
  is '备用1';
comment on column SYS_RECORD.ma011
  is '备用2';
comment on column SYS_RECORD.ma012
  is '备用3';
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
  is '业务菜单表 s_busi_module';
comment on column S_BUSI_MODULE.uuid
  is '主键';
comment on column S_BUSI_MODULE.menu_name
  is '菜单名称';
comment on column S_BUSI_MODULE.menu_code
  is '模块编码';
comment on column S_BUSI_MODULE.parent_uuid
  is '上级模块主键';
comment on column S_BUSI_MODULE.url
  is 'URL链接';
comment on column S_BUSI_MODULE.user_type
  is '用户类型 常量定义
暂定：企业：ENT，政府:GOV，系统:SYS';
comment on column S_BUSI_MODULE.is_valid
  is '是否有效 字典定义
暂定：无效：00，有效：10';
comment on column S_BUSI_MODULE.menu_type
  is '权限类别_数据字典';
comment on column S_BUSI_MODULE.remark
  is '备注';
comment on column S_BUSI_MODULE.ordernum
  is '顺序号';
comment on column S_BUSI_MODULE.creator
  is '创建人';
comment on column S_BUSI_MODULE.create_date
  is '创建日期';
comment on column S_BUSI_MODULE.editor
  is '修改人';
comment on column S_BUSI_MODULE.modified_date
  is '修改日期';
comment on column S_BUSI_MODULE.is_show
  is '是否显示：10显示,00不显示';
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
  is '业务操作表 s_busi_operation';
comment on column S_BUSI_OPERATION.uuid
  is '业务操作主键';
comment on column S_BUSI_OPERATION.action_name
  is '业务操作名称';
comment on column S_BUSI_OPERATION.action_code
  is '操作编码';
comment on column S_BUSI_OPERATION.style_name
  is '样式名称';
comment on column S_BUSI_OPERATION.url
  is '业务操作请求';
comment on column S_BUSI_OPERATION.is_valid
  is '是否有效 字典定义
暂定：无效：00，有效：10';
comment on column S_BUSI_OPERATION.remark
  is '备注';
comment on column S_BUSI_OPERATION.creator
  is '创建人';
comment on column S_BUSI_OPERATION.create_date
  is '创建日期';
comment on column S_BUSI_OPERATION.menu_uuid
  is '业务模块主键';
comment on column S_BUSI_OPERATION.bind_func
  is '绑定函数';
comment on column S_BUSI_OPERATION.ordernum
  is '顺序号';
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
  is '业务资源表 s_busi_resource';
comment on column S_BUSI_RESOURCE.uuid
  is '业务资源主键';
comment on column S_BUSI_RESOURCE.resource_name
  is '业务资源名称';
comment on column S_BUSI_RESOURCE.resource_type
  is '业务资源类型';
comment on column S_BUSI_RESOURCE.module_opera_uuid
  is '业务菜单/业务操作主键';
comment on column S_BUSI_RESOURCE.url
  is '资源';
comment on column S_BUSI_RESOURCE.is_default
  is '是否默认资源
0：默认，1：自定义';
comment on column S_BUSI_RESOURCE.creator
  is '创建人';
comment on column S_BUSI_RESOURCE.create_date
  is '创建日期';
comment on column S_BUSI_RESOURCE.bind_func
  is '绑定函数';
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
  is '部门表 s_department';
comment on column S_DEPARTMENT.uuid
  is '部门主键';
comment on column S_DEPARTMENT.depart_name
  is '部门名称';
comment on column S_DEPARTMENT.depart_code
  is '部门编码';
comment on column S_DEPARTMENT.parent_uuid
  is '上级部门主键(父节点)';
comment on column S_DEPARTMENT.organ_uuid
  is '企业/政府机构主键';
comment on column S_DEPARTMENT.remark
  is '备注';
comment on column S_DEPARTMENT.creator
  is '创建人';
comment on column S_DEPARTMENT.create_date
  is '创建日期';
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
  is '部门初始化角色 s_dept_role';
comment on column S_DEPT_ROLE.uuid
  is '主键';
comment on column S_DEPT_ROLE.depart_uuid
  is '部门主键';
comment on column S_DEPT_ROLE.role_uuid
  is '角色主键';
comment on column S_DEPT_ROLE.creator
  is '创建人';
comment on column S_DEPT_ROLE.create_date
  is '创建日期';
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
  is '角色表 s_role';
comment on column S_ROLE.uuid
  is '主键';
comment on column S_ROLE.role_name
  is '角色名称';
comment on column S_ROLE.role_code
  is '角色代码';
comment on column S_ROLE.user_type
  is '用户类型 常量定义
暂定：企业：ENT，政府:GOV，系统:SYS';
comment on column S_ROLE.organ_uuid
  is '企业或政府机构编码';
comment on column S_ROLE.remark
  is '备注';
comment on column S_ROLE.creator
  is '创建人';
comment on column S_ROLE.create_date
  is '创建日期';
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
  is '业务模块操作关联表 s_role_perm';
comment on column S_ROLE_PERM.uuid
  is '主键';
comment on column S_ROLE_PERM.role_uuid
  is '角色ID';
comment on column S_ROLE_PERM.module_uuid
  is '业务模块ID';
comment on column S_ROLE_PERM.opera_uuid
  is '业务模块操作ID';
comment on column S_ROLE_PERM.organ_uuid
  is '企业或政府单位编码';
comment on column S_ROLE_PERM.creator
  is '创建人';
comment on column S_ROLE_PERM.create_date
  is '创建日期';
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
  is '用户表 s_user';
comment on column S_USER.uuid
  is '主键';
comment on column S_USER.login_name
  is '账户名称';
comment on column S_USER.password
  is '密码';
comment on column S_USER.real_name
  is '真实姓名';
comment on column S_USER.age
  is '年龄';
comment on column S_USER.sex
  is '性别 字典表定义
男：M，女：F';
comment on column S_USER.mobile_number
  is '手机';
comment on column S_USER.email
  is '邮箱';
comment on column S_USER.id_card
  is '身份证号码';
comment on column S_USER.user_type
  is '用户类型 常量定义
企业：ENT，政府:GOV，系统:SYS';
comment on column S_USER.is_admin
  is '是否管理员';
comment on column S_USER.depart_uuid
  is '部门编号';
comment on column S_USER.depart_code
  is '部门代码';
comment on column S_USER.gov_uuid
  is '保留字段';
comment on column S_USER.user_status
  is '状态 字典表定义
00:未审核，10：禁用，20：开通';
comment on column S_USER.employee_num
  is '员工编号';
comment on column S_USER.last_access_ip
  is '最后一次访问系统IP';
comment on column S_USER.last_access_datetime
  is '最后一次访问系统时间';
comment on column S_USER.creator
  is '创建人';
comment on column S_USER.create_date
  is '创建日期';
comment on column S_USER.editor
  is '修改人';
comment on column S_USER.modified_date
  is '修改日期';
comment on column S_USER.organ_uuid
  is '企业/政府机构UUID';
comment on column S_USER.mobile_device_access
  is '移动设备访问 1：允许；0：禁止';
comment on column S_USER.outer_net_access
  is '外网访问 1：允许；0：禁止';
comment on column S_USER.ext1
  is '扩展字段1';
comment on column S_USER.ext2
  is '扩展字段2';
comment on column S_USER.ext3
  is '扩展字段3';
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
  is '用户角色关联表 s_user_role';
comment on column S_USER_ROLE.uuid
  is '主键';
comment on column S_USER_ROLE.user_uuid
  is '用户ID';
comment on column S_USER_ROLE.role_uuid
  is '角色ID';
comment on column S_USER_ROLE.creator
  is '创建人';
comment on column S_USER_ROLE.create_date
  is '创建日期';
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
  is 'test table by lixn  机构部门信息表';
comment on column T001.ma001
  is 'ID';
comment on column T001.ma002
  is '机构代码';
comment on column T001.ma003
  is '机构名称';
comment on column T001.ma004
  is '上级机构ID';
comment on column T001.ma005
  is '固定电话';
comment on column T001.ma006
  is '传真';
comment on column T001.ma007
  is '邮箱';
comment on column T001.ma008
  is '标志位,是否有效, 00 无效, 10 有效';
comment on column T001.ma009
  is '备注信息';
comment on column T001.ma010
  is '创建人';
comment on column T001.ma011
  is '创建日期';
comment on column T001.ma012
  is '修改人';
comment on column T001.ma013
  is '修改日期';
comment on column T001.ma014
  is '删除标记 1：有效；0：删除';
comment on column T001.ma015
  is '备用字段1';
comment on column T001.ma016
  is '备用字段2';

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
  is '类别';
comment on column TCODEVALUE.name
  is '名称';
comment on column TCODEVALUE.value
  is '值';
comment on column TCODEVALUE.parent
  is '父';
comment on column TCODEVALUE.findex
  is '排序';

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
  is '令牌属性表';
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
  is '区省市表：TZONE';
comment on column TZONE.objid
  is '唯一标志  5 6 10 11 12暂不使用';
comment on column TZONE.code
  is '编号';
comment on column TZONE.name
  is '名称';
comment on column TZONE.kind
  is '类型';
comment on column TZONE.status
  is '状态';
comment on column TZONE.statuslog
  is '状态变更记录';
comment on column TZONE.flevel
  is '层次';
comment on column TZONE.findex
  is '序号';
comment on column TZONE.parentid
  is '父节点';
comment on column TZONE.areaid
  is '区域';
comment on column TZONE.provid
  is '省份';
comment on column TZONE.cityid
  is '市';
comment on column TZONE.remark
  is '备注';
create index IX_TZONE_STATUS on TZONE (STATUS);
alter table TZONE
  add constraint PK_TZONE_OBJID primary key (OBJID);


spool off
