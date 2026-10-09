package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Produto;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class ProdutoResponse {

    private Integer id;
    private String nome;
    private String descricao;
    private String fotoUrl;
    private BigDecimal valorUnitario;
    private BigDecimal valorComPromocao;
    private LocalDateTime dataCadastro;
    private CategoriaDTO categoria;

    public static ProdutoResponse from(Produto p) {
        ProdutoResponse r = new ProdutoResponse();
        r.setId(p.getId());
        r.setNome(p.getNome());
        r.setDescricao(p.getDescricao());
        r.setFotoUrl(p.getFotoUrl());
        r.setValorUnitario(p.getValorUnitario() != null
                ? BigDecimal.valueOf(p.getValorUnitario()) : null);
        r.setDataCadastro(p.getDataCadastro());
        if (p.getCategoria() != null) {
            r.setCategoria(CategoriaDTO.from(p.getCategoria()));
        }
        return r;
    }
}
