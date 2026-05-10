package com.smartparking.config;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;

@Controller
public class SpaController {

    @RequestMapping(value = {
        "/admin/**",
        "/staff/**",
        "/user/**"
    })
    public String forward() {
        return "forward:/index.html";
    }
}
