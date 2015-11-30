create table B005
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(100) not null,
  ma005 VARCHAR2(100) not null,
  ma006 DATE not null,
  ma007 DATE not null,
  ma008 NUMBER(2),
  ma009 VARCHAR2(200),
  ma010 NUMBER(2) not null,
  ma011 VARCHAR2(255),
  ma012 VARCHAR2(30),
  ma013 VARCHAR2(40) not null,
  ma014 DATE default sysdate not null
)
;

create index CIX_B005_MA003 on B005 (MA003);
create unique index UN_B005_MA003_MA002 on B005 (MA002, MA003);
alter table B005
  add constraint PK_B005_MA001 primary key (MA001);