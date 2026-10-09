package br.edu.univassouras.vasscommerce.service;

import br.edu.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import br.edu.univassouras.vasscommerce.mapper.ProdutoMapper;
import br.edu.univassouras.vasscommerce.model.Categoria;
import br.edu.univassouras.vasscommerce.model.Produto;
import br.edu.univassouras.vasscommerce.repository.CategoriaRepository;
import br.edu.univassouras.vasscommerce.repository.ProdutoRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Implementação com banco de dados (JPA/H2) — ativa com spring.profiles.active=sql
 * É a implementação "produção", que persiste dados de verdade.
 */
@Service
@Profile("sql")
public class ProdutoModelSql implements ProdutoModel {

    private final ProdutoRepository produtoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProdutoModelSql(ProdutoRepository produtoRepository,
                           CategoriaRepository categoriaRepository) {
        this.produtoRepository = produtoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        Produto p = ProdutoMapper.toEntity(req);

        // resolve a categoria real no banco antes de salvar
        if (req.getCategoriaId() != null) {
            Categoria categoria = categoriaRepository.findById(req.getCategoriaId())
                    .orElseThrow(() -> new IllegalArgumentException(
                            "Categoria não encontrada: " + req.getCategoriaId()));
            p.setCategoria(categoria);
        }

        return produtoRepository.save(p);
    }

    @Override
    public Optional<Produto> obterPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        Stream<Produto> stream = produtoRepository.findAll().stream();

        if (nome != null && !nome.isBlank()) {
            stream = stream.filter(p -> p.getNome().toLowerCase()
                    .contains(nome.toLowerCase()));
        }
        if (valorMinimo != null) {
            stream = stream.filter(p -> p.getValorUnitario() != null
                    && BigDecimal.valueOf(p.getValorUnitario()).compareTo(valorMinimo) >= 0);
        }
        if (valorMaximo != null) {
            stream = stream.filter(p -> p.getValorUnitario() != null
                    && BigDecimal.valueOf(p.getValorUnitario()).compareTo(valorMaximo) <= 0);
        }

        return stream.toList();
    }
}
