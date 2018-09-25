package br.com.projeto.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.service.TipoParticipanteService;

@Controller
@RequestMapping(value = "/tiposParticipante")
public class TipoParticipanteController {

    @Autowired
    TipoParticipanteService tipoParticipanteService;
    
    @GetMapping("")    
    public String findAllTipoParticipantes(Model model) {
        model.addAttribute("tiposParticipanteList", this.tipoParticipanteService.findAllTipoParticipantes());
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
    public String updateTipoParticipante(Model model, TipoParticipante tipoParticipante, @PathVariable(required = false, name = "id") Long id) {
    	if (null != id) {
    		this.tipoParticipanteService.updateTipoParticipante(tipoParticipante);
    	} else {
    		this.tipoParticipanteService.insertTipoParticipante(tipoParticipante);
    	}    	
        model.addAttribute("tiposParticipanteList", this.tipoParticipanteService.findAllTipoParticipantes());
        return "tiposParticipante/tiposParticipanteList";
    }

    @GetMapping("/tiposParticipanteDelete/{id}")
    public String tiposParticipanteDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        this.tipoParticipanteService.deleteTipoParticipante(id);
        model.addAttribute("tiposParticipanteList", this.tipoParticipanteService.findAllTipoParticipantes());
        return "tiposParticipante/tiposParticipanteList";
    }
}
