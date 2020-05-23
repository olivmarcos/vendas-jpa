/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendas.projeto;

import java.util.Date;
import models.Cliente;
import controllers.Controller;
import database.DataLayer;
import facade.PedidoFacade;
import java.sql.SQLException;
import java.text.ParseException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import models.Pedido;
import models.Produto;
import models.Vendedor;

/**
 *
 * @author marco
 */
public class VendasProjeto {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException, ParseException {
//
//        RealizarPedido realizarPedido = new RealizarPedido();
//        realizarPedido.setVisible(true);
//        EntityManager manager = null;        
//        try {
//            manager = Persistence.createEntityManagerFactory("vendas").createEntityManager();
//            manager.getTransaction().begin();
//            
//            Cliente cliente = manager.find(Cliente.class, 1);
//            
//            manager.clear();
//            cliente.setCli_nome("Marcos Oliveira");
//            cliente.setCli_ultima_compra(new Date());
//            manager.merge(cliente);
//            manager.getTransaction().commit();
//            System.out.println("Cliente foi atualizado");
//        } catch (Exception err) {
//            err.printStackTrace();
//            if (manager != null) {
//                System.out.println("Rollback");
//                manager.getTransaction().rollback();
//            }
//        } finally {
//            if (manager != null) {
//                manager.close();
//            }
//        }

        Controller controller = new Controller();
        Cliente cliente = new Cliente();

        cliente = (Cliente) controller.recover(cliente, 1);
        Vendedor vendedor = new Vendedor();
        vendedor = (Vendedor) controller.recover(vendedor, 1);

        Produto produto = new Produto();
        produto = (Produto) controller.recover(produto, 1);

        Pedido pedido = new Pedido();
        pedido.setPed_data(new Date());
        pedido.setPed_observacao("teste 2");
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);
        pedido.setCliente(cliente);
        pedido.setVendedor(vendedor);

        PedidoFacade newPedido = new PedidoFacade();

        newPedido.saveOrder(pedido, produto, 222.22, 2);

    }

    /*GRUPO: Marcos Antônio e Eduardo */
}
