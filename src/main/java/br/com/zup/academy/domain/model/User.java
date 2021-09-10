package br.com.zup.academy.domain.model;

import br.com.zup.academy.useful.PasswordScrambler;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.Instant;
import java.util.UUID;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
    @Column(nullable = false, unique = true)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(nullable = false)
    private Instant instant;

    @Deprecated
    public User() {
    }

    public User(@NotBlank @Email String email, @NotNull @Size(min = 6) String cleanPassword) {
        this.email = email;
        this.password = PasswordScrambler.encrypt(cleanPassword);
        this.instant = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", instant=" + instant +
                '}';
    }
}