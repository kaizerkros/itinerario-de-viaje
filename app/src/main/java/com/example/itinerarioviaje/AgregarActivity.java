package com.example.itinerarioviaje;

import android.app.Activity;
import android.os.Bundle;

public class AgregarActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        findViewById(R.id.btnGuardarViaje).setOnClickListener(v -> finish());
    }
}
