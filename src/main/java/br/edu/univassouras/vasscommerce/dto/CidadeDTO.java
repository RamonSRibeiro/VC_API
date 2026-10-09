package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Cidade;
import lombok.Data;

@Data
public class CidadeDTO {
    private Integer id;
    private String nome;

    public static CidadeDTO from(Cidade c) {
        CidadeDTO dto = new CidadeDTO();
        dto.setId(c.getId());
        dto.setNome(c.getNome());
        return dto;
    }
}
