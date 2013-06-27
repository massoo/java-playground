package be.demo.good.examples;

import be.demo.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: massoo
 */
public class PatternSanitization {

    /**
     * a password must be between 6 and 18 characters and may only contain:
     * - letters
     * - numbers
     * - underscores
     * - hyphens
     */
    private static final Logger LOG = LoggerFactory.getLogger(PatternSanitization.class);
    private static final Pattern passwordPattern = Pattern.compile("^[a-z0-9_-]{6,18}$");

    public static void main(String[] args) {

        String validPassword = "myp4ssw0rd";
        //String invalidPassword = "mypa$$w0rd";

        Matcher matcher = passwordPattern.matcher(Utility.normalize(validPassword));

        if (matcher.matches()) {
            LOG.info("password matches, continue processing");
        } else {
            LOG.error("invalid password");
        }

    }

}
