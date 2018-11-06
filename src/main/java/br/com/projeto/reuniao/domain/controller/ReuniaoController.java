package br.com.projeto.reuniao.domain.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String findAllReunioes(@PageableDefault(size=5) Pageable pageable, Model model) {
    	Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
    }
    
    @PostMapping("**/filter")    
    public String findReunioesByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(size=3) Pageable pageable, Model model) {       
        Page<Reuniao> page = reuniaoService.findReunioesByFilter(filter, pageable);
        model.addAttribute("page", page);        
        return "reunioes/reunioesList";
    }
    
    @GetMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String findReuniaoById(Model model, @PathVariable(required = false, name = "id") Long id) {
    	model.addAttribute("tipos", this.tipoService.findAllTipos());
        if (null != id) {
            model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
        } else {
            model.addAttribute("reuniao", new Reuniao());
        }
        return "reunioes/reunioesEdit";
    }
    
    @PostMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String updateReuniao(@Valid Reuniao reuniao, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
    	
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
    	Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
    }

    @GetMapping("/reunioesDelete/{id}")
    public String reunioesDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
        this.reuniaoService.deleteReuniao(id);
        Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
    }
}
