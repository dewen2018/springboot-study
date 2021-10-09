CREATE DATABASE IF NOT EXISTS `m_db01`;
USE `m_db01`;

CREATE DATABASE IF NOT EXISTS `s_db01`;
USE `s_db01`;

CREATE DATABASE IF NOT EXISTS `m_db02`;
USE `m_db02`;

CREATE DATABASE IF NOT EXISTS `s_db02`;
USE `s_db02`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods0
-- ----------------------------
DROP TABLE IF EXISTS `goods0`;
CREATE TABLE `goods0` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(32) DEFAULT NULL,
  `money` decimal(6,2) DEFAULT NULL,
  `stock` int(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1610612745 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods0
-- ----------------------------
INSERT INTO `goods0` VALUES ('-173056000', 'Java编程思想 第0版', '0.00', '20', '2020-04-10 14:46:38');
INSERT INTO `goods0` VALUES ('-173055998', 'Java编程思想 第2版', '0.20', '22', '2020-04-10 14:46:38');
INSERT INTO `goods0` VALUES ('-173055996', 'Java编程思想 第4版', '0.40', '24', '2020-04-10 14:46:38');
INSERT INTO `goods0` VALUES ('-173055994', 'Java编程思想 第6版', '0.60', '26', '2020-04-10 14:46:38');
INSERT INTO `goods0` VALUES ('-173055992', 'Java编程思想 第8版', '0.80', '28', '2020-04-10 14:46:38');

-- ----------------------------
-- Table structure for goods1
-- ----------------------------
DROP TABLE IF EXISTS `goods1`;
CREATE TABLE `goods1` (
  `id` int(6) NOT NULL AUTO_INCREMENT,
  `goods_name` varchar(32) DEFAULT NULL,
  `money` decimal(6,2) DEFAULT NULL,
  `stock` int(4) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of goods1
-- ----------------------------

-- ----------------------------
-- Table structure for t_dict
-- ----------------------------
DROP TABLE IF EXISTS `t_dict`;
CREATE TABLE `t_dict` (
  `id` int(3) NOT NULL,
  `dict_name` varchar(32) DEFAULT NULL,
  `dict_value` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- ----------------------------
-- Records of t_dict
-- ----------------------------
INSERT INTO `t_dict` VALUES ('-34643968', '男', 'man');
INSERT INTO `t_dict` VALUES ('997154816', '男', 'man');
INSERT INTO `t_dict` VALUES ('1122983936', '男', 'man');





CREATE TABLE `t_order0` (
  `id` int(11) NOT NULL,
  `order_no` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_order1` (
  `id` int(11) NOT NULL,
  `order_no` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_order_item0` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `good_name` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE `t_order_item1` (
  `id` int(11) NOT NULL,
  `order_id` int(11) DEFAULT NULL,
  `good_name` varchar(45) DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

set sql_safe_updates = 0;
delete from t_order0;
delete from t_order1;
delete from t_order_item0;
delete from t_order_item1;
