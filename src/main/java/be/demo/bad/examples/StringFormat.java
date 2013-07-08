package be.demo.bad.examples;

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
     * We only need to catch the exception IllegalFormatException and NullPointerException
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            LOG.info(String.format("%s %n","Everything is a test !", 12398123));
        } catch (IllegalFormatException ex) {
            LOG.error(ex.getMessage());
        } catch (NullPointerException ex) {
            LOG.error("No String format given to the String.format()");
        }
    }

}
