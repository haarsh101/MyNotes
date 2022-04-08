package com.example.mynotes;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import com.example.mynotes.utils.InputValidator;

import org.junit.Test;

public class InputValidatorTest {

    @Test
    public void testEmailValidation_Valid() {
        InputValidator inputValidator = new InputValidator();
        assertTrue(inputValidator.isValidEmail("harsh@gmail.com"));
    }

    @Test
    public void testEmailValidation_InvalidFormat() {
        InputValidator inputValidator = new InputValidator();
        assertFalse(inputValidator.isValidEmail("vishnusaxena"));
    }

    @Test
    public void testEmailValidation_InvalidLength() {
        InputValidator inputValidator = new InputValidator();
        assertFalse(inputValidator.isValidEmail("fastracingemulator@company.com"));
    }

    @Test
    public void testMobileValidation_Valid() {
        InputValidator inputValidator = new InputValidator();
        assertTrue(inputValidator.isValidMobile("9907999079"));
    }

    @Test
    public void testMobileValidation_Invalid() {
        InputValidator inputValidator = new InputValidator();
        assertFalse(inputValidator.isValidMobile("9907"));
    }

    @Test
    public void testPasswordValidation_Valid() {
        InputValidator inputValidator = new InputValidator();
        assertTrue(inputValidator.isValidPassword("pAAsword@@999", "harsh"));
    }

    @Test
    public void testPasswordValidation_Invalid() {
        InputValidator inputValidator = new InputValidator();
        assertFalse(inputValidator.isValidPassword("password", "harsh"));
    }

    @Test
    public void testPasswordValidation_InvalidContainsName() {
        InputValidator inputValidator = new InputValidator();
        assertFalse(inputValidator.isValidPassword("harsh2002", "harsh"));
    }
}
