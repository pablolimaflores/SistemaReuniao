package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.projeto.reuniao.application.security.ContextHolder;
import br.com.projeto.reuniao.application.security.PessoaUsuarioSecurity;
import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.repository.IPessoaRepository;

@Service
public class PessoaService implements UserDetailsService {
	
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
	
	@Transactional(readOnly = true)
	public Page<Pessoa> findAllPessoas(Pageable pageable) {
        return this.pessoaRepository.findByFilter("", pageable);
    }
	
	@Transactional(readOnly = true)
	public Page<Pessoa> findPessoasByFilter(String filter, Pageable pageable) {
		return this.pessoaRepository.findByFilter(filter, pageable);
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
	
	@Transactional(readOnly = true)
	public List<Pessoa> findPessoaNotParticipanteByReuniaoId( long idReuniao ) {
		List<Pessoa> pessoas = null;
		
		pessoas = this.pessoaRepository.findNotParticipanteByReuniaoId(idReuniao);
		
		return pessoas;
    }
	
	/**
	 * 
	 * @param pessoa
	 * @return
	 */
	public Pessoa insertPessoa( Pessoa pessoa ) {
    	
		pessoa.setAtivo(true);
		pessoa.refreshCreatedAndUpdated();
		/*CRIPTOGRAMA E INFORMA A SENHA*/
		if (pessoa.getSenha() != null && !pessoa.getSenha().isEmpty()) {
			pessoa.setSenha(new BCryptPasswordEncoder().encode(pessoa.getSenha()));
		}
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
		/*CRIPTOGRAMA E INFORMA A SENHA*/
		if (pessoa.getSenha() != null && !pessoa.getSenha().isEmpty()) {
			pessoa.setSenha(new BCryptPasswordEncoder().encode(pessoa.getSenha()));
		}
		return this.pessoaRepository.saveAndFlush( pessoa );
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deletePessoa( long id ) {
		
		final Pessoa pessoaSaved = this.pessoaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Pessoa com id "+ id + " não encontrado." ) );
		try {
			this.pessoaRepository.deleteById( pessoaSaved.getId() );
			this.pessoaRepository.flush();
		} catch (org.springframework.dao.DataIntegrityViolationException e) {
			 throw new RuntimeException("Não é possível excluir uma pessoa que possui registros associados.");
		}
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	public Pessoa updateStatusPessoa( long id ) {
		
		Pessoa pessoaSaved = this.pessoaRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Pessoa não encontrado." ));		 
		
		Pessoa authenticatedUser = ContextHolder.getAuthenticatedUser();
		
		Assert.isTrue( !authenticatedUser.getId().equals( pessoaSaved.getId() ), "Acesso negado ao tentar realizar esta operação.");  
		
		pessoaSaved.setAtivo( !pessoaSaved.getAtivo() );
		
		return this.pessoaRepository.saveAndFlush( pessoaSaved );
	}
	
	/***
	 * CONSULTA UM USUÁRIO POR LOGIN
	 */
	@Override
	public UserDetails loadUserByUsername(String email) throws BadCredentialsException,DisabledException {
 
		Pessoa usuario = pessoaRepository.findByEmail(email);
 
		if(usuario == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");
 
		if(!usuario.isEnabled())
			throw new DisabledException("Usuário não está ativo no sistema!");
 
		return new PessoaUsuarioSecurity(
				usuario.getEmail(), 
				usuario.getSenha(), 
				usuario.getAtivo(), 
				usuario.getAuthorities());
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countPessoas() {
		return this.pessoaRepository.count();
	}
}
