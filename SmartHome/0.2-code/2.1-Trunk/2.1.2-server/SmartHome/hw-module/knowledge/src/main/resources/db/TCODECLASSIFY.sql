create table TCODECLASSIFY
(
  type VARCHAR2(50) not null,
  name VARCHAR2(50)
)
;

alter table TCODECLASSIFY
  add constraint PK_TCODECLASSIFY_TYPE primary key (TYPE);