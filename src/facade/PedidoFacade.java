package facade;

import controllers.Controller;
import database.DataLayer;
import java.util.ArrayList;
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

    public void fazerPedido(Pedido pedido, Produto produto, Double valorPedidoProduto, int quantidade) {
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

    public void desfazerPedido(Pedido pedido) {
        DataLayer dataLayer = new DataLayer();
        try {
            dataLayer.initTransaction();

            PedidoProduto pedidoProduto = new PedidoProduto();
            ArrayList pedP = (ArrayList) dataLayer.where(pedidoProduto, "pedido_ped_codigo = " + pedido.getPed_codigo());

            pedP.forEach(item -> {
                Produto produto = new Produto();
                produto = (Produto) dataLayer.read(produto, ((PedidoProduto) item).getProduto().getProd_codigo());
                produto.setProd_saldo(produto.getProd_saldo() + ((PedidoProduto) item).getPedp_quantidade());
                dataLayer.update(produto);
                dataLayer.delete(item);
            });

            VendedorComissao vendedorComissao = new VendedorComissao();
            ArrayList vendC = (ArrayList) dataLayer.where(vendedorComissao, "pedido_ped_codigo = " + pedido.getPed_codigo());

            vendC.forEach(item -> {
                dataLayer.delete(item);
            });
            
            dataLayer.delete(pedido);

            dataLayer.closeTransaction();
            System.out.println("Pedido removido com sucesso!");
        } catch (Exception err) {
            System.err.println("Erro ao remover o pedido: " + err.getMessage());
        }
    }
}
