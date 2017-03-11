package com.muhayu.web;

import com.muhayu.domain.User;
import com.muhayu.domain.UserRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("login")
    public String login() {
        return "user/login";
    }

    @GetMapping("/{id}/form")
    public String updateForm(@PathVariable long id, Model model) {
        model.addAttribute("user", repository.findOne(id));
        return "user/updateForm";
    }

    @PutMapping("/update")
    public String update(User user, Model model) {
        repository.save(user);
        return "redirect:/users";
    }
}
