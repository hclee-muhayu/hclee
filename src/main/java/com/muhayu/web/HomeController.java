package com.muhayu.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Created by hyecheon on 2017. 3. 12..
 */
@Controller
public class HomeController {
    @GetMapping("")
    public String home() {
        return "index";
    }
}
