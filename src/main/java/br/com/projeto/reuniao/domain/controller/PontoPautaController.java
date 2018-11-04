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

import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;
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
	
	@Autowired
	ReuniaoService reuniaoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PontoPautaController.class);
	
	@GetMapping("")
	public String findAllPontoPauta(@PageableDefault(size=5) Pageable pageable, Model model) {
		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
		return "pontosPauta/pontosPautaList";
	}
	
	@GetMapping(value={"/pontoPautaEdit/reuniao/{idReuniao}","/pontoPautaEdit/{id}/reuniao/{idReuniao}"})
	public String findPontoPautaById(Model model, @PathVariable(required = false, name = "id") Long id, @PathVariable(required = true, name = "idReuniao") Long idReuniao){
		
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(idReuniao));
		model.addAttribute("responsavelList", pessoaService.findAllPessoas());
		model.addAttribute("tipoList", tipoService.findAllTipos());
		
		if (null != id) {
            model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(id));
        } else {
            model.addAttribute("pontoPauta", new PontoPauta());
        }
        return "pontosPauta/pontoPautaEdit";
	}
	@PostMapping(value={"/pontoPautaEdit/reuniao/{idReuniao}", "/pontoPautaEdit/{id}/reuniao/{idReuniao}"})
	public String updatePontoPauta(@Valid PontoPauta pontoPauta, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PathVariable(required = true, name = "idReuniao") Long idReuniao, @PageableDefault(size=5) Pageable pageable, Model model) {
		
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
    	Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
        return "pontosPauta/pontoPautaList";
	}
	@GetMapping("/pontoPautaDelete/{id}")
	public String pontoPautaDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
		this.pontoPautaService.deletePontoPauta(id);
		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
		return "pontosPauta/pontoPautaList";
	}
	
}
