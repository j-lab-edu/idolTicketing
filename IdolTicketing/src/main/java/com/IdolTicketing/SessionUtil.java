package com.IdolTicketing;

import javax.servlet.http.HttpSession;

public class SessionUtil {
    private static final String LOGIN_USER_ID = "LOGIN_USER_ID";
    private static final String LOGIN_ADMIN_ID = "LOGIN_ADMIN_ID";

    private SessionUtil() {

    }

    public static String getLoginUserId(HttpSession session) {
        return (String) session.getAttribute(LOGIN_USER_ID);
    }

    public static void setLoginUserId(HttpSession session, String userId) {
        session.setAttribute(LOGIN_USER_ID, userId);
    }

    public static  String getLoginAdminId(HttpSession session){
        return (String) session.getAttribute(LOGIN_ADMIN_ID);
    }

    public static void setLoginAdminId(HttpSession session,String userId){
        session.setAttribute(LOGIN_ADMIN_ID,userId);
    }

    public static void clear(HttpSession session) {
        session.invalidate();
    }
}
