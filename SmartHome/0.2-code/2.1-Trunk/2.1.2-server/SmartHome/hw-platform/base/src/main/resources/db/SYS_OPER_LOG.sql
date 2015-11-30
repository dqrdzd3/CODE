create table SYS_OPER_LOG
(
  id             NVARCHAR2(36) not null,
  code           NVARCHAR2(20),
  type           NVARCHAR2(20),
  operator_id    NVARCHAR2(36),
  operator_name  NVARCHAR2(50),
  recording_time DATE,
  class_name     NVARCHAR2(50),
  method_name    NVARCHAR2(50),
  content        NVARCHAR2(500),
  ip             NVARCHAR2(15)
)
  pctfree 10
  initrans 1
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

alter table SYS_OPER_LOG
  add constraint OPER_LOG_ID primary key (ID)
  using index 
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 64K
    next 1M
    minextents 1
    maxextents unlimited
  );

insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('b981df35-4fd0-40d8-9b85-47527263d765', '0000000001', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:47:56', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('e2227cf8-8319-48ef-8b30-5f788fcae90e', '0000000003', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:00', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('1b71002a-a8f7-4a6c-8e13-1a8c2130afa3', '0000000004', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:01', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('ef39b295-7fd5-4a69-a51b-a6eb4036a18d', '0000000005', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:02', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('aa8269d3-f380-4438-81fa-b061b30ebac3', '0000000006', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:03', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('51a94ae7-e52e-43d9-b8b3-e2af7ff9c6b9', '0000000007', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:04', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('7aa03a0f-0d48-488e-9acd-d60f7667ca72', '0000000008', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:04', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('4517d112-1126-46dd-afdb-674a3b8fa08a', '0000000009', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:05', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('2bbbfa57-8eae-4ff4-bc8c-55c0a5f41144', '0000000010', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:48:07', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
insert into SYS_OPER_LOG (id, code, type, operator_id, operator_name, recording_time, class_name, method_name, content)
values ('01f1138a-86d4-4771-a556-3fdc10ac31f3', '0000000002', null, 'fc2bab65-3bcb-4bf8-a71c-2f3b71d2ed85', '超级系统管理员', to_date('02-04-2013 15:47:58', 'dd-mm-yyyy hh24:mi:ss'), 'com.hw.hwsafe.gov.action.C002Action', 'doLoadTree', '马宁测试');
commit;