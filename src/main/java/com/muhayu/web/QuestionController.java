package com.muhayu.web;

import com.muhayu.domain.Question;
import com.muhayu.domain.QuestionRepository;
import com.muhayu.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
@Log4j
@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository repository;

    @GetMapping("/form")
    public String form(HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        return "/qna/form";
    }

    @PostMapping("")
    public String create(String title, String contents, HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        final User userFromSession = HttpSessionUtil.getUserFromSession(session);
        assert userFromSession != null;
        final Question question = new Question(userFromSession.getUserId(), title, contents);
        log.info(question);
        repository.save(question);
        return "redirect:/";
    }
}
