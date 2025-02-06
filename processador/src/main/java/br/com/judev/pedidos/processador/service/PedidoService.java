package br.com.judev.pedidos.processador.service;

import br.com.judev.pedidos.processador.entity.ItemPedido;
import br.com.judev.pedidos.processador.entity.Pedido;
import br.com.judev.pedidos.processador.repository.ItemPedidoRepository;
import br.com.judev.pedidos.processador.repository.PedidoRepository;
import br.com.judev.pedidos.processador.repository.ProdutoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger logger = LoggerFactory.getLogger(PedidoService.class);
    private final ProdutoService produtoService ;
    private final PedidoRepository pedidoRepository;
    private final ItemPedidoService itemPedidoService ;

    public PedidoService(ProdutoService produtoService, PedidoRepository pedidoRepository, ItemPedidoService itemPedidoService) {
        this.produtoService = produtoService;
        this.pedidoRepository = pedidoRepository;
        this.itemPedidoService = itemPedidoService;
    }

    public void save(Pedido pedido){
        produtoService.save(pedido.getItens());
        List<ItemPedido> itens = itemPedidoService.save(pedido.getItens());
        pedidoRepository.save(pedido);
        itemPedidoService.updateItemPedido(itens, pedido);

        logger.info("Pedido salvo: {}", pedido.toString());
    }
}
