create table B00401
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(100),
  ma004 VARCHAR2(100) not null,
  ma005 VARCHAR2(100),
  ma006 VARCHAR2(100),
  ma007 VARCHAR2(100),
  ma008 DATE,
  ma009 VARCHAR2(100),
  ma010 DATE,
  ma011 DATE not null,
  ma012 VARCHAR2(40),
  ma013 VARCHAR2(1000),
  ma014 VARCHAR2(1000),
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(200),
  ma017 VARCHAR2(40) not null,
  ma018 DATE default SYSDATE not null,
  ma019 DATE default SYSDATE not null,
  ma020 VARCHAR2(2) default '0' not null,
  ma021 NUMBER(10),
  ma022 VARCHAR2(40)
)
;

alter table B00401
  add constraint PK_B00401 primary key (MA001);