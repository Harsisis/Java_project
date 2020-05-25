package com.github.harsisis.videotheque.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

public class ValidatorUtil {

    public static boolean isValidDouble(String str) {
        return NumberUtils.isCreatable(str);
    }

    public static boolean isValidInteger(String str) {
        return StringUtils.isNumeric(str);
    }
}