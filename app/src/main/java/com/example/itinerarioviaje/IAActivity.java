package com.example.itinerarioviaje;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class IAActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ia);

        EditText edtConsulta = findViewById(R.id.edtConsultaIA);
        TextView txtRespuesta = findViewById(R.id.txtRespuestaIA);
        findViewById(R.id.btnPreguntarIA).setOnClickListener(v -> {
            String consulta = edtConsulta.getText().toString().trim();
            if (consulta.isEmpty()) {
                edtConsulta.setError(getString(R.string.error_ia_vacio));
                return;
            }
            txtRespuesta.setText(AsistenteViaje.responder(this, consulta));
        });
    }
}
