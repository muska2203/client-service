--liquibase formatted sql

--changeset dkovalenko:2022-04-26
--comment Создание стартовой таблицы `client`
--rollback DROP TABLE IF EXISTS `client`;
create table `client` (
    `id` int not null AUTO_INCREMENT,
    `name` varchar (150) not null,
    `phone_number` varchar (20) not null,
    primary key (`id`)
);

--changeset dkovalenko:2022-04-27
--comment Создание таблицы пользователей
--rollback DROP TABLE IF EXISTS `user`;
create table `user` (
    `id` int not null AUTO_INCREMENT,
    `external_id` varchar (100) not null unique,
    `name` varchar (150) not null,
    `first_name` varchar (100) not null,
    `last_name` varchar (100) not null,
    `email` varchar (100) not null unique,
    primary key (`id`)
);

--changeset dkovalenko:2022-07-27
--comment Очистка таблицы клиентов и добавление поля для связи с пользователем.
--rollback ALTER TABLE `client` DROP COLUMN `user_id`
DELETE FROM `client`;
ALTER TABLE `client` ADD COLUMN `user_id` int not null REFERENCES `user`(`id`);

