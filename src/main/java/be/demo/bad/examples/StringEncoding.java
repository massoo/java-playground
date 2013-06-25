package be.demo.bad.examples;

import be.demo.bad.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;

/**
 * User: massoo
 */
public class StringEncoding {

    private static final Logger LOG = LoggerFactory.getLogger(StringEncoding.class);
    public static void main(String[] args) {

        String encodedString = new String("A" + "\u00ea" + "\u00f1" + "\u00fc" + "C");

        try {

            byte[] utf8Bytes = encodedString.getBytes("UTF-8");
            byte[] isoBytes = encodedString.getBytes("ISO-8859-1");

            LOG.info("non readable string: {}",new String(isoBytes));
            LOG.info("readable result: {}",new String(utf8Bytes));

            printBytes(utf8Bytes,"UTF-8");
            printBytes(isoBytes,"ISO-8859-1");


        } catch (UnsupportedEncodingException ex) {
            LOG.error(ex.getMessage());
        }
    }

    public static void printBytes(byte[] byteArray,String encoding) {
        for (int i=0; i < byteArray.length; i++) {
            LOG.info(encoding + " [" + i + "]: " + Utility.byteToHex(byteArray[i]));
        }
    }

}
