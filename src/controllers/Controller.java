package controllers;

import database.DataLayer;
import java.sql.SQLException;
import java.text.ParseException;
import javax.persistence.Persistence;

import models.Cliente;

public class Controller {

    private DataLayer dataLayer = new DataLayer();

    public void save(Object object) throws SQLException, ParseException {
        try {
            this.dataLayer.create(object);
            System.out.println("Objeto inserido com sucesso");
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
            dataLayer.rollback();
        }
    }

    public Object recover(Object object, int id) throws SQLException {
        Object recoveredObject = this.dataLayer.read(object, id);
        return recoveredObject;
    }

    public void update(Object object, int id) throws SQLException, ParseException {
        this.dataLayer.update(object);
    }

    public void delete(Object object) throws SQLException {
        this.dataLayer.delete(object);
    }
}
