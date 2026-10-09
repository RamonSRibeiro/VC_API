package br.edu.univassouras.vasscommerce.controller;

import br.edu.univassouras.vasscommerce.dto.*;
import br.edu.univassouras.vasscommerce.repository.CartaoRepository;
import br.edu.univassouras.vasscommerce.repository.ClienteRepository;
import br.edu.univassouras.vasscommerce.repository.PedidoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    private final ClienteRepository clienteRepo;
    private final CartaoRepository cartaoRepo;
    private final PedidoRepository pedidoRepo;

    public ClienteController(ClienteRepository clienteRepo,
                              CartaoRepository cartaoRepo,
                              PedidoRepository pedidoRepo) {
        this.clienteRepo = clienteRepo;
        this.cartaoRepo = cartaoRepo;
        this.pedidoRepo = pedidoRepo;
    }

    // GET /cliente/<id>
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> buscar(@PathVariable Integer id) {
        return clienteRepo.findById(id)
                .map(c -> ResponseEntity.ok(ClienteDTO.from(c)))
                .orElse(ResponseEntity.notFound().build());
    }

    // GET /cliente/<idcliente>/formas-de-pagamento
    @GetMapping("/{idCliente}/formas-de-pagamento")
    public ResponseEntity<List<CartaoDTO>> formasDePagamento(@PathVariable Integer idCliente) {
        if (!clienteRepo.existsById(idCliente)) {
            return ResponseEntity.notFound().build();
        }
        List<CartaoDTO> cartoes = cartaoRepo.findByClienteIdAndExcluidoFalse(idCliente)
                .stream().map(CartaoDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(cartoes);
    }

    // GET /cliente/<idcliente>/endereco
    @GetMapping("/{idCliente}/endereco")
    public ResponseEntity<EnderecoDTO> endereco(@PathVariable Integer idCliente) {
        var clienteOpt = clienteRepo.findById(idCliente);
        if (clienteOpt.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var cliente = clienteOpt.get();
        if (cliente.getEndereco() == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(EnderecoDTO.from(cliente.getEndereco()));
    }

    // GET /cliente/<idcliente>/pedido
    @GetMapping("/{idCliente}/pedido")
    public ResponseEntity<List<PedidoDTO>> pedidos(@PathVariable Integer idCliente) {
        if (!clienteRepo.existsById(idCliente)) {
            return ResponseEntity.notFound().build();
        }
        List<PedidoDTO> pedidos = pedidoRepo.findByClienteId(idCliente)
                .stream().map(PedidoDTO::from).collect(Collectors.toList());
        return ResponseEntity.ok(pedidos);
    }
}
