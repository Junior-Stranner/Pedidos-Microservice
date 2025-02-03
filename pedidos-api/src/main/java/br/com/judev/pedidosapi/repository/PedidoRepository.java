package br.com.judev.pedidosapi.repository;

import br.com.judev.pedidosapi.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PedidoRepository extends JpaRepository<Pedido, UUID> {

}
