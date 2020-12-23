/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : meals

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 23/12/2020 15:46:20
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_auth`;
CREATE TABLE `sys_auth`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `authKey` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权KEY',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '授权名称',
  `level` smallint(6) NULL DEFAULT NULL COMMENT '权限等级（当前类型下的等级）',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '父级ID（生成树）',
  `status` smallint(6) NOT NULL DEFAULT 1 COMMENT '权限状态 1启用 2锁定 3删除 锁定和删除涉及异步刷新引用此权限及所有子权限的数据',
  `type` smallint(255) NULL DEFAULT NULL COMMENT '权限类型 1菜单 2按钮/接口',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '权限集表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_auth
-- ----------------------------
INSERT INTO `sys_auth` VALUES (1, 'SYS', '系统管理', 1, -1, 1, 1);
INSERT INTO `sys_auth` VALUES (2, 'SYS-USER', '用户管理', 2, 1, 1, 1);
INSERT INTO `sys_auth` VALUES (3, 'SYS-USER-V', '查看用户', 1, 2, 1, 2);
INSERT INTO `sys_auth` VALUES (4, 'SYS-USER-C', '创建用户', 1, 2, 1, 2);
INSERT INTO `sys_auth` VALUES (5, 'SYS-USER-M', '更新用户', 1, 2, 1, 2);
INSERT INTO `sys_auth` VALUES (6, 'SYS-USER-D', '删除用户', 1, 2, 1, 2);
INSERT INTO `sys_auth` VALUES (7, 'SYS-USER-P', '个人资料管理', 1, 2, 1, 2);
INSERT INTO `sys_auth` VALUES (8, 'SYS-ROLE', '角色管理', 2, 1, 1, 1);
INSERT INTO `sys_auth` VALUES (9, 'SYS-ROLE-V', '查看角色', 1, 8, 1, 2);
INSERT INTO `sys_auth` VALUES (10, 'SYS-ROLE-C', '创建角色', 1, 8, 1, 2);
INSERT INTO `sys_auth` VALUES (11, 'SYS-ROLE-M', '更新角色', 1, 8, 1, 2);
INSERT INTO `sys_auth` VALUES (12, 'SYS-ROLE-D', '删除角色', 1, 8, 1, 2);
INSERT INTO `sys_auth` VALUES (13, 'SYS-AUTH', '权限集管理', 2, 1, 1, 1);
INSERT INTO `sys_auth` VALUES (14, 'SYS-AUTH-V', '查看权限集', 1, 13, 1, 2);
INSERT INTO `sys_auth` VALUES (15, 'SYS-AUTH-P', '个人权限集', 1, 13, 1, 2);
INSERT INTO `sys_auth` VALUES (16, 'DEV', '开发者能力', 1, -1, 1, 1);
INSERT INTO `sys_auth` VALUES (17, 'DEV-CODE', '代码生成', 2, 16, 1, 1);
INSERT INTO `sys_auth` VALUES (18, 'DEV-CODE-M', '代码生成操作', 1, 17, 1, 2);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '字典ID',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '字典名称',
  `value` smallint(6) NULL DEFAULT NULL COMMENT '字典值（必须配置为数字）',
  `ex_key` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '拓展KEY（为某些特殊场景补充字符串value）',
  `type` int(11) NULL DEFAULT NULL COMMENT '字典类型（请将此值配置常量）',
  `level` smallint(6) NULL DEFAULT NULL COMMENT '字段等级',
  `pid` bigint(20) NULL DEFAULT NULL COMMENT '默认为0 反之则为父级ID',
  `status` smallint(255) NULL DEFAULT NULL COMMENT '字典启用状态 1启用 2废弃',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `type`(`type`) USING BTREE COMMENT '常用索引类型'
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公共字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `remark` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '备注',
  `role_key` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色KEY（与前端保持一致）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 'admin');
INSERT INTO `sys_role` VALUES (2, '开发者', 'dev', 'dev');

-- ----------------------------
-- Table structure for sys_role_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_auth`;
CREATE TABLE `sys_role_auth`  (
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  `aid` bigint(20) NULL DEFAULT NULL COMMENT '授权ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '角色授权清单' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_auth
-- ----------------------------
INSERT INTO `sys_role_auth` VALUES (3, 1);
INSERT INTO `sys_role_auth` VALUES (3, 2);
INSERT INTO `sys_role_auth` VALUES (3, 3);
INSERT INTO `sys_role_auth` VALUES (3, 4);
INSERT INTO `sys_role_auth` VALUES (3, 5);
INSERT INTO `sys_role_auth` VALUES (3, 6);
INSERT INTO `sys_role_auth` VALUES (3, 7);
INSERT INTO `sys_role_auth` VALUES (3, 8);
INSERT INTO `sys_role_auth` VALUES (3, 9);
INSERT INTO `sys_role_auth` VALUES (3, 10);
INSERT INTO `sys_role_auth` VALUES (3, 11);
INSERT INTO `sys_role_auth` VALUES (3, 12);
INSERT INTO `sys_role_auth` VALUES (3, 13);
INSERT INTO `sys_role_auth` VALUES (3, 14);
INSERT INTO `sys_role_auth` VALUES (3, 15);
INSERT INTO `sys_role_auth` VALUES (4, 16);
INSERT INTO `sys_role_auth` VALUES (4, 17);
INSERT INTO `sys_role_auth` VALUES (4, 18);
INSERT INTO `sys_role_auth` VALUES (2, 16);
INSERT INTO `sys_role_auth` VALUES (2, 17);
INSERT INTO `sys_role_auth` VALUES (2, 18);
INSERT INTO `sys_role_auth` VALUES (1, 1);
INSERT INTO `sys_role_auth` VALUES (1, 2);
INSERT INTO `sys_role_auth` VALUES (1, 3);
INSERT INTO `sys_role_auth` VALUES (1, 4);
INSERT INTO `sys_role_auth` VALUES (1, 5);
INSERT INTO `sys_role_auth` VALUES (1, 6);
INSERT INTO `sys_role_auth` VALUES (1, 7);
INSERT INTO `sys_role_auth` VALUES (1, 8);
INSERT INTO `sys_role_auth` VALUES (1, 9);
INSERT INTO `sys_role_auth` VALUES (1, 10);
INSERT INTO `sys_role_auth` VALUES (1, 11);
INSERT INTO `sys_role_auth` VALUES (1, 12);
INSERT INTO `sys_role_auth` VALUES (1, 13);
INSERT INTO `sys_role_auth` VALUES (1, 14);
INSERT INTO `sys_role_auth` VALUES (1, 15);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '账号',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码（密文）',
  `salt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '加密盐值',
  `status` smallint(6) NOT NULL DEFAULT 1 COMMENT '用户状态',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `name`(`name`) USING BTREE COMMENT '账号唯一'
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 'admin', '0415DA408052AC695D1E9AFC81B62A0BB0849DBB3A0CDE780E112149AF3EA5B8', '48e84c90-b754-422f-a475-ebe22f0a291e', 1);
INSERT INTO `sys_user` VALUES (2, 'test', 'D7766436B5E64E19334519C737B419C5D5BFF447633A8CD14EFB21283F90CCF6', '56f8f687-574f-492c-b5a0-e0f3207a2e7a', 1);

-- ----------------------------
-- Table structure for sys_user_auth
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_auth`;
CREATE TABLE `sys_user_auth`  (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `aid` bigint(20) NULL DEFAULT NULL COMMENT '授权ID'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户授权清单（为了方便查询，用户授权收据会直接生成好）' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_auth
-- ----------------------------
INSERT INTO `sys_user_auth` VALUES (1, 1);
INSERT INTO `sys_user_auth` VALUES (1, 2);
INSERT INTO `sys_user_auth` VALUES (1, 3);
INSERT INTO `sys_user_auth` VALUES (1, 4);
INSERT INTO `sys_user_auth` VALUES (1, 5);
INSERT INTO `sys_user_auth` VALUES (1, 6);
INSERT INTO `sys_user_auth` VALUES (1, 7);
INSERT INTO `sys_user_auth` VALUES (1, 8);
INSERT INTO `sys_user_auth` VALUES (1, 9);
INSERT INTO `sys_user_auth` VALUES (1, 10);
INSERT INTO `sys_user_auth` VALUES (1, 11);
INSERT INTO `sys_user_auth` VALUES (1, 12);
INSERT INTO `sys_user_auth` VALUES (1, 13);
INSERT INTO `sys_user_auth` VALUES (1, 14);
INSERT INTO `sys_user_auth` VALUES (1, 15);
INSERT INTO `sys_user_auth` VALUES (1, 16);
INSERT INTO `sys_user_auth` VALUES (1, 17);
INSERT INTO `sys_user_auth` VALUES (1, 18);
INSERT INTO `sys_user_auth` VALUES (2, 1);
INSERT INTO `sys_user_auth` VALUES (2, 2);
INSERT INTO `sys_user_auth` VALUES (2, 3);
INSERT INTO `sys_user_auth` VALUES (2, 4);
INSERT INTO `sys_user_auth` VALUES (2, 5);
INSERT INTO `sys_user_auth` VALUES (2, 6);
INSERT INTO `sys_user_auth` VALUES (2, 7);
INSERT INTO `sys_user_auth` VALUES (2, 8);
INSERT INTO `sys_user_auth` VALUES (2, 9);
INSERT INTO `sys_user_auth` VALUES (2, 10);
INSERT INTO `sys_user_auth` VALUES (2, 11);
INSERT INTO `sys_user_auth` VALUES (2, 12);
INSERT INTO `sys_user_auth` VALUES (2, 13);
INSERT INTO `sys_user_auth` VALUES (2, 14);
INSERT INTO `sys_user_auth` VALUES (2, 15);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `uid` bigint(20) NOT NULL COMMENT '用户ID',
  `rid` bigint(20) NOT NULL COMMENT '角色ID',
  UNIQUE INDEX `user_role`(`uid`, `rid`) USING BTREE,
  INDEX `user_ix`(`uid`) USING BTREE,
  INDEX `role_ix`(`rid`) USING BTREE,
  CONSTRAINT `user_i` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (2, 1);

-- ----------------------------
-- View structure for user_role_str
-- ----------------------------
DROP VIEW IF EXISTS `user_role_str`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_role_str` AS (select `uri`.`uid` AS `uid`,group_concat(`uri`.`name` separator '/') AS `role_str` from (select `r`.`name` AS `name`,`ur`.`uid` AS `uid` from (`meals`.`sys_user_role` `ur` left join `meals`.`sys_role` `r` on((`ur`.`rid` = `r`.`id`)))) `uri` group by `uri`.`uid`);

SET FOREIGN_KEY_CHECKS = 1;
