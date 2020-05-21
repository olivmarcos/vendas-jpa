package models;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int cli_codigo;
    private String cli_nome;
    private String cli_cpf;
    private String cli_ultima_compra;

    public int getCli_codigo() {
        return this.cli_codigo;
    }

    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }

    public String getCli_nome() {
        return this.cli_nome;
    }

    public void setCli_nome(String cli_nome) {
        this.cli_nome = cli_nome;
    }

    public String getCli_cpf() {
        return this.cli_cpf;
    }

    public void setCli_cpf(String cli_cpf) {
        this.cli_cpf = cli_cpf;
    }

    public String getCli_ultima_compra() {
        return this.cli_ultima_compra;
    }

    public void setCli_ultima_compra(Date cli_ultima_compra) {
        String data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(cli_ultima_compra);
        this.cli_ultima_compra = data;
    }

    public void setCli_ultima_compra(String cli_ultima_compra) {
        this.cli_ultima_compra = cli_ultima_compra;
    }

    @Override
    public String toString() {
        return "{" + " cli_codigo='" + getCli_codigo() + "'" + ", cli_nome='" + getCli_nome() + "'" + ", cli_cpf='"
                + getCli_cpf() + "'" + ", cli_ultima_compra='" + getCli_ultima_compra() + "'" + "}";
    }
}