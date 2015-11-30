create table S_ROLE_PERM
(
  uuid        VARCHAR2(40) not null,
  role_uuid   VARCHAR2(40),
  module_uuid VARCHAR2(40),
  opera_uuid  VARCHAR2(40),
  organ_uuid  VARCHAR2(40),
  creator     VARCHAR2(40),
  create_date DATE
)
;

alter table S_ROLE_PERM
  add constraint PK_S_ROLE_PERM_UUID primary key (UUID);