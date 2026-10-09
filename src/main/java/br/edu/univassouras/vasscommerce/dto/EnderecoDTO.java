package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Endereco;
import lombok.Data;

@Data
public class EnderecoDTO {
    private Integer id;
    private String rua;
    private Integer numero;
    private String cep;
    private String complemento;
    private String telefone;
    private String bairro;
    private String cidade;
    private String estado;

    public static EnderecoDTO from(Endereco e) {
        EnderecoDTO dto = new EnderecoDTO();
        dto.setId(e.getId());
        dto.setRua(e.getRua());
        dto.setNumero(e.getNumero());
        dto.setCep(e.getCep());
        dto.setComplemento(e.getComplemento());
        dto.setTelefone(e.getTelefone());
        dto.setBairro(e.getBairro());
        if (e.getCidade() != null) {
            dto.setCidade(e.getCidade().getNome());
            if (e.getCidade().getEstado() != null) {
                dto.setEstado(e.getCidade().getEstado().getNome());
            }
        }
        return dto;
    }
}
