package be.demo.good.controllers;

import be.demo.bean.Login;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * User: massoo
 */
@Controller
@RequestMapping(value = "/jsr")
public class LoginControler {

    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String login(ModelMap modelMap) {
        modelMap.addAttribute("login",new Login());
        return "jsr/index";
    }

    @RequestMapping(value="/doLogin",method = RequestMethod.POST)
    public String doLogin(@Valid Login login, BindingResult bindingResult, ModelMap modelMap) {

        if (bindingResult.hasErrors()) {
            return "jsr/index";
        } else {
            modelMap.addAttribute("email", login.getEmail());
            modelMap.addAttribute("password", login.getPassword());

            return "jsr/result";
        }
    }

}
