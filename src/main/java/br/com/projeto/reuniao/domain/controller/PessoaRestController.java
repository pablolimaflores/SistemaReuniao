package br.com.projeto.reuniao.domain.controller;

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

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.service.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaRestController {		
	
	@Autowired
    private PessoaService pessoaService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<Pessoa> findAllPessoas() {
        return this.pessoaService.findAllPessoas();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Pessoa findPessoaById( @PathVariable long id ) {
        return this.pessoaService.findPessoaById( id );
    }
    
    /**
     * 
     * @param pessoa
     * @return
     */
    @PostMapping
    public Pessoa insertPessoa( @RequestBody Pessoa pessoa ) {
    	return this.pessoaService.insertPessoa( pessoa );
    }

    /**
     * 
     * @param id
     * @param pessoa
     * @return
     */
    @PutMapping("/{id}")
    public Pessoa updatePessoa( @RequestBody Pessoa pessoa ) {
        return this.pessoaService.updatePessoa( pessoa );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.pessoaService.deletePessoa( id );
	}
    
}
