create table SYS_CODE_SEQUENCE
(
  code     VARCHAR2(40) not null,
  sequence NUMBER(10) not null
)
;

alter table SYS_CODE_SEQUENCE
  add constraint PK_CODE_SEQUENCE_CODE primary key (CODE);