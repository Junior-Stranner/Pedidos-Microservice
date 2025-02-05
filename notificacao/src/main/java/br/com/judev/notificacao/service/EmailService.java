package br.com.judev.notificacao.service;

import br.com.judev.notificacao.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

@Service
public class EmailService {


    private final EmailService emailService;

    public EmailService(EmailService emailService) {
        this.emailService = emailService;
    }

    private void enviarEmail(Pedido pedido){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom("pedidos-api@company.com");
        simpleMailMessage.setTo(pedido.getEmailNotificacao);
        simpleMailMessage.setSubject("Pedido de compra");
        simpleMailMessage.setText(this.gerarMensagem());
        emailService.send(simpleMailMessage);
    }

    private String gerarMensagem(Pedido pedido) {
        Pedido pedidoId = new Pedido();
    }


}
