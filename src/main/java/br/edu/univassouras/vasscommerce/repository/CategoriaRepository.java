package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
