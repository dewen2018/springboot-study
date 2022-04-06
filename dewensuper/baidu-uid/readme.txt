UidGenerator是Java实现的, 基于Snowflake算法的唯一ID生成器。
UidGenerator以组件形式工作在应用项目中, 支持自定义workerId位数和初始化策略,
从而适用于docker等虚拟化环境下实例自动重启、漂移等场景。
 在实现上, UidGenerator通过借用未来时间来解决sequence天然存在的并发限制;
  采用RingBuffer来缓存已生成的UID, 并行化UID的生产和消费, 同时对CacheLine补齐，
  避免了由RingBuffer带来的硬件级「伪共享」问题. 最终单机QPS可达600万。
DROP TABLE IF EXISTS WORKER_NODE;
CREATE TABLE WORKER_NODE
(
ID BIGINT NOT NULL AUTO_INCREMENT COMMENT 'auto increment id',
HOST_NAME VARCHAR(64) NOT NULL COMMENT 'host name',
PORT VARCHAR(64) NOT NULL COMMENT 'port',
TYPE INT NOT NULL COMMENT 'node type: ACTUAL or CONTAINER',
LAUNCH_DATE DATE NOT NULL COMMENT 'launch date',
MODIFIED DATETIME NOT NULL COMMENT 'modified time',
CREATED DATETIME NOT NULL COMMENT 'created time',
PRIMARY KEY(ID)
)
 COMMENT='DB WorkerID Assigner for UID Generator',ENGINE = INNODB;