create table S_BUSI_MODULE
(
  uuid          VARCHAR2(40) not null,
  menu_name     VARCHAR2(30),
  menu_code     VARCHAR2(60),
  parent_uuid   VARCHAR2(40),
  url           VARCHAR2(200),
  user_type     VARCHAR2(3),
  is_valid      VARCHAR2(2),
  menu_type     VARCHAR2(2),
  remark        VARCHAR2(200),
  ordernum      NUMBER(4),
  creator       VARCHAR2(40),
  create_date   DATE,
  editor        VARCHAR2(40),
  modified_date DATE,
  is_show       VARCHAR2(2)
)
;

alter table S_BUSI_MODULE
  add constraint PK_S_BUSI_MODULE primary key (UUID);