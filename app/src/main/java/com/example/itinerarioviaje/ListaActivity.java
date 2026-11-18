package com.example.itinerarioviaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ListaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        findViewById(R.id.btnAgregar).setOnClickListener(v -> startActivity(new Intent(this, AgregarActivity.class)));
        findViewById(R.id.btnPerfil).setOnClickListener(v -> startActivity(new Intent(this, IAActivity.class)));
        findViewById(R.id.cardViaje1).setOnClickListener(v -> startActivity(new Intent(this, DetalleActivity.class)));
        findViewById(R.id.cardViaje2).setOnClickListener(v -> startActivity(new Intent(this, DetalleActivity.class)));
        findViewById(R.id.cardViaje3).setOnClickListener(v -> startActivity(new Intent(this, DetalleActivity.class)));
    }
}
