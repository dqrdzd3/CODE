create table M118
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40),
  ma003 VARCHAR2(40),
  ma004 DATE,
  ma005 VARCHAR2(100),
  ma006 VARCHAR2(400),
  ma007 VARCHAR2(40),
  ma008 VARCHAR2(40),
  ma009 VARCHAR2(40)
)
;

alter table M118
  add constraint PK_M118 primary key (MA001);