package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Estado;
import lombok.Data;

@Data
public class EstadoDTO {
    private Integer id;
    private String sigla;
    private String nome;

    public static EstadoDTO from(Estado e) {
        EstadoDTO dto = new EstadoDTO();
        dto.setId(e.getId());
        dto.setSigla(e.getSigla());
        dto.setNome(e.getNome());
        return dto;
    }
}
