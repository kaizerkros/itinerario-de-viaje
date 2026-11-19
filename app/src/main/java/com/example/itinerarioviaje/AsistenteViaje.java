package com.example.itinerarioviaje;

import android.content.Context;

import java.text.Normalizer;
import java.util.Locale;

final class AsistenteViaje {
    private AsistenteViaje() {
    }

    static String responder(Context context, String consulta) {
        String normalizada = Normalizer.normalize(consulta.toLowerCase(Locale.ROOT), Normalizer.Form.NFD)
                .replaceAll("\\p{M}", "");

        if (normalizada.contains("valparaiso")) {
            return context.getString(R.string.sugerencia_valparaiso);
        }
        if (normalizada.contains("atacama") || normalizada.contains("san pedro")) {
            return context.getString(R.string.sugerencia_atacama);
        }
        if (normalizada.contains("torres del paine") || normalizada.contains("paine")) {
            return context.getString(R.string.sugerencia_paine);
        }
        if (normalizada.contains("presupuesto") || normalizada.contains("econom")) {
            return context.getString(R.string.sugerencia_presupuesto);
        }
        if (normalizada.contains("seguridad") || normalizada.contains("seguro")) {
            return context.getString(R.string.sugerencia_seguridad);
        }
        if (normalizada.contains("comida") || normalizada.contains("comer")
                || normalizada.contains("restaurante")) {
            return context.getString(R.string.sugerencia_comida);
        }
        if (normalizada.contains("transporte") || normalizada.contains("movil")
                || normalizada.contains("traslado")) {
            return context.getString(R.string.sugerencia_transporte);
        }
        return context.getString(R.string.sugerencia_general, consulta.trim());
    }
}
