package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Cliente;
import lombok.Data;
import java.time.LocalDate;

@Data
public class ClienteDTO {
    private Integer id;
    private String nomeCompleto;
    private String email;
    private String fotoUrl;
    private LocalDate dataNascimento;
    private String cpf;

    public static ClienteDTO from(Cliente c) {
        ClienteDTO dto = new ClienteDTO();
        dto.setId(c.getId());
        dto.setNomeCompleto(c.getNomeCompleto());
        dto.setEmail(c.getEmail());
        dto.setFotoUrl(c.getFotoUrl());
        dto.setDataNascimento(c.getDataNascimento());
        dto.setCpf(c.getCpf());
        return dto;
    }
}
