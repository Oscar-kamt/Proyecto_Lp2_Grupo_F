package com.tiendaropa.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


import jakarta.servlet.http.HttpSession;



@Controller
public class HomeController {



@GetMapping("/")
public String inicio(HttpSession session){


    if(session.getAttribute("usuario") == null){

        return "redirect:/login";

    }


    return "index";

}


}