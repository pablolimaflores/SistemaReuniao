package br.com.projeto.reuniao.controller;

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

import br.com.projeto.reuniao.domain.entity.Usuario;
import br.com.projeto.reuniao.service.UsuarioService;

@RestController
@RequestMapping(value = "/api/usuarios")
public class UsuarioController {

	@Autowired
    private UsuarioService usuarioService;

	/**
	 * 
	 * @return
	 */
    @GetMapping
    public List<Usuario> findAllUsuarios() {
        return this.usuarioService.findAllUsuarios();
    }
    
    /**
     * 
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Usuario findUsuarioById( @PathVariable long id ) {
        return this.usuarioService.findUsuarioById( id );
    }
    
    /**
     * 
     * @param usuario
     * @return
     */
    @PostMapping
    public Usuario insertUsuario( @RequestBody Usuario usuario ) {
    	return this.usuarioService.insertUsuario( usuario );
    }

    /**
     * 
     * @param id
     * @param usuario
     * @return
     */
    @PutMapping("/{id}")
    public Usuario updateUsuario( @RequestBody Usuario usuario ) {
        return this.usuarioService.updateUsuario( usuario );
    }
    
    /**
	 * 
	 * @param id
	 * @param projetoId
	 */
	@DeleteMapping("/{id}")
	public void deleteLicaoAprendida( @PathVariable long id ) {
		this.usuarioService.deleteUsuario( id );
	}
    
}
