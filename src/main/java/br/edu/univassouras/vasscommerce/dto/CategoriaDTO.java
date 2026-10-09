package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Categoria;
import lombok.Data;

@Data
public class CategoriaDTO {
    private Integer id;
    private String nome;
    private String descricao;
    private String imagemSimboloUrl;

    public static CategoriaDTO from(Categoria c) {
        CategoriaDTO dto = new CategoriaDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        dto.setDescricao(c.getDescricao());
        dto.setImagemSimboloUrl(c.getImagemSimboloUrl());
        return dto;
    }
}
