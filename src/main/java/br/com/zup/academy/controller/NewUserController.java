package br.com.zup.academy.controller;

import br.com.zup.academy.controller.request.NewUserRequest;
import br.com.zup.academy.domain.repository.UserRepository;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.validation.Validated;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.transaction.Transactional;
import javax.validation.Valid;

@Controller("/users")
@Validated
public class NewUserController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository repository;

    public NewUserController(UserRepository repository) {
        this.repository = repository;
    }

    @Post
    @Transactional
    public HttpResponse<?> register(@Body @Valid NewUserRequest request) {
        this.logger.info("receiving a new user registration request -> " + request);
        this.logger.info("converting the request to a domain object");
        this.logger.info("registering new user");
        var user = this.repository.save(request.toDomain());
        this.logger.info("new user registered successfully");
        this.logger.info("finalizing request -> " + request);
        return HttpResponse.status(HttpStatus.CREATED);
    }
}
