package br.com.projeto.reuniao.domain.service;

import java.time.LocalDate;
import java.time.LocalTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.entity.Pessoa;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.entity.Role;
import br.com.projeto.reuniao.domain.entity.Tipo;
import br.com.projeto.reuniao.domain.entity.TipoParticipante;

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
    TipoService tipoService;
    
    @Autowired
    TipoParticipanteService tipoParticipanteService;
    
    @Autowired
    ReuniaoService reuniaoService;
    
    @Autowired
    ParticipanteService participanteService;
    
    @Autowired
    PontoPautaService pontoPautaService;

    /**
     * Inicializa o sistema com alguns cadastros, caso não existam registros na tabela
     */
    @EventListener(ApplicationReadyEvent.class)
    public void initializeTestData() {
    	LOGGER.info("Initialize test data");
    	
    	Pessoa admin = new Pessoa("Administrador do Sistema", "admin@admin.com", "(45) 3030-3030", "(45) 99999-9999", true, "admin", Role.ROLE_ADMIN);
    	Pessoa user = new Pessoa("Usuario padrão do sistema", "user@user.com", "(45) 2020-2020", "(45) 98888-8888", true, "user", Role.ROLE_USER);
    	Pessoa joao = new Pessoa("João da Silva", "joao@joao.com", "(51) 3210-3210", "(51) 98765-4321", true, "user", Role.ROLE_USER);
    	Pessoa maria = new Pessoa("Maria dos Santos", "maria@maria.com", "(11) 2345-5432", "(45) 91234-5678", true, "user", Role.ROLE_USER);
    	
    	if(pessoaService.countPessoas() == 0) {    		
			pessoaService.insertPessoa(admin);						  
			pessoaService.insertPessoa(user);
			pessoaService.insertPessoa(joao);
			pessoaService.insertPessoa(maria);						
    	}
    	
    	Tipo informativo = new Tipo("Informativo", "Deve apresentar tema relativo a decisão em outras instâncias e que afetam direta ou indiretamente os envolvidos.", false);
    	Tipo deliberativo = new Tipo("Deliberativo", "Deve apresentar o tema de consulta aos integrantes, devendo considerar tempo extra para debate, para que todos os integrantes possam tenham o mesmo tempo para se pronunciar.", true);
    	Tipo brainstorm = new Tipo("Brainstorm", "Idealmente deve ser uma reunião com poucos ou apenas um tema de pauta, dividido em duas partes, apresentação de ideias (todas as ideias são válidas, até as mais absurdas e devem ser anotadas pelo secretario) e debate das ideiais, onde as devem ser aprimoradas ou descartadas.", true);
    	Tipo trabalho = new Tipo("Trabalho", "Focada na realização de uma atividade ou demanda em tempo determinado, idealmente 2hs.", true);
    	
    	if (tipoService.countTipos() == 0) {
    		tipoService.insertTipo(informativo);
	        tipoService.insertTipo(deliberativo);
	        tipoService.insertTipo(brainstorm);
	        tipoService.insertTipo(trabalho);		            	
    	}
    	
    	TipoParticipante solicitante = new TipoParticipante("Solicitante", "Pessoa responsável por agendar e informar todos os dados necessários para marcar a reunião.");
    	TipoParticipante mediador = new TipoParticipante("Mediador", "Quem conduzirá a reunião (poderá ser a mesma pessoa que solicitou a reunião ou não). Engloba a funções de facilitador e colaborador.");
    	TipoParticipante secretario = new TipoParticipante("Secretário", "Possui a função de anotador e é responsável por registrar sobre o andamento da reunião e fazer a ata de reunião.");
    	TipoParticipante integrante = new TipoParticipante("Integrante", "Demais pessoas que irão compor a reunião e discutir sobre a mesma.");
    	
    	if (tipoParticipanteService.countTiposParticipante() == 0) {
    		tipoParticipanteService.insertTipoParticipante(solicitante);
	        tipoParticipanteService.insertTipoParticipante(mediador);
	        tipoParticipanteService.insertTipoParticipante(secretario);
	        tipoParticipanteService.insertTipoParticipante(integrante);
    	}
    	
    	Reuniao reuniao = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 10, 2), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao1 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 9, 30), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(9, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao2 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 8, 30), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(8, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao3 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 7, 30), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(7, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao4 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 6, 30), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(6, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao5 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 5, 30), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(5, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	
    	Reuniao reuniao6 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 10, 15), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao7 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 10, 31), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao8 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 10, 25), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao9 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 11, 22), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao10 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 11, 22), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao11 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 12, 25), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	
    	if (reuniaoService.countReunioes() == 0) {    		
    		reuniaoService.insertReuniao(reuniao);
    		reuniaoService.insertReuniao(reuniao1);
    		reuniaoService.insertReuniao(reuniao2);
    		reuniaoService.insertReuniao(reuniao3);
    		reuniaoService.insertReuniao(reuniao4);
    		reuniaoService.insertReuniao(reuniao5);
    		reuniaoService.insertReuniao(reuniao6);
    		reuniaoService.insertReuniao(reuniao7);
    		reuniaoService.insertReuniao(reuniao8);
    		reuniaoService.insertReuniao(reuniao9);
    		reuniaoService.insertReuniao(reuniao10);
    		reuniaoService.insertReuniao(reuniao11);
    		
    	}
    	
    	Participante participanteAdmin = new Participante(true, admin, reuniao, solicitante);
    	Participante participanteJoao = new Participante(true, joao, reuniao, mediador);
    	Participante participanteMaria = new Participante(true, maria, reuniao, secretario);
    	Participante participanteUser = new Participante(true, user, reuniao, integrante);
    	
    	if (participanteService.countParticipantes() == 0) {    		
    		participanteService.insertParticipante(participanteAdmin);
    		participanteService.insertParticipante(participanteJoao);
    		participanteService.insertParticipante(participanteMaria);
    		participanteService.insertParticipante(participanteUser);
    	}
    	
//    	PontoPauta pontoPauta1 = new PontoPauta(1, "Pauta 01", 30, "", joao, informativo);
//    	PontoPauta pontoPauta2 = new PontoPauta(2, "Pauta 02", 60, "", joao, informativo);
//    	
//    	if (pontoPautaService.countPontoPauta() == 0) {
//    		pontoPautaService.insertPontoPauta(pontoPauta1);
//    		pontoPautaService.insertPontoPauta(pontoPauta2);
//    	}
    	    	
    	LOGGER.info("Initialization completed");
    }

}
