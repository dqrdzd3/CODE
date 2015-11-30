create table S_USER
(
  uuid                 VARCHAR2(40) not null,
  login_name           VARCHAR2(20),
  password             VARCHAR2(64),
  real_name            VARCHAR2(20),
  age                  NUMBER(2),
  sex                  VARCHAR2(1),
  mobile_number        VARCHAR2(20),
  email                VARCHAR2(30),
  id_card              VARCHAR2(25),
  user_type            VARCHAR2(3),
  is_admin             VARCHAR2(1),
  depart_uuid          VARCHAR2(40),
  depart_code          VARCHAR2(40),
  gov_uuid             VARCHAR2(40),
  user_status          VARCHAR2(2),
  employee_num         VARCHAR2(40),
  last_access_ip       VARCHAR2(39),
  last_access_datetime DATE,
  creator              VARCHAR2(40),
  create_date          DATE,
  editor               VARCHAR2(40),
  modified_date        DATE,
  organ_uuid           VARCHAR2(40),
  mobile_device_access VARCHAR2(1),
  outer_net_access     VARCHAR2(1),
  ext1                 VARCHAR2(20),
  ext2                 VARCHAR2(20),
  ext3                 VARCHAR2(20)
)
;

alter table S_USER
  add constraint PK_S_USER_UUID primary key (UUID);