package br.com.projeto.reuniao.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	
    @RequestMapping("/")
    public String indice() {        
        return "login";
    }
    
    @RequestMapping("/index")
    public String index() {        
        return "index";
    }
    
    @RequestMapping("/home")
    public String home() {        
        return "home";
    }
    
    @RequestMapping("/login")
    public String login() {        
        return "login";
    }
    
    @RequestMapping("/logout")
    public String logout() {        
        return "logout";
    }
	
}
