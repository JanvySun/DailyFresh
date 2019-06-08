package cn.hnist.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

public final class CookieUtils {

    /**
     * 将Cookie封装到Map中
     */
    public static Map<String, Cookie> CookiesToMap(HttpServletRequest request) {
        Map<String, Cookie> cookieMap = new HashMap<>();
        Cookie[] cookies = request.getCookies();
        if (null != cookies) {
            for (Cookie cookie : cookies) {
                cookieMap.put(cookie.getName(), cookie);
            }
        }
        return cookieMap;
    }

    /**
     * 根据Cookie名获取cookie
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        Map<String, Cookie> cookieMap = CookiesToMap(request);
        return cookieMap.getOrDefault(name, null);
    }

    /**
     * 添加Cookie
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        Cookie cookie = new Cookie(name.trim(), value.trim());
        cookie.setMaxAge(60 * 60 * 24); // 设置为1天
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    /**
     * 删除cookie
     */
    public static void delCookie(HttpServletRequest request, HttpServletResponse response, String name) {
        Cookie cookie = getCookie(request, name);
        if (null!=cookie){
            cookie.setValue(null);
            cookie.setMaxAge(0);
            cookie.setPath("/");
            response.addCookie(cookie);
        }
    }

}
