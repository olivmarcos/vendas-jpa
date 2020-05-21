package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Vendedor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vend_codigo;
    private String vend_nome;
    private Double vend_percentual_comissao;

    public int getVend_codigo() {
        return this.vend_codigo;
    }

    public void setVend_codigo(int vend_codigo) {
        this.vend_codigo = vend_codigo;
    }

    public String getVend_nome() {
        return this.vend_nome;
    }

    public void setVend_nome(String vend_nome) {
        this.vend_nome = vend_nome;
    }

    public Double getVend_percentual_comissao() {
        return this.vend_percentual_comissao;
    }

    public void setVend_percentual_comissao(Double vend_percentual_comissao) {
        this.vend_percentual_comissao = vend_percentual_comissao;
    }

    @Override
    public String toString() {
        return "Vendedor{" + "vend_codigo=" + vend_codigo + ", vend_nome=" + vend_nome + ", vend_percentual_comissao=" + vend_percentual_comissao + '}';
    }
}