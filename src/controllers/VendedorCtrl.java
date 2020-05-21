package controllers;

import java.sql.SQLException;
import java.util.ArrayList;

import dao.VendedorDao;
import models.Vendedor;

public class VendedorCtrl {

    public boolean save(String[] dados) throws SQLException {

        Vendedor vendedor = new Vendedor();
        vendedor.vetorTo(dados);

        VendedorDao vendedorDao = new VendedorDao();

        if (vendedor.getVend_codigo() == 0) {
            vendedorDao.insert(vendedor);
            return true;
        } else {
            vendedorDao.update(vendedor);
            return false;
        }
    }

    public Vendedor recover(int id) throws SQLException {
        
        VendedorDao vendedorDao = new VendedorDao();
        Vendedor vendedor = vendedorDao.recover(id);

        return vendedor;
    }

    public void update(String[] dados) throws SQLException {
        Vendedor vendedor = new Vendedor();

        vendedor.vetorTo(dados);

        VendedorDao vendedorDao = new VendedorDao();

        if (vendedor.getVend_codigo() == 0) {
            vendedorDao.insert(vendedor);
        } else {
            vendedorDao.update(vendedor);
        }
    }

    public boolean delete(int id) throws SQLException {
        VendedorDao vendedorDao = new VendedorDao();
        if (vendedorDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        VendedorDao vendedorDao = new VendedorDao();

        ArrayList<Vendedor> listaVendedores = vendedorDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaVendedores.size()][3];
        for (int i = 0; i < listaVendedores.size(); i++) {
            matrizReturn[i] = listaVendedores.get(i).toVetor();
        }
        return matrizReturn;
    }
}