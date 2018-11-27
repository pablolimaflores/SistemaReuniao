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
import org.springframework.web.bind.annotation.RequestParam;

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;
import br.com.projeto.reuniao.domain.service.TipoService;


/**
 *Controller utilizado para manipular o acesso as funcionalidades do sistema na entidade Reuniao. 
 */
@Controller
@RequestMapping(value = "/reunioes")
public class ReuniaoController {

	/**
	 * Injeção de dependência
	 */
    @Autowired
    ReuniaoService reuniaoService; 
    
    /**
	 * Injeção de dependência
	 */
    @Autowired
    TipoService tipoService;
    
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
    
    private static final Logger LOGGER = LoggerFactory.getLogger(ReuniaoController.class);
    
    /**
	 * Método para listagem de todas as reuniões cadastradas no sistema, em formato de paginação, com tamanho
     * máximo de 5 registros exibidos por página.
     * 
	 * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
	 * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
	 * @return retorno para a página de renderização.
	 */
    @GetMapping("")    
    public String findAllReunioes(@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
    	Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
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
    public String findReunioesByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {       
        Page<Reuniao> page = reuniaoService.findReunioesByFilter(filter, pageable);
        model.addAttribute("page", page);        
        return "reunioes/reunioesList";
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
    @GetMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String findReuniaoById(Model model, @PathVariable(required = false, name = "id") Long id) {
    	model.addAttribute("tipos", this.tipoService.findAllTipos());
    	model.addAttribute("pessoas", this.pessoaService.findAllPessoas());
        if (null != id) {
            model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
        } else {
            model.addAttribute("reuniao", new Reuniao());
        }
        return "reunioes/reunioesEdit";
    }
    
    /**
     * 
     * @param model
     * @param id
     * @return
     */
    @GetMapping(value={"/reunioesExec","/reunioesExec/{id}"})
    public String findReuniaoForExecById(Model model, @PathVariable(required = false, name = "id") Long id) {
    	model.addAttribute("tipos", this.tipoService.findAllTipos());
        if (null != id) {
            model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
            model.addAttribute("pontosPauta", this.pontoPautaService.findAllPontoPautas());
            model.addAttribute("pontoPauta", new PontoPauta());
        } else {
            model.addAttribute("reuniao", new Reuniao());
        }
        return "reunioes/reunioesExec";
    }
    
    /**
     * Método utilizado para inserir um novo registro e também para atualizar um registro existente na
     * base de dados. Ao verificar se o registro em questão já está no banco, se sim retorna as informações
     * do mesmo para ser editado, se não insere um objeto novo na base de dados.
     * 
     * @param reuniao objeto que vem montado do formulário para ser persistido no banco de dados.
     * @param bindingResult utilizado como argumento para validar o objeto.
     * @param id utilizado para buscar as informações do objeto Reuniao, em caso de edição.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @PostMapping(value={"/reunioesEdit","/reunioesEdit/{id}"})
    public String updateReuniao(@Valid Reuniao reuniao, BindingResult bindingResult, @PathVariable(required = false, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("reuniao", reuniao);
            return "reunioes/reunioesEdit";
        }
    	
    	if (null != id) {
    		this.reuniaoService.updateReuniao(reuniao);
    	} else {
    		this.reuniaoService.insertReuniao(reuniao);
    	}    	
    	Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
    }

    /**
     * Método utilizado para exclusão de registros na base de dados
     * 
     * @param id utilizado para excluir o registro.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @GetMapping("/reunioesDelete/{id}")
    public String reunioesDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
        this.reuniaoService.deleteReuniao(id);
        Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
    	model.addAttribute("page", page);
        return "reunioes/reunioesList";
    }    
    
    @GetMapping(value={"/reunioesExec/{id}/pontoPauta","/reunioesExec/{id}/pontoPauta/{idPontoPauta}"})
	public String findPontoPautaById(@PathVariable(required = true, name = "id") Long id, @PathVariable(required = false, name = "idPontoPauta") Long idPontoPauta, Model model){
		
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));	
		model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(1));
		
		if (null != idPontoPauta) {
            model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(idPontoPauta));
        } else {
            model.addAttribute("pontoPauta", new PontoPauta());
        }	
		return "reunioes/reunioesExec";
	}
    
    @ModelAttribute("pontosPauta")
    public List<PontoPauta> pontos(){
    	return this.pontoPautaService.findAllPontoPautas();
    }
    
    @GetMapping(value= {"/pontoPauta/{id}/reuniao/{idReuniao}"})
    public String registrarPauta(@Valid PontoPauta pontoPauta, BindingResult bindingResult, @PathVariable(required = true, name = "id") Long id,@PathVariable(required = true, name = "idReuniao") Long idReuniao, Model model) {
    		
    	model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(id));
    	model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(idReuniao));
    	
    	if(null != id) {
    		this.pontoPautaService.updatePontoPauta(pontoPauta);
    		System.out.println("Foi!!!!!!!!!");
    	}
    	
    	return "reunioes/reunioesExec";
    }
    
    @PostMapping(value={"/reunioesExec/{id}/pontoPauta","/reunioesExec/{id}/pontoPauta/{idPontoPauta}"})
	public String updtatePontoPautaById(@Valid PontoPauta pontoPauta, BindingResult bindingResult, @PathVariable(required = true, name = "id") Long id, 
			@PathVariable(required = false, name = "idPontoPauta") Long idPontoPauta, Model model){
		
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));	
		model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(idPontoPauta));
		
		if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("pontoPauta", pontoPauta);
            return "pontosPauta/pontoPautaEdit";
        }
    	
    	if (null != id) {
    		this.pontoPautaService.updatePontoPauta(pontoPauta);
    	}else {
    		this.pontoPautaService.insertPontoPauta(pontoPauta);
    	}    	
    	List<PontoPauta> pontosPauta = pontoPautaService.findAllPontoPautas();
		model.addAttribute("pontosPauta", pontosPauta);
//		Page<PontoPauta> page = pontoPautaService.findAllPontoPautasPageable(pageable);
//		model.addAttribute("page", page);
        		
		return "reunioes/reunioesExec";
	}
}
