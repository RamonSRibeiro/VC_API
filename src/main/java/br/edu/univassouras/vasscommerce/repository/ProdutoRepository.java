package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    List<Produto> findByCategoriaId(Integer categoriaId);
}
