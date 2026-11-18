package com.example.itinerarioviaje;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;

public class RegistroActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        Button btnRegistrar = findViewById(R.id.btnRegistrar);
        btnRegistrar.setOnClickListener(v -> finish());
    }
}
