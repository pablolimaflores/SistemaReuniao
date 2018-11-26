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

import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.ReuniaoService;

@RestController
@RequestMapping(value = "/api/reunioes")
public class ReuniaoRestController {		
	
	@Autowired
    private ReuniaoService reuniaoService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<Reuniao> findAllReunioes() {
        return this.reuniaoService.findAllReunioes();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Reuniao findReuniaoById( @PathVariable long id ) {
        return this.reuniaoService.findReuniaoById( id );
    }
    
    /**
     * 
     * @param reuniao
     * @return
     */
    @PostMapping
    public Reuniao insertReuniao( @RequestBody Reuniao reuniao ) {
    	return this.reuniaoService.insertReuniao( reuniao );
    }

    /**
     * 
     * @param id
     * @param reuniao
     * @return
     */
    @PutMapping("/{id}")
    public Reuniao updateReuniao( @RequestBody Reuniao reuniao ) {
        return this.reuniaoService.updateReuniao( reuniao );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.reuniaoService.deleteReuniao( id );
	}
    
}
