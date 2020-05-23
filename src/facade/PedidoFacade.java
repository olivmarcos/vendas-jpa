package facade;

import database.DataLayer;
import java.util.Collection;
import java.util.Date;
import models.Cliente;
import models.Pedido;
import models.PedidoProduto;
import models.Produto;
import models.ProdutoMovimentacao;
import models.Vendedor;
import models.VendedorComissao;

public class PedidoFacade {

    public void saveOrder(Pedido pedido, Produto produto, Double valorPedidoProduto, int quantidade) {
        DataLayer dataLayer = new DataLayer();

        try {
            dataLayer.initTransaction();
            
            dataLayer.create(pedido);
            
            dataLayer.clear();
            produto.setProd_saldo(produto.getProd_saldo() - quantidade);
            dataLayer.update(produto);

            PedidoProduto pedidoProduto = new PedidoProduto();

            pedidoProduto.setPedp_quantidade(quantidade);
            pedidoProduto.setPedp_valor(valorPedidoProduto);
            pedidoProduto.setPedp_valor_total(pedidoProduto.getPedp_quantidade() * pedidoProduto.getPedp_valor());
            pedidoProduto.setPedido(pedido);
            pedidoProduto.setProduto(produto);

            dataLayer.create(pedidoProduto);

            ProdutoMovimentacao produtoMovimentacao = new ProdutoMovimentacao();

            produtoMovimentacao.setProdm_descricao("Saída do produto " + produto.getProd_descricao() + " às " + pedido.getPed_data());
            produtoMovimentacao.setProdm_data(pedido.getPed_data());
            produtoMovimentacao.setProduto(produto);

            dataLayer.create(produtoMovimentacao);

            Cliente cliente = pedido.getCliente();
            cliente.setCli_ultima_compra(new Date());
            dataLayer.update(cliente);

            Vendedor vendedor = pedido.getVendedor();

            VendedorComissao vendedorComissao = new VendedorComissao();

            vendedorComissao.setVnc_comissao((vendedor.getVend_percentual_comissao() / 100) * pedidoProduto.getPedp_valor_total());
            vendedorComissao.setPedido(pedido);
            vendedorComissao.setVendedor(vendedor);
            
            dataLayer.create(vendedorComissao);
            
            dataLayer.closeTransaction();
            System.out.println("Registro salvo com sucesso!");
        } catch (Exception e) {
            System.out.println("Não foi possível salvar o registro " + e.getMessage());
        }
    }
}
