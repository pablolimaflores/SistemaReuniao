package br.com.projeto.reuniao.domain.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.mail.internet.MimeMessage;

@RestController
public class EmailController {

    @Autowired private JavaMailSender mailSender;

    @RequestMapping(path = "/email-send", method = RequestMethod.GET)
    public String sendMail() {
        try {
            MimeMessage mail = mailSender.createMimeMessage();

            MimeMessageHelper helper = new MimeMessageHelper( mail );
            helper.setTo( "pablolimaflores@gmail.com" );
//            helper.setTo( "tiagomarinsq97@gmail.com" );
            helper.setSubject( "FlexMeeting: Teste Envio de e-mail" );
            helper.setText("<p>Testando essa desgraça de email pelo FlexMeeting... :P </p>", true);
            mailSender.send(mail);

            return "OK";
        } catch (Exception e) {
            e.printStackTrace();
            return "Erro ao enviar e-mail";
        }
    }
}
