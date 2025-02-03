package br.com.judev.pedidosapi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    private UUID id = UUID.randomUUID();
    @ManyToOne
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;
    private Integer quantidade;
}
