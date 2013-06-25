package be.demo.bad.dao;

import be.demo.bad.bean.Login;

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
