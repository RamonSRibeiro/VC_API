package br.edu.univassouras.vasscommerce.mapper;

import br.edu.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import br.edu.univassouras.vasscommerce.dto.ProdutoResponse;
import br.edu.univassouras.vasscommerce.model.Categoria;
import br.edu.univassouras.vasscommerce.model.Produto;

import java.time.LocalDateTime;

/**
 * Utilitário estático de conversão entre DTOs e entidade Produto.
 * Mantém controllers e services livres de lógica de mapeamento.
 */
public class ProdutoMapper {

    private ProdutoMapper() {}

    /** Converte request DTO → entidade (sem id, sem datas). */
    public static Produto toEntity(ProdutoCreateRequest req) {
        Produto p = new Produto();
        p.setNome(req.getNome());
        p.setDescricao(req.getDescricao());
        p.setFotoUrl(req.getFotoUrl());
        p.setValorUnitario(req.getValorUnitario() != null
                ? req.getValorUnitario().doubleValue() : null);
        p.setDataCadastro(LocalDateTime.now());
        p.setDataUltimaAtualizacao(LocalDateTime.now());

        if (req.getCategoriaId() != null) {
            Categoria cat = new Categoria();
            cat.setId(req.getCategoriaId());
            p.setCategoria(cat);
        }
        return p;
    }

    /** Converte entidade → response DTO. */
    public static ProdutoResponse toResponse(Produto p) {
        return ProdutoResponse.from(p);
    }
}
