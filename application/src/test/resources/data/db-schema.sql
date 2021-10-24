DROP TABLE IF EXISTS user;

CREATE TABLE `user`(
   `id` BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '自增主键',
   `user_id` VARCHAR(32) NOT NULL COMMENT '用戶Id',
   `user_name` VARCHAR(32) NOT NULL COMMENT '用户名',
   `nick_name` VARCHAR(32) NOT NULL COMMENT '昵称',
   `email` VARCHAR(32) NOT NULL COMMENT '邮箱',
   `valid` INT UNSIGNED NOT NULL COMMENT '是否有效：1=有效',
   `ctime` INT UNSIGNED NOT NULL COMMENT '创建时间',
   `utime` INT UNSIGNED NOT NULL COMMENT '更新时间',
   PRIMARY KEY (`id`),
   UNIQUE KEY `idx_user_id` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户表';