package br.com.projeto.reuniao.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import br.com.projeto.reuniao.application.security.ContextHolder;
import br.com.projeto.reuniao.application.security.UsuarioSecurity;
import br.com.projeto.reuniao.domain.entity.Usuario;
import br.com.projeto.reuniao.domain.repository.IUsuarioRepository;

@Service
public class UsuarioService implements UserDetailsService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public List<Usuario> findAllUsuarios() {
        return this.usuarioRepository.findAll();
    }
	
	/**
	 * 
	 * @return
	 */
	@Transactional(readOnly = true)
	public Usuario findUsuarioById( long id ) {
		
		final Usuario usuarioSaved = this.usuarioRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Usuario com id "+ id + " não encontrado." ) );
		return usuarioSaved;
    }
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario insertUsuario( Usuario usuario ) {
		
		usuario.refreshCreatedAndUpdated();
		usuario.activate();
		/*CRIPTOGRAMA E INFORMA A SENHA*/
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		
		return this.usuarioRepository.save( usuario );		
	}
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario updateUsuario( Usuario usuario ) {
		
		this.usuarioRepository.findById( usuario.getId() )
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Usuario com id "+usuario.getId() + " não encontrado." ) );
		usuario.refreshUpdated();
		/*CRIPTOGRAMA E INFORMA A SENHA*/
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return this.usuarioRepository.saveAndFlush( usuario );
	}
	
	/**
	 * 
	 * @param novaSenha
	 * @param id
	 * @return
	 */
	public Usuario updateSenhaUsuario( String novaSenha, Long id ) {
		
		Usuario usuarioSaved = this.usuarioRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Usuario com id "+id + " não encontrado." ) );
		usuarioSaved.refreshUpdated();
		/*CRIPTOGRAMA E INFORMA A SENHA*/
		usuarioSaved.setSenha(new BCryptPasswordEncoder().encode(novaSenha));
		return this.usuarioRepository.saveAndFlush( usuarioSaved );
	}
	
	/**
	 * 
	 * @param id
	 */
	public void deleteUsuario( long id ) {
		
		final Usuario usuarioSaved = this.usuarioRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Registro de Usuario com id "+ id + " não encontrado." ) );
		
		this.usuarioRepository.deleteById( usuarioSaved.getId() );
    }
	
	/**
	 * 
	 * @param id
	 * @return
	 */
//	@PreAuthorize("hasAnyAuthority('"+ Perfil.ADMINISTRADOR_VALUE +"')")
	public Usuario updateStatusUsuario( long id ) {
		
		Usuario usuarioSaved = this.usuarioRepository.findById( id )
				.orElseThrow(() -> new IllegalArgumentException( "Usuario não encontrado." ));
		
		Usuario authenticatedUser = ContextHolder.getAuthenticatedUser();
		
		Assert.isTrue( !authenticatedUser.getId().equals( usuarioSaved.getId() ), "Acesso negado ao tentar realizar esta operação.");    	
		
		usuarioSaved.setAtivo( !usuarioSaved.getAtivo() );
		
		return this.usuarioRepository.saveAndFlush( usuarioSaved );
	}
	
	/***
	 * CONSULTA UM USUÁRIO POR LOGIN
	 */
	@Override
	public UserDetails loadUserByUsername(String login) throws BadCredentialsException,DisabledException {
 
		Usuario usuario = usuarioRepository.findByLogin(login);
 
		if(usuario == null)
			throw new BadCredentialsException("Usuário não encontrado no sistema!");
 
		if(!usuario.isEnabled())
			throw new DisabledException("Usuário não está ativo no sistema!");
 
		return new UsuarioSecurity(
				usuario.getLogin(), 
				usuario.getSenha(), 
				usuario.getAtivo(), 
				usuario.getAuthorities());
	}
	
	/**
	 * Médodo utilizado apenas para verificação de existência de registros.
	 * @return
	 */
	public long countUsuarios() {
		return this.usuarioRepository.count();
	}
	
}
