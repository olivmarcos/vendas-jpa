package controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dao.ProdutoMovimentacaoDao;
import models.ProdutoMovimentacao;

public class ProdutoMovimentacaoCtrl {

    public boolean save(String[] dados) throws SQLException, ParseException {

        ProdutoMovimentacao produtoMovimentacao = new ProdutoMovimentacao();
        produtoMovimentacao.vetorTo(dados);

        ProdutoMovimentacaoDao produtoMovimentacaoDao = new ProdutoMovimentacaoDao();

        if (produtoMovimentacao.getProdm_codigo() == 0) {
            produtoMovimentacaoDao.insert(produtoMovimentacao);
            return true;
        } else {
            produtoMovimentacaoDao.update(produtoMovimentacao);
            return false;
        }
    }

    public String[] recover(int id) throws SQLException {
        
        ProdutoMovimentacaoDao produtoMovimentacaoDao = new ProdutoMovimentacaoDao();
        ProdutoMovimentacao produtoMovimentacao = produtoMovimentacaoDao.recover(id);

        return produtoMovimentacao.toVetor();
    }

    public void update(String[] dados) throws SQLException, ParseException {
        ProdutoMovimentacao produtoMovimentacao = new ProdutoMovimentacao();

        produtoMovimentacao.vetorTo(dados);

        ProdutoMovimentacaoDao produtoMovimentacaoDao = new ProdutoMovimentacaoDao();

        if (produtoMovimentacao.getProdm_codigo() == 0) {
            produtoMovimentacaoDao.insert(produtoMovimentacao);
        } else {
            produtoMovimentacaoDao.update(produtoMovimentacao);
        }
    }

    public boolean delete(int id) throws SQLException {
        ProdutoMovimentacaoDao ProdutoMovimentacaoDao = new ProdutoMovimentacaoDao();
        if (ProdutoMovimentacaoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        ProdutoMovimentacaoDao ProdutoMovimentacaoDao = new ProdutoMovimentacaoDao();

        ArrayList<ProdutoMovimentacao> listaProdutoMovimentacoes = ProdutoMovimentacaoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaProdutoMovimentacoes.size()][3];
        for (int i = 0; i < listaProdutoMovimentacoes.size(); i++) {
            matrizReturn[i] = listaProdutoMovimentacoes.get(i).toVetor();
        }
        return matrizReturn;
    }
}