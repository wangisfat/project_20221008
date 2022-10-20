/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 80028
 Source Host           : localhost:3306
 Source Schema         : wangl20220927

 Target Server Type    : MySQL
 Target Server Version : 80028
 File Encoding         : 65001

 Date: 08/10/2022 09:44:33
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_config
-- ----------------------------
DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`  (
                               `id` bigint(0) NOT NULL AUTO_INCREMENT,
                               `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                               `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改时间',
                               `config_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典编码',
                               `config_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '字典名称',
                               PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1164 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统数据字典' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_config_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_config_item`;
CREATE TABLE `sys_config_item`  (
                                    `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                    `gmt_create` datetime(0) DEFAULT NULL COMMENT '创建时间',
                                    `gmt_modified` datetime(0) DEFAULT NULL COMMENT '修改时间',
                                    `config_id` bigint(0) DEFAULT NULL COMMENT '配置编码',
                                    `item_code` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '数据字典id',
                                    `item_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置项名称',
                                    `item_value` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '配置项描述',
                                    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 355 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统数据字典配置项' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org`  (
                            `id` bigint(0) NOT NULL AUTO_INCREMENT,
                            `gmt_create` datetime(0) DEFAULT NULL,
                            `gmt_modified` datetime(0) DEFAULT NULL,
                            `org_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL,
                            `org_type` char(2) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '01: 机构/单位 02：部门 03：组织节点',
                            `parent_id` bigint(0) DEFAULT NULL,
                            `display_order` int(0) DEFAULT NULL,
                            `org_source_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_unicode_ci DEFAULT NULL COMMENT '组织唯一标识，来源外部系统',
                            `is_enabled` tinyint(1) DEFAULT NULL,
                            `is_deleted` tinyint(1) DEFAULT NULL,
                            `org_source_type` tinyint(1) DEFAULT NULL COMMENT '数据来源类型',
                            PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 271 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_org_user_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_org_user_rel`;
CREATE TABLE `sys_org_user_rel`  (
                                     `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                     `gmt_create` datetime(0) DEFAULT NULL,
                                     `gmt_modified` datetime(0) DEFAULT NULL,
                                     `org_id` bigint(0) DEFAULT NULL,
                                     `user_id` bigint(0) DEFAULT NULL,
                                     `display_order` int(0) DEFAULT NULL,
                                     `is_main_org` tinyint(1) DEFAULT NULL COMMENT '0-挂职部门，1-主职部门',
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 104 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT,
                             `gmt_create` datetime(0) DEFAULT NULL,
                             `gmt_modified` datetime(0) DEFAULT NULL,
                             `role_code` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                             `role_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                             `role_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '01: 管理角色 02：业务角色',
                             `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
                             `is_deleted` tinyint(1) DEFAULT NULL,
                             `scope_type` tinyint(1) DEFAULT NULL COMMENT '是否层级角色',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
                             `id` bigint(0) NOT NULL AUTO_INCREMENT,
                             `gmt_create` datetime(0) DEFAULT NULL,
                             `gmt_modified` datetime(0) DEFAULT NULL,
                             `user_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '姓名',
                             `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '账号名',
                             `initial_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '初始密码',
                             `login_password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '登陆密码',
                             `last_change_password_time` datetime(0) DEFAULT NULL COMMENT '最后一次修改密码时间',
                             `mobile` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '手机号',
                             `display_order` int(0) DEFAULT NULL COMMENT '排序号',
                             `email` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '邮箱',
                             `avatar` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像URL',
                             `remark` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '备注',
                             `is_enabled` tinyint(1) DEFAULT NULL COMMENT '是否启用',
                             `is_deleted` tinyint(1) DEFAULT NULL COMMENT '是否删除',
                             `user_source_type` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户来源',
                             `user_source_id` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '外部来源用户id',
                             `user_level` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '用户等级',
                             `user_status` char(2) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '人员状态（01-未激活 02-已锁定 03-正常）',
                             `unlock_time` datetime(0) DEFAULT NULL COMMENT '解锁时间',
                             PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 109 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_user_role_rel
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role_rel`;
CREATE TABLE `sys_user_role_rel`  (
                                      `id` bigint(0) NOT NULL AUTO_INCREMENT,
                                      `gmt_create` datetime(0) DEFAULT NULL,
                                      `gmt_modified` datetime(0) DEFAULT NULL,
                                      `user_id` bigint(0) DEFAULT NULL,
                                      `role_id` bigint(0) DEFAULT NULL,
                                      PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 157 CHARACTER SET = utf8 COLLATE = utf8_unicode_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
