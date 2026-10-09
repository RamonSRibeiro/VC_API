package br.edu.univassouras.vasscommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fotoUrl;
    private LocalDateTime dataCriacao;
    private boolean excluido;

    @Enumerated(EnumType.STRING)
    private TipoCartao tipo;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;
}
