package br.edu.univassouras.vasscommerce.config;

import br.edu.univassouras.vasscommerce.model.*;
import br.edu.univassouras.vasscommerce.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * Popula o banco H2 em memória com dados de exemplo para facilitar os testes.
 */
@Component
public class DataLoader implements CommandLineRunner {

    private final EstadoRepository estadoRepo;
    private final CidadeRepository cidadeRepo;
    private final CategoriaRepository categoriaRepo;
    private final ProdutoRepository produtoRepo;
    private final ClienteRepository clienteRepo;
    private final EnderecoRepository enderecoRepo;
    private final CartaoRepository cartaoRepo;
    private final PedidoRepository pedidoRepo;
    private final ItemPedidoRepository itemPedidoRepo;
    private final PagamentoRepository pagamentoRepo;
    private final TabelaPromocaoRepository tabelaPromocaoRepo;
    private final PromocaoItemRepository promocaoItemRepo;

    public DataLoader(EstadoRepository estadoRepo,
                      CidadeRepository cidadeRepo,
                      CategoriaRepository categoriaRepo,
                      ProdutoRepository produtoRepo,
                      ClienteRepository clienteRepo,
                      EnderecoRepository enderecoRepo,
                      CartaoRepository cartaoRepo,
                      PedidoRepository pedidoRepo,
                      ItemPedidoRepository itemPedidoRepo,
                      PagamentoRepository pagamentoRepo,
                      TabelaPromocaoRepository tabelaPromocaoRepo,
                      PromocaoItemRepository promocaoItemRepo) {
        this.estadoRepo = estadoRepo;
        this.cidadeRepo = cidadeRepo;
        this.categoriaRepo = categoriaRepo;
        this.produtoRepo = produtoRepo;
        this.clienteRepo = clienteRepo;
        this.enderecoRepo = enderecoRepo;
        this.cartaoRepo = cartaoRepo;
        this.pedidoRepo = pedidoRepo;
        this.itemPedidoRepo = itemPedidoRepo;
        this.pagamentoRepo = pagamentoRepo;
        this.tabelaPromocaoRepo = tabelaPromocaoRepo;
        this.promocaoItemRepo = promocaoItemRepo;
    }

