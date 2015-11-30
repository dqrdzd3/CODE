create table S_BUSI_OPERATION
(
  uuid        VARCHAR2(40) not null,
  action_name VARCHAR2(40),
  action_code VARCHAR2(10),
  style_name  VARCHAR2(30),
  url         VARCHAR2(200),
  is_valid    VARCHAR2(2),
  remark      VARCHAR2(200),
  creator     VARCHAR2(40),
  create_date DATE,
  menu_uuid   VARCHAR2(40),
  bind_func   VARCHAR2(200),
  ordernum    NUMBER(4)
)
;

alter table S_BUSI_OPERATION
  add constraint PK_S_BUSI_OPERATION primary key (UUID);