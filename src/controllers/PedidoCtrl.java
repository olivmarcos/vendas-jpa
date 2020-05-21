package controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dao.PedidoDao;
import facade.PedidoFacade;
import frontend.RealizarPedido;
import models.Cliente;
import models.Pedido;
import models.Produto;
import models.Vendedor;

public class PedidoCtrl {

    public boolean save(String[] dados) throws SQLException, ParseException {
        Pedido pedido = new Pedido();
        pedido.vetorTo(dados);

        PedidoDao pedidoDao = new PedidoDao();

        if (pedido.getPed_codigo() == 0) {
            pedidoDao.insert(pedido);
            return true;
        } else {
            pedidoDao.update(pedido);
            return false;
        }
    }

    public Pedido recover(int id) throws SQLException {
        
        PedidoDao pedidoDao = new PedidoDao();
        Pedido pedido = pedidoDao.recover(id);

        return pedido;
    }

    public void update(String[] dados) throws SQLException, ParseException {
        Pedido pedido = new Pedido();

        pedido.vetorTo(dados);

        PedidoDao pedidoDao = new PedidoDao();

        if (pedido.getPed_codigo() == 0) {
            pedidoDao.insert(pedido);
        } else {
            pedidoDao.update(pedido);
        }
    }

    public boolean delete(int id) throws SQLException {
        PedidoDao pedidoDao = new PedidoDao();
        if (pedidoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        PedidoDao pedidoDao = new PedidoDao();

        ArrayList<Pedido> listaPedidos = pedidoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaPedidos.size()][3];
        for (int i = 0; i < listaPedidos.size(); i++) {
            matrizReturn[i] = listaPedidos.get(i).toVetor();
        }
        return matrizReturn;
    }
    
    public void criarPedido(Cliente cliente, Vendedor vendedor, Pedido pedido, Produto produto, Double valorPedidoProduto, int quantidade) throws SQLException {
        PedidoFacade pedidoFacade = new PedidoFacade();
        pedidoFacade.fazerPedido(cliente, vendedor, pedido, produto, valorPedidoProduto, quantidade);
    }
}