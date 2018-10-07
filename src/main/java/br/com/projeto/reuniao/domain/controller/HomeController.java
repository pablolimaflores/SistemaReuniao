package br.com.projeto.reuniao.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import br.com.projeto.reuniao.domain.service.ReuniaoService;

@Controller
public class HomeController {
	
	@Autowired
    ReuniaoService reuniaoService;
	
	@GetMapping("/")
    public String indice() {        
        return "login";
    }
    
	@GetMapping("/index")
	//Gambiarra temporaria
    public String index(Model model) {      
		model.addAttribute("reunioesList", this.reuniaoService.findAllReunioes());
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
	
	@GetMapping("/403")
    public String Error403(){
        return "403";
    }
	
}
