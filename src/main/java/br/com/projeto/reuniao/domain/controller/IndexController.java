package br.com.projeto.reuniao.domain.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.ReuniaoService;

/**
 * 
 * Controller utilizado para carregar as informações na tela principal do sistema, exibindo as reuniões que ja ocorreram 
 *e as que estam agendadas.
 */

@Controller
@RequestMapping(value = "/index")
public class IndexController {

	 @Autowired
	 ReuniaoService reuniaoService;
	
	@GetMapping("")
    public String index(@PageableDefault(size=5) Pageable pageable, Model model) { 
		Page<Reuniao> pageExec = reuniaoService.findAllReuniaoExecutedByData(pageable);
		Page<Reuniao> pageSched = reuniaoService.findAllReuniaoScheduledByData(pageable);
		model.addAttribute("pageExec", pageExec);
		model.addAttribute("pageSched", pageSched);
		return "index";
    }
}
