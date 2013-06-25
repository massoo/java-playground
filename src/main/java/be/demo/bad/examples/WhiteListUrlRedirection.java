package be.demo.bad.examples;

import be.demo.bad.utility.Utility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * User: massoo
 */
public class WhiteListUrlRedirection {

    private static final Logger LOG = LoggerFactory.getLogger(WhiteListUrlRedirection.class);

    public static void main(String[] args) {
        String url_1 = "http://www.evil.com";
        String url_2 = "https://www.google.com";

        LOG.info(String.valueOf(Utility.isValidRedirectionURL(url_1)));
        LOG.info(String.valueOf(Utility.isValidRedirectionURL(url_2)));
    }

}
