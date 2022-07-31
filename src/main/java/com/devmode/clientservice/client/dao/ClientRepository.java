package com.devmode.clientservice.client.dao;

import com.devmode.clientservice.client.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.stream.Stream;


public interface ClientRepository extends JpaRepository<Client, Integer> {

    Stream<Client> findAllByUserId(Integer userId);

    /**
     * Поиск клиента по его идентификатору.
     * Для ограничения доступность клиента неверным пользователям был добавлен параметр userId.
     *
     * @param id идентификатор клиента.
     * @param userId идентификатор пользователя.
     * @return Клиент, если был найден.
     */
    Optional<Client> getByIdAndUserId(Integer id, Integer userId);

}
