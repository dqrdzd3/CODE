create table B002
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(50) not null,
  ma003 VARCHAR2(40) not null,
  ma004 VARCHAR2(50) not null,
  ma005 NUMBER(19,2) not null,
  ma006 NUMBER(4) not null,
  ma007 NUMBER(4) not null,
  ma008 NUMBER(4),
  ma009 VARCHAR2(20),
  ma010 VARCHAR2(30),
  ma011 VARCHAR2(20),
  ma012 VARCHAR2(20),
  ma013 VARCHAR2(20),
  ma014 VARCHAR2(20),
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(20),
  ma017 NUMBER(4),
  ma018 NUMBER(4),
  ma019 VARCHAR2(20) not null,
  ma020 VARCHAR2(20) not null,
  ma021 NUMBER(2),
  ma022 DATE default sysdate not null,
  ma023 DATE default sysdate not null,
  ma024 VARCHAR2(200),
  ma025 VARCHAR2(40) not null
)
;

create index CIX_B002_MA003 on B002 (MA003);
create index IX_B002_MA002 on B002 (MA002);
create index IX_B002_MA022 on B002 (MA022);
create index IX_B002_MA023 on B002 (MA023);
alter table B002
  add constraint PK_B002_MA001 primary key (MA001);