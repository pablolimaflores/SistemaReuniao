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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.service.PessoaService;

/**
 *Controller utilizado para manipular o acesso as funcionalidades do sistema na entidade Pessoa. 
 */
@Controller
@RequestMapping(value = "/pessoas")
public class PessoaController {

	/**
	 * Injeção de dependência
	 */
    @Autowired
    PessoaService pessoaService;
    
    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);
    
    /**
     * Método para listagem de todas as pessoas cadastradas no sistema, em formato de paginação, com tamanho
     * máximo de 5 registros exibidos por página.
     * 
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @return retorno para a página de renderização.
     */
    @GetMapping("")    
    public String findAllPessoas(@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model){
        Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "pessoas/pessoasList";
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
    public String findPessoasByFilter(@RequestParam("filter") String filter, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {       
        Page<Pessoa> page = pessoaService.findPessoasByFilter(filter, pageable);
        model.addAttribute("page", page);        
        return "pessoas/pessoasList";
    }
    
    /**
     * Método para buscar um registro no banco de dados pelo ID caso necessário, caso o id seja
     * encontrado na base de dados, as informações do objeto são retornadas para serem manipuladas,
     * caso o id não seja encontrado, é inserido um novo registro na sessão para que seja possível,
     * em seguida ser montado o objeto e persistido na base de dados.
     * 
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @param id utilizado para buscar as informações do objeto a ser pesquisado.
     * @return retorno para a página de renderização.
     */
    @GetMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String findPessoaById(Model model, @PathVariable(required = false, name = "id") Long id) {
        if (null != id) {
            model.addAttribute("pessoa", this.pessoaService.findPessoaById(id));
        } else {
            model.addAttribute("pessoa", new Pessoa());
        }
        return "pessoas/pessoasEdit";
    }
    
    /**
     * Método utilizado para inserir um novo registro e também para atualizar um registro existente na
     * base de dados. Ao verificar se o registro em questão já está no banco, se sim retorna as informações
     * do mesmo para ser editado, se não insere um objeto novo na base de dados.
     * 
     * @param pessoa objeto que vem montado do formulário para ser persistido no banco de dados.
     * @param bindingResult utilizado como argumento para validar o objeto.
     * @param id utilizado para buscar as informações do objeto a ser pesquisado.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @PostMapping(value={"/pessoasEdit","/pessoasEdit/{id}"})
    public String updatePessoa(@Valid Pessoa pessoa, BindingResult bindingResult, 
    		@PathVariable(required = false, name = "id") Long id, 
    		@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, 
    		RedirectAttributes attr,
    		Model model) {
    	
    	if(bindingResult.hasErrors()) {
            bindingResult.getAllErrors().forEach(err -> {
                LOGGER.info("ERROR {}", err.getDefaultMessage());
            });
            model.addAttribute("pessoa", pessoa);
            return "pessoas/pessoasEdit";
        }
    	
    	if (null != id) {    		
    		this.pessoaService.updatePessoa(pessoa);
//    		attr.addFlashAttribute("success", "Pessoa atualizada com sucesso.");
    	} else {
    		this.pessoaService.insertPessoa(pessoa);
//    		attr.addFlashAttribute("success", "Pessoa inserida com sucesso.");
    	}   
    	attr.addFlashAttribute("success", "Operação efetuada com sucesso.");
    	Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "redirect:/pessoas";
    }

    
    /**
     * Método utilizado para exclusão de registros na base de dados.
     * 
     * @param id utilizado para excluir o registro na base de dados.
     * @param pageable argumento para possibilitar paginar as informações vindas das consultas.
     * @param model model utilizado para inserir um objeto ou uma informação que será renderizada na página.
     * @return retorno para a página de renderização.
     */
    @GetMapping("/pessoasDelete/{id}")
    public String pessoasDelete(@PathVariable(required = true, name = "id") Long id, @PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
    	model.addAttribute("success", "Pessoa excluida com sucesso.");
    	this.pessoaService.deletePessoa(id);
        Page<Pessoa> page = pessoaService.findAllPessoas(pageable);
        model.addAttribute("page", page);
        return "pessoas/pessoasList";
    }
}
