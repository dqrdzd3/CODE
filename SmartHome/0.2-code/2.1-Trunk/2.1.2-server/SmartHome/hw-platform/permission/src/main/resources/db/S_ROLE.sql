create table S_ROLE
(
  uuid        VARCHAR2(40) not null,
  role_name   VARCHAR2(60),
  role_code   VARCHAR2(30),
  user_type   VARCHAR2(3),
  organ_uuid  VARCHAR2(40),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE
)
;

alter table S_ROLE
  add constraint PK_S_ROLE_UUID primary key (UUID);