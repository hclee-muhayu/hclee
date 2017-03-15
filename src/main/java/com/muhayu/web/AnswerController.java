package com.muhayu.web;

import com.muhayu.HttpSessionUtil;
import com.muhayu.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by hyecheon on 2017. 3. 15..
 */
@Controller
@RequestMapping("/questions/{questionId}/answers")
public class AnswerController {

    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;

    @PostMapping("")
    public String create(@PathVariable Long questionId, String contents, HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        User loginUser = HttpSessionUtil.getUserFromSession(session);
        final Question question = questionRepository.findOne(questionId);
        final Answer answer = new Answer(loginUser, question, contents);
        answerRepository.save(answer);
        return String.format("redirect:/questions/%d", questionId);
    }
}
