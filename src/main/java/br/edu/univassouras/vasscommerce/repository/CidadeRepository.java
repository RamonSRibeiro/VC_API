package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {
    List<Cidade> findByEstadoId(Integer estadoId);
}