    @Override
    public void run(String... args) {

        // Estados
        Estado rj = new Estado();
        rj.setSigla("RJ");
        rj.setNome("Rio de Janeiro");
        estadoRepo.save(rj);

        Estado sp = new Estado();
        sp.setSigla("SP");
        sp.setNome("São Paulo");
        estadoRepo.save(sp);

        // Cidades
        Cidade vassouras = new Cidade();
        vassouras.setNome("Vassouras");
        vassouras.setEstado(rj);
        cidadeRepo.save(vassouras);

        Cidade saoPaulo = new Cidade();
        saoPaulo.setNome("São Paulo");
        saoPaulo.setEstado(sp);
        cidadeRepo.save(saoPaulo);

        // Categorias
        Categoria eletronicos = new Categoria();
        eletronicos.setNome("Eletrônicos");
        eletronicos.setDescricao("Smartphones, TVs e mais");
        eletronicos.setImagemSimboloUrl("https://example.com/eletronicos.png");
        categoriaRepo.save(eletronicos);

        Categoria vestuario = new Categoria();
        vestuario.setNome("Vestuário");
        vestuario.setDescricao("Roupas e acessórios");
        vestuario.setImagemSimboloUrl("https://example.com/vestuario.png");
        categoriaRepo.save(vestuario);

        // Produtos
        Produto celular = new Produto();
        celular.setNome("Smartphone X");
        celular.setDescricao("Smartphone top de linha");
        celular.setValorUnitario(1500.00);
        celular.setDataCadastro(LocalDateTime.now());
        celular.setDataUltimaAtualizacao(LocalDateTime.now());
        celular.setCategoria(eletronicos);
        produtoRepo.save(celular);

        Produto camiseta = new Produto();
        camiseta.setNome("Camiseta Básica");
        camiseta.setDescricao("Camiseta 100% algodão");
        camiseta.setValorUnitario(49.90);
        camiseta.setDataCadastro(LocalDateTime.now());
        camiseta.setDataUltimaAtualizacao(LocalDateTime.now());
        camiseta.setCategoria(vestuario);
        produtoRepo.save(camiseta);

        // Tabela de promoção Black Friday — ativa no momento
        TabelaPromocao blackFriday = new TabelaPromocao();
        blackFriday.setNome("Black Friday");
        blackFriday.setDescricao("Descontos imperdíveis");
        blackFriday.setDataInicio(LocalDate.now().minusDays(1));
        blackFriday.setDataFim(LocalDate.now().plusDays(5));
        blackFriday.setDataCadastro(LocalDateTime.now());
        blackFriday.setDataUltimaAtualizacao(LocalDateTime.now());
        tabelaPromocaoRepo.save(blackFriday);

        // Item de promoção: Smartphone com desconto
        PromocaoItem promoItem = new PromocaoItem();
        promoItem.setProduto(celular);
        promoItem.setTabelaPromocao(blackFriday);
        promoItem.setValorPromocao(1199.00);
        promoItem.setDataUltimaAtualizacao(LocalDateTime.now());
        promocaoItemRepo.save(promoItem);

        // Endereço — salvar ANTES do cliente
        Endereco endereco = new Endereco();
        endereco.setRua("Rua das Flores");
        endereco.setNumero(42);
        endereco.setCep("27700-000");
        endereco.setBairro("Centro");
        endereco.setTelefone("(24) 99999-9999");
        endereco.setDataCadastro(LocalDateTime.now());
        endereco.setDataUltimaAtualizacao(LocalDateTime.now());
        endereco.setCidade(vassouras);
        enderecoRepo.save(endereco);

        // Cliente
        Cliente cliente = new Cliente();
        cliente.setNomeCompleto("João da Silva");
        cliente.setEmail("joao@email.com");
        cliente.setSenha("hash_da_senha");
        cliente.setCpf("123.456.789-00");
        cliente.setDataNascimento(LocalDate.of(1990, 5, 10));
        cliente.setDataCadastro(LocalDateTime.now());
        cliente.setDataUltimaAtualizacao(LocalDateTime.now());
        cliente.setEndereco(endereco);
        clienteRepo.save(cliente);

        // Cartões
        Cartao cartaoCredito = new Cartao();
        cartaoCredito.setTipo(TipoCartao.CREDITO);
        cartaoCredito.setDataCriacao(LocalDateTime.now());
        cartaoCredito.setExcluido(false);
        cartaoCredito.setCliente(cliente);
        cartaoRepo.save(cartaoCredito);

        Cartao cartaoDebito = new Cartao();
        cartaoDebito.setTipo(TipoCartao.DEBITO);
        cartaoDebito.setDataCriacao(LocalDateTime.now());
        cartaoDebito.setExcluido(false);
        cartaoDebito.setCliente(cliente);
        cartaoRepo.save(cartaoDebito);

        // Pedido
        Pedido pedido = new Pedido();
        pedido.setDataCadastro(LocalDateTime.now());
        pedido.setValorTotal(1500.00);
        pedido.setStatusAtual(PedidoStatus.AGUARDANDO_PAGAMENTO);
        pedido.setCliente(cliente);
        pedidoRepo.save(pedido);

        // Item do pedido
        ItemPedido item = new ItemPedido();
        item.setQuantidade(1);
        item.setValorUnitario(1500.00);
        item.setPedido(pedido);
        item.setProduto(celular);
        itemPedidoRepo.save(item);

        // Pagamento dividido em dois cartões (relacionamento ternário)
        Pagamento pag1 = new Pagamento();
        pag1.setValorPago(1000.00);
        pag1.setPedido(pedido);
        pag1.setCartao(cartaoCredito);
        pagamentoRepo.save(pag1);

        Pagamento pag2 = new Pagamento();
        pag2.setValorPago(500.00);
        pag2.setPedido(pedido);
        pag2.setCartao(cartaoDebito);
        pagamentoRepo.save(pag2);

        System.out.println("=== Dados de exemplo carregados com sucesso! ===");
    }
}
