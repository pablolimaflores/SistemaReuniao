package br.com.projeto.reuniao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
            model.addAttribute("usuario", this.usuarioService.findUsuarioById(id));
        } else {
            model.addAttribute("usuario", new Usuario());
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
    
}
