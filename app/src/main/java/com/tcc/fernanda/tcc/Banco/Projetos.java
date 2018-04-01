package com.tcc.fernanda.tcc.Banco;

/**
 * Created by fernanda on 01/03/18.
 */

public class Projetos {
    private String nomeProjeto;
    private String descricaoProjeto;
    private Boolean projetoAtivo;
    private int semestre;
    private int usuario;

    public Projetos(){

    }

    public String getNomeProjeto() {
        return nomeProjeto;
    }

    public void setNomeProjeto(String nomeProjeto) {
        this.nomeProjeto = nomeProjeto;
    }

    public String getDescricaoProjeto() {
        return descricaoProjeto;
    }

    public void setDescricaoProjeto(String descricaoProjeto) {
        this.descricaoProjeto = descricaoProjeto;
    }

    public Boolean getProjetoAtivo() {
        return projetoAtivo;
    }

    public void setProjetoAtivo(Boolean projetoAtivo) {
        this.projetoAtivo = projetoAtivo;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
