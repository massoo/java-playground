package be.demo.bean;

import org.hibernate.annotations.NamedQueries;
import org.hibernate.annotations.NamedQuery;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * User: massoo
 */
@Entity
@NamedQueries({
        @NamedQuery(
                name = "getLogins",
                query = "from Login l "
        ),
        @NamedQuery(
                name = "getLoginsByEmail",
                query = "from Login l where l.email = :email"
        ),
        @NamedQuery(
                name = "deleteAllLogins",
                query = "delete from Login"
        )
})
public class Login {

    @Id
    @GeneratedValue
    private long id;
    @NotEmpty
    @Email          //test
    private String email;
    @NotEmpty
    @Length(min = 6, max = 16)
    private String password;

    public long getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
