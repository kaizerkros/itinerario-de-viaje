package com.example.itinerarioviaje;

import android.app.Activity;
import android.os.Bundle;

public class DetalleActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        findViewById(R.id.btnEliminar).setOnClickListener(v -> finish());
    }
}
