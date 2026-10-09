package br.edu.univassouras.vasscommerce.model;

public enum PedidoStatus {
    AGUARDANDO_PAGAMENTO(1),
    SEPARANDO_ESTOQUE(2),
    ENTREGUE_TRANSPORTADORA(3),
    ENTREGUE_CLIENTE(4);

    private final int codigo;

    PedidoStatus(int codigo) {
        this.codigo = codigo;
    }

    public int getCodigo() {
        return codigo;
    }
}
