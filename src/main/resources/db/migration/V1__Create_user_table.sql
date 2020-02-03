create table user
(
	id int auto_increment,
	name VARCHAR(50),
	account_id VARCHAR(100),
	token CHAR(36),
	gmt_create BIGINT,
	gmt_Modified BIGINT,
	constraint User_pk
		primary key (id)
);