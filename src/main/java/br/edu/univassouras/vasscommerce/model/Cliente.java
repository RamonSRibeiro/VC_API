package br.edu.univassouras.vasscommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.time.LocalDate;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "cliente")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Cliente extends Usuario {

    private LocalDate dataNascimento;
    private String cpf;

    @OneToOne
    @JoinColumn(name = "endereco_id")
    private Endereco endereco;

    @OneToMany(mappedBy = "cliente")
    private List<Cartao> cartoes;

    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos;
}
