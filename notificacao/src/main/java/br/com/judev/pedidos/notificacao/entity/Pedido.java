package br.com.judev.pedidos.notificacao.entity;

import br.com.judev.pedidos.notificacao.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Pedido {
    private UUID id = UUID.randomUUID();

    private String cliente;

    private List<ItemPedido> itens;

    private double valorTotal;
    private String emailNotificacao = "teste@gmail.com";

    private Status status = Status.EM_PROCESSAMENTO;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime dataHora = LocalDateTime.now();

    public UUID getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public String getEmailNotificacao() {
        return emailNotificacao;
    }

    public Status getStatus() {
        return status;
    }



}
