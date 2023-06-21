package com.example.smartdumbbell_web.Main.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/main")
    public String GoMain(){return "main";}

    @GetMapping("/test")
    public String test(){return "test2";}


}
