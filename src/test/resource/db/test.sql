CREATE DATABASE mytest;

CREATE TABLE t_user(
  user_id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
  user_name VARCHAR(255) NOT NULL ,
  password VARCHAR(255) NOT NULL ,
  mobile VARCHAR(255) NOT NULL
) ENGINE=INNODB DEFAULT CHARSET=utf8;

insert into t_user values(5, 'qian', '555', '13691156270');