create database double_z;
use double_z;

-- 创建账户表 dz_user
create table dz_user (
	id int(10) not null auto_increment COMMENT '账户编号',
    role_id int(10) NOT NULL COMMENT '角色编号',
    username varchar(100) NOT NULL COMMENT '用户名',
    password varchar(100) NOT NULL COMMENT '密码',
    email varchar(100) NOT NULL COMMENT '邮箱',
    phone varchar(100) NOT NULL  COMMENT '手机号码',
    status int(5) NOT NULL COMMENT '账户状态',
	avatar varchar(500) NOT NULL DEFAULT '/images/avatar/avatar-1.jpg' COMMENT '头像',
    -- login_date datetime NOT NULL COMMENT '登录时间',
    create_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (id)
) DEFAULT CHARSET=utf8;

insert into dz_user(id,role_id,username,password,email,phone,status) values(1,1,'admin','123456','1762063213@qq.com','17728698128',0)