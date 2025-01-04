CREATE DATABASE IF NOT EXISTS `mall_db`;
USE `mall_db`;

CREATE TABLE role
(
    role_id     TINYINT                                                        NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    name        VARCHAR(32)                                                    NOT NULL COMMENT '名称',
    description VARCHAR(128) COMMENT '描述',
    user_id     BIGINT                                                         NOT NULL COMMENT '创建者ID',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (role_id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='角色表';

CREATE TABLE permission
(
    permission_id TINYINT                                                        NOT NULL AUTO_INCREMENT COMMENT '权限ID',
    name          VARCHAR(32)                                                    NOT NULL COMMENT '名称',
    description   VARCHAR(128) COMMENT '描述',
    user_id       BIGINT                                                         NOT NULL COMMENT '创建者ID',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (permission_id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限表';

CREATE TABLE role_permission
(
    role_permission_id TINYINT                                                        NOT NULL AUTO_INCREMENT COMMENT '权限角色表ID',
    role_id            TINYINT                                                        NOT NULL COMMENT '角色ID',
    permission_id      TINYINT                                                        NOT NULL COMMENT '权限ID',
    user_id            BIGINT                                                         NOT NULL COMMENT '创建者ID',
    create_time        DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time        DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (role_permission_id),
    CONSTRAINT FOREIGN KEY (role_id) REFERENCES ROLE (role_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (permission_id) REFERENCES PERMISSION (permission_id),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='权限角色表';

CREATE TABLE user
(
    user_id          BIGINT                                                             NOT NULL AUTO_INCREMENT COMMENT '用户ID',
    wx_id            VARCHAR(32) UNIQUE COMMENT '微信号',
    nick_name        VARCHAR(32)                                                        NOT NULL COMMENT '用户名',
    real_name        VARCHAR(16) COMMENT '真实姓名',
    gender           TINYINT      DEFAULT 2 COMMENT '性别（男：1；女：0；未知：2）',
    birthday         DATETIME COMMENT '生日（例如：2024/6/4）',
    avatar           VARCHAR(256) DEFAULT 'default-user-photo.png' COMMENT '头像路径',
    phone            VARCHAR(16) COMMENT '手机号',
    message          VARCHAR(128) COMMENT '个性签名',
    password         VARCHAR(32)                                                        NOT NULL COMMENT '登录密码',
    payment_password VARCHAR(6) COMMENT '支付密码',
    email            VARCHAR(32) COMMENT '邮箱',
    last_login       DATETIME                                                           NOT NULL COMMENT '最后一次登录时间',
    status           TINYINT                                                            NOT NULL COMMENT '状态（启用：1；禁用：0）',
    integral         INT          DEFAULT 0 COMMENT '用户积分',
    role_id          TINYINT COMMENT '角色ID',
    create_time      DATETIME     DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time      DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (user_id),
    CONSTRAINT FOREIGN KEY (role_id) REFERENCES ROLE (role_id)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户表';

CREATE TABLE user_browse_history
(
    history_id     BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户浏览记录ID',
    user_id        BIGINT                                                         NOT NULL COMMENT '用户ID',
    product_id     BIGINT                                                         NOT NULL COMMENT '商品ID',
    `product_name` varchar(32)                                                    NOT NULL COMMENT '商品名称',
    `picture`      VARCHAR(256)                                                   NOT NULL COMMENT '商品图片路径',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户浏览记录表';

CREATE TABLE label
(
    label_id    INT AUTO_INCREMENT PRIMARY KEY COMMENT '标签ID',
    name        VARCHAR(32)                                                    NOT NULL UNIQUE COMMENT '名称',
    weights     BIGINT                                                         NOT NULL COMMENT '权重',
    description VARCHAR(512) COMMENT '描述',
    create_time DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='标签表';

CREATE TABLE user_label
(
    user_label_id BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '用户标签表ID',
    user_id       BIGINT                                                         NOT NULL COMMENT '用户ID',
    label_id      INT                                                            NOT NULL COMMENT '标签ID',
    interest      Long                                                           NOT NULL COMMENT '兴趣度',
    create_time   DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time   DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (label_id) REFERENCES label (label_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='用户标签表';

CREATE TABLE `product_collect`
(
    `collect_id`   BIGINT                                                         NOT NULL AUTO_INCREMENT COMMENT '商品收藏ID',
    `user_id`      BIGINT                                                         NOT NULL COMMENT '用户ID',
    `product_id`   BIGINT                                                         NOT NULL COMMENT '商品ID',
    `product_name` varchar(32)                                                    NOT NULL COMMENT '商品名称',
    `picture`      VARCHAR(256)                                                   NOT NULL COMMENT '商品图片路径',
    `create_time`  DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    `update_time`  DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`collect_id`),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
) COMMENT ='商品收藏表';

CREATE TABLE `shipping_address`
(
    `address_id`  BIGINT                                                         NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
    `user_id`     BIGINT                                                         NOT NULL COMMENT '用户ID',
    `name`        VARCHAR(32)                                                    NOT NULL COMMENT '收货人名字',
    `province`    VARCHAR(16)                                                    NOT NULL COMMENT '省',
    `city`        VARCHAR(16)                                                    NOT NULL COMMENT '市',
    `area`        VARCHAR(16)                                                    NOT NULL COMMENT '区/县',
    `street`      VARCHAR(32)                                                    NOT NULL COMMENT '街道',
    `full`        VARCHAR(32)                                                    NOT NULL COMMENT '详细地址',
    `phone`       VARCHAR(16)                                                    NOT NULL COMMENT '电话',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    `update_time` DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`address_id`),
    CONSTRAINT FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='收货地址表';

CREATE TABLE classify
(
    classify_id INT AUTO_INCREMENT PRIMARY KEY COMMENT '商品分类ID',
    parent_id   INT          DEFAULT NULL COMMENT '上一级分类ID',
    name        VARCHAR(32)                                                        NOT NULL COMMENT '名称',
    number      VARCHAR(32)                                                        NOT NULL COMMENT '分类编码',
    description VARCHAR(512) DEFAULT NULL COMMENT '描述',
    icon        VARCHAR(256)                                                       NOT NULL COMMENT '图标路径',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    CONSTRAINT FOREIGN KEY (parent_id) REFERENCES classify (classify_id) ON DELETE SET NULL
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品分类表';

CREATE TABLE brand
(
    brand_id    INT AUTO_INCREMENT PRIMARY KEY COMMENT '商品品牌ID',
    number      VARCHAR(32)                                                        NOT NULL COMMENT '品牌编码',
    name        VARCHAR(32)                                                        NOT NULL COMMENT '品牌名称',
    icon        VARCHAR(256)                                                       NOT NULL COMMENT '图标路径',
    picture     VARCHAR(256)                                                       NOT NULL COMMENT '专区大图路径',
    description VARCHAR(512) DEFAULT NULL COMMENT '描述',
    create_time DATETIME     DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间'
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品品牌表';

CREATE TABLE product
(
    product_id         BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '商品ID',
    number             VARCHAR(32)   NOT NULL COMMENT '商品编码',
    name               VARCHAR(128)  NOT NULL COMMENT '商品名称',
    classify_id        INT           NOT NULL COMMENT '商品分类ID',
    brand_id           INT           NOT NULL COMMENT '商品品牌ID',
    price              DOUBLE        NOT NULL COMMENT '价格',
    preferential_price DOUBLE                 DEFAULT NULL COMMENT '优惠价格',
    parameter          VARCHAR(512) COMMENT '特有规格参数（JSON格式：{属性：参数}）',
    sale               INT           NOT NULL DEFAULT 0 COMMENT '销量',
    brief_description  VARCHAR(512)  NOT NULL COMMENT '简述',
    description        VARCHAR(1024)          DEFAULT NULL COMMENT '详细描述',
    status             TINYINT       NOT NULL DEFAULT 1 COMMENT '状态（1: 上架, 0: 下架）',
    cover_picture      VARCHAR(256)  NOT NULL COMMENT '封面图路径',
    details_picture    VARCHAR(1024) NOT NULL COMMENT '详细图路径',
    create_time        DATETIME               DEFAULT CURRENT_TIMESTAMP NOT NULL COMMENT '创建时间',
    update_time        DATETIME               DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    FOREIGN KEY (classify_id) REFERENCES classify (classify_id) ON DELETE CASCADE,
    FOREIGN KEY (brand_id) REFERENCES brand (brand_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品表';

CREATE TABLE stock
(
    stock_id       BIGINT AUTO_INCREMENT COMMENT '库存ID',
    product_id     BIGINT                                                         NOT NULL UNIQUE COMMENT '商品ID',
    amount         INT                                                            NOT NULL COMMENT '库存量',
    warning_amount INT                                                            NOT NULL COMMENT '警报库存量',
    create_time    DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time    DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (stock_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE
) COMMENT = '库存表';

CREATE TABLE product_label
(
    product_label_id BIGINT AUTO_INCREMENT COMMENT '商品标签表ID',
    product_id       BIGINT                                                         NOT NULL COMMENT '商品ID',
    label_id         INT                                                            NOT NULL COMMENT '标签ID',
    create_time      DATETIME DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    update_time      DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (product_label_id),
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    FOREIGN KEY (label_id) REFERENCES label (label_id) ON DELETE CASCADE
) COMMENT = '商品标签表';

CREATE TABLE `product_evaluation`
(
    `evaluation_id` BIGINT                                                             NOT NULL AUTO_INCREMENT COMMENT '商品评价ID',
    `product_id`    BIGINT                                                             NOT NULL COMMENT '商品ID',
    `user_id`       BIGINT                                                             NOT NULL COMMENT '用户ID',
    `content`       VARCHAR(512)                                                       NOT NULL COMMENT '内容',
    `reply`         VARCHAR(512) DEFAULT NULL COMMENT '商家回复',
    `is_reply`      BOOLEAN                                                            NOT NULL COMMENT '是否回复',
    `star`          TINYINT                                                            NOT NULL COMMENT '评分',
    `picture`       VARCHAR(256) DEFAULT NULL COMMENT '图片路径',
    `is_anonymous`  BOOLEAN                                                            NOT NULL COMMENT '是否匿名',
    `create_time`   DATETIME     DEFAULT CURRENT_TIMESTAMP                             NOT NULL COMMENT '创建时间',
    `update_time`   DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP NOT NULL COMMENT '修改时间',
    PRIMARY KEY (`evaluation_id`),
    KEY `idx_product_user` (`product_id`, `user_id`) -- 联合索引
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='商品评价表';

CREATE TABLE `orders`
(
    `order_id`       BIGINT                 NOT NULL AUTO_INCREMENT COMMENT '订单ID',
    `user_id`        BIGINT                 NOT NULL COMMENT '用户ID',
    `product_id`     BIGINT                 NOT NULL COMMENT '商品ID',
    `parameter`      VARCHAR(512) COMMENT '特有规格参数（json{属性：参数}）',
    `number`         VARCHAR(32)            NOT NULL COMMENT '订单流水号',
    `money`          DOUBLE                 NOT NULL COMMENT '订单金额',
    `payment_method` TINYINT                NOT NULL COMMENT '支付方式（微信：0；支付宝：1）',
    `memo`           VARCHAR(128) DEFAULT NULL COMMENT '订单备注',
    `status`         TINYINT                NOT NULL COMMENT '状态（1:待付款 2:待发货 3:待收货 4:待评价 5:成功 6:失败）',
    `address_id`     BIGINT                 NOT NULL COMMENT '地址ID',
    `courier_number` VARCHAR(32)  DEFAULT NULL COMMENT '快递单号',
    `amount`         INT                    NOT NULL COMMENT '商品数量',
    `pay_time`       DATETIME     DEFAULT NULL COMMENT '付款时间',
    `ship_time`      DATETIME     DEFAULT NULL COMMENT '发货时间',
    `finish_time`    DATETIME     DEFAULT NULL COMMENT '完成时间',
    `cancel_time`    DATETIME     DEFAULT NULL COMMENT '取消时间',
    `is_pay`         BOOLEAN                NOT NULL COMMENT '是否已经支付（1：已支付，0：未支付）',
    `is_delete`      TINYINT      DEFAULT 0 NOT NULL COMMENT '删除状态（0：没有删除， 1：回收站， 2：永久删除）',
    `cancel_reason`  TINYINT      DEFAULT NULL COMMENT '关闭原因（1-超时未支付 2-退款关闭 3-买家取消）',
    `create_time`    DATETIME     DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time`    DATETIME     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
    PRIMARY KEY (`order_id`),
    CONSTRAINT FOREIGN KEY (user_id) REFERENCES user (user_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE,
    CONSTRAINT FOREIGN KEY (address_id) REFERENCES shipping_address (address_id) ON DELETE CASCADE
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4 COMMENT ='订单表';

