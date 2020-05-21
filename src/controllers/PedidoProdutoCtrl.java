package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.PedidoProdutoDao;
import models.PedidoProduto;

public class PedidoProdutoCtrl {

    public boolean save(String[] dados) throws SQLException {
        PedidoProduto pedidoProduto = new PedidoProduto();
        pedidoProduto.vetorTo(dados);

        PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao();

        if (pedidoProduto.getPedp_codigo() == 0) {
            pedidoProdutoDao.insert(pedidoProduto);
            return true;
        } else {
            pedidoProdutoDao.update(pedidoProduto);
            return false;
        }
    }

    public String[] recover(int id) throws SQLException {
        
        PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao();
        PedidoProduto pedidoProduto = pedidoProdutoDao.recover(id);

        return pedidoProduto.toVetor();
    }

    public void update(String[] dados) throws SQLException {
        PedidoProduto pedidoProduto = new PedidoProduto();

        pedidoProduto.vetorTo(dados);

        PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao();

        if (pedidoProduto.getPedp_codigo() == 0) {
            pedidoProdutoDao.insert(pedidoProduto);
        } else {
            pedidoProdutoDao.update(pedidoProduto);
        }
    }

    public boolean delete(int id) throws SQLException {
        PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao();
        if (pedidoProdutoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        PedidoProdutoDao pedidoProdutoDao = new PedidoProdutoDao();

        ArrayList<PedidoProduto> listaPedidosProdutos = pedidoProdutoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaPedidosProdutos.size()][3];
        for (int i = 0; i < listaPedidosProdutos.size(); i++) {
            matrizReturn[i] = listaPedidosProdutos.get(i).toVetor();
        }
        return matrizReturn;
    }
}