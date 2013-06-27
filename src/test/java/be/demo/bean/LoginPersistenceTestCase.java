package be.demo.bean;

import be.demo.api.ILoginDAO;
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
// TODO: had to exclude tiles configurer and move this altered applicationContext to test/resources
@ContextConfiguration(locations = {"classpath:configuration/applicationContext.xml"})
public class LoginPersistenceTestCase {

    private String email = "test@demo.com";

    @Autowired
    private ILoginDAO goodLoginDao;

    @Before
    public void doBefore() {
        Login login = new Login();
        login.setEmail(email);
        login.setPassword("password");

        goodLoginDao.addLogin(login);
    }

    @After
    public void doAfter() {
        goodLoginDao.deleteAll();
    }

    @Test
    public void testLogin() {
        List<Login> loginList = goodLoginDao.getLogins();
        Assert.assertThat(1, CoreMatchers.equalTo(loginList.size()));
    }

    @Test
    public void testLoginByEmail() {
        goodLoginDao.getLoginByEmail(email);
    }

    @Test(expected = QueryException.class)
    public void testBADLoginByEmail() {
        Login login = goodLoginDao.BADgetLoginByEmail(email);
        Assert.assertThat(login, CoreMatchers.notNullValue());
        Assert.assertThat(login.getEmail(), CoreMatchers.equalTo(email));
    }

}
