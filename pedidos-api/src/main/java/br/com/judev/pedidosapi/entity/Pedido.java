package br.com.judev.pedidosapi.entity;

import br.com.judev.pedidosapi.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name="pedidos")
@NoArgsConstructor@AllArgsConstructor
public class Pedido {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id = UUID.randomUUID();
    private String Cliente;

    @OneToMany(mappedBy = "pedido", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemPedido> itemPedidos = new ArrayList<>();    private double valorTotal;
    private String emailNotificacao;
    private Status status;
    @JsonFormat(pattern = "yyyy-mm-dd HH:mm:ss")
    @DateTimeFormat(pattern  ="yyyy-mm-dd HH:mm:ss")
    private LocalDateTime dataHora;
}
