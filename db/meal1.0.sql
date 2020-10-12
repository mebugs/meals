/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/*==============================================================*/

/*==============================================================*/
/* Table: sys_dept                                              */
/*==============================================================*/
create table sys_dept
(
   id                   bigint not null auto_increment comment '部门ID',
   name                 varchar(32) comment '部门名',
   role                 bigint comment '部门权限',
   primary key (id)
);

alter table sys_dept comment '系统部门表';

/*==============================================================*/
/* Table: sys_role                                              */
/*==============================================================*/
create table sys_role
(
   id                   bigint not null auto_increment comment '角色ID',
   name                 varchar(32) comment '角色名',
   remark               text comment '备注',
   primary key (id)
);

/*==============================================================*/
/* Table: sys_user                                              */
/*==============================================================*/
create table sys_user
(
   id                   bigint not null auto_increment comment '用户ID',
   name                 varchar(32) comment '账号',
   password             varchar(64) comment '密码（密文）',
   salt                 varchar(32) comment '加密盐值',
   status               smallint comment '用户状态',
   primary key (id)
);

alter table sys_user comment '系统用户表';

/*==============================================================*/
/* Index: user_name_status                                      */
/*==============================================================*/
create unique index user_name_status on sys_user
(
   name,
   status
);

/*==============================================================*/
/* Table: sys_user_dept                                         */
/*==============================================================*/
create table sys_user_dept
(
   uid                  bigint not null comment '用户ID',
   did                  bigint not null comment '部门ID'
);

alter table sys_user_dept comment '系统用户部门表';

/*==============================================================*/
/* Index: user_dept                                             */
/*==============================================================*/
create unique index user_dept on sys_user_dept
(
   uid,
   did
);

/*==============================================================*/
/* Table: sys_user_role                                         */
/*==============================================================*/
create table sys_user_role
(
   uid                  bigint not null comment '用户ID',
   rid                  bigint not null comment '角色ID'
);

alter table sys_user_role comment '系统用户角色表';

/*==============================================================*/
/* Index: user_role                                             */
/*==============================================================*/
create unique index user_role on sys_user_role
(
   uid,
   rid
);

