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

import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.service.TipoService;

@Controller
@RequestMapping(value = "/tipos")
public class TipoController {

    @Autowired
    TipoService tipoService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoController.class);
    
    @GetMapping("")    
    public String findAllTipos(Model model) {
        model.addAttribute("tiposList", this.tipoService.findAllTipos());
        return "tipos/tiposList";
    }
    
    @GetMapping(value={"/tiposEdit","/tiposEdit/{id}"})
    public String findTipoById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("tipo", this.tipoService.findTipoById(id));
        } else {
            model.addAttribute("tipo", new Tipo());
        }
        return "tipos/tiposEdit";
    }

//    @PostMapping(value={"/tiposEdit","/tiposEdit/{id}"})
//    public String updateTipo(Model model, Tipo tipo, @PathVariable(required = false, name = "id") Long id) {
//    	if (null != id) {
//    		this.tipoService.updateTipo(tipo);
//    	} else {
//    		this.tipoService.insertTipo(tipo);
//    	}    	
//        model.addAttribute("tiposList", this.tipoService.findAllTipos());
//        return "tipos/tiposList";
//    }
    
    @PostMapping(value={"/tiposEdit","/tiposEdit/{id}"})
    public String updateTipo(Model model, @Valid Tipo tipo, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("tipo", tipo);
            return "tipos/tiposEdit";
        }
    	
    	if (null != id) {
    		this.tipoService.updateTipo(tipo);
    	} else {
    		this.tipoService.insertTipo(tipo);
    	}    	
        model.addAttribute("tiposList", this.tipoService.findAllTipos());
        return "tipos/tiposList";
    }

    @GetMapping("/tiposDelete/{id}")
    public String tiposDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        this.tipoService.deleteTipo(id);
        model.addAttribute("tiposList", this.tipoService.findAllTipos());
        return "tipos/tiposList";
    }
}