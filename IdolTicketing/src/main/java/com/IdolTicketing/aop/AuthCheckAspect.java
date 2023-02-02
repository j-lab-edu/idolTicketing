package com.IdolTicketing.aop;

import com.IdolTicketing.SessionUtil;
import com.IdolTicketing.exception.CNAdminException;
import com.IdolTicketing.exception.CNLoginException;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;

@Aspect
@Component
@Log4j2
public class AuthCheckAspect {
    @Around("@annotation(com.IdolTicketing.aop.LoginCheck) && @ annotation(loginCheck)")
    public Object UserLoginCheck(ProceedingJoinPoint jp, LoginCheck loginCheck) throws Throwable {
        log.debug("AOP - User Login Check Started");
        Object[] signatureArgs = jp.getArgs();
        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String sessionUserId = null;
        boolean isAdmin = false;

        if (SessionUtil.getLoginUserId(session) != null)
            sessionUserId = SessionUtil.getLoginUserId(session);

        if (SessionUtil.getLoginAdminId(session) != null) {
            isAdmin = true;
            sessionUserId = SessionUtil.getLoginAdminId(session);
        }

        if (sessionUserId != null) {
            // ADMIN상태인데 USER만 or USER상태인데 ADMIN만 가능한 API를 호출했을때 발생시켜야한다. // 2. DB MYSQL 데이터가 있지만, 잘못된 요청
            if ((!isAdmin && loginCheck.toString().contains("ADMIN") && !loginCheck.toString().contains("USER")) ||
                    (isAdmin && loginCheck.toString().contains("USER") && !loginCheck.toString().contains("ADMIN")))
                throw new CNAdminException("");
        } else
            throw new CNLoginException("");

        if (jp.getArgs() != null) {
            signatureArgs[0] = sessionUserId;
            signatureArgs[1] = isAdmin;
        }
        return jp.proceed(signatureArgs);
    }

}