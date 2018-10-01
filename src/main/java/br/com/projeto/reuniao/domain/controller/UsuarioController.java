package br.com.projeto.reuniao.domain.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.domain.entity.Usuario;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.UsuarioService;

//@RestController
@Controller
@RequestMapping(value = "/usuarios")
public class UsuarioController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioController.class);
	
  @Autowired
  UsuarioService usuarioService;
  
  @Autowired
  PessoaService pessoaService;
  
  
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
          model.addAttribute("pessoas", this.pessoaService.findAllPessoas());
      }
      return "usuarios/usuariosEdit";
  }

//  @PostMapping(value={"/usuariosEdit","/usuariosEdit/{id}"})
//  public String updateUsuario(Model model, Usuario usuario, @PathVariable(required = false, name = "id") Long id) {
//  	if (null != id) {
//  		this.usuarioService.updateUsuario(usuario);
//  	} else {
//  		this.usuarioService.insertUsuario(usuario);
//  	}    	
//      model.addAttribute("usuariosList", this.usuarioService.findAllUsuarios());
//      return "usuarios/usuariosList";
//  }
  
  @PostMapping(value={"/usuariosEdit","/usuariosEdit/{id}"})
  public String updateUsuario(Model model, @Valid Usuario usuario, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id) {
  	
  	if(bindingResult.hasErrors()) {
          bindingResult.getAllErrors().forEach(err -> {
              LOGGER.info("ERROR {}", err.getDefaultMessage());
          });
          model.addAttribute("usuario", usuario);
          return "usuarios/usuariosEdit";
      }
  	
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
