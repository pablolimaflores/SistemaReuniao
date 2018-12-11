package br.com.projeto.reuniao.domain.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
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
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.projeto.reuniao.SistemaReuniaoApp;
import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.domain.service.ParticipanteService;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;
import br.com.projeto.reuniao.domain.service.TipoParticipanteService;
import br.com.projeto.reuniao.domain.service.TipoService;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;

/**
 * Controller utilizado para manipular o acesso as funcionalidades do sistema na
 * entidade Reuniao.
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

	/**
	 * Injeção de dependência
	 */
	@Autowired
	ParticipanteService participanteService;

	/**
	 * Injeção de dependência
	 */
	@Autowired
	TipoParticipanteService tipoParticipanteService;

	/**
	 * Datasource para o report
	 */
	@Autowired
	private DataSource dataSource;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReuniaoController.class);

	/**
	 * Método para listagem de todas as reuniões cadastradas no sistema, em formato
	 * de paginação, com tamanho máximo de 5 registros exibidos por página.
	 * 
	 * @param pageable
	 *            argumento para possibilitar paginar as informações vindas das
	 *            consultas.
	 * @param model
	 *            utilizado para inserir um objeto ou uma informação que será
	 *            renderizada na página.
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
	 * @param model
	 *            utilizado para inserir um objeto ou uma informação que será
	 *            renderizada na página.
	 * @param pageable
	 *            argumento para possibilitar paginar as informações vindas das
	 *            consultas.
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
	 * Método para buscar um registro no banco de dados pelo ID caso necessário,
	 * caso o id seja encontrado na base de dados, as informações do objeto são
	 * retornadas para serem manipuladas, caso o id não seja encontrado, é inserido
	 * um novo registro na sessão para que seja possível, em seguida ser montado o
	 * objeto e persistido na base de dados.
	 * 
	 * @param model
	 *            utilizado para inserir um objeto ou uma informação que será
	 *            renderizada na página.
	 * @param id
	 *            utilizado para buscar as informações do objeto reuniao.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping(value = { "/reunioesEdit", "/reunioesEdit/{id}" })
	public String findReuniaoById(Model model, @PathVariable(required = false, name = "id") Long id) {
		model.addAttribute("participantes", this.participanteService.findAllParticipantes());
		if (null != id) {
			model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
		} else {
			model.addAttribute("reuniao", new Reuniao());
		}
		return "reunioes/reunioesEdit";
	}

	/**
	 * 
	 * @return
	 */
	@ModelAttribute("pessoas")
	public List<Pessoa> findPessoas() {
		return this.pessoaService.findAllPessoas();
	}

	/**
	 * 
	 * @return
	 */
	@ModelAttribute("tipos")
	public List<Tipo> findTipos() {
		return this.tipoService.findAllTipos();
	}

	/**
	 * 
	 * @return
	 */
	@ModelAttribute("tiposParticipante")
	public List<TipoParticipante> findTiposParticipante() {
		return this.tipoParticipanteService.findAllTiposParticipante();
	}

	/**
	 * 
	 * @return
	 */
	@ModelAttribute("participantes")
	public List<Participante> listParticipantesByReuniaoId(Long id) {
		if (null != id) {
			return this.participanteService.listParticipanteByReuniaoId(id);
		} else {
			return new ArrayList<Participante>();
		}
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/reunioesExec", "/reunioesExec/{id}" })
	public String findReuniaoForExecById(Model model, @PathVariable(required = false, name = "id") Long id) {
		model.addAttribute("tipos", this.tipoService.findAllTipos());
		if (null != id) {
			model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
			model.addAttribute("pontosPauta", this.pontoPautaService.listPontoPautaByReuniaoId(id));
			model.addAttribute("pontoPauta", new PontoPauta());
		} else {
			model.addAttribute("reuniao", new Reuniao());
		}
		return "reunioes/reunioesExec";
	}

	/**
	 * Método utilizado para inserir um novo registro e também para atualizar um
	 * registro existente na base de dados. Ao verificar se o registro em questão já
	 * está no banco, se sim retorna as informações do mesmo para ser editado, se
	 * não insere um objeto novo na base de dados.
	 * 
	 * @param reuniao
	 *            objeto que vem montado do formulário para ser persistido no banco
	 *            de dados.
	 * @param bindingResult
	 *            utilizado como argumento para validar o objeto.
	 * @param id
	 *            utilizado para buscar as informações do objeto Reuniao, em caso de
	 *            edição.
	 * @param pageable
	 *            argumento para possibilitar paginar as informações vindas das
	 *            consultas.
	 * @param model
	 *            utilizado para inserir um objeto ou uma informação que será
	 *            renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@PostMapping(value = { "/reunioesEdit", "/reunioesEdit/{id}" })
	public String updateReuniao(@Valid Reuniao reuniao, BindingResult bindingResult,
			@PathVariable(required = false, name = "id") Long id,
			@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {

		if (bindingResult.hasErrors()) {
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
	 * @param id
	 *            utilizado para excluir o registro.
	 * @param pageable
	 *            argumento para possibilitar paginar as informações vindas das
	 *            consultas.
	 * @param model
	 *            utilizado para inserir um objeto ou uma informação que será
	 *            renderizada na página.
	 * @return retorno para a página de renderização.
	 */
	@GetMapping("/reunioesDelete/{id}")
	public String reunioesDelete(@PathVariable(required = true, name = "id") Long id,
			@PageableDefault(SistemaReuniaoApp.MAXROWS) Pageable pageable, Model model) {
		this.reuniaoService.deleteReuniao(id);
		Page<Reuniao> page = reuniaoService.findAllReunioesPage(pageable);
		model.addAttribute("page", page);
		return "reunioes/reunioesList";
	}

	@GetMapping(value = { "/reunioesExec/{id}/pontoPauta", "/reunioesExec/{id}/pontoPauta/{idPontoPauta}" })
	public String findPontoPautaById(@PathVariable(required = true, name = "id") Long id,
			@PathVariable(required = false, name = "idPontoPauta") Long idPontoPauta, Model model) {

		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
		model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(1));

		if (null != idPontoPauta) {
			model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(idPontoPauta));
		} else {
			model.addAttribute("pontoPauta", new PontoPauta());
		}
		return "reunioes/reunioesExec";
	}

	@ModelAttribute("responsavelList")
	public List<Pessoa> resp() {
		return this.pessoaService.findAllPessoas();
	}

	@ModelAttribute("tipoList")
	public List<Tipo> tipos() {
		return this.tipoService.findAllTipos();
	}

	@PostMapping(value = { "/pontoPauta/reuniao/{idReuniao}" })
	public String registrarPauta(@PathVariable(required = true, name = "idReuniao") Long id,
			@RequestParam(name = "idPauta") Long idPauta, @RequestParam(name = "disc") String disc, Model model) {

		PontoPauta p = this.pontoPautaService.findPontoPautaById(idPauta);
		model.addAttribute("pontoPauta", p);

		model.addAttribute("pontosPauta", this.pontoPautaService.listPontoPautaByReuniaoId(id));

		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));

		p.setDiscussao(disc);
		this.pontoPautaService.updatePontoPauta(p);
		System.out.println("Foi!!!!!!!!!");

		return "reunioes/reunioesExec";
	}

	@PostMapping(value = { "/reunioesExec/{id}/pontoPauta", "/reunioesExec/{id}/pontoPauta/{idPontoPauta}" })
	public String updtatePontoPautaById(@Valid PontoPauta pontoPauta, BindingResult bindingResult,
			@PathVariable(required = true, name = "id") Long id,
			@PathVariable(required = false, name = "idPontoPauta") Long idPontoPauta, Model model) {

		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
		model.addAttribute("pontoPauta", this.pontoPautaService.findPontoPautaById(idPontoPauta));

		if (bindingResult.hasErrors()) {
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
		List<PontoPauta> pontosPauta = pontoPautaService.findAllPontoPautas();
		model.addAttribute("pontosPauta", pontosPauta);
		// Page<PontoPauta> page =
		// pontoPautaService.findAllPontoPautasPageable(pageable);
		// model.addAttribute("page", page);

		return "reunioes/reunioesExec";
	}

	/**
	 * 
	 * @param model
	 * @param id
	 * @return
	 */
	@GetMapping(value = { "/participantesEdit/reuniao", "/participantesEdit/reuniao/{idReuniao}" })
	public String findReuniaoForParticipantesByReuniaoId(Model model,
			@PathVariable(required = false, name = "idReuniao") Long idReuniao, Participante participante) {
		
		model.addAttribute("tiposParticipante", this.tipoParticipanteService.findAllTiposParticipante());
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(idReuniao));
		model.addAttribute("participantes", this.participanteService.listParticipanteByReuniaoId(idReuniao));

		return "reunioes/participantesEdit";
	}

	@PostMapping(value = { "/participantesEdit/reuniao/{idReuniao}/participante/{id}" })
	public String insertParticipanteById(Participante participante, BindingResult bindingResult,
			@PathVariable("id") Long id, @PathVariable("idReuniao") Long idReuniao, Model model) {

		model.addAttribute("participantes", this.participanteService.listParticipanteByReuniaoId(idReuniao));
		model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(idReuniao));
