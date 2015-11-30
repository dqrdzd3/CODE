create table B00301
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(100) not null,
  ma004 VARCHAR2(100) not null,
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(40),
  ma007 DATE,
  ma008 VARCHAR2(500),
  ma009 VARCHAR2(40),
  ma010 DATE default SYSDATE,
  ma011 DATE default SYSDATE,
  ma012 VARCHAR2(2),
  ma013 VARCHAR2(40)
)
;

alter table B00301
  add constraint PK_B00301 primary key (MA001);
alter table B00301
  add constraint FK_B00301_B003 foreign key (MA002)
  references B003 (MA001) on delete cascade;