create table B006
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(60) not null,
  ma004 VARCHAR2(20),
  ma005 NUMBER(2),
  ma006 VARCHAR2(200),
  ma007 NUMBER(19,2),
  ma008 VARCHAR2(60),
  ma009 NUMBER(19,2),
  ma010 VARCHAR2(60),
  ma011 NUMBER(19),
  ma012 VARCHAR2(100),
  ma013 VARCHAR2(100),
  ma014 VARCHAR2(200),
  ma015 VARCHAR2(40) not null,
  ma016 DATE default sysdate not null,
  ma017 NUMBER(2) default 0
)
;

alter table B006
  add constraint PK_B006_MA001 primary key (MA001);