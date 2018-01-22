 package com.example.yo.quicktrade.productos;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Producto;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

 public class NewProductActivity extends AppCompatActivity {
    EditText nombre, descripcion, precio;
    RadioGroup radioGroup;
    Button cerrar, subir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_product);
        nombre = findViewById(R.id.nombre);
        descripcion = findViewById(R.id.descripcion);
        precio = findViewById(R.id.precio);
        cerrar = findViewById(R.id.volverButton);
        subir = findViewById(R.id.registrarButton);
        radioGroup = findViewById(R.id.radioGroup);
        cerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        subir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(nombre.getText().toString().length()>0 && descripcion.getText().toString().length()>0 && precio.getText().toString().length()>0 && radioGroup.getCheckedRadioButtonId() >= 0) {
                    Producto producto = new Producto(
                            nombre.getText().toString(),
                            descripcion.getText().toString(),
                            precio.getText().toString(),
                            ((RadioButton) findViewById(radioGroup.getCheckedRadioButtonId())).getText().toString(),
                            FirebaseAuth.getInstance().getCurrentUser().getUid());
                    DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("productos");
                    ddbb.child(ddbb.push().getKey()).setValue(producto);
                    Toast.makeText(NewProductActivity.this, "Producto creado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(NewProductActivity.this, "Introduce todos los datos necesarios", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
