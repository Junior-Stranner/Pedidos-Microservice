package br.com.judev.pedidos.processador.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "item_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;
}
