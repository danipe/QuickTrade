package com.example.yo.quicktrade.usuarios;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yo.quicktrade.R;
import com.example.yo.quicktrade.modelos.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    EditText usuario, correo, pass, nombre, apellidos, direccion;
    Button volver, registrar;
    final String TAG = "tag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usuario = findViewById(R.id.username);
        correo = findViewById(R.id.correo);
        pass = findViewById(R.id.password);
        nombre = findViewById(R.id.nombre);
        apellidos = findViewById(R.id.apellidos);
        direccion = findViewById(R.id.direccion);
        volver = findViewById(R.id.volverButton);
        registrar = findViewById(R.id.registrarButton);
        registrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registrar();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void registrar() {
        final FirebaseAuth mAuth;
// ...
        mAuth = FirebaseAuth.getInstance();
        mAuth.createUserWithEmailAndPassword(correo.getText().toString(), pass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            DatabaseReference ddbb = FirebaseDatabase.getInstance().getReference("usuarios");
                            ddbb.child(user.getUid()).setValue(new Usuario(
                                    usuario.getText().toString(),
                                    nombre.getText().toString(),
                                    direccion.getText().toString(),
                                    apellidos.getText().toString()
                            ));
                            Toast.makeText(RegisterActivity.this, "Usuario registrado con éxito", Toast.LENGTH_SHORT).show();
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Fallo en la autenticación",
                                    Toast.LENGTH_SHORT).show();
                            Toast.makeText(RegisterActivity.this, "Asegurate de introducir bien los datos",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    };
}
