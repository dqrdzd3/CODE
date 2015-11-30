create table S_BUSI_RESOURCE
(
  uuid              VARCHAR2(40) not null,
  resource_name     VARCHAR2(40),
  resource_type     VARCHAR2(2),
  module_opera_uuid VARCHAR2(40),
  url               VARCHAR2(200),
  is_default        VARCHAR2(1),
  creator           VARCHAR2(40),
  create_date       DATE,
  bind_func         VARCHAR2(200)
)
;

alter table S_BUSI_RESOURCE
  add constraint PK_S_BUSI_RESOURCE primary key (UUID);