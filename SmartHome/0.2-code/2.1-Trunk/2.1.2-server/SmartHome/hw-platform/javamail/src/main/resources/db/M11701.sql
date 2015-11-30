create table M11701
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(40),
  ma004 DATE,
  ma005 VARCHAR2(1),
  ma006 VARCHAR2(1),
  ma007 VARCHAR2(40),
  ma008 VARCHAR2(40)
)
;

alter table M11701
  add constraint PK_M11701 primary key (MA001);
alter table M11701
  add constraint FK_M11701_REFERENCE_M117 foreign key (MA002)
  references M117 (MA001) on delete cascade;