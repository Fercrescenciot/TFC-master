package com.tcc.fernanda.tcc.Banco;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Exclude;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;

/**
 * Created by fernanda on 01/03/18.
 */

public class Usuario {

    private String id;
    private String nome;
    private String senha;
    private String email;
    private int tipoUsu;

    public Usuario(){

    }

    public void Salvar(){
        DatabaseReference databaseReference = ConfiguracaoFireBase.getFirebase();
        databaseReference.child("usuarios").child( getId()).setValue(this);
    }

    @Exclude
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTipoUsu() {
        return tipoUsu;
    }

    public void setTipoUsu(int tipoUsu) {
        this.tipoUsu = tipoUsu;
    }
}
