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
    	Pessoa joao = new Pessoa("João da Silva", "joao@joao.com", "(51) 3210-3210", "(51) 98765-4321", true, "1234", Role.ROLE_USER);
    	Pessoa maria = new Pessoa("Maria dos Santos", "maria@maria.com", "(11) 2345-5432", "(45) 91234-5678", true, "1234", Role.ROLE_USER);
    	
    	Pessoa usuario1 = new Pessoa("Carlos Pereira", "carlos@carlos.com", "(45) 3030-3030", "(45) 99999-9999", false, null, Role.ROLE_USER);
    	Pessoa usuario2 = new Pessoa("Atanagildo Estrovaldo", "atanagildo@atanagildo.com", "(45) 2020-2020", "(45) 98888-8888", true, "1234", Role.ROLE_USER);
    	Pessoa usuario3 = new Pessoa("Rosigicleidy Marinalva Lima", "rosigicleidy@rosigicleidy.com", "(51) 3210-3210", "(51) 98765-4321", true, "1234", Role.ROLE_USER);
    	Pessoa usuario4 = new Pessoa("Zena Feira Matoso", "zena@zena.com", "(11) 2345-5432", "(45) 91234-5678", false, null, Role.ROLE_USER);
    	
    	Pessoa usuario5 = new Pessoa("Xena Oliveira", "xena@xena.com", "(45) 3030-3030", "(45) 99999-9999", true, "1234", Role.ROLE_USER);
    	Pessoa usuario6 = new Pessoa("Zara Santos", "zara@zara.com", "(45) 2020-2020", "(45) 98888-8888", true, "1234", Role.ROLE_USER);
    	Pessoa usuario7 = new Pessoa("Aristrogindson Maraes Silva", "aristrogindson@aristrogindson.com", "(51) 3210-3210", "(51) 98765-4321", false, null, Role.ROLE_USER);
    	Pessoa usuario8 = new Pessoa("Sonia Blade", "sonia@sonia.com", "(11) 2345-5432", "(45) 91234-5678", true, "1234", Role.ROLE_USER);
    	
    	Pessoa usuario9 = new Pessoa("Ryo Sakasaki", "ryo@ryo.com", "(45) 3030-3030", "(45) 99999-9999", true, "1234", Role.ROLE_USER);
    	Pessoa usuario10 = new Pessoa("Didier Eribon", "didier@didier.com", "(45) 2020-2020", "(45) 98888-8888", false, "1234", Role.ROLE_USER);
    	Pessoa usuario11 = new Pessoa("Denise Moraes", "denise@denise.com", "(51) 3210-3210", "(51) 98765-4321", true, null, Role.ROLE_USER);
    	Pessoa usuario12 = new Pessoa("Chun Li", "chunli@chunli.com", "(11) 2345-5432", "(45) 91234-5678", true, "1234", Role.ROLE_USER);
    	
    	if(pessoaService.countPessoas() == 0) {    		
			pessoaService.insertPessoa(admin);						  
			pessoaService.insertPessoa(user);
			pessoaService.insertPessoa(joao);
			pessoaService.insertPessoa(maria);
			
			pessoaService.insertPessoa(usuario1);
			pessoaService.insertPessoa(usuario2);
			pessoaService.insertPessoa(usuario3);
			pessoaService.insertPessoa(usuario4);
			
			pessoaService.insertPessoa(usuario5);
			pessoaService.insertPessoa(usuario6);
			pessoaService.insertPessoa(usuario7);
			pessoaService.insertPessoa(usuario8);
			
			pessoaService.insertPessoa(usuario9);
			pessoaService.insertPessoa(usuario10);
			pessoaService.insertPessoa(usuario11);
			pessoaService.insertPessoa(usuario12);
			
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
    	
    	Reuniao reuniao = new Reuniao("Fechamento das atividades de setembro/2018", LocalDate.of(2018, 9, 28), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "Revisão e fechamento das atividades executada no mês", trabalho);    	
    	Reuniao reuniao1 = new Reuniao("Reunião de planejamento de outubro/2018", LocalDate.of(2018, 10, 1), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(8, 30), LocalTime.of(9, 30), "", "Analisar junto a equipe quais serão as atividades que pretende-se executadar neste mês de outubro de 2018.", trabalho);        
    	Reuniao reuniao2 = new Reuniao("Repassar a equipe sobre alterações na lei 1234", LocalDate.of(2018, 10, 1), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(16, 15), LocalTime.of(17, 15), "", "apresentar o sistema", informativo);        
    	Reuniao reuniao3 = new Reuniao("Renuião para definição calendário para atividades extra", LocalDate.of(2018, 10, 2), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(7, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao4 = new Reuniao("Definição de pautas pra Evento", LocalDate.of(2018, 10, 5), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(6, 30), LocalTime.of(12, 00), "", "apresentar o sistema", deliberativo);
    	Reuniao reuniao5 = new Reuniao("Levantamento de Ideias novo projeto", LocalDate.of(2018, 10, 8), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(5, 30), LocalTime.of(12, 00), "", "apresentar o sistema", brainstorm);
    	Reuniao reuniao6 = new Reuniao("Informar sobre mudança na resolução 4321/18", LocalDate.of(2018, 10, 19), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao7 = new Reuniao("Reunião semanal", LocalDate.of(2018, 10, 23), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao8 = new Reuniao("Definir sobre visita técnica dos alunos de informátiva", LocalDate.of(2018, 10, 24), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao9 = new Reuniao("Reunião para definição das datas das apresentações", LocalDate.of(2018, 11, 5), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao10 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 11, 22), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao11 = new Reuniao("Balanço geral de 2018", LocalDate.of(2018, 12, 30), "Cataratas do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	    
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
