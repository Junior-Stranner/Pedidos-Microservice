package br.com.judev.pedidos.processador.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class Produto {

    @Id
    private UUID id = UUID.randomUUID();
    private String nome;
    private double valor;


}
