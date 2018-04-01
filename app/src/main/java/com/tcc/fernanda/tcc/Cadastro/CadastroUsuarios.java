package com.tcc.fernanda.tcc.Cadastro;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tcc.fernanda.tcc.Banco.Usuario;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

public class CadastroUsuarios extends AppCompatActivity {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference usuariosReferencia = databaseReference.child("usuarios");
    private FirebaseAuth firebaseAuth;

    private EditText nomeUsuario;
    private EditText senhaUsuario;
    private EditText confirmSehaUsuario;
    private EditText emailUsuario;
    private Button btnVoltar;
    private Button btncadastrarusuario;
    private Spinner spinnerUsuario;



    String nomes[] = {"Administrador", "Aluno", "Orientador"};

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuarios);

        final Usuario usuario = new Usuario();


        nomeUsuario = findViewById(R.id.idNomeusu);
        senhaUsuario = findViewById(R.id.idSenhaUsu);
        emailUsuario = findViewById(R.id.idEmailUsu);
        spinnerUsuario = findViewById(R.id.idSpinnesUsuario);
        btnVoltar = findViewById(R.id.btn_voltar_CU);
        btncadastrarusuario = findViewById(R.id.btn_cad_CU);

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroUsuarios.this, MainActivity.class));
            }
        });


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, nomes);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerUsuario.setAdapter(adapter);


        spinnerUsuario.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                usuario.setTipoUsu(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btncadastrarusuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                usuario.setNome(nomeUsuario.getText().toString());
                usuario.setSenha(senhaUsuario.getText().toString());
                usuario.setEmail(emailUsuario.getText().toString());
                firebaseAuth = ConfiguracaoFireBase.getFirebaseAuth();
                firebaseAuth.createUserWithEmailAndPassword(
                        usuario.getEmail(),
                        usuario.getSenha()
                ).addOnCompleteListener(CadastroUsuarios.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                            FirebaseUser firebaseUser = task.getResult().getUser();
                            usuario.setId(firebaseUser.getUid());
                            usuario.Salvar();

                        } else {
                            Toast.makeText(getApplicationContext(), "ERRO", Toast.LENGTH_SHORT).show();
                        }
                    }

                    ;
                });


            }
        });

    }













}
