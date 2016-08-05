/**
 *
 */
package org.hmzb.xss.user;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * @author ptzhuf
 */
@Controller
@RequestMapping("/users")
public class UserController {
    /**
     * logger.
     */
    private static final Logger LOG = getLogger(UserController.class);

    @Autowired
    private UserService userService;

    /**
     * 进入用户列表页面.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String get(String showMsg, Model model) {

        List<User> userList = userService.findAlll();
        String msg = showMsg;
        // 防御反射型跨站脚本XSS攻击
//        msg = HtmlUtils.htmlEscape(showMsg);
        // 防御反射型跨站脚本XSS攻击 结束
        model.addAttribute("showMsg", msg);
        model.addAttribute("userList", userList);
        return "user/page";
    }

    /**
     * 添加用户.
     *
     * @param formObject 表单数据
     */
    @RequestMapping(value = "", method = RequestMethod.POST)
    public String post(User formObject, BindingResult bindingResult) {
        // formObject.setNickname(HtmlUtils.htmlEscape(formObject.getNickname()));
        userService.save(formObject);

        return "redirect:/users";
    }

    /**
     * 更新用户数据.
     *
     * @param formObject 表单数据
     * @return
     */
    @RequestMapping(value = "", method = RequestMethod.PUT)
    public String put(User formObject, HttpSession session, HttpServletRequest request, String fixToken) {
        // 防御csrf攻击, 可以放在全局拦截器中间做校验, 只要有传递fixToken参数
//        String sessionFixToken = (String) session.getAttribute("fixToken");
//        if (sessionFixToken == null || !sessionFixToken.equals(fixToken)) {
//            LOG.error("遭遇csrf攻击注意检查. ip : {}", request.getRemoteAddr());
//            throw new RuntimeException("token校验失败");
//        }
        // 防御csrf攻击 结束

        User user = (User) session.getAttribute("user");
        if (formObject.getId() == null) {
            formObject.setId(user.getId());
        }
        if (StringUtils.isEmpty(formObject.getPassword())) {
            formObject.setPassword(user.getPassword());
        }
        if (StringUtils.isEmpty(formObject.getUsername())) {
            formObject.setUsername(user.getUsername());
        }
        userService.update(formObject);
        session.setAttribute("user", formObject);
        return "redirect:/users";
    }

    /**
     * 删除用户.
     *
     * @param id 用户id.
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delete(@PathVariable Integer id) {
        userService.delete(id);
        return "redirect:/users";
    }

}
