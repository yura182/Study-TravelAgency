package com.yura.travel.encoder;

import com.yura.travel.exception.PasswordGenerationException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

@Component
public class PasswordEncoder {
    private static final Logger LOGGER = Logger.getLogger("file");

    public String encode(String password) {
        return hashPassword(password);
    }

    private String hashPassword(String password) {
        try {
            return Base64.getEncoder()
                    .encodeToString(password.getBytes(StandardCharsets.UTF_8.toString()));
        } catch (UnsupportedEncodingException ex) {
            LOGGER.warn("Error during password hashing");
            throw new PasswordGenerationException("Error during password hashing");
        }
    }

    public boolean verifyPassword(String password, String key) {
        String encryptedPassword = hashPassword(password);
        return encryptedPassword.equals(key);
    }
}

