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

import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.TipoService;

@Controller
@RequestMapping(value="/pontosPauta")
public class PontoPautaController {

	@Autowired
	PontoPautaService pontoPautaService;
	
	@Autowired
	PessoaService pessoaService;
	
	@Autowired
	TipoService tipoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PontoPautaController.class);
	
	@GetMapping("")
	public String findAllPontoPauta(Model model) {
		model.addAttribute("pontoPautaList", pontoPautaService.findAllPontoPautas());
		return "pontosPauta/pontosPautaList";
	}
	
	@GetMapping(value={"/pontoPautaEdit","/pontoPautaEdit/{id}"})
	public String findPontoPautaById(Model model, @PathVariable(required = false, name = "id") Long id){
		if (null != id) {
            model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(id));
        } else {
            model.addAttribute("pontoPauta", new PontoPauta());
            model.addAttribute("responsavelList", pessoaService.findAllPessoas());
    		model.addAttribute("tipoList", tipoService.findAllTipos());
        }
        return "pontosPauta/pontoPautaEdit";
	}
	@PostMapping(value={"pontoPautaEdit", "pontoPautaEdit/{id}"})
	public String updatePontoPauta(Model model, @Valid PontoPauta pontoPauta, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id) {
		
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("pontoPauta", pontoPauta);
            return "pontosPauta/pontoPautaEdit";
        }
    	
    	if (null != id) {
    		this.pontoPautaService.updatePontoPauta(pontoPauta);
    	} else {
    		this.pontoPautaService.insertPontoPauta(pontoPauta);
    	}    	
        model.addAttribute("pontoPautaList", this.pontoPautaService.findAllPontoPautas());
        return "pontosPauta/pontoPautaList";
	}
	@GetMapping("/pontoPautaDelete/{id}")
	public String pontoPautaDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
		this.pontoPautaService.deletePontoPauta(id);
		model.addAttribute("pontoPautaList", this.pontoPautaService.findAllPontoPautas());
		return "pontosPauta/pontoPautaList";
	}
	
}
