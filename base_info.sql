-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        8.0.19 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  10.3.0.5771
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- 导出 base_info 的数据库结构
CREATE DATABASE IF NOT EXISTS `base_info` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `base_info`;

-- 导出  表 base_info.student_info 结构
CREATE TABLE IF NOT EXISTS `student_info` (
  `id` bigint NOT NULL DEFAULT '0' COMMENT '序号',
  `uid` bigint NOT NULL DEFAULT '0' COMMENT 'user表的id',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `type` varchar(50) NOT NULL DEFAULT '0' COMMENT '学生类别',
  `number` bigint unsigned NOT NULL DEFAULT '0' COMMENT '学号',
  `major` varchar(255) DEFAULT NULL COMMENT '专业',
  `class_name` varchar(255) DEFAULT NULL COMMENT '班级',
  `sex` char(10) DEFAULT NULL COMMENT '性别',
  `master` varchar(255) DEFAULT NULL COMMENT '导师/班主任',
  `instructor` varchar(255) DEFAULT NULL COMMENT '辅导员',
  `native_place` varchar(255) DEFAULT NULL COMMENT '籍贯',
  `student_origin` varchar(255) DEFAULT NULL COMMENT '生源地',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族',
  `religion` varchar(255) DEFAULT NULL COMMENT '宗教信仰',
  `politics_status` varchar(255) DEFAULT NULL COMMENT '政治面貌',
  `dormitory` varchar(255) DEFAULT NULL COMMENT '宿舍号',
  `phone_number` bigint DEFAULT NULL COMMENT '电话号码',
  `id_number` varchar(50) DEFAULT NULL COMMENT '身份证号',
  `emergency_contact` varchar(255) DEFAULT NULL COMMENT '紧急联系人',
  `emergency_phone` varchar(255) DEFAULT NULL COMMENT '紧急联系人电话',
  `ewards_punishments` text COMMENT '奖惩情况',
  `mental_state` text COMMENT '心理状况',
  `tutoring_case` text COMMENT '辅导情况',
  `remark` text COMMENT '备注',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='学生基本信息表';

-- 数据导出被取消选择。

-- 导出  表 base_info.user_info 结构
CREATE TABLE IF NOT EXISTS `user_info` (
  `id` bigint NOT NULL DEFAULT '0',
  `user_name` varchar(50) NOT NULL DEFAULT '0',
  `password` varchar(50) NOT NULL DEFAULT '0',
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表';

-- 数据导出被取消选择。

-- 导出  表 base_info.user_token 结构
CREATE TABLE IF NOT EXISTS `user_token` (
  `id` bigint unsigned NOT NULL AUTO_INCREMENT,
  `gmt_create` datetime DEFAULT CURRENT_TIMESTAMP,
  `gmt_modified` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `uid` bigint DEFAULT NULL COMMENT 'user表对应的id',
  `token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=929318296739553281 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- 数据导出被取消选择。

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
