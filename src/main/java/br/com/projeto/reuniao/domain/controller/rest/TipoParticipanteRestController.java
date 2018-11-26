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

import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.domain.service.TipoParticipanteService;

@RestController
@RequestMapping(value = "/api/tiposParticipante")
public class TipoParticipanteRestController {		
	
	@Autowired
    private TipoParticipanteService tipoParticipanteService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<TipoParticipante> findAllTiposParticipante() {
        return this.tipoParticipanteService.findAllTiposParticipante();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public TipoParticipante findTipoParticipanteById( @PathVariable long id ) {
        return this.tipoParticipanteService.findTipoParticipanteById( id );
    }
    
    /**
     * 
     * @param tipoParticipante
     * @return
     */
    @PostMapping
    public TipoParticipante insertTipoParticipante( @RequestBody TipoParticipante tipoParticipante ) {
    	return this.tipoParticipanteService.insertTipoParticipante( tipoParticipante );
    }

    /**
     * 
     * @param id
     * @param tipoParticipante
     * @return
     */
    @PutMapping("/{id}")
    public TipoParticipante updateTipoParticipante( @RequestBody TipoParticipante tipoParticipante ) {
        return this.tipoParticipanteService.updateTipoParticipante( tipoParticipante );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.tipoParticipanteService.deleteTipoParticipante( id );
	}
    
}
