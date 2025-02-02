package br.com.judev.pedidosapi.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class ItemPedido {

    @Id
    private UUID id = UUID.randomUUID();

}
