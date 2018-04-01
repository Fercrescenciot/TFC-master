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
import com.tcc.fernanda.tcc.Banco.Eventos;
import com.tcc.fernanda.tcc.Banco.Usuario;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

import java.util.ArrayList;

public class CadastrarEventos extends AppCompatActivity {

    private DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private DatabaseReference eventosReferencia = databaseReference.child("eventos");

    private EditText nomeEvento;
    private EditText dataEvento;
    private EditText localEvento;
    private CheckBox eventoAtivo;
    private EditText descricaoEvento;
    private Button btnVoltar;
    private Button btnCadastrarEvento;
    private Spinner spinnerUsuario;
    private DatabaseReference firebase;


    ArrayAdapter adapter;
    private ArrayList<String> usuarios;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_eventos);

        final Eventos eventos = new Eventos();
        usuarios = new ArrayList<>();

        nomeEvento = findViewById(R.id.idNomeEvento);
        dataEvento = findViewById(R.id.idDataEvento);
        localEvento = findViewById(R.id.idLocalEvento);
        eventoAtivo = findViewById(R.id.checkBoxEvento);
        spinnerUsuario = findViewById(R.id.idSpinnerEventos);
        descricaoEvento = findViewById(R.id.idDescricaoEvento);
        btnVoltar = findViewById(R.id.btn_voltar_CE);
        btnCadastrarEvento =findViewById(R.id.btn_cad_CE);

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
                eventos.setUsuario(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        btnCadastrarEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventos.setNomeEvento(nomeEvento.getText().toString());
                eventos.setDataEvento(Integer.parseInt(dataEvento.getText().toString()));
                eventos.setLocalEvento(localEvento.getText().toString());
                eventos.setEventoAtivo(eventoAtivo.isChecked());
                eventos.setDescricaoEvento(descricaoEvento.getText().toString());

               eventosReferencia.push().setValue(eventos);


                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(CadastrarEventos.this, MainActivity.class));

            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CadastrarEventos.this, MainActivity.class));
            }
        });
    }
}
