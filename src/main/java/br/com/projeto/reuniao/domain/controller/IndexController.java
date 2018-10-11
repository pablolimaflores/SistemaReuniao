package br.com.projeto.reuniao.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.service.ReuniaoService;

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	 @Autowired
	 ReuniaoService reuniaoService;
	
	@GetMapping("")
    public String index(Model model) {      
		model.addAttribute("reuniaoFinalizada", this.reuniaoService.findAllReuniaoExecutedByData());
		model.addAttribute("reuniaoAgendada", this.reuniaoService.findAllReuniaoScheduledByData());
		return "index";
    }
}
