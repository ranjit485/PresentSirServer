package com.ranjit.ps.controller;

import com.ranjit.ps.model.Location;
import com.ranjit.ps.model.User;
import com.ranjit.ps.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/Presentsir")
public class AppController {


    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("home");
        return "welcome";
    }

    @ResponseBody
    @GetMapping("/test")
    public String test() {
        return "Hello World";
    }

}
