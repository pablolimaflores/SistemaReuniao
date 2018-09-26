package br.com.projeto.reuniao.controller;

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

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.service.PessoaService;

@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);
    
    @GetMapping("")    
    public String findAllPessoas(Model model) {
        model.addAttribute("pessoasList", this.pessoaService.findAllPessoas());
        return "pessoas/pessoasList";
    }
    
    @GetMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String findPessoaById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("pessoa", this.pessoaService.findPessoaById(id));
        } else {
            model.addAttribute("pessoa", new Pessoa());
        }
        return "pessoas/pessoasEdit";
    }

//    @PostMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
//    public String updatePessoa(Model model, Pessoa pessoa, @PathVariable(required = false, name = "id") Long id) {
//    	if (null != id) {
//    		this.pessoaService.updatePessoa(pessoa);
//    	} else {
//    		this.pessoaService.insertPessoa(pessoa);
//    	}    	
//        model.addAttribute("pessoasList", this.pessoaService.findAllPessoas());
//        return "pessoas/pessoasList";
//    }
    
    @PostMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String updatePessoa(Model model, @Valid Pessoa pessoa, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("pessoa", pessoa);
            return "pessoas/pessoasEdit";
        }
    	
    	if (null != id) {
    		this.pessoaService.updatePessoa(pessoa);
    	} else {
    		this.pessoaService.insertPessoa(pessoa);
    	}    	
        model.addAttribute("pessoasList", this.pessoaService.findAllPessoas());
        return "pessoas/pessoasList";
    }

    @GetMapping("/pessoasDelete/{id}")
    public String pessoasDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        this.pessoaService.deletePessoa(id);
        model.addAttribute("pessoasList", this.pessoaService.findAllPessoas());
        return "pessoas/pessoasList";
    }
}
