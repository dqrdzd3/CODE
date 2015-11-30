create table B004
(
  ma001 VARCHAR2(40) not null,
  ma002 VARCHAR2(40) not null,
  ma003 NUMBER(19,2),
  ma004 NUMBER(4),
  ma005 NUMBER(4),
  ma006 NUMBER(4),
  ma007 NUMBER(4),
  ma008 NUMBER(4),
  ma009 NUMBER(4),
  ma010 NUMBER(4),
  ma011 NUMBER(4),
  ma012 NUMBER(4),
  ma013 NUMBER(4),
  ma014 NUMBER(4),
  ma015 NUMBER(4),
  ma016 NUMBER(4),
  ma017 NUMBER(4),
  ma018 NUMBER(4),
  ma019 NUMBER(4),
  ma020 NUMBER(4),
  ma021 NUMBER(4),
  ma022 NUMBER(4),
  ma023 NUMBER(4),
  ma024 NUMBER(4),
  ma025 NUMBER(4),
  ma026 NUMBER(4),
  ma027 NUMBER(4),
  ma028 NUMBER(4),
  ma029 NUMBER(4),
  ma030 NUMBER(4),
  ma031 NUMBER(4),
  ma032 NUMBER(4),
  ma033 NUMBER(4),
  ma034 NUMBER(4),
  ma035 NUMBER(4),
  ma036 NUMBER(4),
  ma037 NUMBER(4),
  ma038 NUMBER(4),
  ma039 NUMBER(4),
  ma040 NUMBER(4),
  ma041 NUMBER(19,2),
  ma042 VARCHAR2(50),
  ma043 NUMBER(4),
  ma044 NUMBER(2),
  ma045 DATE default sysdate not null,
  ma046 DATE default sysdate not null,
  ma047 VARCHAR2(200),
  ma048 NUMBER(2) not null,
  ma049 VARCHAR2(200),
  ma050 VARCHAR2(40) not null
)
;

create index CIX_B004_MA002 on B004 (MA002);
create index IX_B004_MA045 on B004 (MA045);
create index IX_B004_MA046 on B004 (MA046);
create index IX_B004_MA048 on B004 (MA048);
alter table B004
  add constraint PK_B004_MA001 primary key (MA001);