create table B00901
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(50),
  ma004 NUMBER(2),
  ma005 VARCHAR2(50),
  ma006 VARCHAR2(50),
  ma007 DATE default sysdate,
  ma008 VARCHAR2(200),
  ma009 VARCHAR2(50)
)
;

alter table B00901
  add constraint PK_B00901_MA001 primary key (MA001);