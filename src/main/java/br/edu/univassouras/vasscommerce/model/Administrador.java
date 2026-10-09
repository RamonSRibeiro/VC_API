package br.edu.univassouras.vasscommerce.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "administrador")
@PrimaryKeyJoinColumn(name = "usuario_id")
public class Administrador extends Usuario {

    @OneToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @OneToMany(mappedBy = "administrador")
    private List<TabelaPromocao> tabelasPromocao;
}
