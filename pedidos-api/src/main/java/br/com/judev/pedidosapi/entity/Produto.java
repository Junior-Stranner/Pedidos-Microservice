package br.com.judev.pedidosapi.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    private UUID id = UUID.randomUUID();
    private String nome;
    private double valor;

}
