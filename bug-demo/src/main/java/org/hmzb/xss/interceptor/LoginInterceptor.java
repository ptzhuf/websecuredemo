package org.hmzb.xss.interceptor;

import org.hmzb.xss.utils.IPUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.UUID;

@Component
public class LoginInterceptor implements HandlerInterceptor {
    /**
     * logger.
     */
    private static final Logger LOG = LoggerFactory
            .getLogger(LoginInterceptor.class);

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object)
     */
    /*
     * (non-Javadoc)
	 *
	 * @see
	 * org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet
	 * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object)
	 */
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        // 检查是否登录了.
        HttpSession session = request.getSession();
        String url = request.getServletPath();
        if (url.equals("/login")) {
            url = "/";
        }
        String username = (String) session.getAttribute("loginUser");
        if (StringUtils.isEmpty(username)) {

            // 防御csrf攻击
//            session.setAttribute("fixToken", UUID.randomUUID().toString().replaceAll("-", ""));
            // 防御csrf攻击 结束

            LOG.error("用户IP : {}, 未登录", IPUtils.getRequestIp(request));
            // 返回到登陆页面
            response.sendRedirect("/login?url=" + url);  // 这里要再加上 token参数防范url redirect攻击
            return false;
        }

        Cookie[] cookies = request.getCookies();
        boolean a = false;
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("username")) {
                a = true;
                break;
            }
        }
        if (!a) {
            response.addCookie(new Cookie("username", username));
        }
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#postHandle(javax.servlet
     * .http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.web.servlet.ModelAndView)
     */
    @Override
    public void postHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {
        // 什么都不做
    }

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.web.servlet.HandlerInterceptor#afterCompletion(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, java.lang.Exception)
     */
    @Override
    public void afterCompletion(HttpServletRequest request,
                                HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        // 什么都不做

    }

}
