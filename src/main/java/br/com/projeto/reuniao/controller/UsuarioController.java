package br.com.projeto.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.projeto.reuniao.domain.entity.Usuario;
import br.com.projeto.reuniao.service.UsuarioService;

//@RestController
@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    UsuarioService usuarioService;
    
    @GetMapping("")    
    public String findAllUsuarios(Model model) {
        model.addAttribute("usuariosList", this.usuarioService.findAllUsuarios());
        return "usuarios/usuariosList";
    }
    
    @GetMapping(value={"/usuariosEdit","/usuariosEdit/{id}"})
    public String findUsuarioById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("usuarios", this.usuarioService.findUsuarioById(id));
        } else {
            model.addAttribute("usuarios", new Usuario());
        }
        return "usuarios/usuariosEdit";
    }

    @PostMapping(value={"/usuariosEdit","/usuariosEdit/{id}"})
    public String updateUsuario(Model model, Usuario usuario, @PathVariable(required = false, name = "id") Long id) {
    	if (null != id) {
    		this.usuarioService.updateUsuario(usuario);
    	} else {
    		this.usuarioService.insertUsuario(usuario);
    	}    	
        model.addAttribute("usuariosList", this.usuarioService.findAllUsuarios());
        return "usuarios/usuariosList";
    }

    @GetMapping("/usuariosDelete/{id}")
    public String usuariosDelete(Model model, @PathVariable(required = true, name = "id") Long id) {
        this.usuarioService.deleteUsuario(id);
        model.addAttribute("usuariosList", this.usuarioService.findAllUsuarios());
        return "usuarios/usuariosList";
    }
			
	
//	@Autowired
//    private UsuarioService usuarioService;
//
//	/**
//	 * 
//	 * @return
//	 */
//    @GetMapping
//    public List<Usuario> findAllUsuarios() {
//        return this.usuarioService.findAllUsuarios();
//    }
//    
//    /**
//     * 
//     * @param id
//     * @return
//     */
//    @GetMapping("/{id}")
//    public Usuario findUsuarioById( @PathVariable long id ) {
//        return this.usuarioService.findUsuarioById( id );
//    }
//    
//    /**
//     * 
//     * @param usuario
//     * @return
//     */
//    @PostMapping
//    public Usuario insertUsuario( @RequestBody Usuario usuario ) {
//    	return this.usuarioService.insertUsuario( usuario );
//    }
//
//    /**
//     * 
//     * @param id
//     * @param usuario
//     * @return
//     */
//    @PutMapping("/{id}")
//    public Usuario updateUsuario( @RequestBody Usuario usuario ) {
//        return this.usuarioService.updateUsuario( usuario );
//    }
//    
//    /**
//	 * 
//	 * @param id
//	 * @param projetoId
//	 */
//	@DeleteMapping("/{id}")
//	public void deleteLicaoAprendida( @PathVariable long id ) {
//		this.usuarioService.deleteUsuario( id );
//	}
    
}
