package be.demo.bean;

import be.demo.dao.ILoginDAO;
import org.hamcrest.CoreMatchers;
import org.hibernate.QueryException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * User: massoo
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:/configuration/applicationContext.xml"})
public class LoginPersistenceTestCase {

    private String email = "test@demo.com";
    @Autowired
    private ILoginDAO loginDAO;

    @Before
    public void doBefore() {
        Login login = new Login();
        login.setEmail(email);
        login.setPassword("password");

        loginDAO.addLogin(login);
    }

    @After
    public void doAfter() {
        loginDAO.deleteAll();
    }

    @Test
    public void testLogin() {
        List<Login> loginList = loginDAO.getLogins();
        Assert.assertThat(1, CoreMatchers.equalTo(loginList.size()));
    }

    @Test
    public void testLoginByEmail() {
        loginDAO.getLoginByEmail(email);
    }

    @Test(expected = QueryException.class)
    public void testBADLoginByEmail() {
        Login login = loginDAO.BADgetLoginByEmail(email);
        Assert.assertThat(login, CoreMatchers.notNullValue());
        Assert.assertThat(login.getEmail(), CoreMatchers.equalTo(email));
    }

}
