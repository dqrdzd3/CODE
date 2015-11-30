create table S_DEPARTMENT
(
  uuid        VARCHAR2(40) not null,
  depart_name VARCHAR2(30),
  depart_code VARCHAR2(20),
  parent_uuid VARCHAR2(40),
  organ_uuid  VARCHAR2(40),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE
)
;

alter table S_DEPARTMENT
  add constraint PK_S_DEPARTMENT_UUID primary key (UUID);