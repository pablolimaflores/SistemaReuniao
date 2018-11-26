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

import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.service.PontoPautaService;

@RestController
@RequestMapping(value = "/api/pontosPauta")
public class PontoPautaRestController {		
	
	@Autowired
    private PontoPautaService pontoPautaService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<PontoPauta> findAllPontosPauta() {
        return this.pontoPautaService.findAllPontoPautas();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public PontoPauta findPontoPautaById( @PathVariable long id ) {
        return this.pontoPautaService.findPontoPautaById( id );
    }
    
    /**
     * 
     * @param pontoPauta
     * @return
     */
    @PostMapping
    public PontoPauta insertPontoPauta( @RequestBody PontoPauta pontoPauta ) {
    	return this.pontoPautaService.insertPontoPauta( pontoPauta );
    }

    /**
     * 
     * @param id
     * @param pontoPauta
     * @return
     */
    @PutMapping("/{id}")
    public PontoPauta updatePontoPauta( @RequestBody PontoPauta pontoPauta ) {
        return this.pontoPautaService.updatePontoPauta( pontoPauta );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.pontoPautaService.deletePontoPauta( id );
	}
    
}
