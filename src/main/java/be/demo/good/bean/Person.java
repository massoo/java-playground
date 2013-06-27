package be.demo.good.bean;

import be.demo.utility.Utility;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


/**
 * User: massoo
 */
public class Person {

    private static final String SPACE = " ";

    @NotEmpty
    @Pattern(regexp = "^(?!\\w*?script).*$", message = "script is not allowed")
    private String firstName;

    @NotEmpty
    private String lastName;

    @NotEmpty
    private String initials;

    /**
     * Normalization before validation is used here
     * @return String: normalized string which applies to the pattern
     */
    public @Pattern(regexp = "^[a-zA-Z_-]*") String getName() {
        StringBuilder sb = new StringBuilder().append(firstName)
                .append(initials)
                .append(lastName);
        return Utility.normalize(sb.toString());
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
