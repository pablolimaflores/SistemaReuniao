package br.com.projeto.reuniao.domain.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;
import br.com.projeto.reuniao.domain.service.TipoService;

/**
 *Controller utilizado para manipular o acesso as funcionalidades do sistema na entidade Pauta. 
 */
@Controller
@RequestMapping(value="/pontosPauta")
public class PontoPautaController {

	/**
	 * Injeção de dependência
	 */
	@Autowired
	PontoPautaService pontoPautaService;
	
	/**
	 * Injeção de dependência
	 */
	@Autowired
	PessoaService pessoaService;
	
	/**
	 * Injeção de dependência
	 */
	@Autowired
	TipoService tipoService;
	
	/**
	 * Injeção de dependência
	 */
	@Autowired
	ReuniaoService reuniaoService;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(PontoPautaController.class);
	
	/**
	 * Método para listagem de todas as pautas cadastradas no sistema, em formato de paginação, com tamanho
     * máximo de 5 registros exibidos por página.
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("")
	public String findAllPontoPauta(@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
		return "pontosPauta/pontosPautaList";
	}
	
	/**
	 * 
	 * @return
	 */
	@ModelAttribute("responsavelList")
	public List<Pessoa> findResponsavel(){
		return pessoaService.findAllPessoas();
	}
	
	/**
	 * Método para inserir uma lista de objetos na model para serem exibidas
	 * no formulário de cadastro de pautas.
	 * 
	 * @return
	 */
	@ModelAttribute("tipoList")
	public List<Tipo> findTipos(){
		return tipoService.findAllTipos();
	}
	
	/**
	 * Método para buscar um registro no banco de dados pelo ID caso necessário, caso o id seja
     * encontrado na base de dados, as informações do objeto são retornadas para serem manipuladas,
     * caso o id não seja encontrado, é inserido um novo registro na sessão para que seja possível,
     * em seguida ser montado o objeto e persistido na base de dados.
     * 
	 * @param id utilizado para buscar as informações do objeto pauta.
	 * @param idReuniao utilizado para buscar informações do objeto reunião. 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping(value={"/pontoPautaEdit/reuniao/{idReuniao}","/pontoPautaEdit/{id}/reuniao/{idReuniao}"})
	public String findPontoPautaById(@PathVariable(required = false, name = "id") Long id, @PathVariable(required = true, name = "idReuniao") Long idReuniao, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model){
		
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(idReuniao));
		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
		
		if (null != id) {
            model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(id));
        } else {
            model.addAttribute("pontoPauta", new PontoPauta());
        }
        return "pontosPauta/pontoPautaEdit";
	}
	
	/**
	 * Método utilizado para inserir um novo registro e também para atualizar um registro existente na
     * base de dados. Ao verificar se o registro em questão já está no banco, se sim retorna as informações
     * do mesmo para ser editado, se não insere um objeto novo na base de dados.
	 * 
	 * @param pontoPauta objeto que vem montado do formulário para ser persistido no banco de dados.
	 * @param bindingResult utilizado como argumento para validar o objeto.
	 * @param id utilizado para buscar as informações do objeto pauta, em caso de edição.
	 * @param idReuniao utilizado para buscar as informações do objeto reuniao.
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@PostMapping(value={"/pontoPautaEdit/reuniao/{idReuniao}", "/pontosPautaEdit/{id}/reuniao/{idReuniao}"})
	public String updatePontoPauta(@Valid PontoPauta pontoPauta, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PathVariable(required = true, name = "idReuniao") Long idReuniao, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
		
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("pontoPauta", pontoPauta);
            return "pontosPauta/pontoPautaEdit";
        }
    	if (null != id) {
    		this.pontoPautaService.updatePontoPauta(pontoPauta);
    	} else {
    		this.pontoPautaService.insertPontoPauta(pontoPauta);
    	}    	
    	Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
        return "pontosPauta/pontoPautaList";
	}
	/**
	 * Método utilizado para exclusão de registros na base de dados.
	 * 
	 * @param id utilizado para excluir o registro.
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("/pontoPautaDelete/{id}")
	public String pontoPautaDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
		this.pontoPautaService.deletePontoPauta(id);
		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
		model.addAttribute("page", page);
		return "pontosPauta/pontoPautaList";
	}	
}
