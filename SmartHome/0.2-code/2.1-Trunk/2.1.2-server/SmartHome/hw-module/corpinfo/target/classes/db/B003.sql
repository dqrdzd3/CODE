create table B003
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(20) not null,
  ma005 VARCHAR2(20) not null,
  ma006 VARCHAR2(20) not null,
  ma007 NUMBER(2),
  ma008 NUMBER(2),
  ma009 VARCHAR2(20),
  ma010 VARCHAR2(50),
  ma011 NUMBER(2),
  ma012 NUMBER(2) not null,
  ma013 DATE default sysdate not null,
  ma014 DATE default sysdate,
  ma015 VARCHAR2(200),
  ma016 NUMBER(2),
  ma017 NUMBER(2),
  ma018 DATE,
  ma019 NUMBER(2),
  ma020 NUMBER(2),
  ma021 VARCHAR2(4),
  ma022 VARCHAR2(40) not null,
  ma023 VARCHAR2(40)
)
;

create index CIX_B003_MA003 on B003 (MA003);
create index IX_B003_MA002 on B003 (MA002);
create index IX_B003_MA012 on B003 (MA012);
create index IX_B003_MA013 on B003 (MA013);
create index IX_B003_MA014 on B003 (MA014);
alter table B003
  add constraint PK_B003_MA001 primary key (MA001);