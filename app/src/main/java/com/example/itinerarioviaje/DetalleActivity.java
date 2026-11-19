package com.example.itinerarioviaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class DetalleActivity extends Activity {
    static final String EXTRA_VIAJE = "com.example.itinerarioviaje.VIAJE";
    private Viaje viaje;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        String viajeJson = getIntent().getStringExtra(EXTRA_VIAJE);
        if (viajeJson == null) {
            finish();
            return;
        }
        viaje = Viaje.deserialize(viajeJson);
        mostrarViaje();

        findViewById(R.id.btnEditar).setOnClickListener(v -> {
            Intent intent = new Intent(this, AgregarActivity.class);
            intent.putExtra(EXTRA_VIAJE, viaje.serialize());
            startActivity(intent);
            finish();
        });
        Button btnMarcar = findViewById(R.id.btnMarcar);
        btnMarcar.setOnClickListener(v -> {
            viaje = new Viaje(
                    viaje.id, viaje.destino, viaje.fecha, viaje.duracion,
                    viaje.presupuesto, viaje.actividades, !viaje.realizado);
            ViajeRepository.guardarViaje(this, viaje);
            mostrarViaje();
        });
        findViewById(R.id.btnEliminar).setOnClickListener(v -> {
            ViajeRepository.eliminarViaje(this, viaje.id);
            finish();
        });
    }

    private void mostrarViaje() {
        ((TextView) findViewById(R.id.txtDestinoDetalle)).setText(viaje.destino);
        ((TextView) findViewById(R.id.txtFechaDetalle)).setText(viaje.fecha);
        ((TextView) findViewById(R.id.txtDuracionDetalle)).setText(viaje.duracion);
        ((TextView) findViewById(R.id.txtPresupuestoDetalle)).setText(
                getString(R.string.presupuesto_detalle_formato, viaje.presupuesto));
        ((TextView) findViewById(R.id.txtActividades)).setText(
                viaje.actividades.isEmpty() ? getString(R.string.sin_actividades) : viaje.actividades);
        ((TextView) findViewById(R.id.txtEstadoDetalle)).setText(
                viaje.realizado ? R.string.estado_realizado : R.string.estado_pendiente);
        ((Button) findViewById(R.id.btnMarcar)).setText(
                viaje.realizado ? R.string.marcar_pendiente : R.string.marcar);
    }
}
