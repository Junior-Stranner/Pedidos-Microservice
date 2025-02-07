package br.com.judev.pedidos.processador.service;

import br.com.judev.pedidos.processador.entity.ItemPedido;
import br.com.judev.pedidos.processador.entity.Pedido;
import br.com.judev.pedidos.processador.entity.Produto;
import br.com.judev.pedidos.processador.repository.ItemPedidoRepository;
import br.com.judev.pedidos.processador.repository.PedidoRepository;
import br.com.judev.pedidos.processador.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;


    public ProdutoService(ProdutoRepository produtoRepository, ItemPedidoRepository itemPedidoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void save(List<ItemPedido> itens) {
        // Salva os Produtos
        itens.forEach(item -> {
            produtoRepository.save(item.getProduto()); // Salva o Produto antes de salvar o ItemPedido
        });
    }
}