--liquibase formatted sql

--changeset dkovalenko:2022-10-03
--comment Создание стартовой таблицы `provider`
--rollback DROP TABLE IF EXISTS `provider`;
create table `provider` (
  `id` int not null AUTO_INCREMENT,
  `user_id` int not null REFERENCES `user`(`id`),
  `phone_number` varchar (20),
  `nickname` varchar (50) unique,
  `name` varchar (50),
  primary key (`id`)
);

--changeset dkovalenko:2022-10-03-1
--comment Создание стартовой таблицы `provider-link`
--rollback DROP TABLE IF EXISTS `provider-link`;
create table `provider_link` (
    `id` int not null AUTO_INCREMENT,
    `provider_id` int not null REFERENCES `provider`(`id`),
    `type` varchar (20) not null,
    `link` varchar (1500) not null,
    primary key (`id`)
);
