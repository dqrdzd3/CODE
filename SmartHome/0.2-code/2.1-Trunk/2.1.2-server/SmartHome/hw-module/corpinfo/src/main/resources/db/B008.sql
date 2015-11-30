create table B008
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(60) not null,
  ma004 VARCHAR2(60) not null,
  ma005 VARCHAR2(60),
  ma006 DATE,
  ma007 VARCHAR2(200),
  ma008 VARCHAR2(200),
  ma009 VARCHAR2(40) not null,
  ma010 DATE default sysdate not null
)
;

alter table B008
  add constraint PK_B008_MA001 primary key (MA001);