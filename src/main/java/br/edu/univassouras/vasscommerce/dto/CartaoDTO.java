package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Cartao;
import br.edu.univassouras.vasscommerce.model.TipoCartao;
import lombok.Data;
import java.time.LocalDateTime;

@Data
public class CartaoDTO {
    private Integer id;
    private String fotoUrl;
    private LocalDateTime dataCriacao;
    private TipoCartao tipo;

    public static CartaoDTO from(Cartao c) {
        CartaoDTO dto = new CartaoDTO();
        dto.setId(c.getId());
        dto.setFotoUrl(c.getFotoUrl());
        dto.setDataCriacao(c.getDataCriacao());
        dto.setTipo(c.getTipo());
        return dto;
    }
}
