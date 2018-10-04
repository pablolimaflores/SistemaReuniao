package br.com.projeto.reuniao.domain.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.ReuniaoService;
import br.com.projeto.reuniao.domain.service.TipoService;

@Controller
@RequestMapping(value = "/reunioes")
public class ReuniaoController {

    @Autowired
    ReuniaoService reuniaoService;
    
    @Autowired
    TipoService tipoService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReuniaoController.class);
    
    @GetMapping("")    
    public String findAllReunioes(Model model) {
        model.addAttribute("reunioesList", this.reuniaoService.findAllReunioes());
        return "reunioes/reunioesList";
    }
    
    @GetMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String findReuniaoById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
        } else {
            model.addAttribute("reuniao", new Reuniao());
            model.addAttribute("tipos", this.tipoService.findAllTipos());
        }
        return "reunioes/reunioesEdit";
    }
    
    @PostMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String updateReuniao(Model model, @Valid Reuniao reuniao, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("reuniao", reuniao);
            return "reunioes/reunioesEdit";
        }
    	
    	if (null != id) {
    		this.reuniaoService.updateReuniao(reuniao);
    	} else {
    		this.reuniaoService.insertReuniao(reuniao);
    	}    	
        model.addAttribute("reunioesList", this.reuniaoService.findAllReunioes());
        return "reunioes/reunioesList";
    }

    @GetMapping("/reunioesDelete/{id}")
    public String reunioesDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        this.reuniaoService.deleteReuniao(id);
        model.addAttribute("reunioesList", this.reuniaoService.findAllReunioes());
        return "reunioes/reunioesList";
    }
}
