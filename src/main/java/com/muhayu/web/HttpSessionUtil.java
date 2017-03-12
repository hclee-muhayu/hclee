package com.muhayu.web;

import com.muhayu.domain.User;

import javax.servlet.http.HttpSession;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
public class HttpSessionUtil {
    public static final String USER_SESSION_KEY = "sessionUser";

    public static boolean isLoginUser(HttpSession session) {
        final Object sessionUser = session.getAttribute(USER_SESSION_KEY);
        return sessionUser != null;
    }

    public static User getUserFromSession(HttpSession session) {
        if (!isLoginUser(session)) {
            return null;
        }
        return (User) session.getAttribute(USER_SESSION_KEY);
    }

    public static void isUserCheck(long userId, User sessionUser) {
        if (userId != sessionUser.getId()) {
            throw new IllegalStateException("you can't update the another user");
        }
    }
}
