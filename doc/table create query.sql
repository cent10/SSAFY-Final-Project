CREATE TABLE `user` (
	`id`	int	auto_increment NOT NULL,
	`email`	varchar(45),
	`name`	varchar(45),
	`domain`	varchar(45),
	`key` int,
    primary key(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `authority` (
	`code`	int	NOT NULL,
	`name`	varchar(45)	NOT NULL,
    primary key(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user_authority` (
	`id`	int	auto_increment NOT NULL,
	`user`	int	NOT NULL,
	`code`	int	NOT NULL,
    primary key(`id`),
    foreign key(`user`) references user(`id`),
    foreign key(`code`) references authority(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `shop` (
	`id`	int	auto_increment	NOT NULL,
	`admin`	int	NOT NULL,
	`name`	varchar(45)	NOT NULL,
	`address`	varchar(200)	NOT NULL,
	`phone`	varchar(15)	NOT NULL,
	`classification`	tinyint	NOT NULL,
	`number`	varchar(45)	NOT NULL,
	`description`	longtext,
    primary key(`id`),
    foreign key(`admin`) references user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `category` (
	`code`	int	NOT NULL,
	`name`	varchar(45)	NOT NULL,
    primary key(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `shop_category` (
	`id`	int	auto_increment NOT NULL,
	`shop`	int	NOT NULL,
	`code`	int	NOT NULL,
    primary key(`id`),
    foreign key(`shop`) references shop(`id`),
    foreign key(`code`) references category(`code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `product` (
	`id`	int	auto_increment	NOT NULL,
	`shop`	int	NOT NULL,
	`name`	varchar(45)	NOT NULL,
	`price`	int	NOT NULL,
	`num`	int	NOT NULL,
    `description`	longtext,
    primary key(`id`),
    foreign key(`shop`) references shop(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `reservation` (
	`id`	int	auto_increment	NOT NULL,
	`user`	int	NOT NULL,
	`shop`	int	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
    primary key(`id`),
    foreign key(`user`) references user(`id`),
    foreign key(`shop`) references shop(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `detail_reservation` (
	`id`	int	auto_increment	NOT NULL,
	`reservation`	int	NOT NULL,
	`product`	int	NOT NULL,
	`num`	int	NOT NULL,
    primary key(`id`),
    foreign key(`reservation`) references reservation(`id`),
    foreign key(`product`) references product(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tip` (
	`id`	int	auto_increment	NOT NULL,
	`user`	int	NOT NULL,
	`title`	varchar(45)	NOT NULL,
    `category`	varchar(20)	NOT NULL,
	`content`	longtext	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
	`hits`	int	DEFAULT 0,
    primary key(`id`),
    foreign key(`user`) references user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `tip_reply` (
	`id`	int	auto_increment	NOT NULL,
	`replier`	int	NOT NULL,
	`tip`	int	NOT NULL,
	`content`	text	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
    primary key(`id`),
    foreign key(`replier`) references user(`id`),
    foreign key(`tip`) references tip(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `notice` (
	`id`	int	auto_increment	NOT NULL,
	`user`	int	NOT NULL,
	`title`	varchar(45)	NOT NULL,
	`content`	longtext	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
	`hits`	int	DEFAULT 0,
    primary key(`id`),
    foreign key(`user`) references user(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `review` (
	`id`	int	auto_increment	NOT NULL,
	`reservation`	int	NOT NULL,
	`rate`	int	NOT NULL,
	`content`	text	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
    primary key(`id`),
    foreign key(`reservation`) references reservation(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `review_reply` (
	`id`	int	auto_increment	NOT NULL,
	`replier`	int	NOT NULL,
	`review`	int	NOT NULL,
	`content`	text	NOT NULL,
	`date`	datetime	DEFAULT current_timestamp(),
    primary key(`id`),
    foreign key(`replier`) references user(`id`),
    foreign key(`review`) references review(`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
