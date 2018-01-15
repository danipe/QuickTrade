package com.example.yo.quicktrade.usuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_usuarios);
        DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("usuarios");
        Query q = ddbb.orderByKey();
        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    users.add(dataSnapshot1.getValue(Usuario.class));
                }
                adapter = new UsersAdapter(ListadoUsuariosActivity.this, users);
                ListView lv = findViewById(R.id.lista);
                lv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
