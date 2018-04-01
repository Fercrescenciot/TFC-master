package com.tcc.fernanda.tcc.Cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tcc.fernanda.tcc.Banco.Mensagem;
import com.tcc.fernanda.tcc.Banco.Usuario;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

import java.util.ArrayList;

public class MensagemEnv extends AppCompatActivity {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference mensagemReferencia = databaseReference.child("mensagens");


    private EditText assuntoMensagem;
    private EditText txtMensagem;
    private Spinner spinnerUsuario;
    private Button btnVoltar;
    private Button btnEnviarmensagem;
    private DatabaseReference firebase;
    final Mensagem mensagem = new Mensagem();

    ArrayAdapter adapter;
    private ArrayList<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mensagem_env);

        usuarios = new ArrayList<>();

        assuntoMensagem = findViewById(R.id.idAssuntoMsg);
        spinnerUsuario = findViewById(R.id.idSpinnerMensagem);
        txtMensagem = findViewById(R.id.idMensagem);
        btnVoltar = findViewById(R.id.btn_voltar_CM);
        btnEnviarmensagem = findViewById(R.id.btn_cad_CM);


       firebase = ConfiguracaoFireBase.getFirebase().child("usuarios");


        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                usuarios.clear();

                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Usuario usuario = dados.getValue(Usuario.class);
                    usuarios.add(usuario.getNome());

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adapter);

        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                mensagem.setUsuario(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MensagemEnv.this, MainActivity.class));
            }
        });

        btnEnviarmensagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mensagem.setTitulo(assuntoMensagem.getText().toString());
                mensagem.setMensagem(txtMensagem.getText().toString());

                mensagemReferencia.push().setValue(mensagem);

                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
            }
        });



    }
}
