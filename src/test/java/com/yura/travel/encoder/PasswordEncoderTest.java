package com.yura.travel.encoder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(Parameterized.class)
public class PasswordEncoderTest {
    private static PasswordEncoder passwordEncoder;
    private String actual;
    private String expected;

    @BeforeClass
    public static void init() {
        passwordEncoder = new PasswordEncoder();
    }

    public PasswordEncoderTest(String actual, String expected) {
        this.actual = actual;
        this.expected = expected;
    }

    @Parameters
    public static Collection<Object[]> password() {
        return Arrays.asList(new Object[][] {
            { "123456" , "MTIzNDU2" },
            { "asdfb234" , "YXNkZmIyMzQ=" },
            { "123aaaaaaaa" , "MTIzYWFhYWFhYWE=" },
            { "Yura123" , "WXVyYTEyMw==" },
            { "qwerty" , "cXdlcnR5" },
        });
    }


    @Test
    public void shouldEncodePassword() {
        String actual = passwordEncoder.encode(this.actual);
        String expected = this.expected;
        assertEquals(expected, actual);
    }

    @Test
    public void shouldVerifyPassword() {
        String encodedPassword = this.expected;
        String plainPassword = this.actual;
        boolean actual = passwordEncoder.verifyPassword(plainPassword, encodedPassword);
        assertTrue(actual);
    }
}