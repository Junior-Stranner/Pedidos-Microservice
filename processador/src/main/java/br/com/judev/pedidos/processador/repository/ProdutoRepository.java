package br.com.judev.pedidos.processador.repository;

import br.com.judev.pedidos.processador.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ProdutoRepository extends JpaRepository<Produto, UUID> {
}
