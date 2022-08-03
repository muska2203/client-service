package com.devmode.clientservice.client;

import com.devmode.clientservice.client.dao.ClientRepository;
import com.devmode.clientservice.client.dto.ClientCreateRequest;
import com.devmode.clientservice.client.dto.ClientPreview;
import com.devmode.clientservice.client.model.Client;
import com.devmode.clientservice.exception.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ClientServerTest {

    private final ClientMapper clientMapper = new ClientMapperImpl();
    private final ClientRepository clientRepository = Mockito.mock(ClientRepository.class);
    private final ClientValidator clientValidator = Mockito.mock(ClientValidator.class);

    private final ClientService clientService = new ClientService(clientRepository, clientMapper, clientValidator);

    @Test
    public void testFindAllByUserId() {
        int userId = 1;
        Client client = new Client();
        client.setId(2);
        client.setName("name");
        client.setPhoneNumber("phone");
        client.setUserId(userId);
        Client secondClient = new Client();
        secondClient.setId(3);
        secondClient.setName("name1");
        secondClient.setPhoneNumber("phone2");
        secondClient.setUserId(userId);

        when(clientRepository.findAllByUserId(eq(userId))).thenReturn(Stream.of(client, secondClient));
        List<ClientPreview> result = clientService.findAllByUserId(userId);
        assertThat(result).hasSize(2);
        assertThat(result).anyMatch(resultClient -> equalClients(client, resultClient));
        assertThat(result).anyMatch(resultClient -> equalClients(secondClient, resultClient));
    }

    @Test
    public void testFindAllByUserId_ReturnsEmpty_IfRepositoryReturnsEmpty() {
        int userId = 1;
        when(clientRepository.findAllByUserId(eq(userId))).thenReturn(Stream.of());
        List<ClientPreview> result = clientService.findAllByUserId(userId);
        assertThat(result).isEmpty();
    }

    @Test
    @SuppressWarnings("unchecked")
    public void testCreate_ThrowsException_IfRequestIsInvalid() {
        int userId = 1;
        ConstraintViolation<ClientCreateRequest> violation = Mockito.mock(ConstraintViolation.class);
        when(clientValidator.validate(any(ClientCreateRequest.class))).thenReturn(Set.of(violation));
        assertThrows(ConstraintViolationException.class, () -> clientService.create(new ClientCreateRequest(), userId));
    }

    @Test
    public void testCreate_ValidCreate_IfRequestIsValid() {
        int userId = 1;
        int clientNewId = 2;
        when(clientValidator.validate(any(ClientCreateRequest.class))).thenReturn(Collections.emptySet());
        ArgumentCaptor<Client> clientCaptor = ArgumentCaptor.forClass(Client.class);
        when(clientRepository.save(clientCaptor.capture())).thenAnswer(invocation -> {
            Client client = invocation.getArgument(0);
            client.setId(clientNewId);
            return client;
        });
        ClientCreateRequest createRequest = new ClientCreateRequest();
        createRequest.setName("name");
        createRequest.setPhoneNumber("+13241321");

        Integer clientId = clientService.create(createRequest, userId);
        assertThat(clientId).isEqualTo(clientNewId);
        Client client = clientCaptor.getValue();
        assertNotNull(client);
        assertThat(client.getId()).isEqualTo(clientNewId);
        assertThat(client.getName()).isEqualTo(createRequest.getName());
        assertThat(client.getPhoneNumber()).isEqualTo(createRequest.getPhoneNumber());
        assertThat(client.getUserId()).isEqualTo(userId);

    }

    @Test
    public void testDelete_ValidDelete_IfClientHasBeenFoundForSpecifiedUser() {
        int userId = 1;
        int clientId = 2;
        Client client = new Client();
        client.setId(clientId);
        client.setUserId(userId);
        when(clientRepository.getByIdAndUserId(clientId, userId)).thenReturn(Optional.of(client));

        clientService.delete(clientId, userId);
        verify(clientRepository, times(1)).delete(eq(client));
    }

    @Test
    public void testDelete_ThrowsException_IfClientHasNotBeenFoundForSpecificUser() {
        int userId = 1;
        int clientId = 2;
        when(clientRepository.getByIdAndUserId(clientId, userId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> clientService.delete(clientId, userId));
        verify(clientRepository, never()).delete(any());
    }

    private static boolean equalClients(Client client, ClientPreview clientPreview) {
        return Objects.equals(client.getId(), clientPreview.getId())
                && Objects.equals(client.getName(), clientPreview.getName())
                && Objects.equals(client.getPhoneNumber(), clientPreview.getPhoneNumber());
    }
}
