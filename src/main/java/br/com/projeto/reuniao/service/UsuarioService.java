package br.com.projeto.reuniao.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.projeto.reuniao.domain.entity.Usuario;
import br.com.projeto.reuniao.repository.IUsuarioRepository;

@Service
public class UsuarioService {
	
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
		
		final Usuario usuarioSaved = this.usuarioRepository.save( usuario );
		return usuarioSaved;
	}
	
	/**
	 * 
	 * @param usuario
	 * @return
	 */
	public Usuario updateUsuario( Usuario usuario ) {
		
		final Usuario usuarioSaved = this.usuarioRepository.findById( usuario.getId() )
				.orElseThrow(() -> new IllegalArgumentException( "Não foi possível atualizar o registro. "
							+"Registro de Usuario com id "+usuario.getId() + " não encontrado." ) );
		
		return usuarioSaved;
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
}
