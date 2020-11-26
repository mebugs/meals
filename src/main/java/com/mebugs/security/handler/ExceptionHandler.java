package com.mebugs.security.handler;

import com.mebugs.data.response.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;


/**
 * 全局异常捕获处理
 * AOP机制
 *
 * @author 米虫先生/mebugs.com
 * @since 2020-10-27
 */
@Component
@Aspect
@Slf4j
public class ExceptionHandler {

    //切入点位置 只扫描sys和serv业务路径
    @Pointcut("execution(* com.mebugs.sys.*.*.*(..)) || execution(* com.mebugs.serv.*.*.*(..)) ")
    public void pointCut(){}

    /**
     * AOP 环绕 捕获异常
     * @param pjp
     * @return
     * @throws Throwable
     */
    @Around("pointCut()")
    public Object handerAround(ProceedingJoinPoint pjp) throws Throwable
    {
        String className = pjp.getTarget().getClass().getName();
        try {
            //执行方法体
            return pjp.proceed(pjp.getArgs());
        } catch (Throwable throwable) {
            //非controller向上抛出异常
            if(className.indexOf("controller")<0)
            {
                throw throwable;
            }else{
                return downException(pjp, throwable);
            }
        }
    }

    /**
     * 为Controller封装异常返回
     * @param pjp
     * @param e
     * @return
     */
    private R downException(ProceedingJoinPoint pjp, Throwable e) {
        R r;
        //可以增加自定义异常的判断
        if (e instanceof NullPointerException) {
            log.error("NullPointerException：方法：" + pjp.getSignature(), e);
            r = R.error("服务端出现意外的空指针异常，请联系运维人员处理");
        }else {
            log.error("其他异常：方法：" + pjp.getSignature() + "，异常：" + e.getMessage() , e);
            if(StringUtils.isNotBlank(e.getMessage()))
            {
                r = R.error(e.getMessage());
            }else{
                r = R.error("未知系统异常");
            }
        }
        return r;
    }

}
