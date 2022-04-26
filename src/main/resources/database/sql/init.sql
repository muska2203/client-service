--liquibase formatted sql

--changeset dkovalenko:2022-04-26
--comment Создание стартовой таблицы `client`
--rollback DROP TABLE IF EXISTS `client`;
create table `client` (
    `id` int not null,
    `name` varchar (150) not null,
    `phone_number` varchar (20) not null,
    primary key (`id`)
)
