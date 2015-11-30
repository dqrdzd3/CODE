create table C006
(
  ma001  VARCHAR2(40) not null,
  ma002  VARCHAR2(80) not null,
  ma003  VARCHAR2(20) not null,
  ma004  VARCHAR2(20),
  ma005  VARCHAR2(20),
  ma006  VARCHAR2(20),
  ma007  VARCHAR2(20) not null,
  ma008  VARCHAR2(20),
  ma009  VARCHAR2(20) not null,
  ma010  VARCHAR2(20) not null,
  ma011  VARCHAR2(64) not null,
  ma012  VARCHAR2(20) not null,
  ma013  VARCHAR2(20),
  ma014  VARCHAR2(20) not null,
  ma015  VARCHAR2(30) not null,
  ma016  DATE not null,
  ma017  VARCHAR2(40),
  ma018  VARCHAR2(20),
  ma019  DATE,
  ma020  VARCHAR2(4) not null,
  ma021  VARCHAR2(1) not null,
  ma022  VARCHAR2(60),
  ma023  VARCHAR2(60),
  ma024  VARCHAR2(200),
  hymlid VARCHAR2(40),
  hydlid VARCHAR2(40),
  hyzlid VARCHAR2(40),
  hyxlid VARCHAR2(40)
)
;

alter table C006
  add constraint PK_C006_MA001 primary key (MA001);
alter table C006
  add constraint UK_C006_MA003 unique (MA003);