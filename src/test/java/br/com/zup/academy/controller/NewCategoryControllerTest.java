package br.com.zup.academy.controller;

import br.com.zup.academy.controller.request.NewCategoryRequest;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import io.micronaut.http.client.exceptions.HttpClientResponseException;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@MicronautTest(transactional = false)
class NewCategoryControllerTest {

    @Inject
    @Client("/")
    private HttpClient client;

    @Test
    void mustReturn200() {
        var request = HttpRequest.POST("/categorys", this.createRequest("sports", null));
        var response = this.client.toBlocking().exchange(request, Object.class);
        assertEquals(HttpStatus.CREATED, response.status());
    }

    @Test
    void mustReturn400() {
        var request = HttpRequest.POST("/categorys", this.createRequest(" ", null));
        var responseException =
                assertThrows(
                        HttpClientResponseException.class,
                        () -> this.client.toBlocking().exchange(request, Object.class)
                );
        assertEquals(HttpStatus.BAD_REQUEST, responseException.getStatus());
    }

    private NewCategoryRequest createRequest(String name, UUID motherCategoryId) {
        return new NewCategoryRequest(name, motherCategoryId);
    }
}