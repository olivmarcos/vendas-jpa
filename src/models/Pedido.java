package models;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Pedido {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ped_codigo;
    private String ped_data;
    private String ped_observacao;
    @ManyToOne
    private Cliente cliente;
    @ManyToOne
    private Vendedor vendedor;
    
    public int getPed_codigo() {
        return this.ped_codigo;
    }

    public void setPed_codigo(int ped_codigo) {
        this.ped_codigo = ped_codigo;
    }

    public String getPed_data() {
        return this.ped_data;
    }

    public void setPed_data(Date ped_data) {
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(ped_data);
        this.ped_data = data;
    }

    public void setPed_data(String ped_data) {
        this.ped_data = ped_data;
    }

    public String getPed_observacao() {
        return this.ped_observacao;
    }

    public void setPed_observacao(String ped_observacao) {
        this.ped_observacao = ped_observacao;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    @Override
    public String toString() {
        return "Pedido{" + "ped_codigo=" + ped_codigo + ", ped_data=" + ped_data + ", ped_observacao=" + ped_observacao + ", cliente=" + cliente + ", vendedor=" + vendedor + '}';
    }
    
    
}