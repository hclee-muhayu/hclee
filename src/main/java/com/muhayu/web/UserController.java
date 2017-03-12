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
        session.removeAttribute("sessionUser");
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
            log.info("Login Failure");
            return "redirect:/users/login";
        }
        if (!password.equals(user.getPassword())) {
            log.info("Login Failure");
            return "redirect:/users/login";
        }
        log.info("Login success");
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id, Model model, HttpSession session) {
        final User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getId() != id) {
            return "redirect:/users/login";
        }
        model.addAttribute("user", repository.findOne(id));
        return "user/updateForm";
    }

    @PutMapping("/update")
    public String update(User user, Model model, HttpSession session) {
        final User sessionUser = (User) session.getAttribute("sessionUser");
        if (sessionUser == null || sessionUser.getId() != user.getId()) {
            return "redirect:/users/login";
        }
        repository.save(user);
        return "redirect:/users";
    }
}
