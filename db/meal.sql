/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : meal

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 10/12/2020 14:39:40
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

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
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '管理员', 'admin', 'admin');
INSERT INTO `sys_role` VALUES (2, '开发者', 'dev', 'dev');
INSERT INTO `sys_role` VALUES (3, '测试者', 'test', 'test');

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
INSERT INTO `sys_user` VALUES (2, 'test', 'F9AE44187C88B860742D431F4A64CDEE5AA6F448A9D170BD7E09690A08246129', 'cb4ea7e5-724a-40b6-8660-9dc0833b743e', 1);

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
  CONSTRAINT `role_i` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `user_i` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1);
INSERT INTO `sys_user_role` VALUES (1, 2);
INSERT INTO `sys_user_role` VALUES (1, 3);
INSERT INTO `sys_user_role` VALUES (2, 3);

-- ----------------------------
-- View structure for user_role_str(快速查询用户的角色名清单 效果：管理员/开发者/测试者)
-- ----------------------------
DROP VIEW IF EXISTS `user_role_str`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `user_role_str` AS (select `uri`.`uid` AS `uid`,group_concat(`uri`.`name` separator '/') AS `role_str` from (select `r`.`name` AS `name`,`ur`.`uid` AS `uid` from (`meal`.`sys_user_role` `ur` left join `meal`.`sys_role` `r` on((`ur`.`rid` = `r`.`id`)))) `uri` group by `uri`.`uid`);

SET FOREIGN_KEY_CHECKS = 1;
