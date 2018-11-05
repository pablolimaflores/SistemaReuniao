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

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.service.PessoaService;

@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

    @Autowired
    PessoaService pessoaService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);
    
    @GetMapping("")    
    public String findAllPessoas(@PageableDefault(size=5) Pageable pageable, Model model){
        Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "pessoas/pessoasList";
    }
    
    @PostMapping("**/filter")    
    public String findPessoasByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(size=3) Pageable pageable, Model model) {       
        Page<Pessoa> page = pessoaService.findPessoasByFilter(filter, pageable);
        model.addAttribute("page", page);        
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
    
    @PostMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String updatePessoa(@Valid Pessoa pessoa, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
    	
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
    	Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "pessoas/pessoasList";
    }

    @GetMapping("/pessoasDelete/{id}")
    public String pessoasDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
        this.pessoaService.deletePessoa(id);
        Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "pessoas/pessoasList";
    }
}
