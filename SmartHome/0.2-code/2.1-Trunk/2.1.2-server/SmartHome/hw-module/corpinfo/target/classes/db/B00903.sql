create table B00903
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(40),
  ma004 NUMBER(4),
  ma005 NUMBER(19,2),
  ma006 VARCHAR2(200)
)
;

create unique index UN__B00903_MA002_MA003 on B00903 (MA002, MA003);
alter table B00903
  add constraint PK_B00903_MA001 primary key (MA001);
alter table B00903
  add constraint FK_B00903_B009 foreign key (MA002)
  references B009 (MA001) on delete cascade;