package br.com.projeto.reuniao.domain.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.entity.PontoPauta;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.ParticipanteService;
import br.com.projeto.reuniao.domain.service.PessoaService;
import br.com.projeto.reuniao.domain.service.PontoPautaService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ReuniaoService reuniaoService;

	@Autowired
	private ParticipanteService participanteService;
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PontoPautaService pontoPautaService;


	@GetMapping(path = "reunioes/reunioesEdit/{id}/email")
	public String sendMailByReuniao(Model model, @PathVariable(required = false, name = "id") Long id) {

		if (null != id) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
			Reuniao reuniao = this.reuniaoService.findReuniaoById(id);

			ArrayList<Participante> participantes = (ArrayList<Participante>) this.participanteService
					.listParticipanteByReuniaoId(id);
			
			ArrayList<PontoPauta> pontosPauta = (ArrayList<PontoPauta>) this.pontoPautaService.listPontoPautaByReuniaoId(id);					

			participantes.stream().forEach(p -> {
				try {

					MimeMessage mail = mailSender.createMimeMessage();

					MimeMessageHelper helper = new MimeMessageHelper(mail);
					helper.setTo(p.getPessoa().getEmail());
					helper.setSubject("FlexMeeting: Agendamento de reunião " + reuniao.getTitulo());
					
					StringBuilder emailHtml = new StringBuilder("");
					
					emailHtml.append("<p>Olá.</p>")
					.append("<p>Você está recebendo este e-mail é para informar os dados desta reunião agendada.</p>");
					
					//Dados gerais					
					emailHtml.append("<h2>" + reuniao.getTitulo() + "</h2>")														
					.append("<p><strong>Tipo:</strong> " + reuniao.getTipo().getNome() + "</p>")
					.append("<p><strong>Local:</strong> " + reuniao.getLocal() + "</p>")
					.append("<p><strong>Data:</strong> " + reuniao.getData().format(formatter) + "</p>")
					.append("<p><strong>Horario:</strong> das " + reuniao.getHoraInicio() + " até às " + reuniao.getHoraFim() + "</p>")
					.append("<p><strong>Objetivo:</strong> " + reuniao.getObjetivo() + "</p>")
					.append(!reuniao.getPreRequisito().isEmpty() ? "" : "<p><strong>Pré-requisito:</strong> " + reuniao.getPreRequisito() + "</p>");				
					
					//Participantes
					emailHtml.append("<h4>Participantes</h4>")
					.append("<ul>");
					
					participantes.stream().forEach(partic -> {						
						emailHtml.append("<li><strong>Nome:</strong> " + partic.getPessoa().getNome() + "</li>");
					});			
					
					emailHtml.append("</ul>");
					
					//Pontos de pauta
					emailHtml.append("<h4>Pontos de Pauta</h4>");					
					
					pontosPauta.stream().forEach(pon -> {						
						emailHtml.append("<p><strong>Pauta:</strong> " + pon.getDescricao() + "</p>")
						.append("<p><strong>Tipo:</strong> " + pon.getTipo().getNome() + "</p>")
						.append("<p><strong>Responsável:</strong> " + pon.getResponsavel().getNome() + "</p>")
						;					
					});

					helper.setText(emailHtml.toString(), true);
					
					//Enviar e-mail
					mailSender.send(mail);
					System.out.println("******** E-mail enviado para o participante " + p.getPessoa().getNome() + " ********");
				} catch (Exception e) {
					e.printStackTrace();
					System.out.println("******** Erro ao enviar e-mail para os participantes ********");
				}
			});
		} else {
			try {
				MimeMessage mail = mailSender.createMimeMessage();

				MimeMessageHelper helper = new MimeMessageHelper(mail);
				helper.setTo("pablolimaflores@gmail.com");
				helper.setSubject("FlexMeeting: Teste Envio de e-mail");
				helper.setText("<p>Testando email pelo FlexMeeting... :P </p>", true);
				mailSender.send(mail);				
				System.out.println(" ******* E-mail enviado ********");
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("******** Erro ao enviar e-mail ********");
			}
		}
		
		model.addAttribute("pessoas", this.pessoaService.findAllPessoas());
		model.addAttribute("participantes", this.participanteService.findAllParticipantes());    	
        if (null != id) {
            model.addAttribute("reuniao", this.reuniaoService.findReuniaoById(id));
        } else {
            model.addAttribute("reuniao", new Reuniao());            
        }
        return "reunioes/reunioesEdit";
				
	}
	
}
