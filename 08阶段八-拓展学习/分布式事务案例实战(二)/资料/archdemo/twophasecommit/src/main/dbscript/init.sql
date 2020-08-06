create  DATABASE archdemo1;
use archdemo1;
/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50717
Source Host           : localhost:3306
Source Database       : archdemo1

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2019-04-21 19:27:10
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for order
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT ',amount,product',
  `orderid` varchar(32) DEFAULT NULL,
  `amount` float DEFAULT NULL COMMENT 'orderid',
  `product` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('1', '00001', '3000', '苹果笔记本');
INSERT INTO `order` VALUES ('2', '00002', '3000', '苹果笔记本');
INSERT INTO `order` VALUES ('3', '00003', '3000', '苹果笔记本');
INSERT INTO `order` VALUES ('4', '00004', '3000', '苹果笔记本ss');
INSERT INTO `order` VALUES ('5', '00005', '3000', '苹果笔记本ss');
INSERT INTO `order` VALUES ('6', null, null, null);
INSERT INTO `order` VALUES ('7', null, null, null);
INSERT INTO `order` VALUES ('8', '00002', '3000', '苹果笔记本');




create  DATABASE archdemo2;
use archdemo2;
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for shipping
-- ----------------------------
DROP TABLE IF EXISTS `shipping`;
CREATE TABLE `shipping` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `orderid` varchar(32) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of shipping
-- ----------------------------
INSERT INTO `shipping` VALUES ('1', '00001', '北京黄浦江畔');
INSERT INTO `shipping` VALUES ('2', '00002', '上海市黄浦江畔');
INSERT INTO `shipping` VALUES ('3', '00003', '上海市黄浦江畔');
