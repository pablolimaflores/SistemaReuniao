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

import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.service.TipoService;

@RestController
@RequestMapping(value = "/api/tipos")
public class TipoRestController {		
	
	@Autowired
    private TipoService tipoService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<Tipo> findAllTipos() {
        return this.tipoService.findAllTipos();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Tipo findTipoById( @PathVariable long id ) {
        return this.tipoService.findTipoById( id );
    }
    
    /**
     * 
     * @param tipo
     * @return
     */
    @PostMapping
    public Tipo insertTipo( @RequestBody Tipo tipo ) {
    	return this.tipoService.insertTipo( tipo );
    }

    /**
     * 
     * @param id
     * @param tipo
     * @return
     */
    @PutMapping("/{id}")
    public Tipo updateTipo( @RequestBody Tipo tipo ) {
        return this.tipoService.updateTipo( tipo );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.tipoService.deleteTipo( id );
	}
    
}
