package br.com.judev.pedidosapi.config;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    // Cria e configura o Exchange do tipo Fanout, responsável por distribuir mensagens para todas as filas vinculadas.
    @Bean
    public Exchange pedidosExchange() {
        return new FanoutExchange(exchangeName);
    }

    // Cria o RabbitAdmin para gerenciar automaticamente a infraestrutura do RabbitMQ (filas, exchanges, etc.).
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    // Define o conversor de mensagens para o formato JSON usando a biblioteca Jackson.
    @Bean
    public Jackson2JsonMessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    // Configura o RabbitTemplate, que é usado para enviar mensagens para o RabbitMQ.
    // Ele utiliza o conversor de mensagens definido para converter objetos Java em JSON.
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    // Garante que o RabbitAdmin seja inicializado assim que a aplicação estiver pronta.
    // Isso assegura que as configurações do RabbitMQ sejam aplicadas corretamente.
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
