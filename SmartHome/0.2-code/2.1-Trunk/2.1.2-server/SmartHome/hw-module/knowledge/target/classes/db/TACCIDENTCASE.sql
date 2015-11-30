create table TACCIDENTCASE
(
  objid        VARCHAR2(36) not null,
  name         VARCHAR2(100) not null,
  createtime   DATE,
  address      VARCHAR2(100),
  type         VARCHAR2(100),
  casetrade    VARCHAR2(100),
  module       VARCHAR2(4000),
  cause        VARCHAR2(4000),
  way          VARCHAR2(4000),
  casesw       INTEGER,
  casezs       INTEGER,
  caseqs       INTEGER,
  remarks      VARCHAR2(2000),
  caseproperty NUMBER(20,2),
  creator      VARCHAR2(40) not null,
  creatdate    DATE default sysdate not null
)
;

alter table TACCIDENTCASE
  add constraint PK_TACC_OBJID primary key (OBJID);