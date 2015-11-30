create table K006
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(36) not null,
  ma003 VARCHAR2(36),
  ma004 VARCHAR2(36),
  ma005 VARCHAR2(36),
  ma006 VARCHAR2(36),
  ma007 DATE default sysdate not null,
  ma008 DATE default sysdate,
  ma009 VARCHAR2(200),
  ma010 VARCHAR2(4000),
  ma011 VARCHAR2(4000),
  ma012 VARCHAR2(4000),
  ma013 VARCHAR2(4000),
  ma014 VARCHAR2(4000),
  ma015 VARCHAR2(4000),
  ma016 VARCHAR2(4000),
  ma017 VARCHAR2(4000),
  ma018 VARCHAR2(4000),
  ma019 VARCHAR2(4000),
  ma020 VARCHAR2(4000),
  ma021 VARCHAR2(4000),
  ma022 VARCHAR2(2000),
  ma023 VARCHAR2(2000),
  ma024 VARCHAR2(2000),
  ma025 VARCHAR2(40) not null
)
;

create index IX_K006_MA on K006 (MA006);
create index IX_K006_MA002 on K006 (MA002);
alter table K006
  add constraint PK_K006_MA001 primary key (MA001);