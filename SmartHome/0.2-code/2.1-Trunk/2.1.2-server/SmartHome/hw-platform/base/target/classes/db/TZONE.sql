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

create index IX_TZONE_STATUS on TZONE (STATUS);
alter table TZONE
  add constraint PK_TZONE_OBJID primary key (OBJID);