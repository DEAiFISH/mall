/*
 Navicat Premium Dump SQL

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80040 (8.0.40)
 Source Host           : localhost:3306
 Source Schema         : mall_db

 Target Server Type    : MySQL
 Target Server Version : 80040 (8.0.40)
 File Encoding         : 65001

 Date: 13/01/2025 18:47:42
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for brand
-- ----------------------------
DROP TABLE IF EXISTS `brand`;
CREATE TABLE `brand`  (
  `brand_id` int NOT NULL AUTO_INCREMENT COMMENT '商品品牌ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图标路径',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌名称',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '品牌编码',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '专区大图路径',
  PRIMARY KEY (`brand_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品品牌表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of brand
-- ----------------------------
INSERT INTO `brand` VALUES (1, '2024-12-29 23:51:25', '2024-12-30 00:01:18', '任天堂（日语：任天堂／にんてんどう Nintendō */?）是一家主要从事电子游戏的开发、制造与发行的日本百年企业。于1889年在日本京都市创立，最初以生产花札（赌博用的手牌）起家，后续也尝试多种行业，1970年代后期投入电子游戏产业，在掌上游戏机“Game & Watch”与街机游戏《咚奇刚》获取商业成功后，娱乐事业也逐渐大众化而走上正途，于1983年推出家用游戏机“Family Computer”与在1985年推出游戏《超级马力欧兄弟》亦获取空前的成功，且旗下有多个著名IP，包括马力欧、塞尔达传说、星之卡比、宝可梦、动物森友会、皮克敏及斯普拉遁等，为世界知名的电子游戏主机与软件开发商，亦是世界目前游戏主机三大生产商之一。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/f0e78f07-94a0-4d87-b2ff-97526834cfc7-nitendo_icon.png', '任天堂', '1231231231', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/5572182f-8653-452d-8a39-a2e2f27afb7a-Nintendo_pic.png');
INSERT INTO `brand` VALUES (2, '2024-12-30 00:00:17', '2024-12-30 00:00:17', '卡普空有限公司（日语：株式会社カプコン，英语：Capcom Co., Ltd.，中国大陆官方译为“喀普康”，香港官方译为“嘉富康”，台湾官方译为“卡普空”），是一家日本的电子游戏开发商与发行商。创始于1979年，以日本街机制造商开始，发展成如今在日本、美国、欧洲、亚洲都设有事务所的国际公司。据官网所称，其目标是：“通过游戏创造‘游戏文化’，成为带给大众感动的‘感性开发企业’”。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/202c9c50-2803-45d2-8b61-bbfe61bf0a16-capcom_icon.png', '卡普空', '758578905', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/d29bca84-20e6-4784-a7c4-be73c90e5572-capcom_pic.png');
INSERT INTO `brand` VALUES (3, '2024-12-30 00:43:39', '2024-12-30 00:43:39', 'MDHR工作室娱乐公司（StudioMDHR Entertainment Inc. ），一般又被称为MDHR工作室（StudioMDHR），为摩登豪尔工作室（Studio Moldenhauer）的缩写。它是一家由两兄弟：查德与杰瑞德·摩登豪尔成立的独立游戏公司。他们完成的首部游戏：《茶杯头》，于2017年9月29日在Xbox One与PC端的Steam平台上首发。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/202c9c50-2803-45d2-8b61-bbfe61bf0a16-capcom_icon.png', 'MDHR工作室', '54398589342', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/brand/ee583c7c-963d-451c-8c5c-94da8c9353a5-studioMDHR_icon.png');

-- ----------------------------
-- Table structure for classify
-- ----------------------------
DROP TABLE IF EXISTS `classify`;
CREATE TABLE `classify`  (
  `classify_id` int NOT NULL AUTO_INCREMENT COMMENT '商品分类ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `icon` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图标路径',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类编码',
  `parent_id` int NULL DEFAULT NULL COMMENT '上一级分类ID',
  PRIMARY KEY (`classify_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品分类表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of classify
-- ----------------------------
INSERT INTO `classify` VALUES (1, '2024-12-30 00:54:31', '2024-12-30 00:54:31', '任天堂Switch（英语：Nintendo Switch，简称NS或Switch）是日本任天堂公司出品的电子游戏机[5][6]，于2017年3月3日在日本、北美、欧洲和香港发售[7]，同年12月1日在韩国和台湾发售，2019年12月10日在中国大陆发售[8]。拥有可拆卸控制器和可分体式主机，游戏载体使用了专用卡带。主机处理器使用基于英伟达 Tegra X1的定制系统芯片，这也是任天堂首次采用英伟达的芯片[3]，同时也是任天堂首次采用ARM架构芯片。开发期中的主机于2015年3月17日以“NX”代号首次公布，在2016年10月20日首次于在线视频上公开正式名称任天堂Switch和其造型[9]。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/classify/d41e1aca-776b-4f63-8c06-19d9562cb866-switch.png', 'switch', 'switch12312432', NULL);
INSERT INTO `classify` VALUES (2, '2024-12-30 00:55:26', '2024-12-30 00:58:11', 'PlayStation 5（官方缩写：PS5）是索尼互动娱乐于2020年11月12日上市的家用电子游戏机[2][5]。本机为PlayStation 4（PSadfadkn1242354234234234124313242342342344）的后续机型，在硬件上比前代PS4大幅提升，使用PCIe 4.0规格的高速定制固态盘和AMD的定制处理器。PS5随着Xbox Series X/S游戏机在同一个月推出，是第九世代游戏机之一。手柄控制器名为DualSense[3]，使用自适应扳机，支持阻力感应。光盘驱动器版本支持4K 蓝光播放功能。新的安装方式允许用户仅安装游戏部分内容，如仅安装多人模式；不启动游戏也可以查看可加入的多人游戏服务器和可参与的活动。PS5采用定制的AMD图形处理器，具有光线追踪、4K分辨率、每秒显示帧数达120、3D音效，且可向下兼容PS4和PlayStation VR的游戏。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/classify/9e05feb3-a1a4-4850-b421-4ac716ec621c-ps5.png', 'ps5', 'ps5KDKD5345564', NULL);
INSERT INTO `classify` VALUES (3, '2024-12-30 00:56:25', '2024-12-30 00:56:25', 'PlayStation 4（官方缩写：PS4）是索尼互动娱乐推出的家用电子游戏机，2013年11月15日于北美首度贩售。本机作为PlayStation 3的续作机型，于电子游戏史中为第八世代机种。', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/classify/d10dd758-5583-4158-b40e-cf12e0bc07c9-ps4.jpeg', 'ps4', 'ps4SSK5345564', NULL);
INSERT INTO `classify` VALUES (4, '2024-12-30 00:57:20', '2024-12-30 00:57:20', 'Xbox是微软创立的电子游戏品牌。它包括微软开发的从第六到第九世代的一系列电子游戏机，以及应用（游戏）、串流服务，以及在线服务Xbox Live。品牌于2001年11月15日在美国随着初代Xbox游戏机的首发而首次推出。Xbox系列目前由微软于2022年成立的微软游戏（Microsoft Gaming）部门持有', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/classify/7a96923c-e0a9-4c5d-82fd-52f39849294c-xbox.png', 'xbox', 'xboxSSK5345564', NULL);

-- ----------------------------
-- Table structure for homepage_carousel
-- ----------------------------
DROP TABLE IF EXISTS `homepage_carousel`;
CREATE TABLE `homepage_carousel`  (
  `chart_id` bigint NOT NULL AUTO_INCREMENT COMMENT '轮播图ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '图片路径',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`chart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '主页轮播图表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of homepage_carousel
-- ----------------------------

-- ----------------------------
-- Table structure for label
-- ----------------------------
DROP TABLE IF EXISTS `label`;
CREATE TABLE `label`  (
  `label_id` int NOT NULL AUTO_INCREMENT COMMENT '标签ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '名称',
  `weights` bigint NOT NULL COMMENT '权重',
  PRIMARY KEY (`label_id`) USING BTREE,
  UNIQUE INDEX `name`(`name` ASC) USING BTREE,
  UNIQUE INDEX `name_2`(`name` ASC) USING BTREE,
  UNIQUE INDEX `name_3`(`name` ASC) USING BTREE,
  UNIQUE INDEX `name_4`(`name` ASC) USING BTREE,
  UNIQUE INDEX `name_5`(`name` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '标签' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of label
-- ----------------------------
INSERT INTO `label` VALUES (1, '2024-12-30 00:59:17', '2024-12-30 00:59:17', 'duorenduizhan', '多人对战', 20);
INSERT INTO `label` VALUES (2, '2024-12-30 01:03:53', '2024-12-30 01:03:53', 'maoxian', '冒险', 22);
INSERT INTO `label` VALUES (3, '2024-12-30 01:04:09', '2024-12-30 01:04:09', 'jinsong', '惊悚', 10);
INSERT INTO `label` VALUES (4, '2024-12-30 01:04:29', '2024-12-30 01:04:29', 'danren', '单人', 33);

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `order_id` bigint NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `address_id` bigint NOT NULL COMMENT '地址ID',
  `amount` int NOT NULL COMMENT '商品数量',
  `cancel_reason` tinyint NULL DEFAULT NULL COMMENT '关闭原因（1-超时未支付 2-退款关闭 3-买家取消）',
  `cancel_time` datetime NULL DEFAULT NULL COMMENT '取消时间',
  `courier_number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '快递单号',
  `finish_time` datetime NULL DEFAULT NULL COMMENT '完成时间',
  `is_delete` tinyint NOT NULL DEFAULT 0 COMMENT '删除状态（0：没有删除，1：回收站，2：永久删除）',
  `is_pay` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否已经支付（TRUE：已支付，FALSE：未支付）',
  `memo` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '订单备注',
  `money` double NOT NULL COMMENT '订单金额',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '订单流水号',
  `parameter` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '特有规格参数（{属性：参数}）',
  `pay_time` datetime NULL DEFAULT NULL COMMENT '付款时间',
  `payment_method` tinyint NOT NULL COMMENT '支付方式（微信：0；支付宝：1）',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `ship_time` datetime NULL DEFAULT NULL COMMENT '发货时间',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`order_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '订单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of orders
-- ----------------------------

-- ----------------------------
-- Table structure for permission
-- ----------------------------
DROP TABLE IF EXISTS `permission`;
CREATE TABLE `permission`  (
  `permission_id` tinyint NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '权限名称',
  `user_id` bigint NOT NULL COMMENT '创建者 ID',
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of permission
-- ----------------------------
INSERT INTO `permission` VALUES (1, '2024-12-10 11:09:08', '2025-01-13 14:42:58', '添加商品', 'ADD_COM', 1);

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product`  (
  `product_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `brand_id` int NOT NULL COMMENT '商品品牌ID',
  `brief_description` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '简述',
  `classify_id` int NOT NULL COMMENT '商品分类ID',
  `cover_picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '封面图路径',
  `description` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '详细描述',
  `details_picture` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细图路径',
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `number` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品编码',
  `parameter` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '特有规格参数',
  `preferential_price` double NULL DEFAULT NULL COMMENT '优惠价格',
  `price` double NOT NULL DEFAULT 0 COMMENT '价格',
  `sale` int NOT NULL DEFAULT 0 COMMENT '销量',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态',
  PRIMARY KEY (`product_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES (1, '2024-12-30 01:17:51', '2024-12-30 01:28:35', 1, '《集合啦！动物森友会》（日版名：あつまれ どうぶつの森，英文版名：Animal Crossing: New Horizons）是一款由任天堂企划制作本部开发并由任天堂发行在任天堂Switch上的生活模拟游戏。本作是动物森友会系列的第7款作品，也是系列首次繁体中文化和第二次简体中文化[注 1]。游戏首次公布于2018年任天堂直面会，当时预计2019年发售，后来延期至2020年3月20日。', 1, 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/08c43f3f-2f54-4499-9e7c-cf49cadc8b7d-集合1.png', '《集合啦！动物森友会》是款生活模拟游戏[5]，玩家扮演的角色参与Nook Inc.推出的“无人岛移居计划”，打算展开全新的生活[6]。游戏一开始，玩家将设定角色姓名、生日及外观[7]，并在北半球、南半球共4座岛屿中选择移居的岛屿[5]，两者在季节上会有不同的变化[8]。到了无人岛，游戏角色狸克会让玩家自行搭建账篷，并要求玩家帮忙准备营火大会。活动结束后，狸克会带着请款单要求玩家缴清活动费用，玩家能选择“铃钱”和“里数”偿还债务，前者透过贩卖物品赚取，后者需要完成“Nook集里游”上的目标获得[5][7][9]。在游戏，玩家可以搜集树枝、石头、罐子等材料做出许多道具，使用“手册”可以学习更多道具的制作[10]。这些道具都有各自的功能，例如钓竿可以钓鱼[7]，玩家可以透过道具来捕捉各式鱼类、海洋生物、昆虫和采集化石[5]，捕获到的物品可以选择贩卖、制作家俱/料理、用以装饰或是捐赠给博物馆[9]。“Nook手机”是游戏的重要物品，玩家能从手机上的应用程序查看、使用各项功能[11]。点击“照相机”会进入拍照模式，玩家将按照个人喜好，使用各种滤镜效果及缩放功能进行拍摄，这些照片能在相簿中查看[12]。“Nook集里游”相当于成就系统，只要完成任务就能获取里数。里数可以兑换各种商品，例如服装和道具[13]。随着游戏的进行玩家将会成为“Nook集里游+”会员，会员可以解锁更多的背包空间和刷新岛屿资源的道具[14]。《集合啦！动物森友会》支持多人联机模式，与邻近主机或使用网络联机最多支持8名玩家。透过分享Joy-Con，最多支持4名玩家共同在同一台主机上游玩[15]。', '[\"https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/08c43f3f-2f54-4499-9e7c-cf49cadc8b7d-集合1.png\",\"https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/c0f600ca-132a-4429-bd0d-f15a5e028197-集合2.jpg\"]', '集合啦！动物森友会 Animal Crossing: New Horizons', '2132ijirf', '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}', 212.87, 403.42, 335555, 1);
INSERT INTO `product` VALUES (2, '2024-12-30 01:22:01', '2024-12-30 01:22:01', 1, '塞尔达传说 王国之泪》（日语：ゼルダの伝説 ティアーズ オブ ザ キングダム，英语：The Legend of Zelda: Tears of the Kingdom）是一款由任天堂企划制作本部开发并由任天堂发行在任天堂Switch上的开放世界动作冒险游戏[1][2][3]。本作于2019年E3直面会上正式公布，于2023年5月12日发售，发售后获得业界高度好评，多家媒体给予满分评价。', 1, 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/8bc968a5-6ed0-437c-9c52-086547f46f26-王泪1.jpg', '作为《旷野之息》的续作，本作沿用了前作大部分的游戏系统及玩法。玩家操纵主人公林克在与前作相同的海拉鲁大陆探索，可以奔跑、攀爬、游泳、滑翔，同样受到体力限制。前作中的物理、化学交互玩法、开放式解谜玩法得到了保留，武器、盾牌、弓的耐久度设定也得到了保留。前作中的神庙改名破魔神庙，玩家通过神庙内的挑战后可以获得祝福之光，四个祝福之光可兑换一个心之容器或精力容器，提升玩家的生命值或精力值的上限。前作中数量庞大的“克洛格的果实”亦得到沿用，玩家在地图中解开小谜题，获得果实，提升武器、盾牌、弓的数量上限。前作中林克的特殊能力被全数删除，替换成四项全新的主要能力：“究极手”允许玩家移动、旋转、粘合游戏中的物体；“余料建造”允许玩家将游戏中的物品组合到自己的武器与盾、箭上，从而增加武器和盾牌的攻击力和耐久度，也可以赋予它们新特性；“通天术”允许玩家向上穿越自己头顶的天花板和岩壁等障碍物；“倒转乾坤”允许玩家改变物体的运动轨迹，使之沿其运动轨迹倒流。游戏亦搭载“蓝图”功能，允许玩家快速制作出最近利用究极手制作过的道具。游戏亦提供内建设计图。本作引入全新的左纳乌文明，提供风扇机、轮胎、操纵杆等左纳乌装置供玩家使用、建造。玩家使用左纳乌装置受电池限制，电池用尽则所有装置都会关闭，必须等待电量自然恢复。玩家可以提升电池的容量。本作的舞台从海拉鲁大陆的地表延伸到了天空和地底。天空中散布着空岛供玩家探索，地底则提供大量资源供玩家开采。玩家通过地表的深穴可以进入地底，地底大部分区域一片漆黑，玩家需要依赖特定物品照亮环境。玩家可以开启位于地底的破魔之根来永久照亮地图的部分区域。地底区域遍布瘴气和被瘴气污染的敌人，接触瘴气区域或遭被瘴气污染的敌人攻击会扣除玩家的生命值上限。玩家抵达破魔之根或返回地上可恢复生命值上限。', '[\"https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/8bc968a5-6ed0-437c-9c52-086547f46f26-王泪1.jpg\"]', '塞尔达传说：王国之泪 The Legend of Zelda: Tears of the Kingdom ￼', 'zelda11111', '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}', 255.74, 469.24, 212222, 1);
INSERT INTO `product` VALUES (3, '2025-01-08 21:56:01', '2025-01-08 21:56:01', 1, '《塞尔达传说 旷野之息》（日语：ゼルダの伝説 ブレス オブ ザ ワイルド，英语：The Legend of Zelda: Breath of the Wild，香港和台湾译作“萨尔达传说 旷野之息”，副标题又译作“荒野之息”）是一款动作冒险游戏，由任天堂企划制作本部开发，任天堂于2017年发行在任天堂Switch和Wii U主机，为塞尔达传说系列游戏的第十八部。玩家在游戏中操纵主人公林克在世界中探索，从而击败灾厄盖侬，恢复被灭亡的海拉鲁王国。', 1, 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/fb92e6c8-e24a-4521-ba0d-3245654b8aa4-旷野.png', '与1986年最初的《塞尔达传说》游戏类似，玩家得到的指导很少，可以自由探索世界。任务包括收集各种物品和装备，以帮助解谜或辅助任务。游戏中的世界是无序的，旨在鼓励探索和实验，主要的故事任务可以非线性的方式完成。《塞尔达传说 旷野之息》最初于2013年公布，开发历时五年。为了重新思考该系列的惯例，任天堂引入了开放世界和详细的物理引擎等元素。以开放世界异度神剑系列的工作而闻名的Monolith Soft公司协助设计景观和地形。该游戏最初计划在2015年作为Wii U独占游戏发布，但经历了两度延期。最终本作在任天堂Switch和Wii U双平台同时推出，并在2017年3月3日正式发行。本作除了是任天堂Switch的首发游戏之一，同时也是任天堂为Wii U开发的最后一款游戏[5]。在整个2017年，有两波可下载内容（DLC）以扩展通行证的方式发布。游戏于2018年2月1日在台湾与香港发行实体中文版，在韩国发行实体韩文版，已购入其他版本的玩家也可通过补丁更新获得对繁体中文、简体中文和韩文的支持[3][4]。《塞尔达传说 旷野之息》因其开放式的游戏方式和对细节的关注而获得了业界的极高评价，多家媒体给予本作满分。评论家称它是开放世界游戏的一个里程碑，《塞尔达传说 旷野之息》甚至被认为是有史以来最伟大的电子游戏之一。《塞尔达传说 旷野之息》还获得了多个年度游戏奖项，最知名的是2017年游戏大奖（The Game Awards，TGA）的年度游戏奖。截至2024年6月底，《塞尔达传说 旷野之息》已经售出近3200万份，成为史上最畅销的电子游戏之一。', '[\"https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/product/fb92e6c8-e24a-4521-ba0d-3245654b8aa4-旷野.png\"]', '塞尔达传说：旷野之息 The Legend of Zelda: Breath of the Wild ￼', 'zelda22123', '{\"key1\":\"value1\",\"key2\":\"value2\",\"key3\":\"value3\"}', 235.98, 469.24, 280000, 1);

-- ----------------------------
-- Table structure for product_collect
-- ----------------------------
DROP TABLE IF EXISTS `product_collect`;
CREATE TABLE `product_collect`  (
  `collect_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品收藏ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片路径',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`collect_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品收藏表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_collect
-- ----------------------------

-- ----------------------------
-- Table structure for product_evaluation
-- ----------------------------
DROP TABLE IF EXISTS `product_evaluation`;
CREATE TABLE `product_evaluation`  (
  `evaluation_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品评价ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `content` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '评价内容',
  `is_anonymous` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否匿名',
  `is_reply` tinyint(1) NOT NULL DEFAULT 0 COMMENT '是否回复',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '图片路径',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `reply` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '商家回复',
  `star` tinyint NOT NULL COMMENT '评分',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`evaluation_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品评价' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_evaluation
-- ----------------------------

-- ----------------------------
-- Table structure for product_label
-- ----------------------------
DROP TABLE IF EXISTS `product_label`;
CREATE TABLE `product_label`  (
  `product_label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '商品标签表ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `label_id` int NOT NULL COMMENT '标签ID',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  PRIMARY KEY (`product_label_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '商品标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of product_label
-- ----------------------------
INSERT INTO `product_label` VALUES (1, '2024-12-30 01:33:59', '2024-12-30 01:33:59', 2, 1);
INSERT INTO `product_label` VALUES (2, '2024-12-30 01:34:03', '2024-12-30 01:34:03', 1, 1);
INSERT INTO `product_label` VALUES (3, '2024-12-30 01:34:12', '2024-12-30 01:34:12', 2, 2);
INSERT INTO `product_label` VALUES (4, '2024-12-30 01:34:23', '2024-12-30 01:34:23', 4, 2);

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role`  (
  `role_id` tinyint NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `description` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '描述',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '角色名称',
  `user_id` bigint NOT NULL COMMENT '创建者 ID',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES (1, '2024-12-10 11:08:19', '2025-01-13 14:42:50', '管理员', 'ADMIN', 1);
INSERT INTO `role` VALUES (2, '2024-12-10 11:08:19', '2025-01-13 14:42:50', '用户', 'CUSTOMER', 1);

-- ----------------------------
-- Table structure for role_permission
-- ----------------------------
DROP TABLE IF EXISTS `role_permission`;
CREATE TABLE `role_permission`  (
  `role_permission_id` tinyint NOT NULL AUTO_INCREMENT COMMENT '角色权限id',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `permission_id` tinyint NOT NULL COMMENT '权限id',
  `role_id` tinyint NOT NULL COMMENT '角色id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  PRIMARY KEY (`role_permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '角色权限表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of role_permission
-- ----------------------------
INSERT INTO `role_permission` VALUES (1, '2024-12-10 11:09:28', '2025-01-13 14:42:41', 1, 1, 1);

-- ----------------------------
-- Table structure for search_keyword
-- ----------------------------
DROP TABLE IF EXISTS `search_keyword`;
CREATE TABLE `search_keyword`  (
  `search_keyword_id` bigint NOT NULL AUTO_INCREMENT COMMENT '搜索关键词ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `amount` bigint NOT NULL COMMENT '搜索次数',
  `content` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '关键词内容',
  PRIMARY KEY (`search_keyword_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '搜索关键词表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of search_keyword
-- ----------------------------

-- ----------------------------
-- Table structure for shipping_address
-- ----------------------------
DROP TABLE IF EXISTS `shipping_address`;
CREATE TABLE `shipping_address`  (
  `address_id` bigint NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `area` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '区/县',
  `city` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '市',
  `full` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '详细地址',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '收货人名字',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '电话',
  `province` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '省',
  `street` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '街道',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`address_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '收货地址表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of shipping_address
-- ----------------------------

-- ----------------------------
-- Table structure for stock
-- ----------------------------
DROP TABLE IF EXISTS `stock`;
CREATE TABLE `stock`  (
  `stock_id` bigint NOT NULL AUTO_INCREMENT COMMENT '库存ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `amount` int NOT NULL DEFAULT 0 COMMENT '库存量',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `warning_amount` int NOT NULL DEFAULT 0 COMMENT '警报库存量',
  PRIMARY KEY (`stock_id`) USING BTREE,
  UNIQUE INDEX `product_id`(`product_id` ASC) USING BTREE,
  UNIQUE INDEX `UKkhabtqwr86p7x9mt2krib98tx`(`product_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '库存表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of stock
-- ----------------------------
INSERT INTO `stock` VALUES (1, '2025-01-13 13:04:16', '2025-01-13 13:04:16', 100, 1, 10);
INSERT INTO `stock` VALUES (2, '2025-01-13 13:04:20', '2025-01-13 13:04:20', 100, 2, 10);
INSERT INTO `stock` VALUES (3, '2025-01-13 13:04:23', '2025-01-13 13:04:23', 100, 3, 10);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `avatar` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/avatar/default-user-photo.png' COMMENT '头像路径',
  `birthday` datetime NULL DEFAULT NULL COMMENT '生日',
  `email` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint NULL DEFAULT 2 COMMENT '性别（男：1；女：0；未知：2）',
  `integral` int NOT NULL DEFAULT 0 COMMENT '用户积分',
  `last_login` datetime NOT NULL COMMENT '最后一次登录时间',
  `message` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '个性签名',
  `nick_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '用户名',
  `password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '密码',
  `payment_password` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '支付密码',
  `phone` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '电话号',
  `real_name` varchar(16) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '真实姓名',
  `role_id` tinyint NOT NULL COMMENT '角色ID',
  `status` tinyint NOT NULL DEFAULT 1 COMMENT '状态（启用：1；禁用：0）',
  `wx_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '微信ID',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `wx_id`(`wx_id` ASC) USING BTREE,
  UNIQUE INDEX `wx_id_2`(`wx_id` ASC) USING BTREE,
  UNIQUE INDEX `wx_id_3`(`wx_id` ASC) USING BTREE,
  UNIQUE INDEX `wx_id_4`(`wx_id` ASC) USING BTREE,
  UNIQUE INDEX `wx_id_5`(`wx_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '2024-12-10 11:11:21', '2025-01-13 18:46:49', 'https://mall-j-bucket.oss-cn-hangzhou.aliyuncs.com/avatar/default-user-photo.png', '2002-11-15 00:00:00', 'DEAiFISH@Outlook.com', 1, 0, '2025-01-13 18:46:50', 'message', 'cxx', '$2a$10$meb6UZ8xq6ZlwEejOSjb6O8d2fZt3OIBugqTsarjUD4X0A28RGGbK', '$2a$10$IPA59VvP7ZR22OpIkUtIgeIgPixNAA8ZGAmksSxfx.xpW2pgR7H32', '18111457903', '晁祥翔', 1, 1, 'wx_id');

-- ----------------------------
-- Table structure for user_browse_history
-- ----------------------------
DROP TABLE IF EXISTS `user_browse_history`;
CREATE TABLE `user_browse_history`  (
  `history_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户浏览记录ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `picture` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品图片路径',
  `product_id` bigint NOT NULL COMMENT '商品ID',
  `product_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '商品名称',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`history_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户浏览历史' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_browse_history
-- ----------------------------

-- ----------------------------
-- Table structure for user_label
-- ----------------------------
DROP TABLE IF EXISTS `user_label`;
CREATE TABLE `user_label`  (
  `user_label_id` bigint NOT NULL AUTO_INCREMENT COMMENT '用户标签表ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `interest` bigint NOT NULL COMMENT '兴趣度',
  `label_id` int NOT NULL COMMENT '标签ID',
  `user_id` bigint NOT NULL COMMENT '用户ID',
  PRIMARY KEY (`user_label_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '用户标签表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user_label
-- ----------------------------

-- ----------------------------
-- Table structure for voucher
-- ----------------------------
DROP TABLE IF EXISTS `voucher`;
CREATE TABLE `voucher`  (
  `voucher_id` bigint NOT NULL AUTO_INCREMENT COMMENT '优惠卷ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `amount` int NOT NULL COMMENT '优惠卷余量',
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL COMMENT '优惠卷描述',
  `name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '优惠卷名称',
  `price` double NOT NULL COMMENT '优惠金额',
  PRIMARY KEY (`voucher_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci COMMENT = '优惠卷表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of voucher
-- ----------------------------

SET FOREIGN_KEY_CHECKS = 1;
