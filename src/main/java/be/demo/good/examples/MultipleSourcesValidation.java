package be.demo.good.examples;

import be.demo.good.bean.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * User: massoo
 */
public class MultipleSourcesValidation {

    private static final Logger LOG = LoggerFactory.getLogger(MultipleSourcesValidation.class);

    private static Validator validator;
    private static Pattern pattern;

    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();

        ///using this pattern will stop the script for a single field
        pattern = Pattern.compile("<script(?:(?!<\\/script>).)*<\\/script>");
    }

    public static void main(String[] args) {

        Person person = new Person();
        person.setFirstName("firstname \uFE64script");
        person.setInitials(">alert(1)</script");
        person.setLastName("> lastname");

        goodValidation(person);
        //badValidation(person);

    }

    public static void goodValidation(Person person) {

        Set<ConstraintViolation<Person>> errors = validator.validate(person);

        if (errors.size() > 0) {
            LOG.error("we have errors: " + errors.toArray()[0]);
        } else {
            LOG.info("it's okay to display: " + person.getName());
        }

    }

    public static void badValidation(Person person) {

        boolean isValid = true;

        Matcher matcher = pattern.matcher(person.getFirstName());
        if (matcher.find()) { isValid = false; }

        matcher = pattern.matcher(person.getInitials());
        if (matcher.find()) { isValid = false; }

        matcher = pattern.matcher(person.getLastName());
        if (matcher.find()) { isValid = false; }

        if (isValid) {
            LOG.info("i guess it's NOT okay to show: " + person.getName());
        } else {
            LOG.error("You kidding me that you couldn't hack this validation mechanism..");
        }

    }

}
