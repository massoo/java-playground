package be.demo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.IllegalFormatException;

/**
 * User: massoo
 */
public class StringFormat {

    private static final Logger LOG = LoggerFactory.getLogger(StringFormat.class);

    /**
     * This does not trigger overflows like in other languages
     * We only need to catch the exception IllegalFormatException
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.printf("%s %d", "Everything is a test !", "test");
        } catch (IllegalFormatException ex) {
            LOG.error(ex.getMessage());
        }
    }

}
