package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.repository.IPessoaRepository;

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
		final Pessoa pessoaSaved = this.pessoaRepository.save( pessoa );
		return pessoaSaved;
	}
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa updatePessoa( Pessoa pessoa ) {
		
		Pessoa pessoaSaved = this.pessoaRepository.findById(pessoa.getId())
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Pessoa com id "+pessoa.getId() + " não encontrado." ) );
		pessoaSaved.refreshUpdated();
		return pessoaSaved;
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
}