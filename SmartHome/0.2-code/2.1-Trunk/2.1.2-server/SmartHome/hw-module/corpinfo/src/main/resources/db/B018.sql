create table B018
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(36) not null,
  ma003 VARCHAR2(36) not null,
  ma004 VARCHAR2(36) not null,
  ma005 VARCHAR2(36) not null,
  ma006 VARCHAR2(20),
  ma007 NUMBER(4),
  ma008 NUMBER(4,2),
  ma009 NUMBER(8,2),
  ma010 VARCHAR2(2000) not null,
  ma011 DATE not null,
  ma012 VARCHAR2(2000) not null,
  ma013 VARCHAR2(2000) not null,
  ma014 VARCHAR2(36) not null,
  ma015 VARCHAR2(36) not null,
  ma016 VARCHAR2(36) not null,
  ma017 VARCHAR2(36) not null,
  ma018 VARCHAR2(36) not null,
  ma019 VARCHAR2(36) not null,
  ma020 VARCHAR2(36) not null,
  ma021 VARCHAR2(36) not null,
  ma022 NUMBER(2) not null,
  ma023 VARCHAR2(2000),
  ma024 VARCHAR2(40) not null,
  ma025 DATE default sysdate not null
)
;

alter table B018
  add constraint PK_B018_MA001 primary key (MA001);