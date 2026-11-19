package com.example.itinerarioviaje;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class AgregarActivity extends Activity {
    private Viaje viajeExistente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agregar);
        EditText edtDestino = findViewById(R.id.edtDestino);
        EditText edtFechaInicio = findViewById(R.id.edtFechaInicio);
        EditText edtDuracion = findViewById(R.id.edtDuracion);
        EditText edtPresupuesto = findViewById(R.id.edtPresupuesto);
        EditText edtActividades = findViewById(R.id.edtActividades);

        String viajeJson = getIntent().getStringExtra(DetalleActivity.EXTRA_VIAJE);
        if (viajeJson != null) {
            viajeExistente = Viaje.deserialize(viajeJson);
            edtDestino.setText(viajeExistente.destino);
            edtFechaInicio.setText(viajeExistente.fecha);
            edtDuracion.setText(viajeExistente.duracion);
            edtPresupuesto.setText(viajeExistente.presupuesto.replace("$", "").replace(".", ""));
            edtActividades.setText(viajeExistente.actividades);
        }

        findViewById(R.id.btnGuardarViaje).setOnClickListener(v -> {
            if (edtDestino.getText().toString().trim().isEmpty()) {
                edtDestino.setError(getString(R.string.error_destino));
                return;
            }
            if (edtFechaInicio.getText().toString().trim().isEmpty()) {
                edtFechaInicio.setError(getString(R.string.error_fecha));
                return;
            }
            if (edtDuracion.getText().toString().trim().isEmpty()) {
                edtDuracion.setError(getString(R.string.error_duracion));
                return;
            }
            if (edtPresupuesto.getText().toString().trim().isEmpty()) {
                edtPresupuesto.setError(getString(R.string.error_presupuesto));
                return;
            }

            String id = viajeExistente == null ? "" : viajeExistente.id;
            boolean realizado = viajeExistente != null && viajeExistente.realizado;
            Viaje viaje = new Viaje(
                    id,
                    edtDestino.getText().toString().trim(),
                    edtFechaInicio.getText().toString().trim(),
                    edtDuracion.getText().toString().trim(),
                    getString(R.string.presupuesto_formato, edtPresupuesto.getText().toString().trim()),
                    edtActividades.getText().toString().trim(),
                    realizado);
            ViajeRepository.guardarViaje(this, viaje);
            Toast.makeText(this, R.string.viaje_guardado, Toast.LENGTH_SHORT).show();
            finish();
        });
    }
}
