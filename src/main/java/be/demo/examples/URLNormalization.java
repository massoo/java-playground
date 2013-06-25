package be.demo.examples;

import be.demo.exception.TechnicalException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * User: massoo
 */
public class URLNormalization {

    private static final Logger LOG = LoggerFactory.getLogger(URLNormalization.class);

    /**
     * A URI may be either absolute or relative.
     * A URI string is parsed according to the generic syntax without regard to the scheme, if any,
     * that it specifies. No lookup of the host, if any, is performed,
     * and no scheme-dependent stream handler is constructed.
     */
    public static void main(String[] args) {

        if (args.length != 1) {
            throw new TechnicalException("1 argument is expected !");
        }

        try {

            URI craftedURL = new URI(args[0]).normalize();
            LOG.info("We can work with the URI: {}",craftedURL.toString());

        } catch (URISyntaxException e) {
            LOG.error(e.getMessage());
        }

    }

}
