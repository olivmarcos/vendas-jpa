package facade;

import java.sql.Connection;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import dao.ClienteDao;
import dao.PedidoDao;
import dao.PedidoProdutoDao;
import dao.ProdutoDao;
import dao.ProdutoMovimentacaoDao;
import dao.VendedorComissaoDao;
import database.DatabaseConnection;
import javax.swing.JOptionPane;
import models.Cliente;
import models.Pedido;
import models.PedidoProduto;
import models.Produto;
import models.ProdutoMovimentacao;
import models.Vendedor;
import models.VendedorComissao;

public class PedidoFacade {

    public void fazerPedido(Cliente cliente, Vendedor vendedor, Pedido pedido, Produto produto, Double valorPedidoProduto, int quantidade) throws SQLException {

        Connection conn = DatabaseConnection.getConexaoTransacional();

        try {
            PedidoDao pedidoDao = new PedidoDao(conn);
            pedidoDao.insert(pedido);

            pedido.setPed_codigo(pedidoDao.recoverUltimoCodigo());

            PedidoProduto pedidoProduto = new PedidoProduto();
            pedidoProduto.setPedp_quantidade(quantidade);
            pedidoProduto.setPedp_valor(valorPedidoProduto);
            pedidoProduto.setPedp_valor_total(pedidoProduto.getPedp_quantidade() * pedidoProduto.getPedp_valor());
            pedidoProduto.setPedp_cod_pedido(pedido.getPed_codigo());
            pedidoProduto.setPedp_cod_produto(produto.getProd_codigo());
            PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao(conn);
            pedidoProdutoDao.insert(pedidoProduto);

            ProdutoMovimentacao produtoMovimentacao = new ProdutoMovimentacao();
            produtoMovimentacao.setProdm_descricao("Saída do produto " + produto.getProd_descricao() + " às " + pedido.getPed_data());
            produtoMovimentacao.setProdm_data(pedido.getPed_data());
            produtoMovimentacao.setProdm_cod_produto(produto.getProd_codigo());

            ProdutoMovimentacaoDao produtoMovimentacaoDao = new ProdutoMovimentacaoDao(conn);
            produtoMovimentacaoDao.insert(produtoMovimentacao);

            ProdutoDao produtoDao = new ProdutoDao(conn);
            Produto instanceProduto = produtoDao.recover(produto.getProd_codigo());
            Integer codigo = instanceProduto.getProd_codigo();

            if (codigo != null) {
                instanceProduto.setProd_saldo(instanceProduto.getProd_saldo() - pedidoProduto.getPedp_quantidade());
                produtoDao.update(instanceProduto);
            }

            VendedorComissao vendedorComissao = new VendedorComissao();
            vendedorComissao.setVnc_comissao((vendedor.getVend_percentual_comissao() / 100) * pedidoProduto.getPedp_valor_total());
            vendedorComissao.setVnc_cod_pedido(pedido.getPed_codigo());
            vendedorComissao.setVnc_cod_vendedor(vendedor.getVend_codigo());
            VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao(conn);
            vendedorComissaoDao.insert(vendedorComissao);

            ClienteDao clienteDao = new ClienteDao(conn);
            cliente.setCli_ultima_compra(pedido.getPed_data());
            clienteDao.update(cliente);

            conn.commit();
            System.out.println("Pedido realizado com sucesso.");
        } catch (Exception err) {
            System.err.println("Erro ao realizar o pedido: " + err.getMessage());
            conn.rollback();
        }
    }

    public void desfazerPedido(Pedido pedido) throws SQLException {
        Connection conn = DatabaseConnection.getConexaoTransacional();
        try {
            ProdutoMovimentacaoDao produtoMovimentacaoDao = new ProdutoMovimentacaoDao(conn);
            ProdutoMovimentacao produtoMovimentacao = produtoMovimentacaoDao.recoverPorData(pedido.getPed_data());
            produtoMovimentacaoDao.delete(produtoMovimentacao.getProdm_codigo());

            PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao(conn);
            PedidoProduto pedidoProduto = pedidoProdutoDao.recoverPorPedido(pedido.getPed_codigo());

            ProdutoDao produtoDao = new ProdutoDao(conn);
            Produto produto = produtoDao.recover(pedidoProduto.getPedp_cod_produto());

            produto.setProd_saldo(produto.getProd_saldo() + pedidoProduto.getPedp_quantidade());
            produtoDao.update(produto);

            VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao(conn);
            VendedorComissao vendedorComissao = vendedorComissaoDao.recoverPorPedido(pedido.getPed_codigo());
            vendedorComissaoDao.delete(vendedorComissao.getVnc_cod_pedido());

            pedidoProdutoDao.delete(pedidoProduto.getPedp_codigo());

            PedidoDao pedidoDao = new PedidoDao(conn);
            pedidoDao.delete(pedido.getPed_codigo());

            ClienteDao clienteDao = new ClienteDao(conn);
            Cliente cliente = clienteDao.recover(pedido.getPed_cod_cliente());

            if (pedidoDao.recoverUltimoPedido(cliente.getCli_codigo()) == null) {
                System.out.println("Não há mais pedidos para este vendedor.");
            } else {
                Pedido pedido2 = pedidoDao.recoverUltimoPedido(cliente.getCli_codigo());
                cliente.setCli_ultima_compra(pedido2.getPed_data());
            }

            clienteDao.update(cliente);
            conn.commit();
            System.out.println("Pedido removido com sucesso!");
        } catch (Exception err) {
            System.err.println("Erro ao remover o pedido: " + err.getMessage());
            conn.rollback();
        }
    }
}
