package cn.net.leadu.aop;

import com.google.gson.Gson;
import java.util.Arrays;
import javax.servlet.http.HttpServletRequest;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Created by huzongcheng on 2017/3/7.
 */
@Aspect
@Component
public class WebLogAspect {

    private static Logger logger = LoggerFactory.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * cn.net.leadu.controller..*.*(..))")
    public void webRequestLog() {
    }

    @Before("webRequestLog()")
    public void doBefore(JoinPoint joinPoint) {
        try {
            // 接收到请求，记录请求内容
            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            HttpServletRequest request = attributes.getRequest();

            String userAgent = request.getHeader("User-Agent");
            String userName = SecurityContextHolder.getContext().getAuthentication().getName();
            String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
            String url = request.getRequestURL().toString();
            String method = request.getMethod();
            String params = Arrays.toString(joinPoint.getArgs());

            logger.info("User-Agent:{}, userName: {}, ClassMethod: {}, url: {}, method: {}, params: {} ", userAgent, userName, classMethod, url, method, params);
        } catch (Exception e) {
            logger.error("***操作请求日志记录失败doBefore()***", e);
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webRequestLog()")
    public void doAfterReturning(Object ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + new Gson().toJson(ret));
    }

}
