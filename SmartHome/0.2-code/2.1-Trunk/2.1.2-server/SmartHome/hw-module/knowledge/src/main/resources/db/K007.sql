create table K007
(
  ma001 VARCHAR2(36) not null,
  ma003 VARCHAR2(36),
  ma004 VARCHAR2(50) not null,
  ma005 VARCHAR2(50) not null,
  ma006 VARCHAR2(50) not null,
  ma007 VARCHAR2(50) not null,
  ma008 VARCHAR2(50),
  ma009 VARCHAR2(4000),
  ma010 VARCHAR2(200),
  ma011 VARCHAR2(50),
  ma012 VARCHAR2(60),
  ma013 DATE default SYSDATE,
  ma014 VARCHAR2(50) not null,
  ma015 DATE,
  ma016 VARCHAR2(5) default (3),
  ma017 DATE,
  ma018 DATE not null,
  ma019 DATE,
  ma020 VARCHAR2(100),
  ma002 VARCHAR2(36) not null
)
;

alter table K007
  add constraint PK_K007_MA001 primary key (MA001);