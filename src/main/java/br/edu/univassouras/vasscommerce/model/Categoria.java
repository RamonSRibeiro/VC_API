package br.edu.univassouras.vasscommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "categoria")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String imagemSimboloUrl;
    private String nome;
    private String descricao;

    @OneToMany(mappedBy = "categoria")
    private List<Produto> produtos;
}
