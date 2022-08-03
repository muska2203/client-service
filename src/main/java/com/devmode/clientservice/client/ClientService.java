package com.devmode.clientservice.client;

import com.devmode.clientservice.client.dao.ClientRepository;
import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.client.dto.ClientPreview;
import com.devmode.clientservice.client.model.Client;
import com.devmode.clientservice.exception.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Сервис для стандартных операций над клиентами.
 */
@Service
@RequiredArgsConstructor
public class ClientService {

    private final ClientRepository clientRepository;

    private final ClientMapper clientMapper;

    private final ClientValidator clientValidator;

    /**
     * Производит поиск всех клиентов указанного пользователя.
     * <br>TODO: Будет заменен на поиск клиентов с указанием пагинации.
     *
     * @param userId идентификатор пользователя.
     * @return Не полная информация о найденных пользователях
     * @see ClientPreview
     */
    @Transactional(readOnly = true)
    public List<ClientPreview> findAllByUserId(Integer userId) {
        return clientRepository.findAllByUserId(userId)
                .map(clientMapper::map)
                .collect(Collectors.toList());
    }

    /**
     * Создает клиента с указанными данными.
     *
     * @param createRequest информация для создания пользователя. Дополнительно проверяется на валидность.
     * @param userId        идентификатор пользователя, к которому требуется привязать клиента.
     * @return идентификатор созданного пользователя.
     * @throws ConstraintViolationException если запрос на создание пользователя на прошел валидацию.
     */
    @Transactional
    public Integer create(ClientCreateRequest createRequest, Integer userId) throws ConstraintViolationException {
        Set<ConstraintViolation<ClientCreateRequest>> violations = clientValidator.validate(createRequest);
        if (!violations.isEmpty()) {
            throw new ConstraintViolationException("Client has not been created", violations);
        }
        Client client = clientMapper.map(createRequest);
        client.setUserId(userId);
        Client savedClient = clientRepository.save(client);
        return savedClient.getId();
    }

    /**
     * Производит удаление указанного клиента, если он есть в базе и привязан к указанному пользователю.
     *
     * @param clientId идентификатор клиента.
     * @param userId   идентификатор пользователя.
     * @throws EntityNotFoundException если подходящего по критериям клиента не удалось найти.
     */
    @Transactional
    public void delete(Integer clientId, Integer userId) throws EntityNotFoundException {
        Client client = clientRepository.getByIdAndUserId(clientId, userId)
                .orElseThrow(EntityNotFoundException::new);
        clientRepository.delete(client);
    }
}
