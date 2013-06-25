package be.demo.good.bean;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

/**
 * User: massoo
 */
public class JSRTestCase {

    private Validator validator;

    @Before
    public void doBefore() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testLoginConstraints() {
        Login login = new Login();
        login.setEmail("email");
        login.setPassword("password");

        Set<ConstraintViolation<Login>> errors = validator.validate(login);

        Assert.assertThat(1, CoreMatchers.equalTo(errors.size()));
        System.out.println((errors.toArray())[0]);
    }

    @Test
    public void testCombineSourcesValidation() {
        Person person = new Person();
        person.setFirstName("firstname");
        person.setInitials("initials");
        person.setLastName("/lastname");

        Set<ConstraintViolation<Person>> errors = validator.validate(person);

        Assert.assertThat(1, CoreMatchers.equalTo(errors.size()));
        System.out.println((errors.toArray())[0]);
    }

    @Test
    public void testMaarten() {
        Person person = new Person();
        person.setFirstName("script");
        person.setInitials("initials");
        person.setLastName("lastname");

        Set<ConstraintViolation<Person>> errors = validator.validate(person);

        for (ConstraintViolation<Person> error: errors) {
            System.out.println(error.toString());
        }

    }

}
