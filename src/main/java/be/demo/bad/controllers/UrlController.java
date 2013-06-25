package be.demo.bad.controllers;

import be.demo.bad.utility.Utility;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * User: massoo
 */
@Controller(value = "badUrlController")
@RequestMapping(value = "/bad/url")
public class UrlController {

    @RequestMapping(value = "/index-normalization", method = RequestMethod.GET)
    public String index() {
        return "url/index-url-normalization";
    }

    @RequestMapping(value = "/doNormalize")
    public String urlNormalization(@RequestParam String url, ModelMap modelMap) {

        try {
            url = Utility.normalizeURLCustom(url);
            modelMap.addAttribute("url", url);
        } catch (Exception e) {
            modelMap.addAttribute("error", "Error " + e.getMessage());
        }

        return "url/url-normalized";
    }

}
