package br.edu.univassouras.vasscommerce.dto;

import br.edu.univassouras.vasscommerce.model.ItemPedido;
import lombok.Data;

@Data
public class ItemPedidoDTO {
    private Integer id;
    private Integer quantidade;
    private Double valorUnitario;
    private String nomeProduto;

    public static ItemPedidoDTO from(ItemPedido item) {
        ItemPedidoDTO dto = new ItemPedidoDTO();
        dto.setId(item.getId());
        dto.setQuantidade(item.getQuantidade());
        dto.setValorUnitario(item.getValorUnitario());
        if (item.getProduto() != null) {
            dto.setNomeProduto(item.getProduto().getNome());
        }
        return dto;
    }
}
