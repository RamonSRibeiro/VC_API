package br.edu.univassouras.vasscommerce.controller;

import br.edu.univassouras.vasscommerce.dto.CategoriaDTO;
import br.edu.univassouras.vasscommerce.dto.ProdutoDTO;
import br.edu.univassouras.vasscommerce.model.Produto;
import br.edu.univassouras.vasscommerce.repository.CategoriaRepository;
import br.edu.univassouras.vasscommerce.repository.ProdutoRepository;
import br.edu.univassouras.vasscommerce.repository.PromocaoItemRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {

    private final CategoriaRepository categoriaRepo;
    private final ProdutoRepository produtoRepo;
    private final PromocaoItemRepository promocaoItemRepo;

    public CategoriaController(CategoriaRepository categoriaRepo,
                                ProdutoRepository produtoRepo,
                                PromocaoItemRepository promocaoItemRepo) {
        this.categoriaRepo = categoriaRepo;
        this.produtoRepo = produtoRepo;
        this.promocaoItemRepo = promocaoItemRepo;
    }

    // GET /categoria — lista todas as categorias
    // GET /categoria?nome=<nomeCategoria> — busca por nome
    @GetMapping
    public List<CategoriaDTO> listar(@RequestParam(required = false) String nome) {
        if (nome != null && !nome.isBlank()) {
            return categoriaRepo.findByNomeContainingIgnoreCase(nome)
                    .stream().map(CategoriaDTO::from).collect(Collectors.toList());
        }
        return categoriaRepo.findAll()
                .stream().map(CategoriaDTO::from).collect(Collectors.toList());
    }

    // GET /categoria/<idcategoria>/produto — lista produtos da categoria
    @GetMapping("/{idCategoria}/produto")
    public ResponseEntity<List<ProdutoDTO>> listarProdutos(@PathVariable Integer idCategoria) {
        if (!categoriaRepo.existsById(idCategoria)) {
            return ResponseEntity.notFound().build();
        }
        List<Produto> produtos = produtoRepo.findByCategoriaId(idCategoria);
        LocalDate hoje = LocalDate.now();
        List<ProdutoDTO> dtos = produtos.stream().map(p -> {
            ProdutoDTO dto = ProdutoDTO.from(p);
            // aplica o menor preço de promoção ativa, se houver
            var promocoes = promocaoItemRepo.findPromocoesAtivasByProduto(p.getId(), hoje);
            if (!promocoes.isEmpty()) {
                dto.setValorComPromocao(promocoes.get(0).getValorPromocao());
            }
            return dto;
        }).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }
}
