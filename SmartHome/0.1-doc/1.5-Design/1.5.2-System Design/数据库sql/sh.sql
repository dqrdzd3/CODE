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
  is '历史报警信息';
comment on column A001.ma001
  is '历史报警标识';
comment on column A001.ma002
  is '值';
comment on column A001.ma003
  is '设备状态';
comment on column A001.ma004
  is '设备类型';
comment on column A001.ma005
  is '报警时间';
comment on column A001.ma006
  is '设备ID(WIFI模块ID)';
comment on column A001.ma007
  is '是否已推送(10已推送00未推送)';
comment on column A001.ma008
  is '备注';
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
  is '企业表b001';
comment on column B001.ma001
  is '唯一标志';
comment on column B001.ma002
  is '企业编号';
comment on column B001.ma003
  is '组织机构代码';
comment on column B001.ma004
  is '法人单位名称';
comment on column B001.ma005
  is '状态变更记录';
comment on column B001.ma006
  is '区域id  未用到';
comment on column B001.ma007
  is '省份id';
comment on column B001.ma008
  is '城市id';
comment on column B001.ma009
  is '县区id';
comment on column B001.ma010
  is '经济类型(注册类型)()';
comment on column B001.ma011
  is '行业类型';
comment on column B001.ma012
  is '成立时间';
comment on column B001.ma013
  is '行政主管部门';
comment on column B001.ma014
  is '职工总数';
comment on column B001.ma015
  is '固定资产总值';
comment on column B001.ma016
  is '年总收入';
comment on column B001.ma017
  is '年利润';
comment on column B001.ma018
  is '主要产品';
comment on column B001.ma019
  is '是否中央企业';
comment on column B001.ma020
  is '企业email地址';
comment on column B001.ma021
  is '银行名称-保留字段';
comment on column B001.ma022
  is '银行帐号-保留字段';
comment on column B001.ma023
  is '税号-保留字段';
comment on column B001.ma024
  is '电话-保留字段';
comment on column B001.ma025
  is '占地面积';
comment on column B001.ma026
  is '填报单位负责人姓名（法人名称）';
comment on column B001.ma027
  is '填报单位负责人电话';
comment on column B001.ma028
  is '填报人Id';
comment on column B001.ma029
  is '填表人姓名';
comment on column B001.ma030
  is '填表人电话';
comment on column B001.ma031
  is '填表日期';
comment on column B001.ma032
  is '修改时间';
comment on column B001.ma033
  is '通讯地址';
comment on column B001.ma034
  is '邮政编码';
comment on column B001.ma035
  is '经度';
comment on column B001.ma036
  is '纬度';
comment on column B001.ma037
  is '上报时间';
comment on column B001.ma038
  is '审核状态 1不通过，2通过  暂时没用';
comment on column B001.ma039
  is '审核人Id';
comment on column B001.ma040
  is '审核人';
comment on column B001.ma041
  is '审核时间';
comment on column B001.ma042
  is '审核意见';
comment on column B001.ma043
  is '类别 0-政府;1企业';
comment on column B001.ma044
  is '备注';
comment on column B001.ma045
  is '法人单位注册地址';
comment on column B001.ma046
  is '所在化工园';
comment on column B001.ma047
  is '法定代表人';
comment on column B001.ma048
  is '安全生产负责人';
comment on column B001.ma049
  is '安全生产负责人联系电话';
comment on column B001.ma050
  is '传真';
comment on column B001.ma051
  is '企业类型（业务类型）1-危化品企业,
2-烟花爆竹企业,
3-非煤矿山企业,
4-其他企业';
comment on column B001.ma052
  is '有无重大危险源: 0-无,1-有';
comment on column B001.ma053
  is '隶属关系   1-央企,2-央企下属单位,3-省属企业,4-省企下属单位,5-市属企业,6-其他';
comment on column B001.ma054
  is '营业执照注册号';
comment on column B001.ma055
  is '安全生产许可证编号';
comment on column B001.ma056
  is '危险化学品登记证编号';
comment on column B001.ma057
  is '安全生产标准化证书编号';
comment on column B001.ma058
  is '高新技术企业认定证书号';
comment on column B001.ma059
  is '主要负责人人数';
comment on column B001.ma060
  is '分管负责人和安全管理人员人数';
comment on column B001.ma061
  is '特种作业人数';
comment on column B001.ma062
  is '其他从业人数';
comment on column B001.ma063
  is '农民工人数';
comment on column B001.ma064
  is '是否存在重大事故隐患 0-否，1-是';
comment on column B001.ma065
  is '是否有专兼职应急救援队伍 0-否，1-是';
comment on column B001.ma066
  is '厂区平面图';
comment on column B001.ma067
  is '管网图';
comment on column B001.ma068
  is '重大危险源图片';
comment on column B001.ma069
  is '拼音代码';
comment on column B001.ma070
  is '英文名字';
comment on column B001.ma071
  is '是否标注：1标注过，0未标注';
