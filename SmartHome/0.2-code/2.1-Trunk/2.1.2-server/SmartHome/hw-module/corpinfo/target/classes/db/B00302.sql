create table B00302
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(100) not null,
  ma005 VARCHAR2(100),
  ma006 DATE,
  ma007 DATE not null,
  ma008 VARCHAR2(40) not null,
  ma009 DATE default SYSDATE not null,
  ma010 DATE default SYSDATE,
  ma011 VARCHAR2(2),
  ma012 VARCHAR2(40)
)
;

alter table B00302
  add constraint PK_B00302 primary key (MA001);
alter table B00302
  add constraint FK_B00302_B003 foreign key (MA002)
  references B003 (MA001) on delete cascade;