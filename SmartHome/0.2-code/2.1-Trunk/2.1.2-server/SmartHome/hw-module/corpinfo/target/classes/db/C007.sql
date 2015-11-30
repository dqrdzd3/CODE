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

alter table C007
  add constraint PK_C007 primary key (ID);