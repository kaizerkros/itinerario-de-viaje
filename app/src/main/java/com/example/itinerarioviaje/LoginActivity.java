package com.example.itinerarioviaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LoginActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button btnIngresar = findViewById(R.id.btnIngresar);
        Button btnCrearCuenta = findViewById(R.id.btnCrearCuenta);
        btnIngresar.setOnClickListener(v -> startActivity(new Intent(this, ListaActivity.class)));
        btnCrearCuenta.setOnClickListener(v -> startActivity(new Intent(this, RegistroActivity.class)));
    }
}
