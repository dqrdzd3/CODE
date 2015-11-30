create table TREGULATION
(
  ma001 VARCHAR2(50) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(50) not null,
  ma004 VARCHAR2(50) not null,
  ma005 VARCHAR2(2000),
  ma006 VARCHAR2(200),
  ma007 VARCHAR2(200),
  ma008 DATE not null,
  ma009 VARCHAR2(50),
  ma010 DATE,
  ma011 VARCHAR2(500),
  ma012 VARCHAR2(500),
  ma013 NUMBER(2),
  ma014 DATE default SYSDATE not null,
  ma015 DATE,
  ma016 VARCHAR2(400),
  ma017 VARCHAR2(40) not null
)
;

alter table TREGULATION
  add constraint PK_TREGULATION_MA001 primary key (MA001);