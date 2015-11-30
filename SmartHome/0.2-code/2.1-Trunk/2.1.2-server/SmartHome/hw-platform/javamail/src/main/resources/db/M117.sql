create table M117
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 DATE,
  ma004 VARCHAR2(2000),
  ma005 VARCHAR2(40),
  ma006 VARCHAR2(1),
  ma007 VARCHAR2(400),
  ma008 VARCHAR2(40),
  ma009 VARCHAR2(40),
  ma010 VARCHAR2(40)
)
;

alter table M117
  add constraint PK_M117 primary key (MA001);