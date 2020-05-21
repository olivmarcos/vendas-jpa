package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class PedidoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int pedp_codigo;
    private int pedp_quantidade;
    private Double pedp_valor;
    private Double pedp_valor_total;
    @ManyToOne
    private Pedido pedido;
    @ManyToOne
    private Produto produto;

    public int getPedp_codigo() {
        return this.pedp_codigo;
    }

    public void setPedp_codigo(int pedp_codigo) {
        this.pedp_codigo = pedp_codigo;
    }

    public int getPedp_quantidade() {
        return this.pedp_quantidade;
    }

    public void setPedp_quantidade(int pedp_quantidade) {
        this.pedp_quantidade = pedp_quantidade;
    }

    public Double getPedp_valor() {
        return this.pedp_valor;
    }

    public void setPedp_valor(Double pedp_valor) {
        this.pedp_valor = pedp_valor;
    }

    public Double getPedp_valor_total() {
        return this.pedp_valor_total;
    }

    public void setPedp_valor_total(Double pedp_valor_total) {
        this.pedp_valor_total = pedp_valor_total;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "PedidoProduto{" + "pedp_codigo=" + pedp_codigo + ", pedp_quantidade=" + pedp_quantidade + ", pedp_valor=" + pedp_valor + ", pedp_valor_total=" + pedp_valor_total + ", pedido=" + pedido + ", produto=" + produto + '}';
    }
}
