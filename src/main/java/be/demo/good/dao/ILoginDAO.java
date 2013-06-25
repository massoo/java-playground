package be.demo.good.dao;

import be.demo.good.bean.Login;

import java.util.List;

/**
 * User: massoo
 */
public interface ILoginDAO {

    void addLogin(Login login);
    List<Login> getLogins();
    Login getLoginByEmail(String email);
    Login BADgetLoginByEmail(String email);
    void deleteAll();

}
