package br.edu.univassouras.vasscommerce.service;

import br.edu.univassouras.vasscommerce.dto.ProdutoCreateRequest;
import br.edu.univassouras.vasscommerce.mapper.ProdutoMapper;
import br.edu.univassouras.vasscommerce.model.Produto;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Implementação em memória — ativa com spring.profiles.active=mem
 * Útil para testes e ambientes sem banco de dados.
 */
@Service
@Profile("mem")
public class ProdutoModelMemoria implements ProdutoModel {

    private final List<Produto> banco = new ArrayList<>();
    private final AtomicInteger seq = new AtomicInteger(0);

    @Override
    public Produto criar(ProdutoCreateRequest req) {
        Produto p = ProdutoMapper.toEntity(req);
        p.setId(seq.incrementAndGet());
        banco.add(p);
        return p;
    }

    @Override
    public Optional<Produto> obterPorId(Integer id) {
        return banco.stream()
                .filter(p -> Objects.equals(p.getId(), id))
                .findFirst();
    }

    @Override
    public List<Produto> buscar(String nome, BigDecimal valorMinimo, BigDecimal valorMaximo) {
        return banco.stream()
                .filter(p -> nome == null
                        || p.getNome().toLowerCase().contains(nome.toLowerCase()))
                .filter(p -> valorMinimo == null
                        || (p.getValorUnitario() != null
                            && BigDecimal.valueOf(p.getValorUnitario()).compareTo(valorMinimo) >= 0))
                .filter(p -> valorMaximo == null
                        || (p.getValorUnitario() != null
                            && BigDecimal.valueOf(p.getValorUnitario()).compareTo(valorMaximo) <= 0))
                .toList();
    }
}
