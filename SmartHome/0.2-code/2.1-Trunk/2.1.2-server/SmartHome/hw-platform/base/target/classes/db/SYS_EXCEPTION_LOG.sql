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
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table SYS_EXCEPTION_LOG
  add constraint EXCEPTION_LOG_ID primary key (ID)
  using index 
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );
