drop table if exists t_article;
create table t_article(
	id int(4) not null auto_increment,
	title varchar(100) not null,
	intro text,
	content mediumtext,
	authorId int(4),
	createTime datetime,
	lastModifyTime datetime,
	articleType int(4),
	primary key(id)	
)AUTO_INCREMENT=1;
