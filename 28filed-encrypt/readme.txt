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




分析
首先说说EnumTypeHandler与EnumOrdinalTypeHandler两者之间的区别吧：
EnumTypeHandler存入数据库的是枚举的name，EnumOrdinalTypeHandler存入数据库的是枚举的位置。例如下方的枚举，当我们有一个枚举值是EStatus.init时，
这时我们使用mybatis EnumTypeHandler存入数据库的是"init"字符串；而EnumOrdinalTypeHandler存入的是3,因为init是第四个值，第一个值disable的index是0。
    public enum EStatus {

        disable("0"), enable("1"), deleted("2"),
        init("10"), start("11"), wait("12"), end("13");

    }