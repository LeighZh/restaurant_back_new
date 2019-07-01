DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `loginName` VARCHAR(20) DEFAULT NULL COMMENT '登陆名',
  `loginPwd` VARCHAR(20) DEFAULT NULL COMMENT '密码',
  `trueName` VARCHAR(20) DEFAULT NULL COMMENT '真实姓名',
  `email` VARCHAR(20) DEFAULT NULL COMMENT '电子邮件',
  `phone` VARCHAR(15) DEFAULT NULL COMMENT '电话',
  `address` VARCHAR(50) DEFAULT NULL COMMENT '收货地址',
  `createTime` TIMESTAMP DEFAULT NOW() COMMENT '创建时间',
  `money` DOUBLE DEFAULT NULL COMMENT '消费总金额',
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='用户表';

/*Data for the table `users` */

INSERT  INTO `users`(`loginName`,`loginPwd`) VALUES ('123456','123456'),('1','1');






DROP TABLE IF EXISTS `admin`;

CREATE TABLE `admin` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `loginName` VARCHAR(20) DEFAULT NULL COMMENT '登陆名',
  `loginPwd` VARCHAR(20) DEFAULT NULL COMMENT '密码',
  `role` VARCHAR(20) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY  (`id`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='管理员表';

/*Data for the table `mealseries` */

INSERT  INTO `admin`(`loginName`,`loginPwd`) VALUES ('admin','123456');






DROP TABLE IF EXISTS `mealseries`;

CREATE TABLE `mealseries` (
  `seriesId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '菜系编号',
  `seriesName` VARCHAR(10) DEFAULT NULL COMMENT '菜系名称',
  PRIMARY KEY  (`seriesId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='菜系表';

/*Data for the table `mealseries` */

INSERT  INTO `mealseries`(`seriesId`,`seriesName`) VALUES (1,'鲁菜'),(2,'川菜'),(3,'粤菜'),(4,'苏菜'),(5,'闽菜'),(6,'浙菜'),(7,'湘菜'),(8,'徽菜'),(9,'西餐'),(10,'西点'),(11,'药膳'),(12,'私房菜');






CREATE TABLE `meals` (
  `mealId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '餐品编号',
  `mealSeriesId` INT(11) DEFAULT NULL COMMENT '菜系编号',
  `mealName` VARCHAR(20) DEFAULT NULL COMMENT '餐品名称',
  `mealSummarize` VARCHAR(20) DEFAULT NULL COMMENT '餐品摘要',
  `mealDescription` VARCHAR(100) DEFAULT NULL COMMENT '餐品说明',
  `mealPrice` VARCHAR(15) DEFAULT NULL COMMENT '餐品价格',
  `mealImage` VARCHAR(50) DEFAULT NULL COMMENT '餐品图片保存地址',
  PRIMARY KEY  (`mealId`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='菜品表';

/*Data for the table `mealseries` */

INSERT  INTO `meals`(`mealSeriesId`,`mealName`) VALUES ('2','2'),('1','1');






DROP TABLE IF EXISTS `shoppingCart`;

CREATE TABLE `shoppingCart` (
  `cartId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '购物车编号',
  `userId` INT(11) DEFAULT NULL COMMENT '用户编号',
  `mealId` INT(11) DEFAULT NULL COMMENT '餐品编号',
  `count` INT(11) DEFAULT NULL COMMENT '存放数量',
  PRIMARY KEY  (`cartId`),
  FOREIGN KEY(userId) REFERENCES users(id),
  FOREIGN KEY(mealId) REFERENCES meals(mealId)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='购物车';

/*Data for the table `mealseries` */

INSERT  INTO `shoppingCart`(`userId`, `mealId`, `count`) VALUES (1,1,1);






DROP TABLE IF EXISTS `mainOrder`;

CREATE TABLE `mainOrder` (
  `oid` INT(11) NOT NULL AUTO_INCREMENT COMMENT '主订单编号',
  `userId` INT(11) NOT NULL COMMENT '用户编号',
  `orderTime` TIMESTAMP DEFAULT NOW() COMMENT '订单生成时间',
  `orderState` VARCHAR(2) DEFAULT NULL COMMENT '订单状态',
  `orderPrice` INT(11) DEFAULT NULL COMMENT '总价格',
  PRIMARY KEY  (`oid`),
  FOREIGN KEY(userId) REFERENCES users(id)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='主订单';

/*Data for the table `mealseries` */





DROP TABLE IF EXISTS `subsetOrder`;

CREATE TABLE `subsetOrder` (
  `odId` INT(11) NOT NULL AUTO_INCREMENT COMMENT '子单编号',
  `oId` INT(11) DEFAULT NULL COMMENT '主订单编号',
  `mealId` INT(11) DEFAULT NULL COMMENT '餐品编号',
  `mealPrice` INT(11) DEFAULT NULL COMMENT '餐品单价',
  `mealCount` INT(11) DEFAULT NULL COMMENT '餐品数量',
  PRIMARY KEY  (`odId`),
  FOREIGN KEY(mealId) REFERENCES meals(mealId),
  FOREIGN KEY(oid) REFERENCES mainOrder(oid)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='主订单';

/*Data for the table `mealseries` */

DROP TABLE IF EXISTS `authority`;

CREATE TABLE `authority` (
  `text` varchar(20)  COMMENT '权限名称',
  `listName` VARCHAR(20) DEFAULT NULL COMMENT '列标题',
  `iconCls` VARCHAR(20) DEFAULT NULL COMMENT '功能按钮图片',
  `name` VARCHAR(20) DEFAULT NULL COMMENT '存员工或老板',
  PRIMARY KEY  (`text`)
) ENGINE=INNODB DEFAULT CHARSET=utf8 COMMENT='权限';

insert into authority (text,listName) values ("消费管理","消费管理"),("订单管理","订单管理"),("菜谱管理","菜谱管理"),("菜系管理","菜系管理"),("类型管理","类型管理"),("用户评价","用户评价"),("用户查询","用户查询"),("餐桌管理","餐桌管理");

/*Data for the table `mealseries` */









