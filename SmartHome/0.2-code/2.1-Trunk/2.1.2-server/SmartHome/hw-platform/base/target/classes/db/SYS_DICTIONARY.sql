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

alter table SYS_DICTIONARY
  add constraint PK_DICTIONARY_ID primary key (ID);
alter table SYS_DICTIONARY
  add constraint UQ_DICTIONARY_KEY unique (KEY);