/*
 Navicat Premium Data Transfer

 Source Server         : TestMySql
 Source Server Type    : MySQL
 Source Server Version : 80015
 Source Host           : localhost:3306
 Source Schema         : vuedb

 Target Server Type    : MySQL
 Target Server Version : 80015
 File Encoding         : 65001

 Date: 02/11/2020 17:01:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for news
-- ----------------------------
DROP TABLE IF EXISTS `news`;
CREATE TABLE `news`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `context` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` int(11) NULL DEFAULT NULL,
  `img` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `add_time` timestamp(0) NULL DEFAULT NULL,
  `status` int(11) NULL DEFAULT NULL,
  `review_time` timestamp(0) NULL DEFAULT NULL,
  `review_user` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `news_tp`(`type`) USING BTREE,
  INDEX `review_u`(`review_user`) USING BTREE,
  CONSTRAINT `news_tp` FOREIGN KEY (`type`) REFERENCES `news_type` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `review_u` FOREIGN KEY (`review_user`) REFERENCES `user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of news
-- ----------------------------
INSERT INTO `news` VALUES (1, '疫情新闻', '', 1, NULL, '2020-11-01 16:29:54', 1, NULL, NULL);
INSERT INTO `news` VALUES (2, '教育新闻', NULL, 1, NULL, '2020-11-02 16:55:13', 2, NULL, 1);
INSERT INTO `news` VALUES (3, '山区支教', NULL, 2, NULL, '2020-11-02 11:19:42', 2, NULL, 1);
INSERT INTO `news` VALUES (4, '教育新闻', NULL, 1, NULL, '2020-11-04 16:42:31', 1, NULL, NULL);
INSERT INTO `news` VALUES (5, '北京新闻', NULL, 1, NULL, '2020-11-02 16:57:47', 1, NULL, NULL);
INSERT INTO `news` VALUES (6, '教育新闻', NULL, 1, NULL, '2020-07-02 16:42:49', 1, NULL, NULL);
INSERT INTO `news` VALUES (7, '山区支教', NULL, 2, NULL, '2020-05-02 16:42:55', 1, NULL, NULL);
INSERT INTO `news` VALUES (8, '国内新闻', NULL, 2, NULL, '2020-12-02 16:42:59', 1, NULL, NULL);
INSERT INTO `news` VALUES (9, '国外新闻', NULL, 2, NULL, '2020-11-24 16:43:02', 1, NULL, NULL);
INSERT INTO `news` VALUES (10, '红色新闻', NULL, 1, NULL, '2020-06-02 16:43:06', 1, NULL, NULL);
INSERT INTO `news` VALUES (11, '河南新闻', NULL, 1, NULL, '2019-11-02 16:43:09', 1, NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
