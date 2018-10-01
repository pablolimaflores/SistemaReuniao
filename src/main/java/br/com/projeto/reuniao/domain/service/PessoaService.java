package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.repository.IPessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private IPessoaRepository pessoaRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Pessoa> findAllPessoas() {
        return this.pessoaRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Pessoa findPessoaById( long id ) {
		
		final Pessoa pessoaSaved = this.pessoaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Pessoa com id "+ id + " não encontrado." ) );
		return pessoaSaved;
    }
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa insertPessoa( Pessoa pessoa ) {
    	
		pessoa.refreshCreatedAndUpdated();		
		return this.pessoaRepository.save( pessoa );		
	}
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa updatePessoa( Pessoa pessoa ) {
		
		this.pessoaRepository.findById(pessoa.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Pessoa com id "+pessoa.getId() + " não encontrado." ) );
		pessoa.refreshUpdated();
		
		return this.pessoaRepository.saveAndFlush( pessoa );
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deletePessoa( long id ) {
		
		final Pessoa pessoaSaved = this.pessoaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Pessoa com id "+ id + " não encontrado." ) );
		
		this.pessoaRepository.deleteById( pessoaSaved.getId() );
    }
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countPessoas() {
		return this.pessoaRepository.count();
	}
}
