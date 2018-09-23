package br.com.projeto.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    
    @GetMapping("")    
    public String findAllPessoas(Model model) {
        model.addAttribute("pessoasList", this.pessoaService.findAllPessoas());
        return "pessoas/pessoasList";
    }
    
    @GetMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String findPessoaById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("pessoas", this.pessoaService.findPessoaById(id));
        } else {
            model.addAttribute("pessoas", new Pessoa());
        }
        return "pessoas/pessoasEdit";
    }

    @PostMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String updatePessoa(Model model, Pessoa pessoa, @PathVariable(required = false, name = "id") Long id) {
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
