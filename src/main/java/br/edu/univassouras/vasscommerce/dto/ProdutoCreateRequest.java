package br.edu.univassouras.vasscommerce.dto;

import jakarta.validation.constraints.*;
import lombok.Data;
import java.math.BigDecimal;

@Data
public class ProdutoCreateRequest {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Size(max = 500, message = "Descrição deve ter no máximo 500 caracteres")
    private String descricao;

    private String fotoUrl;

    @NotNull(message = "Valor unitário é obrigatório")
    @DecimalMin(value = "0.01", message = "Valor unitário deve ser maior que zero")
    private BigDecimal valorUnitario;

    @NotNull(message = "ID da categoria é obrigatório")
    private Integer categoriaId;
}
