package com.example.yo.quicktrade.usuarios;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.adapters.ProductosAdapter;
import com.example.yo.quicktrade.modelos.Producto;
import com.example.yo.quicktrade.productos.NewProductActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PerfilActivity extends AppCompatActivity {
    Button subir, cerrar;
    ListView lista;
    RadioGroup radioGroup;

    @Override
    protected void onRestart() {
        super.onRestart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        subir = findViewById(R.id.subirButton);
        cerrar = findViewById(R.id.cerrarButton);
        lista = findViewById(R.id.lista);
        radioGroup = findViewById(R.id.radioGroup);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                query();
            }
        });
        query();

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PerfilActivity.this, NewProductActivity.class);
                startActivity(i);
            }
        });

        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
    }

    public void query() {
        DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("productos");
        Query q = ddbb.orderByKey();
        if(radioGroup.getCheckedRadioButtonId() >= 0) {
            q = ddbb.orderByChild("categoria").equalTo(((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString());
        }

        q.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ArrayList<Producto> productos = new ArrayList<>();
                for (DataSnapshot dataSnapshot1: dataSnapshot.getChildren()) {
                    productos.add(dataSnapshot1.getValue(Producto.class));
                }

                lista.setAdapter(new ProductosAdapter(productos, PerfilActivity.this));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}
