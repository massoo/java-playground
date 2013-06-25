package be.demo.bad.controllers;

import be.demo.bad.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;

/**
 * User: massoo
 */
@Controller
@RequestMapping(value = "/url")
public class RedirectController {

    @RequestMapping(value = "/index-redirect", method = RequestMethod.GET)
    public String index() {
        return "url/index-url-redirect";
    }

    @RequestMapping(value = "/doRedirect", method = RequestMethod.GET)
    public String doRedirect(@RequestParam("url") String redirectURL, ModelMap modelMap, HttpServletResponse response) {

        // validate against our whitelist
        if (Utility.isValidRedirectionURL(redirectURL)) {
            return "redirect:" + redirectURL;
        } else {
            modelMap.addAttribute("error", "URL not in the whitelist!");
            return "url/index-url-redirect";
        }

    }

    @RequestMapping(value="/redirected",method = RequestMethod.GET)
    public String redirected() {
        return "url/url-redirected";
    }

}
