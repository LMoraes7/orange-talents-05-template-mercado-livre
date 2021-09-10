package br.com.zup.academy.controller;

import br.com.zup.academy.controller.request.NewUserRequest;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(transactional = false)
class NewUserControllerTest {

    @Inject
    @Client("/")
    private HttpClient client;

    @Test
    void mustReturn200() {
        var request = HttpRequest.POST("/users", this.createRequest("email@email.com", "12345678"));
        var response = this.client.toBlocking().exchange(request, Object.class);
        assertEquals(HttpStatus.CREATED, response.status());
    }

    @Test
    void mustReturn400() {
        var request = HttpRequest.POST("/users", this.createRequest("email@email.com", null));
        var responseException =
                assertThrows(
                        HttpClientResponseException.class,
                        () -> this.client.toBlocking().exchange(request, Object.class)
                );
        assertEquals(HttpStatus.BAD_REQUEST, responseException.getStatus());
    }

    private NewUserRequest createRequest(String email, String password) {
        return new NewUserRequest(email, password);
    }
}