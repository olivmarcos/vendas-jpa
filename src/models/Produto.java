package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int prod_codigo;
    private String prod_descricao;
    private int prod_saldo;
    private String prod_unidade;

    public int getProd_codigo() {
        return this.prod_codigo;
    }

    public void setProd_codigo(int prod_codigo) {
        this.prod_codigo = prod_codigo;
    }

    public String getProd_descricao() {
        return this.prod_descricao;
    }

    public void setProd_descricao(String prod_descricao) {
        this.prod_descricao = prod_descricao;
    }

    public int getProd_saldo() {
        return this.prod_saldo;
    }

    public void setProd_saldo(int prod_saldo) {
        this.prod_saldo = prod_saldo;
    }

    public String getProd_unidade() {
        return this.prod_unidade;
    }

    public void setProd_unidade(String prod_unidade) {
        this.prod_unidade = prod_unidade;
    }

    @Override
    public String toString() {
        return "Produto{" + "prod_codigo=" + prod_codigo + ", prod_descricao=" + prod_descricao + ", prod_saldo=" + prod_saldo + ", prod_unidade=" + prod_unidade + '}';
    }
}
