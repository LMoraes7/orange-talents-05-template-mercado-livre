package br.com.zup.academy.controller.request;

import br.com.zup.academy.domain.model.User;
import br.com.zup.academy.domain.repository.UserRepository;
import io.micronaut.test.extensions.junit5.annotation.MicronautTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@MicronautTest(transactional = false)
class NewUserRequestTest {

    @Inject
    private UserRepository repository;

    @Inject
    private Validator validator;

    private User user;

    @BeforeEach
    void setUp() {
        this.user = this.repository.save(
                this.createUser("email2@email.com", "12345678")
        );
    }

    @AfterEach
    void close() {
        this.repository.deleteAll();
    }

    @Test
    void mustValidate() {
        var request = this.createRequest("email@email.com", "12345678");
        var errors = this.validate(request);
        assertTrue(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenEmailIsNullEmptyOrInvalid() {
        var request = this.createRequest(null, "12345678");
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());

        request = this.createRequest(" ", "12345678");
        errors = this.validate(request);
        assertFalse(errors.isEmpty());

        request = this.createRequest("emailemail.com", "12345678");
        errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenEmailIsDuplicated() {
        var request = this.createRequest(this.user.getEmail(), "12345678");
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    @Test
    void doNotValidateWhenPasswordIsNullEmptyOrLessThan6Characters() {
        var request = this.createRequest("email@email.com", null);
        var errors = this.validate(request);
        assertFalse(errors.isEmpty());

        request = this.createRequest("email@email.com", " ");
        errors = this.validate(request);
        assertFalse(errors.isEmpty());

        request = this.createRequest("email@email.com", "12345");
        errors = this.validate(request);
        assertFalse(errors.isEmpty());
    }

    private Set<ConstraintViolation<NewUserRequest>> validate(NewUserRequest request) {
        return this.validator.validate(request);
    }

    private NewUserRequest createRequest(String email, String password) {
        return new NewUserRequest(email, password);
    }

    private User createUser(String email, String cleanPassword) {
        return new User(email, cleanPassword);
    }
}