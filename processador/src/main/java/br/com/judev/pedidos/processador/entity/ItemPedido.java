package br.com.judev.pedidos.processador.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "item_pedidos")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter@Setter
public class ItemPedido {
    @Id
    private UUID id = UUID.randomUUID();

    @ManyToOne
    private Produto produto;
    private Integer quantidade;

    @ManyToOne
    private Pedido pedido;

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public ItemPedido getProduto(){
        return getProduto();
    }


}
