package com.tcc.fernanda.tcc.Cadastro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.tcc.fernanda.tcc.Banco.Projetos;
import com.tcc.fernanda.tcc.Banco.Usuario;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

import java.util.ArrayList;

public class CadastroProjetos extends AppCompatActivity {


    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference projetosReferencia = databaseReference.child("projetos");

    private EditText nomeProjeto;
    private EditText descricaoProjeto;
    private CheckBox projetoAtivo;
    private Button btnVoltar;
    private Button btnCadastrarProjeto;
    private Spinner spinnerSemestre;
    private Spinner spinnerAlunos;
    private DatabaseReference firebase;

    String dados[] = {"1º Semestre", "2º Semestre"};



    ArrayAdapter adapter;
    private ArrayList<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_projetos);


        final Projetos projetos = new Projetos();
        usuarios = new ArrayList<>();

        nomeProjeto = findViewById(R.id.idNomeProjeto);
        spinnerSemestre = findViewById(R.id.idSpinnerProjetos);
        spinnerAlunos = findViewById(R.id.idSpinnerAlunosPJ);
        descricaoProjeto = findViewById(R.id.idDescricaoProjetos);
        projetoAtivo = findViewById(R.id.checkBoxProjeto);
        btnVoltar = findViewById(R.id.btn_voltar_CP);
        btnCadastrarProjeto = findViewById(R.id.btn_cad_CP);

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


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, dados);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapter);

        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, usuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAlunos.setAdapter(adapter);

        spinnerSemestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                projetos.setSemestre(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        spinnerAlunos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                projetos.setUsuario(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });




        btnCadastrarProjeto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                projetos.setNomeProjeto(nomeProjeto.getText().toString());
                projetos.setDescricaoProjeto(descricaoProjeto.getText().toString());
                projetos.setProjetoAtivo(projetoAtivo.isChecked());

                projetosReferencia.push().setValue(projetos);

                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroProjetos.this, MainActivity.class));
            }
        });
    }
}
