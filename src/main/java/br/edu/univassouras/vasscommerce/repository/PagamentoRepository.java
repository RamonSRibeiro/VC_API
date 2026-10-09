package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Pagamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {
}
