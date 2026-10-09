package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Produto;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ProdutoDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String fotoUrl;
    private Double valorUnitario;
    private Double valorComPromocao; // menor preço de promoção ativa, se houver
    private LocalDateTime dataCadastro;
    private CategoriaDTO categoria;

    public static ProdutoDTO from(Produto p) {
        ProdutoDTO dto = new ProdutoDTO();
        dto.setId(p.getId());
        dto.setNome(p.getNome());
        dto.setDescricao(p.getDescricao());
        dto.setFotoUrl(p.getFotoUrl());
        dto.setValorUnitario(p.getValorUnitario());
        dto.setDataCadastro(p.getDataCadastro());
        if (p.getCategoria() != null) {
            dto.setCategoria(CategoriaDTO.from(p.getCategoria()));
        }
        return dto;
    }
}
