package be.demo.good.dao;

import be.demo.good.bean.Login;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: massoo
 */
@Repository
@Transactional
public class LoginDAO implements ILoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addLogin(Login login) {
        sessionFactory.getCurrentSession().persist(login);
    }

    @Override
    public List<Login> getLogins() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getLogins");
        return query.list();
    }

    @Override
    public Login getLoginByEmail(String email) {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getLoginsByEmail");
        query.setString("email", email);

        try {
            return (Login) query.uniqueResult();
        } catch (NonUniqueResultException ex) {
            // do something with the exception, this is not normal
            return null;
        }

    }

    @Override
    public Login BADgetLoginByEmail(String email) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Login l where l.email = " + email);

        return (Login) query.list().get(0);
    }

    @Override
    public void deleteAll() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteAllLogins");
        query.executeUpdate();
    }
}
