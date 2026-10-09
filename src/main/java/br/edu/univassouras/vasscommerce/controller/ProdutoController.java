package br.edu.univassouras.vasscommerce.controller;

import br.edu.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import br.edu.univassouras.vasscommerce.dto.ProdutoResponse;
import br.edu.univassouras.vasscommerce.mapper.ProdutoMapper;
import br.edu.univassouras.vasscommerce.service.ProdutoModel;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;

/**
 * Controller de Produto — depende apenas da interface ProdutoModel (IoC/DI).
 * Nunca faz new de implementações; toda lógica de negócio fica no service.
 */
@RestController
@RequestMapping(value = "/produto", produces = "application/json")
public class ProdutoController {

    private final ProdutoModel model;

    // injeção por construtor — sem @Autowired em campo
    public ProdutoController(ProdutoModel model) {
        this.model = model;
    }

    /**
     * POST /produto
     * Cria um novo produto. Retorna 201 Created com Location e body.
     */
    @PostMapping(consumes = "application/json")
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoCreateRequest body) {
        var criado = model.criar(body);
        URI location = URI.create("/produto/" + criado.getId());
        return ResponseEntity.created(location).body(ProdutoMapper.toResponse(criado));
    }

    /**
     * GET /produto/{id}
     * Retorna 200 com o produto ou 404 se não encontrado.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProdutoResponse> buscarPorId(@PathVariable Integer id) {
        return model.obterPorId(id)
                .map(p -> ResponseEntity.ok(ProdutoMapper.toResponse(p)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * GET /produto?nome=&valorMinimo=&valorMaximo=
     * Lista produtos com filtros opcionais. Retorna 200 sempre.
     */
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listar(
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) BigDecimal valorMinimo,
            @RequestParam(required = false) BigDecimal valorMaximo) {

        List<ProdutoResponse> resultado = model.buscar(nome, valorMinimo, valorMaximo)
                .stream()
                .map(ProdutoMapper::toResponse)
                .toList();

        return ResponseEntity.ok(resultado);
    }
}