comment on column B001.ma080
  is '是否上报  0未上报 1企业上报到县区 2县区上报到市安监局';
comment on column B001.ma081
  is '备用2';
comment on column B001.ma082
  is '备用3';
comment on column B001.ma083
  is '备用4';
comment on column B001.ma084
  is '备用5';
comment on column B001.ma085
  is '备用6';
comment on column B001.ma086
  is '备用7';
comment on column B001.ma087
  is '备用8';
comment on column B001.ma088
  is '备用9';
comment on column B001.ma089
  is '备用10';
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
  is '机构信息表 b002';
comment on column B002.ma001
  is '唯一标志';
comment on column B002.ma002
  is '安全管理机构编号';
comment on column B002.ma003
  is '企业id';
comment on column B002.ma004
  is '安全管理机构名称';
comment on column B002.ma005
  is '安全生产资金投入';
comment on column B002.ma006
  is '从业人数';
comment on column B002.ma007
  is '从业人数接受本单位安全培训人数';
comment on column B002.ma008
  is '从业人数缴交社会工伤保险人数';
comment on column B002.ma009
  is '姓名';
comment on column B002.ma010
  is '办公室电话';
comment on column B002.ma011
  is '手机';
comment on column B002.ma012
  is '传真';
comment on column B002.ma013
  is '姓名';
comment on column B002.ma014
  is '办公室电话';
comment on column B002.ma015
  is '手机';
comment on column B002.ma016
  is '传真';
comment on column B002.ma017
  is '技术人员数';
comment on column B002.ma018
  is '安全管理人员数';
comment on column B002.ma019
  is '应急预案制定情况';
comment on column B002.ma020
  is '安全评价情况';
comment on column B002.ma021
  is '状态  0未上报 1上报';
comment on column B002.ma022
  is '登记时间';
comment on column B002.ma023
  is '更新时间';
comment on column B002.ma024
  is '备注';
comment on column B002.ma025
  is '填表人';
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
  is '国民经济行业分类表(国标)';
comment on column C007.id
  is '主键
';
comment on column C007.key
  is '编码
';
comment on column C007.value
  is '值
';
comment on column C007.display
  is '显示内容
';
comment on column C007.describe
  is '描述
';
comment on column C007.ordernum
  is '排序
';
comment on column C007.parentid
  is '父节点
';
comment on column C007.levelnum
  is '层级数
';
comment on column C007.del_flag
  is '删除标记 1：有效；0：删除
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
  is '设备类型表';
comment on column D001.ma001
  is '设备类型标识';
comment on column D001.ma002
  is '监测气体类型';
comment on column D001.ma003
  is '创建时间';
comment on column D001.ma004
  is '备用1';
comment on column D001.ma005
  is '备用2';
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
  is '用户设备明细信息';
comment on column D002.ma001
  is '用户设备明细标识';
comment on column D002.ma002
  is '用户信息标识';
comment on column D002.ma003
  is '设备类型标识';
comment on column D002.ma004
  is '设备标识';
comment on column D002.ma005
  is '创建时间';
comment on column D002.ma006
  is '备用1';
comment on column D002.ma007
  is '备用2';
comment on column D002.ma008
  is '设备名称';
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
  is '传感器操作信息';
comment on column D003.ma001
  is '传感器操作标识';
comment on column D003.ma002
  is '用户标识';
comment on column D003.ma003
  is '操作内容';
comment on column D003.ma004
  is '操作时间';
comment on column D003.ma005
  is '备用1';
comment on column D003.ma006
  is '备用2';
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
  is '传感器阀值明细信息';
comment on column D004.ma001
  is '传感器阀值明细标识';
comment on column D004.ma002
  is '对照值最小';
comment on column D004.ma003
  is '对照值最大';
comment on column D004.ma004
  is '信息';
comment on column D004.ma005
  is '是否启用';
comment on column D004.ma006
  is '设备类型ID';
comment on column D004.ma007
  is '创建时间';
comment on column D004.ma008
  is '备用1';
comment on column D004.ma009
  is '备用2';
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
  is 'APP操作日志';
comment on column D005.ma001
  is '主键';
comment on column D005.ma002
  is '操作系统(ios,android,其他)';
comment on column D005.ma003
  is '操作系统版本号';
comment on column D005.ma004
  is 'APP应用名称';
comment on column D005.ma005
  is '异常日志内容';
comment on column D005.ma006
  is '移动设备类型(手机或平板)';
comment on column D005.ma007
  is '移动设备型号';
comment on column D005.ma008
  is '创建人(手机号)';
comment on column D005.ma009
  is '创建时间';
comment on column D005.ma010
  is '日志内容(全部内容)';
comment on column D005.ma011
  is '日志类型(10登录日志，00异常日志)';
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
  is '健康知识信息';
comment on column K001.ma001
  is 'ID';
comment on column K001.ma002
  is '题目';
comment on column K001.ma003
  is '内容';
comment on column K001.ma004
  is '关键字';
