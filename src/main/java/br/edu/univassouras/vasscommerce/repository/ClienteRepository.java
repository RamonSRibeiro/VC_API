package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente, Integer> {
}
