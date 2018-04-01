package com.tcc.fernanda.tcc.VisualizarPrimeiro;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.tcc.fernanda.tcc.Banco.Projetos;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

import java.util.ArrayList;

public class VisualizaProjetos extends AppCompatActivity {


    private ListView listViewProjetos;
    private Button btnVoltar;
    private Button btnCadastrarProjetos;
    private ArrayAdapter adapter;
    private DatabaseReference firebase;
    private ArrayList<String> projetos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualiza_projetos);

        listViewProjetos =   findViewById(R.id.lv_projetos);
        btnCadastrarProjetos = findViewById(R.id.btn_cad_VP);
        btnVoltar = findViewById(R.id.btn_voltar_VP);
        projetos =  new ArrayList<>();

        firebase = ConfiguracaoFireBase.getFirebase().child("projetos");

        firebase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                projetos.clear();

                for(DataSnapshot dados: dataSnapshot.getChildren()){
                    Projetos projeto = dados.getValue(Projetos.class);
                    projetos.add(projeto.getNomeProjeto());

                }
                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, projetos);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listViewProjetos.setAdapter(adapter);


        listViewProjetos.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                Toast.makeText(getApplicationContext(),"Deu certo", Toast.LENGTH_SHORT).show();
                return false;
            }
        });


        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualizaProjetos.this, MainActivity.class));
            }
        });
    }
}
