package org.hmzb.xss;

import org.hmzb.xss.user.User;
import org.hmzb.xss.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by ptzhuf on 2016/8/1.
 */
@Controller
public class GlobalController {
    @Autowired
    private UserService userService;

    @RequestMapping("**/*")
    public String notFound() {
        return "redirect:/";
    }

    /**
     * 去登录页.
     *
     * @param url   目标地址
     * @param model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.GET)
    public String toLoginPage(String url, Model model, HttpSession session) {

        String username = (String) session.getAttribute("loginUser");
        if (StringUtils.isEmpty(username)) {
            model.addAttribute("url", url);
            return "login";
        } else {
            return "redirect:" + url;
        }
    }

    /**
     * 登录.
     *
     * @param url      目标地址
     * @param username 用户名
     * @param password 密码
     * @param session  session
     * @param model    model
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public String login(String url, String username, String password, HttpSession session, Model model) {
        User user = userService.findByUsername(username);
        String userPassword = user.getPassword();
        model.addAttribute("url", url);
        if (userPassword.equals(password)) {
            // 增加url后的token校验防范 url redirect 攻击
            // 增加url域名白名单校验

            session.setAttribute("loginUser", user.getUsername());
            session.setAttribute("user", user);
            return "redirect:" + url;
        } else {
            model.addAttribute("msg", "用户名密码错误");
            return "login";
        }
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return "redirect:/users";
    }

}
