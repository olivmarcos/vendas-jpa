package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.ProdutoDao;
import models.Produto;

public class ProdutoCtrl {
    
    public boolean save(String[] dados) throws SQLException {
        Produto produto = new Produto();
        produto.vetorTo(dados);

        ProdutoDao produtoDao = new ProdutoDao();

        if (produto.getProd_codigo() == 0) {
            produtoDao.insert(produto);
            return true;
        } else {
            produtoDao.update(produto);
            return false;
        }
    }

    public Produto recover(int id) throws SQLException {
        
        ProdutoDao produtoDao = new ProdutoDao();
        Produto produto = produtoDao.recover(id);

        return produto;
    }

    public void update(String[] dados) throws SQLException {
        Produto produto = new Produto();

        produto.vetorTo(dados);

        ProdutoDao produtoDao = new ProdutoDao();

        if (produto.getProd_codigo() == 0) {
            produtoDao.insert(produto);
        } else {
            produtoDao.update(produto);
        }
    }

    public boolean delete(int id) throws SQLException {
        ProdutoDao produtoDao = new ProdutoDao();
        if (produtoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        ProdutoDao ProdutoDao = new ProdutoDao();

        ArrayList<Produto> listaProdutos = ProdutoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaProdutos.size()][3];
        for (int i = 0; i < listaProdutos.size(); i++) {
            matrizReturn[i] = listaProdutos.get(i).toVetor();
        }
        return matrizReturn;
    }
}