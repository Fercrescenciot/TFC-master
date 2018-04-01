package com.tcc.fernanda.tcc.Banco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;

/**
 * Created by fernanda on 01/03/18.
 */

public class Eventos {


    private String id;
    private String nomeEvento;
    private int dataEvento;
    private String localEvento;
    private Boolean eventoAtivo;
    private String descricaoEvento;
    private int usuario;

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Eventos(){

    }


    public void Salvar(){
        DatabaseReference databaseReference = ConfiguracaoFireBase.getFirebase();
        databaseReference.child("eventos").child( getId()).setValue(this);
    }

    public String getNomeEvento() {
        return nomeEvento;
    }

    public void setNomeEvento(String nomeEvento) {
        this.nomeEvento = nomeEvento;
    }

    public int getDataEvento() {
        return dataEvento;
    }

    public void setDataEvento(int dataEvento) {
        this.dataEvento = dataEvento;
    }

    public String getLocalEvento() {
        return localEvento;
    }

    public void setLocalEvento(String localEvento) {
        this.localEvento = localEvento;
    }


    public String getDescricaoEvento() {
        return descricaoEvento;
    }

    public void setDescricaoEvento(String descricaoEvento) {
        this.descricaoEvento = descricaoEvento;
    }

    public Boolean getEventoAtivo() {
        return eventoAtivo;
    }

    public void setEventoAtivo(Boolean eventoAtivo) {
        this.eventoAtivo = eventoAtivo;
    }


    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

}
