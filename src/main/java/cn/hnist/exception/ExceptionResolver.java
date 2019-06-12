package cn.hnist.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@Component("exceptionResolver")
public class ExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o, Exception e) {
        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView();

        WebException we;
        if (e instanceof WebException){
            we = (WebException) e;
        } else {
            we = new WebException("未知异常");
        }
        // 将异常信息输出在控制台
        we.printStackTrace();

        mv.addObject("info", we.getMessage());
        mv.addObject("title","服务器异常");
        mv.setViewName("info");
        return mv;
    }
}
