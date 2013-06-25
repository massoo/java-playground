package be.demo.good.examples;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * User: massoo
 */
public class DateNormalization {

    private static final Logger LOG = LoggerFactory.getLogger(DateNormalization.class);
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-YYYY", Locale.ENGLISH);

    public static void main(String[] args) {
        String stringDate = "02-04-2012";

        try {
            Date date = sdf.parse(stringDate);

            // we can now start using the date
            LOG.info(date.toString());
        } catch (ParseException e) {
            LOG.error("Error converting date: {}",e.getMessage());
        }

    }

}
