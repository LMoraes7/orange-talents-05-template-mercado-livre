package br.com.zup.academy.useful;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordScrambler {

    public static String encrypt(String cleanPassword) {
        return new BCryptPasswordEncoder().encode(cleanPassword);
    }
}
