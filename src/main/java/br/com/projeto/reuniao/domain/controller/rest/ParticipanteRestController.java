package br.com.projeto.reuniao.domain.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.service.ParticipanteService;

@RestController
@RequestMapping(value = "/api/participantes")
public class ParticipanteRestController {		
	
	@Autowired
    private ParticipanteService participanteService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<Participante> findAllParticipantes() {
        return this.participanteService.findAllParticipantes();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Participante findParticipanteById( @PathVariable long id ) {
        return this.participanteService.findParticipanteById( id );
    }
    
    /**
     * 
     * @param participante
     * @return
     */
    @PostMapping
    public Participante insertParticipante( @RequestBody Participante participante ) {
    	return this.participanteService.insertParticipante( participante );
    }

    /**
     * 
     * @param id
     * @param participante
     * @return
     */
    @PutMapping("/{id}")
    public Participante updateParticipante( @RequestBody Participante participante ) {
        return this.participanteService.updateParticipante( participante );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.participanteService.deleteParticipante( id );
	}
    
}
