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
import br.com.projeto.reuniao.domain.entity.PontoPauta;
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
    	
    	Reuniao reuniao = new Reuniao("Fechamento das atividades de setembro/2018", LocalDate.of(2018, 10, 28), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "Revisão e fechamento das atividades executada no mês", trabalho);    	
    	Reuniao reuniao1 = new Reuniao("Reunião de planejamento de outubro/2018", LocalDate.of(2018, 11, 12), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(8, 30), LocalTime.of(9, 30), "", "Analisar junto a equipe quais serão as atividades que pretende-se executadar neste mês de outubro de 2018.", trabalho);        
    	Reuniao reuniao2 = new Reuniao("Repassar a equipe sobre alterações na lei 1234", LocalDate.of(2018, 11, 14), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(16, 15), LocalTime.of(17, 15), "", "apresentar o sistema", informativo);        
    	Reuniao reuniao3 = new Reuniao("Renuião para definição calendário para atividades extra", LocalDate.of(2018, 12, 7), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(7, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao4 = new Reuniao("Definição de pautas pra Evento", LocalDate.of(2018, 12, 5), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(6, 30), LocalTime.of(12, 00), "", "apresentar o sistema", deliberativo);
    	Reuniao reuniao5 = new Reuniao("Levantamento de Ideias novo projeto", LocalDate.of(2018, 11, 29), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(5, 30), LocalTime.of(12, 00), "", "apresentar o sistema", brainstorm);
    	Reuniao reuniao6 = new Reuniao("Informar sobre mudança na resolução 4321/18", LocalDate.of(2018, 11, 19), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao7 = new Reuniao("Reunião semanal", LocalDate.of(2018, 10, 23), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
    	Reuniao reuniao8 = new Reuniao("Definir sobre visita técnica dos alunos de informátiva", LocalDate.of(2018, 12, 20), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao9 = new Reuniao("Reunião para definição das datas das apresentações", LocalDate.of(2018, 11, 15), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", trabalho);
    	Reuniao reuniao10 = new Reuniao("Apresentação do sistema SGR", LocalDate.of(2018, 12, 22), "Instituto Federal do Paraná - Câmpus Foz do Iguaçu", LocalTime.of(10, 30), LocalTime.of(12, 00), "", "apresentar o sistema", informativo);
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
    	Participante participanteUsuario1Reuniao = new Participante(true, usuario1, reuniao, solicitante);
    	Participante participanteUsuario2Reuniao = new Participante(true, usuario2, reuniao, solicitante);
    	Participante participanteUsuario3Reuniao = new Participante(true, usuario3, reuniao, solicitante);
    	Participante participanteUsuario4Reuniao = new Participante(true, usuario4, reuniao, solicitante);
    	
    	Participante participanteUsuario1Reuniao1 = new Participante(true, usuario1, reuniao1, solicitante);
    	Participante participanteUsuario2Reuniao1 = new Participante(true, usuario2, reuniao1, solicitante);
    	Participante participanteUsuario3Reuniao1 = new Participante(true, usuario3, reuniao1, solicitante);
    	Participante participanteUsuario4Reuniao1 = new Participante(true, usuario4, reuniao1, solicitante);
    	
    	Participante participanteUsuario1Reuniao2 = new Participante(true, usuario1, reuniao2, solicitante);
    	Participante participanteUsuario2Reuniao2 = new Participante(true, usuario2, reuniao2, solicitante);
    	
    	Participante participanteUsuario3Reuniao3 = new Participante(true, usuario3, reuniao3, solicitante);
    	Participante participanteUsuario4Reuniao3 = new Participante(true, usuario4, reuniao3, solicitante);
    	
    	Participante participanteUsuario4Reuniao4 = new Participante(true, usuario4, reuniao4, solicitante);
    	Participante participanteUsuario5Reuniao5 = new Participante(true, usuario5, reuniao5, solicitante);
    	Participante participanteUsuario6Reuniao6 = new Participante(true, usuario6, reuniao6, solicitante);
    	Participante participanteUsuario7Reuniao7 = new Participante(true, usuario7, reuniao7, solicitante);
    	Participante participanteUsuario8Reuniao8 = new Participante(true, usuario8, reuniao8, solicitante);
    	Participante participanteUsuario9Reuniao9 = new Participante(true, usuario9, reuniao9, solicitante);    	
    	Participante participanteUsuario10Reuniao10 = new Participante(true, usuario10, reuniao10, solicitante);
    	Participante participanteUsuario11Reuniao11 = new Participante(true, usuario11, reuniao11, solicitante);
    	
    	
    	if (participanteService.countParticipantes() == 0) {    		
    		participanteService.insertParticipante(participanteAdmin);
    		participanteService.insertParticipante(participanteJoao);
    		participanteService.insertParticipante(participanteMaria);
    		participanteService.insertParticipante(participanteUser);
    		participanteService.insertParticipante(participanteUsuario1Reuniao);
    		participanteService.insertParticipante(participanteUsuario2Reuniao);
    		participanteService.insertParticipante(participanteUsuario3Reuniao);
    		participanteService.insertParticipante(participanteUsuario4Reuniao);
    		
    		participanteService.insertParticipante(participanteUsuario1Reuniao1);
    		participanteService.insertParticipante(participanteUsuario2Reuniao1);
    		participanteService.insertParticipante(participanteUsuario3Reuniao1);
    		participanteService.insertParticipante(participanteUsuario4Reuniao1);
    		
    		participanteService.insertParticipante(participanteUsuario1Reuniao2);
    		participanteService.insertParticipante(participanteUsuario2Reuniao2);
    		
    		participanteService.insertParticipante(participanteUsuario3Reuniao3);
    		participanteService.insertParticipante(participanteUsuario4Reuniao3);
    		
    		participanteService.insertParticipante(participanteUsuario4Reuniao4);
    		participanteService.insertParticipante(participanteUsuario5Reuniao5);
    		participanteService.insertParticipante(participanteUsuario6Reuniao6);
    		participanteService.insertParticipante(participanteUsuario7Reuniao7);
    		participanteService.insertParticipante(participanteUsuario8Reuniao8);
    		participanteService.insertParticipante(participanteUsuario9Reuniao9);
    		participanteService.insertParticipante(participanteUsuario10Reuniao10);
    		participanteService.insertParticipante(participanteUsuario11Reuniao11);
    		
    	}
    	
    	PontoPauta pontoPauta1 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao);
    	PontoPauta pontoPauta2 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao);
    	PontoPauta pontoPauta3 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao);
    	PontoPauta pontoPauta4 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao);
    	PontoPauta pontoPauta5 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao);
    	PontoPauta pontoPauta6 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao);
    	PontoPauta pontoPauta7 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao);
    	PontoPauta pontoPauta8 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao);
    	PontoPauta pontoPauta9 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao);
    	PontoPauta pontoPauta10 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao);
    	
    	PontoPauta pontoPauta1Reuniao1 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao1);
    	PontoPauta pontoPauta2Reuniao1 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao1);
    	PontoPauta pontoPauta3Reuniao1 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao1);
    	PontoPauta pontoPauta4Reuniao1 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao1);
    	PontoPauta pontoPauta5Reuniao1 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao1);
    	PontoPauta pontoPauta6Reuniao1 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao1);
    	PontoPauta pontoPauta7Reuniao1 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao1);
    	PontoPauta pontoPauta8Reuniao1 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao1);
    	PontoPauta pontoPauta9Reuniao1 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao1);
    	PontoPauta pontoPauta10Reuniao1 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao1);
    	
    	PontoPauta pontoPauta1Reuniao2 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao2);
    	PontoPauta pontoPauta2Reuniao2 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao2);
    	PontoPauta pontoPauta3Reuniao2 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao2);
    	PontoPauta pontoPauta4Reuniao2 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao2);
    	PontoPauta pontoPauta5Reuniao2 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao2);
    	PontoPauta pontoPauta6Reuniao2 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao2);
    	PontoPauta pontoPauta7Reuniao2 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao2);
    	PontoPauta pontoPauta8Reuniao2 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao2);
    	PontoPauta pontoPauta9Reuniao2 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao2);
    	PontoPauta pontoPauta10Reuniao2 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao2);
    	
    	PontoPauta pontoPauta1Reuniao3 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao3);
    	PontoPauta pontoPauta2Reuniao3 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao3);
    	PontoPauta pontoPauta3Reuniao3 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao3);
    	PontoPauta pontoPauta4Reuniao3 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao3);
    	PontoPauta pontoPauta5Reuniao3 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao3);
    	PontoPauta pontoPauta6Reuniao3 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao3);
    	PontoPauta pontoPauta7Reuniao3 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao3);
    	PontoPauta pontoPauta8Reuniao3 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao3);
    	PontoPauta pontoPauta9Reuniao3 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao3);
    	PontoPauta pontoPauta10Reuniao3 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao3);
    	
    	PontoPauta pontoPauta1Reuniao4 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao4);
    	PontoPauta pontoPauta2Reuniao4 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao4);
    	PontoPauta pontoPauta3Reuniao4 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao4);
    	PontoPauta pontoPauta4Reuniao4 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao4);
    	PontoPauta pontoPauta5Reuniao4 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao4);
    	PontoPauta pontoPauta6Reuniao4 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao4);
    	PontoPauta pontoPauta7Reuniao4 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao4);
    	PontoPauta pontoPauta8Reuniao4 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao4);
    	PontoPauta pontoPauta9Reuniao4 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao4);
    	PontoPauta pontoPauta10Reuniao4 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao4);
    	
    	PontoPauta pontoPauta1Reuniao5 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao5);
    	PontoPauta pontoPauta2Reuniao5 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao5);
    	PontoPauta pontoPauta3Reuniao5 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao5);
    	PontoPauta pontoPauta4Reuniao5 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao5);
    	PontoPauta pontoPauta5Reuniao5 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao5);
    	PontoPauta pontoPauta6Reuniao5 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao5);
    	PontoPauta pontoPauta7Reuniao5 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao5);
    	PontoPauta pontoPauta8Reuniao5 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao5);
    	PontoPauta pontoPauta9Reuniao5 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao5);
    	PontoPauta pontoPauta10Reuniao5 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao5);
    	
    	PontoPauta pontoPauta1Reuniao6 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao6);
    	PontoPauta pontoPauta2Reuniao6 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao6);
    	PontoPauta pontoPauta3Reuniao6 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao6);
    	PontoPauta pontoPauta4Reuniao6 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao6);
    	PontoPauta pontoPauta5Reuniao6 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao6);
    	PontoPauta pontoPauta6Reuniao6 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao6);
    	PontoPauta pontoPauta7Reuniao6 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao6);
    	PontoPauta pontoPauta8Reuniao6 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao6);
    	PontoPauta pontoPauta9Reuniao6 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao6);
    	PontoPauta pontoPauta10Reuniao6 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao6);
    	
    	PontoPauta pontoPauta1Reuniao7 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao7);
    	PontoPauta pontoPauta2Reuniao7 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao7);
    	PontoPauta pontoPauta3Reuniao7 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao7);
    	PontoPauta pontoPauta4Reuniao7 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao7);
    	PontoPauta pontoPauta5Reuniao7 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao7);
    	PontoPauta pontoPauta6Reuniao7 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao7);
    	PontoPauta pontoPauta7Reuniao7 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao7);
    	PontoPauta pontoPauta8Reuniao7 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao7);
    	PontoPauta pontoPauta9Reuniao7 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao7);
    	PontoPauta pontoPauta10Reuniao7 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao7);
    	
    	PontoPauta pontoPauta1Reuniao8 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao8);
    	PontoPauta pontoPauta2Reuniao8 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao8);
    	PontoPauta pontoPauta3Reuniao8 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao8);
    	PontoPauta pontoPauta4Reuniao8 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao8);
    	PontoPauta pontoPauta5Reuniao8 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao8);
    	PontoPauta pontoPauta6Reuniao8 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao8);
    	PontoPauta pontoPauta7Reuniao8 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao8);
    	PontoPauta pontoPauta8Reuniao8 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao8);
    	PontoPauta pontoPauta9Reuniao8 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao8);
    	PontoPauta pontoPauta10Reuniao8 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao8);
    	
    	PontoPauta pontoPauta1Reuniao9 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao9);
    	PontoPauta pontoPauta2Reuniao9 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao9);
    	PontoPauta pontoPauta3Reuniao9 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao9);
    	PontoPauta pontoPauta4Reuniao9 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao9);
    	PontoPauta pontoPauta5Reuniao9 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao9);
    	PontoPauta pontoPauta6Reuniao9 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao9);
    	PontoPauta pontoPauta7Reuniao9 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao9);
    	PontoPauta pontoPauta8Reuniao9 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao9);
    	PontoPauta pontoPauta9Reuniao9 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao9);
    	PontoPauta pontoPauta10Reuniao9 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao9);
    	
    	PontoPauta pontoPauta1Reuniao10 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao10);
    	PontoPauta pontoPauta2Reuniao10 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao10);
    	PontoPauta pontoPauta3Reuniao10 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao10);
    	PontoPauta pontoPauta4Reuniao10 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao10);
    	PontoPauta pontoPauta5Reuniao10 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao10);
    	PontoPauta pontoPauta6Reuniao10 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao10);
    	PontoPauta pontoPauta7Reuniao10 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao10);
    	PontoPauta pontoPauta8Reuniao10 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao10);
    	PontoPauta pontoPauta9Reuniao10 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao10);
    	PontoPauta pontoPauta10Reuniao10 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao10);
    	
    	PontoPauta pontoPauta1Reuniao11 = new PontoPauta(1, "Pauta 01", 30,joao, informativo, reuniao11);
    	PontoPauta pontoPauta2Reuniao11 = new PontoPauta(2, "Pauta 02", 60, usuario2, deliberativo, reuniao11);
    	PontoPauta pontoPauta3Reuniao11 = new PontoPauta(3, "Pauta 03", 30,usuario3, trabalho, reuniao11);
    	PontoPauta pontoPauta4Reuniao11 = new PontoPauta(4, "Pauta 04", 60, usuario4, trabalho, reuniao11);
    	PontoPauta pontoPauta5Reuniao11 = new PontoPauta(5, "Pauta 05", 30,usuario5, trabalho, reuniao11);
    	PontoPauta pontoPauta6Reuniao11 = new PontoPauta(6, "Pauta 06", 60, usuario6, trabalho, reuniao11);
    	PontoPauta pontoPauta7Reuniao11 = new PontoPauta(7, "Pauta 07", 30,usuario7, trabalho, reuniao11);
    	PontoPauta pontoPauta8Reuniao11 = new PontoPauta(8, "Pauta 08", 60, usuario8, trabalho, reuniao11);
    	PontoPauta pontoPauta9Reuniao11 = new PontoPauta(9, "Pauta 09", 30,usuario9, trabalho, reuniao11);
    	PontoPauta pontoPauta10Reuniao11 = new PontoPauta(10, "Pauta 10", 60, usuario10, brainstorm, reuniao11);
    	
    	
    	
    	if (pontoPautaService.countPontoPauta() == 0) {
    		pontoPautaService.insertPontoPauta(pontoPauta1);
    		pontoPautaService.insertPontoPauta(pontoPauta2);
    		pontoPautaService.insertPontoPauta(pontoPauta3);
    		pontoPautaService.insertPontoPauta(pontoPauta4);
    		pontoPautaService.insertPontoPauta(pontoPauta5);
    		pontoPautaService.insertPontoPauta(pontoPauta6);
    		pontoPautaService.insertPontoPauta(pontoPauta7);
    		pontoPautaService.insertPontoPauta(pontoPauta8);
    		pontoPautaService.insertPontoPauta(pontoPauta9);
    		pontoPautaService.insertPontoPauta(pontoPauta10);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao1);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao1);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao2);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao2);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao3);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao3);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao4);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao4);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao5);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao5);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao6);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao6);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao7);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao7);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao8);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao8);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao9);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao9);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao10);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao10);
    		
    		pontoPautaService.insertPontoPauta(pontoPauta1Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta2Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta3Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta4Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta5Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta6Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta7Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta8Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta9Reuniao11);
    		pontoPautaService.insertPontoPauta(pontoPauta10Reuniao11);
    	}
    	    	
    	LOGGER.info("\n******** Initialization completed ********\n");
    }

}
