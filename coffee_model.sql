CREATE TABLE `user` (
`user_id` int(11) NOT NULL AUTO_INCREMENT,
`account` varchar(20) NOT NULL,
`password` varchar(30) NOT NULL,
`tel` varchar(20) NULL,
`email` varchar(50) NULL,
PRIMARY KEY (`user_id`) ,
UNIQUE INDEX `account_unique` (`account` ASC) USING BTREE
);
CREATE TABLE `item` (
`item_id` int(11) NOT NULL AUTO_INCREMENT,
`name` varchar(30) NOT NULL,
`type` varchar(20) NOT NULL,
`stock` int(11) NOT NULL,
`price` numeric(10,2) NOT NULL,
`description` varchar(255) NULL,
`image_url` varchar(255) NULL,
PRIMARY KEY (`item_id`) 
);
CREATE TABLE `admin` (
`admin_id` int(11) NOT NULL AUTO_INCREMENT,
`account` varchar(20) NOT NULL,
`password` varchar(30) NOT NULL,
PRIMARY KEY (`admin_id`) ,
UNIQUE INDEX `aacount_unique` (`account` ASC) USING BTREE
);
CREATE TABLE `order` (
`order_id` int(11) NOT NULL AUTO_INCREMENT,
`user_id` int(11) NOT NULL,
`order_total` numeric(10,2) NULL,
`created_time` datetime(6) NOT NULL ON UPDATE CURRENT_TIMESTAMP(6),
PRIMARY KEY (`order_id`) 
);
CREATE TABLE `order_detail` (
`order_id` int(11) NOT NULL,
`item_id` int(11) NOT NULL,
`number` int(11) NOT NULL,
PRIMARY KEY (`order_id`, `item_id`) 
);
CREATE TABLE `shoppingcart_item` (
`user_id` int(11) NOT NULL,
`item_id` int(11) NOT NULL,
`number` int(11) NOT NULL,
PRIMARY KEY (`user_id`, `item_id`) 
);

ALTER TABLE `order` ADD CONSTRAINT `fk_order_user_auth_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `order_detail` ADD CONSTRAINT `fk_order_detail_order_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `order_detail` ADD CONSTRAINT `fk_order_detail_item_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `shoppingcart_item` ADD CONSTRAINT `fk_shoppingcart_user_auth_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
ALTER TABLE `shoppingcart_item` ADD CONSTRAINT `fk_shoppingcart_item_1` FOREIGN KEY (`item_id`) REFERENCES `item` (`item_id`) ON DELETE CASCADE ON UPDATE CASCADE;

