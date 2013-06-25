package be.demo.good.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: massoo
 */
public class StringToNumber {

    private static final Logger LOG = LoggerFactory.getLogger(StringToNumber.class);

    public static void main(String[] args) {

        String stringNumber = "1928";

        try {
            int number = Integer.parseInt(stringNumber);
            LOG.info("String to int successfully parsed: {}",number);
        } catch (NumberFormatException ex) {
            LOG.error("String to int parsing failed");
        }

    }

}
