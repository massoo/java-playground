package be.demo.good.dao;

import be.demo.api.ILoginDAO;
import be.demo.bean.Login;
import org.hibernate.NonUniqueResultException;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * User: massoo
 */
@Repository
@Transactional
public class GoodLoginDao implements ILoginDAO {

    @Autowired
    private SessionFactory sessionFactory;

    public void addLogin(Login login) {
        sessionFactory.getCurrentSession().persist(login);
    }

    public List<Login> getLogins() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("getLogins");
        return query.list();
    }

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

    public Login BADgetLoginByEmail(String email) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("from Login l where l.email = " + email);

        return (Login) query.list().get(0);
    }

    public void deleteAll() {
        Query query = sessionFactory.getCurrentSession().getNamedQuery("deleteAllLogins");
        query.executeUpdate();
    }
}
