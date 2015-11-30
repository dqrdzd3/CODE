create table B00902
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(40),
  ma004 NUMBER(4),
  ma005 NUMBER(19,2),
  ma006 VARCHAR2(200)
)
;

alter table B00902
  add constraint PK_B00902_MA001 primary key (MA001);
alter table B00902
  add constraint FK_B00902_B009 foreign key (MA002)
  references B009 (MA001) on delete cascade;