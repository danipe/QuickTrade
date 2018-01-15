package com.example.yo.quicktrade.usuarios;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yo.quicktrade.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    EditText correo, password;
    Button volver, login;
    final String TAG = "tag";
    final FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (mAuth.getCurrentUser()!=null) {
            Toast.makeText(this, ""+mAuth.getCurrentUser().getEmail(), Toast.LENGTH_SHORT).show();
            Intent i = new Intent(LoginActivity.this, PerfilActivity.class);
            startActivity(i);
        }
        setContentView(R.layout.activity_login);
        correo = findViewById(R.id.correo);
        password = findViewById(R.id.password);
        volver = findViewById(R.id.volverButton);
        login = findViewById(R.id.registrarButton);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
        volver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void login() {
        mAuth.signInWithEmailAndPassword(correo.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()) {
                    Toast.makeText(LoginActivity.this, "Usuario logeado", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(LoginActivity.this, PerfilActivity.class);
                    startActivity(i);
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, "Fallo en la autenticación", Toast.LENGTH_SHORT).show();
                    Toast.makeText(LoginActivity.this, "Asegurate de introducir datos correctos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
