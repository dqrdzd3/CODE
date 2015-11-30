create table S_DEPT_ROLE
(
  uuid        VARCHAR2(40) not null,
  depart_uuid VARCHAR2(40),
  role_uuid   VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;

alter table S_DEPT_ROLE
  add constraint PK_S_DEPT_ROLE_UUID primary key (UUID);