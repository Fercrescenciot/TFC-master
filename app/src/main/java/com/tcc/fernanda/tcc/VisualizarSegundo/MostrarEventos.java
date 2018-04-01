package com.tcc.fernanda.tcc.VisualizarSegundo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
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

public class MostrarEventos extends AppCompatActivity {


    private EditText nomeEvento;
    private EditText dataEvento;
    private EditText localEvento;
    private CheckBox eventoAtivo;
    private EditText descricaoEvento;
    private Button btnVoltar;
    private Button btnCadastrarEvento;
    private Spinner spinnerUsuario;
    private DatabaseReference firebase;
    private DatabaseReference firebase2;


    ArrayAdapter adapter;
    private ArrayList<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostrar_eventos);


        final Eventos eventos = new Eventos();
        usuarios = new ArrayList<>();

        nomeEvento = findViewById(R.id.idNomeEvento);
        dataEvento = findViewById(R.id.idDataEvento);
        localEvento = findViewById(R.id.idLocalEvento);
        eventoAtivo = findViewById(R.id.checkBoxEvento);
        spinnerUsuario = findViewById(R.id.idSpinnerEventos);
        descricaoEvento = findViewById(R.id.idDescricaoEvento);
        btnVoltar = findViewById(R.id.btn_voltar_ME);
        btnCadastrarEvento =findViewById(R.id.btn_cad_ME);

        Bundle extra = getIntent().getExtras();


        if(extra!= null){

            String nome = extra.getString("nome");
            Toast.makeText(MostrarEventos.this, nome, Toast.LENGTH_LONG).show();




            firebase2 = ConfiguracaoFireBase.getFirebase().child("eventos").child(nome);

/*
                .addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String prevDataSnapshot) {
                nomeEvento.setText("");
                localEvento.setText("");
                descricaoEvento.setText("");

                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Eventos eve = dataSnapshot.getValue(Eventos.class);
                    nomeEvento.setText(eve.getNomeEvento());
                   //dataEvento.setText(eve.getDataEvento());
                   localEvento.setText(eve.getLocalEvento());
                   descricaoEvento.setText(eve.getDescricaoEvento());

                }
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });*/


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

                //eventosReferencia.push().setValue(eventos);


                Toast.makeText(getApplicationContext(), "Cadastro efetuado com sucesso", Toast.LENGTH_SHORT).show();

            }
        });

        //FECHA O BUNDLE
        }else{
            Toast.makeText(getApplicationContext(), "OPPPPPPPPPPPPPPA", Toast.LENGTH_LONG).show();
        }

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MostrarEventos.this, MainActivity.class));
            }
        });
    }
    }
