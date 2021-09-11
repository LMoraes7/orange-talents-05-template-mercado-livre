package br.com.zup.academy.controller.request;

import br.com.zup.academy.domain.model.Category;
import br.com.zup.academy.domain.repository.CategoryRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
class NewCategoryRequestTest {

    @Inject
    private Validator validator;

    @Inject
    private CategoryRepository repository;

    private Category category;

    @BeforeEach
    void setUp() {
        this.category = this.repository.save(
                this.createCategory("sports", null)
        );
    }

    @AfterEach
    void close() {
        this.repository.deleteAll();
    }

    @Test
    void mustValidate() {
        var request = this.createRequest("electronics", null);
        var errors = this.validate(request);
        assertTrue(errors.isEmpty());

        request = this.createRequest("soccer", this.category.getId());
        errors = this.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenNameIsNullOrEmpty() {
        var request = this.createRequest(null, null);
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());

        request = this.createRequest(" ", this.category.getId());
        errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenNameForDuplicate() {
        var request = this.createRequest(this.category.getName(), null);
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenMotherCategoryIdNotInvalid() {
        var request = this.createRequest("electronics", UUID.randomUUID());
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    private Set<ConstraintViolation<NewCategoryRequest>> validate(NewCategoryRequest request) {
        return this.validator.validate(request);
    }

    private NewCategoryRequest createRequest(String name, UUID motherCategoryId) {
        return new NewCategoryRequest(name, motherCategoryId);
    }

    private Category createCategory(String name, Category motherCategory) {
        return new Category(name, motherCategory);
    }
}