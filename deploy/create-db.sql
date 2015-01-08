DROP DATABASE IF EXISTS `shop`;
CREATE DATABASE `shop` default charset=utf8 ;
use `shop`;


delete from mysql.user where User = 'shop_user';
grant select,update,delete,insert on `shop`.* to 'shop_user'@'%' identified by 'shop_pwd';
grant select,update,delete,insert on `shop`.* to 'shop_user'@'localhost' identified by 'shop_pwd';
flush privileges;