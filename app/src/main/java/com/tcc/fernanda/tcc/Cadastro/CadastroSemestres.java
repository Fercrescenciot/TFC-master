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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.tcc.fernanda.tcc.Banco.Semestre;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

public class CadastroSemestres extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference semestresReferencia = databaseReference.child("semestres");

    private Spinner spinnerSemestre;
    private EditText ano;
    private Button btnVoltar;
    private Button btnCadastrarSemestre;

    String semestre[] = {"1 semestre", "2 semestre"};

    ArrayAdapter<String> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_semestres);

        final Semestre semestres = new Semestre();

        btnVoltar = findViewById(R.id.btn_voltar_CS);
        btnCadastrarSemestre = findViewById(R.id.btn_cad_CS);
        spinnerSemestre = findViewById(R.id.idSpinnerSemestres);
        ano = findViewById(R.id.idDataSemestre);


        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, semestre);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerSemestre.setAdapter(adapter);


        spinnerSemestre.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                semestres.setSemestre(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastroSemestres.this, MainActivity.class));
            }
        });

        btnCadastrarSemestre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(ano.equals(null)){
                    Toast.makeText(getApplicationContext(), "Falta colocar o ano", Toast.LENGTH_SHORT).show();
                }else {
                    semestres.setAno(Integer.parseInt(ano.getText().toString()));
                    semestresReferencia.push().setValue(semestres);
                    Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }

}

