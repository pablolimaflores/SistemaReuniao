package br.com.projeto.reuniao.domain.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.service.TipoService;

/**
 *Controller utilizado para manipular o acesso as funcionalidades do sistema na entidade Reuniao. 
 */
@Controller
@RequestMapping(value = "/tipos")
public class TipoController {

	/**
	 * Injeção de dependência
	 */
    @Autowired
    TipoService tipoService;    
    
    private static final Logger LOGGER = LoggerFactory.getLogger(TipoController.class);
    

    /**
	 * Método para listagem de todas os tipos de reuniões cadastradas no sistema, em formato de paginação, com tamanho
     * máximo de 5 registros exibidos por página.
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
    @GetMapping("")
    public String findAllTipos(@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {       
        Page<Tipo> page = tipoService.findAllTipos(pageable);
        model.addAttribute("page", page);
        return "tipos/tiposList";
    }
    
    /**
     * Método para filtrar as buscas por nome, em formato de páginação e com tamanho 
     * máximo de 5 registros exibidos por página
     * 
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @return retorno para a página de renderização.
     */
    @PostMapping("**/filter")    
    public String findTiposByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {       
        Page<Tipo> page = tipoService.findTiposByFilter(filter, pageable);
        model.addAttribute("page", page);        
        return "tipos/tiposList";
    }  
    
    /**
     * Método para buscar um registro no banco de dados pelo ID caso necessário, caso o id seja
     * encontrado na base de dados, as informações do objeto são retornadas para serem manipuladas,
     * caso o id não seja encontrado, é inserido um novo registro na sessão para que seja possível,
     * em seguida ser montado o objeto e persistido na base de dados.
     * 
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @param id utilizado para buscar as informações do objeto reuniao.
     * @return retorno para a página de renderização.
     */
    @GetMapping(value={"/tiposEdit","/tiposEdit/{id}"})	
    public String findTipoById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("tipo", this.tipoService.findTipoById(id));
        } else {
            model.addAttribute("tipo", new Tipo());
        }
        return "tipos/tiposEdit";
    }
    
    /**
     * Método utilizado para inserir um novo registro e também para atualizar um registro existente na
     * base de dados. Ao verificar se o registro em questão já está no banco, se sim retorna as informações
     * do mesmo para ser editado, se não insere um objeto novo na base de dados.
     * 
     * @param tipo objeto que vem montado do formulário para ser persistido no banco de dados.
     * @param bindingResult utilizado como argumento para validar o objeto.
     * @param id utilizado para buscar as informações do objeto Tipo, em caso de edição.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @PostMapping(value={"/tiposEdit","/tiposEdit/{id}"})
    public String updateTipo(@Valid Tipo tipo, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("tipo", tipo);
            return "tipos/tiposEdit";
        }
    	
    	if (null != id) {
    		this.tipoService.updateTipo(tipo);
    	} else {
    		this.tipoService.insertTipo(tipo);
    	}    	
    	Page<Tipo> page = tipoService.findAllTipos(pageable);
        model.addAttribute("page", page);
        return "tipos/tiposList";
    }

    /**
     * Método utilizado para exclusão de registros na base de dados
     * 
     * @param id utilizado para excluir o registro.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @GetMapping("/tiposDelete/{id}")
    public String tiposDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, 
    		Model model) {
        this.tipoService.deleteTipo(id);
        Page<Tipo> page = tipoService.findAllTipos(pageable);
        model.addAttribute("page", page);
        return "tipos/tiposList";
    }
}
