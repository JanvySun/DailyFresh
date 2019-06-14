package cn.hnist.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 拦截器，该拦截器拦截/admin/* 的内容
 */
public class AdminInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {

        // 获取请求uri
        String uri = request.getRequestURI();
        // 如果是登录请求，放行
        if (uri.contains("login"))
            return true;

        // 获取session
        HttpSession session = request.getSession();
        Object user = session.getAttribute("admin");
        if (user != null)
            return true;

        // 未登录且不是指定的页面，转发到登录页面
        response.sendRedirect(request.getContextPath()+"/admin/login");
        return false;
    }
}
