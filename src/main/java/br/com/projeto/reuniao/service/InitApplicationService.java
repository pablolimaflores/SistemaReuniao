package br.com.projeto.reuniao.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.entity.TipoParticipante;
import br.com.projeto.reuniao.domain.entity.Usuario;

/**
 * 
 * @author Pablo
 *
 */
@Service
public class InitApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(InitApplicationService.class);

    @Autowired
    PessoaService pessoaService;
    
    @Autowired
    UsuarioService usuarioService;
    
    @Autowired
    TipoService tipoService;
    
    @Autowired
    TipoParticipanteService tipoParticipanteService;

    /**
     * Initialize the test data
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeTestData() {
    	LOGGER.info("Initialize test data");
    	
    	Pessoa administrador = new Pessoa("Administrador do Sistema", "pablolimaflores@gmail.com");
    	
//    	if (pessoaService.findPessoaById(1L) == null) {	        	       
//	        pessoaService.insertPessoa(administrador);	        		       
//    	}
//    	
//    	Usuario admin = new Usuario("admin", "1234", true, administrador);
//    	
//    	if (!usuarioService.findAllUsuarios().contains(admin)) {	        	       
//    		usuarioService.insertUsuario(admin);	        		       
//    	}
//    	
//    	Tipo informativo = new Tipo("Informativo", "Deve apresentar tema relativo a decisão em outras instâncias e que afetam direta ou indiretamente os envolvidos.", false);
//    	
//    	if (!tipoService.findAllTipos().contains(informativo)) {	        	       
//	        tipoService.insertTipo(informativo);
//	        tipoService.insertTipo(new Tipo("Deliberativo", "Deve apresentar o tema de consulta aos integrantes, devendo considerar tempo extra para debate, para que todos os integrantes possam tenham o mesmo tempo para se pronunciar.", true));
//	        tipoService.insertTipo(new Tipo("Brainstorm", "Idealmente deve ser uma reunião com poucos ou apenas um tema de pauta, dividido em duas partes, apresentação de ideias (todas as ideias são válidas, até as mais absurdas e devem ser anotadas pelo secretario) e debate das ideiais, onde as devem ser aprimoradas ou descartadas.", true));
//	        tipoService.insertTipo(new Tipo("Trabalho", "Focada na realização de uma atividade ou demanda em tempo determinado, idealmente 2hs.", true));		        
//    	}
//    	
//    	TipoParticipante solicitante = new TipoParticipante("Solicitante", "Pessoa responsável por agendar e informar todos os dados necessários para marcar a reunião.");
//    	if (!tipoParticipanteService.findAllTipoParticipantes().contains(solicitante)) {	        
//	        tipoParticipanteService.insertTipoParticipante(solicitante);
//	        tipoParticipanteService.insertTipoParticipante(new TipoParticipante("Mediador", "Quem conduzirá a reunião (poderá ser a mesma pessoa que solicitou a reunião ou não). Engloba a funções de facilitador e colaborador."));
//	        tipoParticipanteService.insertTipoParticipante(new TipoParticipante("Secretário", "Possui a função de anotador e é responsável por registrar sobre o andamento da reunião e fazer a ata de reunião."));
//	        tipoParticipanteService.insertTipoParticipante(new TipoParticipante("Integrante", "Demais pessoas que irão compor a reunião e discutir sobre a mesma."));
//    	}
//    	
    	LOGGER.info("Initialization completed");
    }

}
