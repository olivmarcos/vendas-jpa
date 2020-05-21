package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.VendedorComissaoDao;
import models.VendedorComissao;

public class VendedorComissaoCtrl {

    public boolean save(String[] dados) throws SQLException {
        VendedorComissao vendedorComissao = new VendedorComissao();
        vendedorComissao.vetorTo(dados);

        VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao();

        if (vendedorComissao.getVnc_codigo() == 0) {
            vendedorComissaoDao.insert(vendedorComissao);
            return true;
        } else {
            vendedorComissaoDao.update(vendedorComissao);
            return false;
        }
    }

    public String[] recover(int id) throws SQLException {
        
        VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao();
        VendedorComissao vendedorComissao = vendedorComissaoDao.recover(id);

        return vendedorComissao.toVetor();
    }

    public void update(String[] dados) throws SQLException {
        VendedorComissao vendedorComissao = new VendedorComissao();

        vendedorComissao.vetorTo(dados);

        VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao();

        if (vendedorComissao.getVnc_codigo() == 0) {
            vendedorComissaoDao.insert(vendedorComissao);
        } else {
            vendedorComissaoDao.update(vendedorComissao);
        }
    }

    public boolean delete(int id) throws SQLException {
        VendedorComissaoDao vendedorComissaoDao = new VendedorComissaoDao();
        if (vendedorComissaoDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        VendedorComissaoDao VendedorComissaoDao = new VendedorComissaoDao();

        ArrayList<VendedorComissao> listaVendedorComissoes = VendedorComissaoDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaVendedorComissoes.size()][3];
        for (int i = 0; i < listaVendedorComissoes.size(); i++) {
            matrizReturn[i] = listaVendedorComissoes.get(i).toVetor();
        }
        return matrizReturn;
    }
}