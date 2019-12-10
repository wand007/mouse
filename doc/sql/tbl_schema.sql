drop database if exists mouse;
drop user if exists 'mouse'@'localhost';
-- 支持emoji：需要mysql数据库参数： character_set_server=utf8mb4
create database mouse default character set utf8mb4 collate utf8mb4_unicode_ci;
use mouse;
create user 'mouse'@'localhost' identified by 'litemall123456';
grant all privileges on mouse.* to 'mouse'@'localhost';
flush privileges;