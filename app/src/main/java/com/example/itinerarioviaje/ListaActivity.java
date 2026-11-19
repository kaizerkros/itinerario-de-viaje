package com.example.itinerarioviaje;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class ListaActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);
        findViewById(R.id.btnAgregar).setOnClickListener(v -> startActivity(new Intent(this, AgregarActivity.class)));
        findViewById(R.id.btnPerfil).setOnClickListener(v -> startActivity(new Intent(this, IAActivity.class)));
    }

    @Override
    protected void onResume() {
        super.onResume();
        mostrarViajes(ViajeRepository.getViajes(this));
    }

    private void mostrarViajes(List<Viaje> viajes) {
        LinearLayout contenedor = findViewById(R.id.contenedorViajes);
        contenedor.removeAllViews();
        for (Viaje viaje : viajes) {
            LinearLayout tarjeta = (LinearLayout) LayoutInflater.from(this)
                    .inflate(R.layout.item_viaje, contenedor, false);
            tarjeta.setContentDescription(getString(R.string.viaje_accessibility, viaje.destino));
            TextView titulo = tarjeta.findViewById(R.id.txtDestinoTarjeta);
            titulo.setText(viaje.destino);
            TextView resumen = tarjeta.findViewById(R.id.txtResumenTarjeta);
            resumen.setText(getString(
                    R.string.resumen_viaje, viaje.fecha, viaje.duracion, viaje.presupuesto));

            tarjeta.setOnClickListener(v -> {
                Intent intent = new Intent(this, DetalleActivity.class);
                intent.putExtra(DetalleActivity.EXTRA_VIAJE, viaje.serialize());
                startActivity(intent);
            });
            contenedor.addView(tarjeta);
        }
    }
}
