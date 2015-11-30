create table B012
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(20),
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(50) not null,
  ma005 VARCHAR2(50),
  ma006 VARCHAR2(20) not null,
  ma007 NUMBER(19,2) not null,
  ma008 VARCHAR2(20) not null,
  ma009 VARCHAR2(50),
  ma010 DATE default sysdate not null,
  ma011 DATE default sysdate not null,
  ma012 VARCHAR2(20) not null,
  ma013 VARCHAR2(2000),
  ma014 VARCHAR2(40) not null,
  ma015 NUMBER(2) default 0
)
;

create index CIX_B012_MA003 on B012 (MA003);
create index IX_B012_MA006 on B012 (MA006);
create index IX_B012_MA010 on B012 (MA010);
create index IX_B012_MA011 on B012 (MA011);
create index IX_B012_MA012 on B012 (MA012);
alter table B012
  add constraint PK_B012_MA001 primary key (MA001);