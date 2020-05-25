package com.github.harsisis.videotheque.util;

import org.junit.Test;

import static org.junit.Assert.*;

public class ValidatorUtilTest {

    @Test
    public void given_validInteger_when_isValidInteger_then_true() {
        // given
        String integer = "123";

        // when
        boolean result = ValidatorUtil.isValidInteger(integer);

        assertTrue(result);

    }

    @Test
    public void given_invalidInteger_when_isValidInteger_then_true() {
        // given
        String integer = "error";

        // when
        boolean result = ValidatorUtil.isValidInteger(integer);

        assertFalse(result);

    }

    @Test
    public void given_decimalInput_when_isValidInteger_then_true() {
        // given
        String integer = "123.45";

        // when
        boolean result = ValidatorUtil.isValidInteger(integer);

        assertFalse(result);

    }

    @Test
    public void given_validDecimal_when_isValidDouble_then_true() {
        // given
        String doubleStr = "123.45";

        // when
        boolean result = ValidatorUtil.isValidDouble(doubleStr);

        assertTrue(result);

    }

    @Test
    public void given_invalidDecimal_when_isValidDouble_then_true() {
        // given
        String doubleStr = "error";

        // when
        boolean result = ValidatorUtil.isValidDouble(doubleStr);

        assertFalse(result);

    }

    @Test
    public void given_invalidSeparator_when_isValidDouble_then_true() {
        // given
        String doubleStr = "123,56";

        // when
        boolean result = ValidatorUtil.isValidDouble(doubleStr);

        assertFalse(result);

    }
}