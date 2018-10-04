package br.com.projeto.reuniao.domain.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
	
	@GetMapping("/")
    public String indice() {        
        return "login";
    }
    
	@GetMapping("/index")
    public String index() {        
        return "index";
    }
    
    @GetMapping("/home")
    public String home() {        
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {        
        return "login";
    }
    	
	@GetMapping("/acessoNegado")
	public String acessoNegado(){		
		return "acessoNegado";
	}
	
}
