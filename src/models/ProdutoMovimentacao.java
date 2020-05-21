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
public class ProdutoMovimentacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prodm_codigo;
    private String prodm_data;
    private String prodm_descricao;
    @ManyToOne
    private Produto produto;
    
    public int getProdm_codigo() {
        return this.prodm_codigo;
    }

    public void setProdm_codigo(int prodm_codigo) {
        this.prodm_codigo = prodm_codigo;
    }

    public String getProdm_data() {
        return this.prodm_data;
    }

    public void setProdm_data(Date prodm_data) {
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(prodm_data);
        this.prodm_data = data;
    }

    public void setProdm_data(String prodm_data) {
        this.prodm_data = prodm_data;
    }

    public String getProdm_descricao() {
        return this.prodm_descricao;
    }

    public void setProdm_descricao(String prodm_descricao) {
        this.prodm_descricao = prodm_descricao;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    @Override
    public String toString() {
        return "ProdutoMovimentacao{" + "prodm_codigo=" + prodm_codigo + ", prodm_data=" + prodm_data + ", prodm_descricao=" + prodm_descricao + ", produto=" + produto + '}';
    }
    
    
}