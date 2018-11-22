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

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.domain.service.TipoParticipanteService;

@Controller
@RequestMapping(value = "/tiposParticipante")
public class TipoParticipanteController {

    @Autowired
    TipoParticipanteService tipoParticipanteService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoParticipanteController.class);
    
    @GetMapping("")    
    public String findAllTipoParticipantes(@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
        Page<TipoParticipante> page = tipoParticipanteService.findAllTiposParticipantePage(pageable);
        model.addAttribute("page", page);
        return "tiposParticipante/tiposParticipanteList";
    }
    
    @PostMapping("**/filter")    
    public String findTiposParticipanteByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {       
        Page<TipoParticipante> page = tipoParticipanteService.findTiposParticipanteByFilter(filter, pageable);
        model.addAttribute("page", page);        
        return "tiposParticipante/tiposParticipanteList";
    } 
    
    @GetMapping(value={"/tiposParticipanteEdit","/tiposParticipanteEdit/{id}"})
    public String findTipoParticipanteById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("tipoParticipante", this.tipoParticipanteService.findTipoParticipanteById(id));
        } else {
            model.addAttribute("tipoParticipante", new TipoParticipante());
        }
        return "tiposParticipante/tiposParticipanteEdit";
    }
    
    @PostMapping(value={"/tiposParticipanteEdit","/tiposParticipanteEdit/{id}"})
    public String updateTipoParticipante(@Valid TipoParticipante tipoParticipante, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PageableDefault(size=5) Pageable pageable, Model model) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("tipoParticipante", tipoParticipante);
            return "tiposParticipante/tiposParticipanteEdit";
        }
    	
    	if (null != id) {
    		this.tipoParticipanteService.updateTipoParticipante(tipoParticipante);
    	} else {
    		this.tipoParticipanteService.insertTipoParticipante(tipoParticipante);
    	}    	
    	 Page<TipoParticipante> page = tipoParticipanteService.findAllTiposParticipantePage(pageable);
         model.addAttribute("page", page);
        return "tiposParticipante/tiposParticipanteList";
    }

    @GetMapping("/tiposParticipanteDelete/{id}")
    public String tiposParticipanteDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
        this.tipoParticipanteService.deleteTipoParticipante(id);
        Page<TipoParticipante> page = tipoParticipanteService.findAllTiposParticipantePage(pageable);
        model.addAttribute("page", page);
        return "tiposParticipante/tiposParticipanteList";
    }
}
