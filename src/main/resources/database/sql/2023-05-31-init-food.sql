--liquibase formatted sql

--changeset dkovalenko:2023.06.01-01
--comment Создание таблицы для групп пользователей
--rollback DROP TABLE IF EXISTS user_group;
create table `user_group` (
    `id` int not null AUTO_INCREMENT,
    `owner_id` int not null REFERENCES `user`(`id`),
    `name` varchar(50) not null unique,
    primary key (`id`)
);

--changeset dkovalenko:2023.06.01-02
--comment Создание таблицы для связи пользователей с группами
--rollback DROP TABLE IF EXISTS user_group_member;
create table `user_group_member` (
    `group_id` int not null REFERENCES `user_group`(`id`),
    `user_id` int not null REFERENCES `user`(`id`),
    primary key (`group_id`, `user_id`)
);

--changeset dkovalenko:2023.06.01-03
--comment Создание таблицы для заказа
--rollback DROP TABLE IF EXISTS food_order;
create table `food_order` (
    `id` int not null AUTO_INCREMENT,
    `group_id` int not null REFERENCES `user_group`(`id`),
    `date` timestamp not null default CURRENT_TIMESTAMP,
    `payer_id` int not null REFERENCES `user`(`id`),
    `common_cost` decimal(8, 2) not null default 0,
    primary key (`id`)
);

--changeset dkovalenko:2023.06.01-04
--comment Создание таблицы для единицы заказа
--rollback DROP TABLE IF EXISTS food_order_item;
create table `food_order_item` (
    `id` int not null AUTO_INCREMENT,
    `order_id` int not null REFERENCES `food_order`(`id`),
    `user_id` int not null REFERENCES `user`(`id`),
    `name` varchar(150) not null,
    `cost` decimal(8, 2) not null,
    primary key (`id`)
)
