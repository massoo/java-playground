package be.demo.bad.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * User: massoo
 */
@Controller(value = "badDefaultController")
@RequestMapping(value = "/bad/*")
public class DefaultController {

    @RequestMapping(value="*",method = RequestMethod.GET)
    public String index() {
        return "index";
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public String about() {
        return "about";
    }

}
