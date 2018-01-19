package com.example.yo.quicktrade.usuarios;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Usuario;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class EditarUsuarioActivity extends AppCompatActivity {
    Usuario user;
    EditText direccion, nombre, apellidos, username;
    Button modificar, volver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editar_usuario);
        user = (Usuario) getIntent().getSerializableExtra("user");
        direccion = findViewById(R.id.direccion);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        username = findViewById(R.id.username);
        modificar = findViewById(R.id.modificarButton);
        volver = findViewById(R.id.volverButton);

        direccion.setText(user.getDireccion());
        nombre.setText(user.getNombre());
        apellidos.setText(user.getApellidos());
        username.setText(user.getUser());

        modificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference("usuarios");
                Query q=bbdd.orderByChild("user").equalTo(user.getUser());
                user.setApellidos(apellidos.getText().toString());
                user.setDireccion(direccion.getText().toString());
                user.setNombre(nombre.getText().toString());
                user.setUser(username.getText().toString());
                q.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Log.e("mal", ""+dataSnapshot.getChildrenCount());
                        for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                            String clave=datasnapshot.getKey();
                            bbdd.child(clave).setValue(user);
                        }
                        Toast.makeText(EditarUsuarioActivity.this, "Se ha modificado al hiueputa", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }
}
