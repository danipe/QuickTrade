package com.example.yo.quicktrade;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.yo.quicktrade.usuarios.ListadoUsuariosActivity;
import com.example.yo.quicktrade.usuarios.LoginActivity;
import com.example.yo.quicktrade.usuarios.RegisterActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void registrar(View v) {
        Intent i = new Intent(this, RegisterActivity.class);
        startActivity(i);
    }

    public void login(View v) {
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
    }

    public void mostrar(View v) {
        Intent i = new Intent(this, ListadoUsuariosActivity.class);
        startActivity(i);
    }
}