//		model.addAttribute("participante", this.participanteService.findParticipanteById(id));

//		if (bindingResult.hasErrors()) {
//			bindingResult.getAllErrors().forEach(err -> {
//				LOGGER.info("ERROR {}", err.getDefaultMessage());
//			});
//			model.addAttribute("participante", participante);
//			return "reunioes/participanteEdit";
//		}

		this.participanteService.insertParticipante(participante);		

		List<Participante> participantes = participanteService.listParticipanteByReuniaoId(id);
		model.addAttribute("participantes", participantes);

		return "reunioes/participanteEdit";
	}

	/*------------------------------------------------------------------- 
	 *                REPORTS 
	 *-------------------------------------------------------------------*/

	@GetMapping(value = "/{id}/ata")
	@ResponseBody
	public void exportAtaReuniaoToPDF(@PathVariable("id") long id, HttpServletResponse response)
			throws JRException, IOException, SQLException {

		InputStream jasperStream = this.getClass().getResourceAsStream("/report/ata/ata-reuniao.jasper");

		Map<String, Object> params = new HashMap<>();
		params.put("reuniaoId", id);
		params.put("SUBREPORT_DIR",
				this.getClass().getResourceAsStream("/report/ata/participante/participantes.jasper"));
		params.put("SUBREPORT_DIR2",
				this.getClass().getResourceAsStream("/report/ata/ponto-pauta/pontos-pauta.jasper"));

		JasperReport jasperReport = (JasperReport) JRLoader.loadObject(jasperStream);
		JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, dataSource.getConnection());

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
		String fileName = "ata-reuniao-" + LocalDateTime.now().format(formatter);
		String fileFormat = ".pdf";

		response.setContentType("application/x-pdf");
		response.setHeader("Content-disposition", "inline; filename=" + fileName + fileFormat);
		// response.setContentType("application/odt");
		// response.setHeader("Content-disposition", "inline;
		// filename=ata-reuniao.odt");

		final OutputStream outStream = response.getOutputStream();
		JasperExportManager.exportReportToPdfStream(jasperPrint, outStream);

	}

}
