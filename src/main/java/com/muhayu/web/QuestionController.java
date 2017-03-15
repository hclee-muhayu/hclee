package com.muhayu.web;

import com.muhayu.HttpSessionUtil;
import com.muhayu.domain.Question;
import com.muhayu.domain.QuestionRepository;
import com.muhayu.domain.User;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
@Log4j
@Controller
@RequestMapping("/questions")
public class QuestionController {

    @Autowired
    private QuestionRepository questionRepository;

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
        questionRepository.save(new Question(userFromSession, title, contents));
        return "redirect:/";
    }

    @GetMapping("/{id}")
    public String show(@PathVariable final long id, final Model model) {
        log.info("show");
        final Question one = questionRepository.findOne(id);
        System.out.println(one.getWriter());
        model.addAttribute("question", one);
        return "/qna/show";

    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable final Long id, Model model, HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        User loginUser = HttpSessionUtil.getUserFromSession(session);
        final Question question = questionRepository.findOne(id);
        if (!question.isSameWriter(loginUser)) {
            return "/users/login";
        }
        model.addAttribute("question", question);
        return "/qna/updateForm";
    }

    @PutMapping(value = "/{id}")
    public String update(@PathVariable final Long id, Question updateQuestion, HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        User loginUser = HttpSessionUtil.getUserFromSession(session);
        final Question question = questionRepository.findOne(id);
        if (!question.isSameWriter(loginUser)) {
            return "/users/login";
        }
        final Question storedQuestion = questionRepository.findOne(id);
        storedQuestion.update(updateQuestion);
        questionRepository.save(storedQuestion);
        return String.format("redirect:/questions/%d", id);
    }

    @DeleteMapping(value = "/{id}")
    public String delete(@PathVariable Long id, HttpSession session) {
        if (!HttpSessionUtil.isLoginUser(session)) {
            return "/users/login";
        }
        User loginUser = HttpSessionUtil.getUserFromSession(session);
        final Question question = questionRepository.findOne(id);
        if (!question.isSameWriter(loginUser)) {
            return "/users/login";
        }
        questionRepository.delete(id);
        return "redirect:/";
    }
}
