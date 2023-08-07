package utils;


import exceptions.ApplicationException;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

public class CustomStringUtils extends StringUtils {

    public static String trimNonNumeric(String value) {
        if (isEmpty(value)) {
            throw new ApplicationException("Not valid string value + " + value);
        }
        return value.replaceAll("\\D", "");
    }

    public static String generateRandomString(int charNum) {
        return RandomStringUtils.randomAlphabetic(charNum);
    }
}
