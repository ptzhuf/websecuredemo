package org.hmzb;

import org.hmzb.csrf.CsrfUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AllController {
    @Autowired
    private CookieService cookieService;
    @Autowired
    private CsrfUserService csrfUserService;

    @RequestMapping(value = "/cookies", method = RequestMethod.GET)
    public String get(String cookie) {
        cookieService.save(cookie);
        return "null";
    }

    @RequestMapping(value = "/csrf", method = RequestMethod.GET)
    public String csrf() {
        return "a";
    }

    @RequestMapping(value = "/csrfb", method = RequestMethod.GET)
    public String csrfb() {
        return "b";
    }

    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String urlRedirectAttack() {
        return "login";
    }

    /**
     * 提交账号密码.
     *
     * @return
     */
    @RequestMapping(value = "getUP", method = RequestMethod.POST)
    public String getUP(String username, String password) {
        csrfUserService.save(username, password);
        return "redirect:http://bug.local:8080/users";
    }

    @RequestMapping("/")
    public String index() {
        return "index";
    }

}
