create table sys_account (
  id varchar(32)  comment '账户ID',
  user_id bigint comment '账户关联的用户ID'
  username varchar(100) comment '账户名',
  password varchar(100) comment '账户密码',
  salt varchar(100) comment '盐',
  locked bool default false comment '是否锁定',
  disabled bool default false comment '是否禁用',
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
  constraint pk_sys_account primary key(id)
) charset=utf8 ENGINE=InnoDB;
create unique index idx_sys_account_username on sys_account(username);

create table sys_user(
  id varchar(32) comment '用户Id',
  organization_id bigint comment '用户部门',
  name varchar(32) comment '用户姓名',
  job varchar(32) comment '职业',
  birthday DATETIME comment '生日',
  address varchar(64) comment '地址',
  education_background comment '教育背景',
  id_number varchar(32) comment '用户身份证号',
  mobile varchar(32) comment '用户电话',
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
  constraint pk_sys_user primary key(id)
)charset=utf8 ENGINE=InnoDB;
create index idx_sys_user_organization_id on sys_user(organization_id);

create table sys_organization (
  id varchar(32),
  name varchar(100),
  parent_id bigint,
  available bool default true,
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
  constraint pk_sys_organization primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_organization_parent_id on sys_organization(parent_id);

create table sys_account_role(
  id varchar(32) comment 'ID',
  account_id varchar(32) comment '账户Id',
  role_id varchar(32) comment '角色ID',
  available bool default true,
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
 constraint pk_sys_account_role primary key(account_id,role_id)
)charset=utf8 ENGINE=InnoDB;
create index idx_sys_account_role_id on sys_account_role(account_id,role_id);

create table sys_resource (
  id bigint auto_increment,
  name varchar(100),
  type varchar(50),
  url varchar(200),
  parent_id bigint,
  permission varchar(100),
  available bool default false,
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
  constraint pk_sys_resource primary key(id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_resource_parent_id on sys_resource(parent_id);

create table sys_role_resource (
  id varchar(32),
  role_id varchar(32),
  resource_id varchar(32),
  available bool default true,
  updateTime DATETIME comment'更新时间',
  create_time TIMESTAMP default  CURRENT_TIMESTAMP comment '创建时间',
  constraint pk_sys_role_resource primary key(role_id,resource_id)
) charset=utf8 ENGINE=InnoDB;
create index idx_sys_role_resource_id on sys_role_resource(role_id,resource_id);


