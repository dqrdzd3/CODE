create table M11801
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(400),
  ma004 DATE,
  ma005 VARCHAR2(1),
  ma006 VARCHAR2(40),
  ma007 VARCHAR2(40)
)
;

alter table M11801
  add constraint PK_M11801 primary key (MA001);
alter table M11801
  add constraint FK_M11801_REFERENCE_M118 foreign key (MA002)
  references M118 (MA001) on delete cascade;