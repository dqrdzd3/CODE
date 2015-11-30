create table S_USER_ROLE
(
  uuid        VARCHAR2(40) not null,
  user_uuid   VARCHAR2(40),
  role_uuid   VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;

alter table S_USER_ROLE
  add constraint PK_S_USER_ROLE_UUID primary key (UUID);