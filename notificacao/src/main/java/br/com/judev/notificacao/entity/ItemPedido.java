package br.com.judev.notificacao.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemPedido {
    private UUID id = UUID.randomUUID();

    private Produto produto;
    private Integer quantidade;
}
