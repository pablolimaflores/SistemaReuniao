package br.com.projeto.reuniao.domain.controller;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.projeto.reuniao.domain.entity.Participante;
import br.com.projeto.reuniao.domain.entity.Reuniao;
import br.com.projeto.reuniao.domain.service.ParticipanteService;
import br.com.projeto.reuniao.domain.service.ReuniaoService;

@Controller
public class EmailController {

	@Autowired
	private JavaMailSender mailSender;

	@Autowired
	private ReuniaoService reuniaoService;

	@Autowired
	private ParticipanteService participanteService;


	@GetMapping(path = "reunioes/reunioesEdit/{id}/email")
	public String sendMailByReuniao(@PathVariable(required = false, name = "id") Long id) {

		if (null != id) {
			
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	        
			Reuniao reuniao = this.reuniaoService.findReuniaoById(id);

			ArrayList<Participante> participantes = (ArrayList<Participante>) this.participanteService
					.listParticipanteByReuniaoId(id);

			participantes.stream().forEach(p -> {
				try {

					MimeMessage mail = mailSender.createMimeMessage();

					MimeMessageHelper helper = new MimeMessageHelper(mail);
					helper.setTo(p.getPessoa().getEmail());
					helper.setSubject("FlexMeeting: Agendamento de reunião " + reuniao.getTitulo());
					
					StringBuilder emailHtml = new StringBuilder("");
					
					emailHtml.append("<h2>" + reuniao.getTitulo() + "</h2>")														
					.append("<p>Tipo: " + reuniao.getTipo().getNome() + "</p>")
					.append("<p>Local: " + reuniao.getLocal() + "</p>")
					.append("<p>Data: " + reuniao.getData().format(formatter) + "</p>")
					.append("<p>Horario: das " + reuniao.getHoraInicio() + " até às " + reuniao.getHoraFim() + "</p>")
					.append("<p>Objetivo: " + reuniao.getObjetivo() + "</p>");
					if (!reuniao.getPreRequisito().isEmpty()) {
						emailHtml.append("<p>Pré-requisito: " + reuniao.getPreRequisito() + "</p>");
					}

					helper.setText(emailHtml.toString(), true);
					
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
		
		return "reunioes/reunioesEdit";
	}
	
}
