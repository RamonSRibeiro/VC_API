package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.Pedido;
import br.edu.univassouras.vasscommerce.model.PedidoStatus;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class PedidoDTO {
    private Integer id;
    private LocalDateTime dataCadastro;
    private Double valorTotal;
    private PedidoStatus statusAtual;
    private List<ItemPedidoDTO> itens;

    public static PedidoDTO from(Pedido p) {
        PedidoDTO dto = new PedidoDTO();
        dto.setId(p.getId());
        dto.setDataCadastro(p.getDataCadastro());
        dto.setValorTotal(p.getValorTotal());
        dto.setStatusAtual(p.getStatusAtual());
        if (p.getItens() != null) {
            dto.setItens(p.getItens().stream()
                    .map(ItemPedidoDTO::from)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
