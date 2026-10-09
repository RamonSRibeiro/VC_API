package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findByClienteId(Integer clienteId);
}
