package be.demo.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.Normalizer;

/**
 * User: massoo
 */
public class StringNormalization {

    private static final Logger LOG = LoggerFactory.getLogger(StringNormalization.class);

    public static void main(String[] args) {
        String denormalizedString = "\uFE64" + "script" + "\uFE65%20";
        String normalizedString = Normalizer.normalize(denormalizedString, Normalizer.Form.NFC);
        LOG.info(normalizedString);
    }

}
