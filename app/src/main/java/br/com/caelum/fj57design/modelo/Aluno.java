package br.com.caelum.fj57design.modelo;

import java.io.Serializable;

/**
 * Created by matheus on 09/09/15.
 */
public class Aluno implements Serializable{

    private long id;
    private String nome;
    private String telefone;
    private String site;
    private String endereco;
    private double nota;
    private String caminhoFoto;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public void setCaminhoFoto(String caminhoFoto) {
        this.caminhoFoto = caminhoFoto;
    }

    public String getCaminhoFoto() {
        return caminhoFoto;
    }
}
