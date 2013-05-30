drop table if exists t_user;
create table t_user(
	id int(4) not null AUTO_INCREMENT ,
	userName varchar(20) not null,
	password varchar(50) not null,
	nickName varchar(30),
	mobile varchar(15),
	email varchar(50),
	primary key(id)
)
