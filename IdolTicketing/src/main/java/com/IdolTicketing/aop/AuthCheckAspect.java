package com.IdolTicketing.aop;

import com.IdolTicketing.SessionUtil;
import com.IdolTicketing.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.session.StandardSessionFacade;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpSession;
import java.util.Collections;

@Aspect
@Component
@Order(Ordered.LOWEST_PRECEDENCE)
@Log4j2
@SuppressWarnings("unchecked")
public class AuthCheckAspect {

    @Autowired
    private UserService userService;

    @Around("@annotation(com.IdolTicketing.aop.LoginCheck) && @ annotation(loginCheck)")
    public Object UserLoginCheck(ProceedingJoinPoint jp, LoginCheck loginCheck) throws Throwable {
        log.debug("AOP - User Login Check Started");
        Object[] signatureArgs = jp.getArgs();

        HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.currentRequestAttributes())).getRequest().getSession();
        String sessionUserId = null;

        boolean isAdmin = false;

        String userType = loginCheck.types().toString();

        if (SessionUtil.getLoginUserId(session) == null) {
            LoginCheck.Role USER = loginCheck.types()[1];
            isAdmin = false;
            sessionUserId = SessionUtil.getLoginUserId(session);
        } else {
            LoginCheck.Role ADMIN = loginCheck.types()[0];
            isAdmin = true;
            sessionUserId = SessionUtil.getLoginAdminId(session);
        }

        if (Collections.list(((StandardSessionFacade) session).getAttributeNames()).size() > 0) {
            if (sessionUserId == null) {
                return new ResponseEntity<>(" 권한이 없습니다. ", HttpStatus.UNAUTHORIZED);
            }
        }
        {
            if (sessionUserId == null) {
                return new ResponseEntity<>("로그인해주세요2", HttpStatus.BAD_REQUEST);
            }
        }

        if (jp.getArgs() != null) {
            signatureArgs[0] = sessionUserId;
            signatureArgs[1] = isAdmin;
        }
        return jp.proceed(signatureArgs);
    }

}