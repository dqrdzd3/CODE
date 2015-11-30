create table C004_TEMP
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 VARCHAR2(60),
  ma004 VARCHAR2(200) not null,
  ma005 VARCHAR2(200),
  ma006 VARCHAR2(200),
  ma007 VARCHAR2(20),
  ma008 VARCHAR2(100),
  ma009 VARCHAR2(40) not null,
  ma010 DATE not null
)
;

alter table C004_TEMP
  add constraint C004_TEMP_PK primary key (MA001);