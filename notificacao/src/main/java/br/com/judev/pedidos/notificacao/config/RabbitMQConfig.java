package br.com.judev.pedidos.notificacao.config;

import org.springframework.amqp.core.*;
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

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitMQConfig {

    // Lê o nome do Exchange principal a partir do application.properties
    @Value("${rabbitmq.exchange.name}")
    private String exchangeName;

    // Lê o nome da fila principal
    @Value("${rabbitmq.queue.name}")
    private String queueName;

    // Lê o nome do Exchange para mensagens rejeitadas (Dead Letter Exchange)
    @Value("${rabbitmq.exchange.dlx.name}")
    private String exchangeDlxName;

    // Lê o nome da fila de mensagens rejeitadas (Dead Letter Queue)
    @Value("${rabbitmq.queue.dlq.name}")
    private String queueDlqName;

    /**
     * Declara um Fanout Exchange principal para distribuição de mensagens.
     * O Fanout Exchange envia mensagens para todas as filas associadas a ele.
     */
    @Bean
    public FanoutExchange pedidosExchange() {
        return new FanoutExchange(exchangeName);
    }

    /**
     * Declara um Fanout Exchange para Dead Letter (mensagens rejeitadas).
     */
    @Bean
    public FanoutExchange pedidosDlxExchange() {
        return new FanoutExchange(exchangeDlxName);
    }

    /**
     * Declara a fila principal de notificações.
     * A configuração inclui um Dead Letter Exchange (DLX), para onde as mensagens irão
     * caso falhem o processamento e excedam as tentativas de reenvio.
     */
    @Bean
    public Queue notificacaoQueue() {
        Map<String, Object> argumentos = new HashMap<>();
        // Define para onde mensagens rejeitadas devem ir (Dead Letter Exchange)
        argumentos.put("x-dead-letter-exchange", exchangeDlxName);
        return new Queue(queueName, true, false, false, argumentos);
    }

    /**
     * Declara a Dead Letter Queue (DLQ) que armazenará mensagens rejeitadas ou não processadas corretamente.
     */
    @Bean
    public Queue notificacaoDlqQueue() {
        return new Queue(queueDlqName);
    }

    /**
     * Liga a fila principal (`notificacaoQueue`) ao Exchange principal (`pedidosExchange`).
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(notificacaoQueue()).to(pedidosExchange());
    }

    /**
     * Liga a Dead Letter Queue (`notificacaoDlqQueue`) ao Dead Letter Exchange (`pedidosDlxExchange`).
     */
    @Bean
    public Binding bindingDlq() {
        return BindingBuilder.bind(notificacaoDlqQueue()).to(pedidosDlxExchange());
    }

    /**
     * Configura o `RabbitAdmin`, que é responsável por gerenciar filas e exchanges automaticamente.
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * Define o conversor de mensagens para JSON, garantindo que as mensagens enviadas e recebidas
     * sejam automaticamente convertidas para objetos JSON em vez do formato padrão (byte array).
     */
    @Bean
    public MessageConverter messageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    /**
     * Configura o `RabbitTemplate`, que é usado para enviar mensagens para o RabbitMQ.
     * Também define o conversor de mensagens para JSON.
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }

    /**
     * Inicializa automaticamente as filas e exchanges assim que a aplicação estiver pronta.
     * Isso garante que a infraestrutura do RabbitMQ esteja configurada antes do processamento de mensagens.
     */
    @Bean
    public ApplicationListener<ApplicationReadyEvent> applicationReadyEventApplicationListener(RabbitAdmin rabbitAdmin) {
        return event -> rabbitAdmin.initialize();
    }
}
