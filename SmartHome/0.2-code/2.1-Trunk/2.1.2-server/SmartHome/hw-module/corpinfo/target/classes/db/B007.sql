create table B007
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(60) not null,
  ma003 VARCHAR2(60) not null,
  ma004 VARCHAR2(60),
  ma005 VARCHAR2(20) not null,
  ma006 NUMBER(19,2) not null,
  ma007 NUMBER(19,2),
  ma008 VARCHAR2(100),
  ma009 NUMBER(19,2),
  ma010 NUMBER(19,2),
  ma011 NUMBER(19,2),
  ma012 NUMBER(19,2),
  ma013 NUMBER(19,2),
  ma014 NUMBER(19,2),
  ma015 NUMBER(19,2),
  ma016 NUMBER(19,2),
  ma017 VARCHAR2(20),
  ma018 VARCHAR2(200),
  ma019 VARCHAR2(40) not null,
  ma020 DATE default sysdate not null
)
;

alter table B007
  add constraint PK_B007_MA001 primary key (MA001);