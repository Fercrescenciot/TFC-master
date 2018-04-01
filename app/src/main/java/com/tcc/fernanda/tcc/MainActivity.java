package com.tcc.fernanda.tcc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tcc.fernanda.tcc.Cadastro.CadastroSemestres;
import com.tcc.fernanda.tcc.Cadastro.MensagemEnv;
import com.tcc.fernanda.tcc.VisualizarPrimeiro.VisualizaProjetos;
import com.tcc.fernanda.tcc.VisualizarPrimeiro.VisualizarEventos;
import com.tcc.fernanda.tcc.VisualizarPrimeiro.VisualizarUsuarios;

public class MainActivity extends AppCompatActivity {


    private Button btnPerfil;
    private Button btnSemestres;
    private Button btnProjetos;
    private Button btnEventos;
    private Button btnMensagens;
    private Button btnSair;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btnPerfil = findViewById(R.id.btnUsuario);
        btnSemestres = findViewById(R.id.btnSemestre);
        btnProjetos = findViewById(R.id.btnProjetos);
        btnMensagens = findViewById(R.id.btnMensagens);
        btnEventos = findViewById(R.id.btnEventos);
        btnSair = findViewById(R.id.btnSair);

        btnPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VisualizarUsuarios.class));
            }
        });

        btnSemestres.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CadastroSemestres.class));
            }
        });


        btnProjetos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VisualizaProjetos.class));
            }
        });

        btnMensagens.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, MensagemEnv.class));
            }
        });

        btnEventos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, VisualizarEventos.class));
            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Login.class));
            }
        });

    }
}
