package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Cartao;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CartaoRepository extends JpaRepository<Cartao, Integer> {
    List<Cartao> findByClienteIdAndExcluidoFalse(Integer clienteId);
}
