/*
SQLyog Ultimate v11.25 (64 bit)
MySQL - 5.6.21-log 
*********************************************************************
*/
/*!40101 SET NAMES utf8 */;

create table `t_user` (
	`id` int (11),
	`name` varchar (765),
	`age` int (11),
	`edu` varchar (765),
	`gender` varchar (765)
); 
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('1','tom','20','大学','男');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('2','jerry','19','大学','男');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('3','jack','22','大专','男');
insert into `t_user` (`id`, `name`, `age`, `edu`, `gender`) values('4','rose','24','大专','女');
