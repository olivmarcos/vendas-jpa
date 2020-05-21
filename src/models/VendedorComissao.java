package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.GenerationType;

@Entity
public class VendedorComissao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vnc_codigo;
    private Double vnc_comissao;
    @ManyToOne
    private Vendedor vendedor;
    @ManyToOne
    private Pedido pedido;
    
    public int getVnc_codigo() {
        return this.vnc_codigo;
    }

    public void setVnc_codigo(int vnc_codigo) {
        this.vnc_codigo = vnc_codigo;
    }

    public Double getVnc_comissao() {
        return this.vnc_comissao;
    }

    public void setVnc_comissao(Double vnc_comissao) {
        this.vnc_comissao = vnc_comissao;
    }

    public Vendedor getVendedor() {
        return vendedor;
    }

    public void setVendedor(Vendedor vendedor) {
        this.vendedor = vendedor;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    @Override
    public String toString() {
        return "VendedorComissao{" + "vnc_codigo=" + vnc_codigo + ", vnc_comissao=" + vnc_comissao + ", vendedor=" + vendedor + ", pedido=" + pedido + '}';
    }
}