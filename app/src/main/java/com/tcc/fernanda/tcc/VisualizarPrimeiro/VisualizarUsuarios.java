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
import com.tcc.fernanda.tcc.Banco.Usuario;
import com.tcc.fernanda.tcc.ConfiguracaoFireBase;
import com.tcc.fernanda.tcc.MainActivity;
import com.tcc.fernanda.tcc.R;

import java.util.ArrayList;

public class VisualizarUsuarios extends AppCompatActivity {



    private ListView listViewUsuario;
    private Button btnVoltar;
    private Button btnCadastrarUsuarios;
    private ArrayAdapter adapter;
    private DatabaseReference firebase;
    private ArrayList<String> usuarios;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_usuarios);



        listViewUsuario=   findViewById(R.id.lv_usuarios);
        btnCadastrarUsuarios = findViewById(R.id.btn_Cad_VU);
        btnVoltar = findViewById(R.id.btn_voltar_VU);
        usuarios =  new ArrayList<>();

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

        adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, usuarios);
        adapter.setDropDownViewResource(android.R.layout.simple_list_item_1);
        listViewUsuario.setAdapter(adapter);


        listViewUsuario.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),"Deu certo", Toast.LENGTH_SHORT).show();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(VisualizarUsuarios.this, MainActivity.class));
            }
        });
    }
}
