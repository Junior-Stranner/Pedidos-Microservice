package br.com.judev.pedidosapi.controller;

import br.com.judev.pedidosapi.entity.Pedido;
import br.com.judev.pedidosapi.repository.PedidoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pedidos")
@Validated
public class PedidoController {

    private final PedidoRepository pedidoRepository;

    private final Logger logger = LoggerFactory.getLogger(PedidoController.class);

    public PedidoController(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @PostMapping
    public ResponseEntity<Pedido> criarPedido(@RequestBody Pedido pedido){
        Pedido pedidoConfirmado = pedidoRepository.save(pedido);
        logger.info("Pedido recebido: {}", pedido.toString());
        return ResponseEntity.status(HttpStatus.CREATED).body(pedido);
    }
}
