package com.muhayu.web;

import com.muhayu.domain.User;
import com.muhayu.domain.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by hyecheon on 2017. 3. 7..
 */
@Log4j
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/create")
    public String create(User user) {
        log.info(user);
        repository.save(user);
        return "redirect:/users";
    }

    @GetMapping("/create")
    public String create() {
        return "user/form";
    }

    @GetMapping("")
    public String list(Model model) {
        model.addAttribute("users", repository.findAll());
        return "user/list";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.removeAttribute(HttpSessionUtil.USER_SESSION_KEY);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String login() {
        return "user/login";
    }

    @PostMapping("login")
    public String login(String userId, String password, HttpSession session) {
        User user = repository.findByUserId(userId);
        if (user == null) {
            log.info("Login Failure > user is null");
            return "redirect:/users/login";
        }
        if (!user.matchUserPassword(password)) {
            log.info("Login Failure > password miss match");
            return "redirect:/users/login";
        }
        log.info("Login success");
        session.setAttribute(HttpSessionUtil.USER_SESSION_KEY, user);
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id, Model model, HttpSession session) {
        if (loginChecked(id, session)) return "redirect:/users/login";
        model.addAttribute("user", repository.findOne(id));
        return "user/updateForm";
    }

    @PutMapping("/update")
    public String update(User user, Model model, HttpSession session) {
        if (loginChecked(user.getId(), session)) return "redirect:/users/login";
        repository.save(user);
        return "redirect:/users";
    }

    private boolean loginChecked(@PathVariable long id, HttpSession session) {
        final User sessionUser = (User) session.getAttribute(HttpSessionUtil.USER_SESSION_KEY);
        if (HttpSessionUtil.isLoginUser(session)) {
            return true;
        }
        HttpSessionUtil.isUserCheck(id, sessionUser);
        return false;
    }

}
