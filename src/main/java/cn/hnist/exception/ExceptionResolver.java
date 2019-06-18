package cn.hnist.exception;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component("exceptionResolver")
public class ExceptionResolver implements HandlerExceptionResolver{
    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            Object o, Exception e) {
        // 创建ModelAndView对象
        ModelAndView mv = new ModelAndView();

        // 将异常信息输出在控制台
        e.printStackTrace();

        mv.addObject("info", "服务器正忙...");
        mv.addObject("title","服务器异常");
        mv.setViewName("info");
        return mv;
    }
}
