package br.com.zup.academy.controller.request;

import br.com.zup.academy.domain.model.User;
import br.com.zup.academy.domain.validation.UniqueValue;
import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Introspected
public class NewUserRequest {

    @NotBlank
    @Email
    @UniqueValue(entity = User.class, field = "email")
    private String email;

    @NotNull
    @Size(min = 6)
    private String password;

    public NewUserRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public User toDomain() {
        return new User(this.email, this.password);
    }

    @Override
    public String toString() {
        return "NewUserRequest{" +
                "email='" + email + '\'' +
                '}';
    }
}