package com.vijai.urlmin.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class URLValidatorTest {
    @Mock
    URLValidator INSTANCE;
    @InjectMocks
    URLValidator uRLValidator;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testIsValid() throws Exception {
        final String validUrl = "https://www.google.com/maps/place/%D0%97%D0%B0%D1%81%D1%83%D0%BB%D0%B0%D1%83%D0%BA%D1%81,+%D0%9A%D1%83%D1%80%D0%B7%D0%B5%D0%BC%D1%81%D0%BA%D0%B8%D0%B9+%D1%80%D0%B0%D0%B9%D0%BE%D0%BD+%D0%A0%D0%B8%D0%B3%D0%B8,+%D0%A0%D0%B8%D0%B3%D0%B0/@56.9498806,24.0550675,16z/data=!3m1!4b1!4m5!3m4!1s0x46eed00330f4f421:0xcffc37b4334e078e!8m2!3d56.9498275!4d24.0598116?hl=ru&authuser=0";
        boolean result = uRLValidator.isValid(validUrl);
        Assert.assertTrue(result);
    }

    @Test
    public void testIfIsNotValid() throws Exception {
        final String notValidUrl = "/maps/place/";
        boolean result = uRLValidator.isValid(notValidUrl);
        Assert.assertFalse(result);
    }

    @Test
    public void testIsValidShort() throws Exception {
        boolean result = uRLValidator.isValidShort("http://localhost:3000/ZIwPqjq7");
        Assert.assertTrue(result);
    }
}