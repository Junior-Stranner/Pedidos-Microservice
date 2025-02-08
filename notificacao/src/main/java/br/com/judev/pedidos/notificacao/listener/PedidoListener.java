package br.com.judev.pedidos.notificacao.listener;

import br.com.judev.pedidos.notificacao.entity.Pedido;
import br.com.judev.pedidos.notificacao.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger logger = LoggerFactory.getLogger((PedidoListener.class));
    private final EmailService emailService;

    public PedidoListener(EmailService emailService) {
        this.emailService = emailService;
    }


    @RabbitListener(queues = "pedidos.v1.pedido-criado.gerar-notificacao")
    public void enviarnotificacao(Pedido pedido){
        logger.info("Tentando consmuir a mensagem");
        if(pedido.getValorTotal() > 2000) {
            throw new RuntimeException("Valor muito alto");
        }
        emailService.enviarEmail(pedido);
        logger.info("Notificação gerada: {}", pedido.toString());
    }
}
