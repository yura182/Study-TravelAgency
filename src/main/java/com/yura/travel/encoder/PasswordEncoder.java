package com.yura.travel.encoder;

import com.yura.travel.exception.PasswordGenerationException;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Arrays;
import java.util.Base64;

public class PasswordEncoder {
    private static final Logger LOGGER = Logger.getLogger("file");
    private static final int ITERATIONS = 100;
    private static final int KEY_LENGTH = 256;
    private static final String ALGORITHM = "PBKDF2WithHmacSHA512";
    private static final String SALT = "EqdmPh53c9x33EygXpTpcoJvc4VXLK";


    public String encode(String password) {
        return hashPassword(password, SALT);
    }

    public String hashPassword(String password, String salt) {
        char[] chars = password.toCharArray();
        byte[] bytes = salt.getBytes();

        PBEKeySpec spec = new PBEKeySpec(chars, bytes, ITERATIONS, KEY_LENGTH);

        Arrays.fill(chars, Character.MIN_VALUE);
        try {
            SecretKeyFactory fac = SecretKeyFactory.getInstance(ALGORITHM);
            byte[] securePassword = fac.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(securePassword);

        } catch (NoSuchAlgorithmException | InvalidKeySpecException ex) {
            LOGGER.error("Error during hashing password", ex);
            throw new PasswordGenerationException("Exception during password encode");
        } finally {
            spec.clearPassword();
        }
    }

    public boolean verifyPassword(String password, String key) {
        String encryptedPassword = hashPassword(password, SALT);
        return encryptedPassword.equals(key);
    }
}

