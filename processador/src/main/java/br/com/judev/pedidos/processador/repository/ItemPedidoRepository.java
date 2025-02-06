package br.com.judev.pedidos.processador.repository;

import br.com.judev.pedidos.processador.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido , UUID> {
}