comment on column K001.ma005
  is '创建时间';
comment on column K001.ma006
  is '备注1';
comment on column K001.ma007
  is '备注2';
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
  is '主键';
comment on column P001.ma002
  is '设备id';
comment on column P001.ma003
  is '备用';
comment on column P001.ma004
  is '备用';
comment on column P001.ma005
  is '备用';
comment on column P001.ma006
  is '生成日期';
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
  is '用户id';
comment on column RANKING.ma003
  is '气体类型';
comment on column RANKING.ma004
  is '状态';
comment on column RANKING.ma006
  is '全国排名';
comment on column RANKING.ma007
  is '进行排名时间';
comment on column RANKING.ma008
  is '备注';
comment on column RANKING.ma009
  is '备注';
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
  is '常见问题及解决信息';
comment on column S001.ma001
  is '常见问题标识';
comment on column S001.ma002
  is '标题';
comment on column S001.ma003
  is '解决方法';
comment on column S001.ma004
  is '添加日期';
comment on column S001.ma005
  is '备注1';
comment on column S001.ma006
  is '备注2';
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
  is '推送记录信息';
comment on column S002.ma001
  is '推送记录标识';
comment on column S002.ma002
  is '操作栏目';
comment on column S002.ma003
  is '记录号';
comment on column S002.ma004
  is '推送时间';
comment on column S002.ma005
  is '推送内容';
comment on column S002.ma006
  is '推给的人';
comment on column S002.ma007
  is '发送状态';
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
  is '预约服务信息';
comment on column S003.ma001
  is '预约服务标识';
comment on column S003.ma002
  is '用户标识';
comment on column S003.ma003
  is '题目';
comment on column S003.ma004
  is '内容';
comment on column S003.ma005
  is '预约时间';
comment on column S003.ma006
  is '记录添加时间';
comment on column S003.ma007
  is '确认时间';
comment on column S003.ma008
  is '备注2';
comment on column S003.ma009
  is '备注1';
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
  is '讨论区信息';
comment on column S004.ma001
  is '讨论区标识';
comment on column S004.ma002
  is '主题';
comment on column S004.ma003
  is '内容';
comment on column S004.ma004
  is '建立时间';
comment on column S004.ma005
  is '用户id';
comment on column S004.ma006
  is '备注1';
comment on column S004.ma007
  is '备注2';
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
  is '用户留言信息';
comment on column S005.ma001
  is '用户留言标识';
comment on column S005.ma002
  is '讨论区ID';
comment on column S005.ma003
  is '回复内容';
comment on column S005.ma004
  is '回复时间';
comment on column S005.ma005
  is '用户ID';
comment on column S005.ma006
  is '备注1';
comment on column S005.ma007
  is '备注2';
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
  is '用户反馈';
comment on column S006.ma001
  is '留言板标识';
comment on column S006.ma002
  is '内容';
comment on column S006.ma003
  is '创建时间';
comment on column S006.ma004
  is '用户id';
comment on column S006.ma005
  is '回复内容';
comment on column S006.ma006
  is '回复状态：0未回复，1已回复';
comment on column S006.ma007
  is '回复时间';
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
  is '应急措施处理';
comment on column S007.ma001
  is '应急id';
comment on column S007.ma002
  is '阀值信息';
comment on column S007.ma003
  is '措施';
comment on column S007.ma004
  is '舒适度';
comment on column S007.ma005
  is '添加日期';
comment on column S007.ma006
  is '备注1';
comment on column S007.ma007
  is '备注2';
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
  is '气体类型';
comment on column S008.ma003
  is '状态';
comment on column S008.ma004
  is '标题';
comment on column S008.ma005
  is '内容';
comment on column S008.ma006
  is '是否启用   1 启用  0 关闭';
comment on column S008.ma007
  is '适合月份   0 表示都适合';
comment on column S008.ma008
  is '备注';
comment on column S008.ma009
  is '备注';
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
  is '用户信息';
comment on column U001.ma001
  is '用户信息标识';
comment on column U001.ma002
  is '省';
comment on column U001.ma003
  is '市';
comment on column U001.ma004
  is '地址';
comment on column U001.ma005
  is 'EMAIL';
comment on column U001.ma006
  is '账号名(PHONE)';
comment on column U001.ma007
  is '添加时间';
comment on column U001.ma008
  is '昵称';
comment on column U001.ma009
  is '密码';
comment on column U001.ma010
  is '令牌';
comment on column U001.ma011
  is '验证码';
comment on column U001.ma012
  is '头像';
comment on column U001.ma013
  is '头像文件名';
comment on column U001.ma014
  is '经度';
comment on column U001.ma015
  is '纬度';
comment on column U001.ma016
  is '详细位置';
comment on column U001.ma018
  is 'ios token';
comment on column U001.ma019
  is '当前登录的设备类型:android ios';
comment on column U001.ma020
  is '备用5';
comment on column U001.ma017
  is '头像流';
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
