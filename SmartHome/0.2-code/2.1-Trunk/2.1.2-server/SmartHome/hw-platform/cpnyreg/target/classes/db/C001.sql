create table C001
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(60) not null,
  ma003 VARCHAR2(20) not null,
  ma004 VARCHAR2(36),
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(20),
  ma007 VARCHAR2(30) not null,
  ma008 VARCHAR2(2) not null,
  ma009 NUMBER(4) default 0 not null,
  ma010 VARCHAR2(400),
  ma011 VARCHAR2(40) not null,
  ma012 DATE not null,
  ma013 VARCHAR2(40),
  ma014 DATE,
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(8),
  ma017 VARCHAR2(1),
  ma018 VARCHAR2(20),
  ma019 VARCHAR2(20)
)
;

alter table C001
  add constraint PK_C001 primary key (MA001);