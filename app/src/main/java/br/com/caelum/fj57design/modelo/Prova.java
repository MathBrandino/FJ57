package br.com.caelum.fj57design.modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by matheus on 14/09/15.
 */
public class Prova implements Serializable {

    private String materia;
    private String data;
    private List<String> topicos = new ArrayList<>();

    public Prova(String materia, String data) {
        this.materia = materia;
        this.data = data;
    }

    public String getMateria() {
        return materia;
    }

    public String getData() {
        return data;
    }

    public List<String> getTopicos() {
        return topicos;
    }

    public void setTopicos(List<String> topicos) {
        this.topicos = topicos;
    }

    @Override
    public String toString() {
        return getMateria();
    }
}
