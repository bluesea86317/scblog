drop table if exists t_comment ;
create table t_comment(
	id int(10) auto_increment,
	visitor varchar(30),
	email varchar(50),
	website varchar(200),
	comment varchar(300),
	articleId int(4),
	createTime datetime,
	followedId int(10),
	status int(2) default 0,
	primary key(id)
);
