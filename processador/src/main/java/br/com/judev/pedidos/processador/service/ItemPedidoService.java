package br.com.judev.pedidos.processador.service;

import br.com.judev.pedidos.processador.entity.ItemPedido;
import br.com.judev.pedidos.processador.entity.Pedido;
import br.com.judev.pedidos.processador.repository.ItemPedidoRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }


    public List<ItemPedido> save(List<ItemPedido> itens) {
        itemPedidoRepository.saveAll(itens);
        return itens;
    }

    public void updateItemPedido(List<ItemPedido> itens, Pedido pedido) {
        itens.forEach(item -> {
            item.setPedido(pedido);//informando ao item o seu pedido
            this.save(itens);
        });
    }
}
