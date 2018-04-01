package com.tcc.fernanda.tcc.Banco;

/**
 * Created by fernanda on 01/03/18.
 */

public class Mensagem {
    private String titulo;
    private String mensagem;
    private int usuario;

    public Mensagem(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }


    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }
}
