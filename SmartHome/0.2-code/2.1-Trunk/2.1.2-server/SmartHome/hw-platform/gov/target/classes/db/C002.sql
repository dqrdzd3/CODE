create table C002
(
  ma001 VARCHAR2(36) not null,
  ma002 VARCHAR2(20) not null,
  ma003 VARCHAR2(50) not null,
  ma004 VARCHAR2(36) not null,
  ma005 VARCHAR2(20),
  ma006 VARCHAR2(20),
  ma007 VARCHAR2(30) not null,
  ma008 VARCHAR2(2) not null,
  ma009 VARCHAR2(300),
  ma010 VARCHAR2(40) not null,
  ma011 DATE not null,
  ma012 VARCHAR2(40),
  ma013 DATE,
  ma014 NUMBER(4) default 0,
  ma015 VARCHAR2(20),
  ma016 VARCHAR2(8),
  ma017 VARCHAR2(1),
  ma018 VARCHAR2(20),
  ma019 VARCHAR2(20)
)
;

alter table C002
  add constraint PK_C002 primary key (MA001);