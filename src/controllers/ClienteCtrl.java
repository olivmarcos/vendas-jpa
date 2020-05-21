package controllers;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;

import dao.ClienteDao;
import models.Cliente;

public class ClienteCtrl {

    public boolean save(String[] dados) throws SQLException, ParseException {
        Cliente cliente = new Cliente();
        cliente.vetorTo(dados);

        ClienteDao clienteDao = new ClienteDao();

        if (cliente.getCli_codigo() == 0) {
            clienteDao.insert(cliente);
            return true;
        } else {
            clienteDao.update(cliente);
            return false;
        }
    }

    public Cliente recover(int id) throws SQLException {
        
        ClienteDao clienteDao = new ClienteDao();
        Cliente cliente = clienteDao.recover(id);

        return cliente;
    }

    public void update(String[] dados) throws SQLException, ParseException {
        Cliente cliente = new Cliente();

        cliente.vetorTo(dados);

        ClienteDao clienteDao = new ClienteDao();

        if (cliente.getCli_codigo() == 0) {
            clienteDao.insert(cliente);
        } else {
            clienteDao.update(cliente);
        }
    }

    public boolean delete(int id) throws SQLException {
        ClienteDao clienteDao = new ClienteDao();
        if (clienteDao.delete(id)) {
            return true;
        }
        return false;

    }

    public String[][] recoverAll() throws SQLException {
        ClienteDao clienteDao = new ClienteDao();

        ArrayList<Cliente> listaClientes = clienteDao.recoverAll();

        String[][] matrizReturn;
        matrizReturn = new String[listaClientes.size()][3];
        for (int i = 0; i < listaClientes.size(); i++) {
            matrizReturn[i] = listaClientes.get(i).toVetor();
        }
        return matrizReturn;
    }
}