package br.com.zup.academy.controller;

import br.com.zup.academy.controller.request.NewCategoryRequest;
import br.com.zup.academy.domain.repository.CategoryRepository;
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

@Controller("/categorys")
@Validated
public class NewCategoryController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private CategoryRepository repository;

    public NewCategoryController(CategoryRepository repository) {
        this.repository = repository;
    }

    @Post
    @Transactional
    public HttpResponse<?> register(@Body @Valid NewCategoryRequest request) {
        this.logger.info("receiving a request to register a new category -> " + request);
        this.logger.info("converting the request to a domain object");
        this.logger.info("registering new category");
        this.repository.save(request.toDomain(this.repository));
        this.logger.info("new category registered successfully");
        this.logger.info("finalizing request -> " + request);
        return HttpResponse.status(HttpStatus.CREATED);
    }
}