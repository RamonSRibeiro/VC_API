package br.edu.univassouras.vasscommerce.service;

import br.edu.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import br.edu.univassouras.vasscommerce.model.Produto;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

/**
 * Contrato da camada de negócio para Produto.
 * Controllers dependem apenas desta interface — nunca de implementações concretas.
 */
public interface ProdutoModel {

    /** Cria e persiste um novo produto. */
    Produto criar(ProdutoCreateRequest req);

    /** Busca produto pelo id. */
    Optional<Produto> obterPorId(Integer id);

    /**
     * Busca produtos por filtros opcionais.
     * Todos os parâmetros são opcionais — null significa "sem filtro".
     */
    List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo);
}
