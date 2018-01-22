package com.example.yo.quicktrade.usuarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.adapters.UsersAdapter;
import com.example.yo.quicktrade.modelos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListadoUsuariosActivity extends AppCompatActivity {

    UsersAdapter adapter;
    ArrayList<Usuario> users = new ArrayList<>();
    ListView lv;
    Button volver;

    @Override
    protected void onRestart() {
        super.onRestart();
        users = new ArrayList<>();
        query();
        lv = findViewById(R.id.lista);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        volver = findViewById(R.id.volverButton);
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        query();
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(users.size()>0) {
                    Intent i = new Intent(ListadoUsuariosActivity.this, EditarUsuarioActivity.class);
                    i.putExtra("user", users.get(position));
                    startActivity(i);
                }
            }
        });
    }

    public void query() {
        DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("usuarios");
        Query q = ddbb.orderByKey();
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    users.add(dataSnapshot1.getValue(Usuario.class));
                }
                adapter = new UsersAdapter(ListadoUsuariosActivity.this, users);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
