package br.edu.univassouras.vasscommerce.repository;

import br.edu.univassouras.vasscommerce.model.PromocaoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import java.time.LocalDate;
import java.util.List;

public interface PromocaoItemRepository extends JpaRepository<PromocaoItem, Integer> {

    @Query("""
        SELECT pi FROM PromocaoItem pi
        WHERE pi.produto.id = :produtoId
          AND pi.tabelaPromocao.dataInicio <= :hoje
          AND pi.tabelaPromocao.dataFim >= :hoje
        ORDER BY pi.valorPromocao ASC
    """)
    List<PromocaoItem> findPromocoesAtivasByProduto(@Param("produtoId") Integer produtoId,
                                                    @Param("hoje") LocalDate hoje);
}
