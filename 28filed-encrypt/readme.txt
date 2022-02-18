CREATE TABLE `customer` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `phone` varchar(64) DEFAULT NULL COMMENT '手机号',
  `address` varchar(200) DEFAULT NULL COMMENT '地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='客户表';

ALTER TABLE `customer`
ADD COLUMN `state` INT(1) NULL AFTER `address`;



http://localhost:8080/addCustomer?phone=13800138000&address=地址地址
http://localhost:8080/findCustomer?phone=13800138000