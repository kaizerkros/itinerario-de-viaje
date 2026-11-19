package com.example.itinerarioviaje;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

final class ViajeRepository {
    private static final String PREFERENCES = "itinerario_viaje";
    private static final String KEY_VIAJES = "viajes";

    private ViajeRepository() {
    }

    static List<Viaje> getViajes(Context context) {
        SharedPreferences preferences = preferences(context);
        if (!preferences.contains(KEY_VIAJES)) {
            guardar(preferences, crearViajesDeEjemplo(context));
        }
        return leer(preferences.getString(KEY_VIAJES, "[]"));
    }

    static Viaje guardarViaje(Context context, Viaje viaje) {
        List<Viaje> viajes = getViajes(context);
        Viaje guardado = new Viaje(
                viaje.id.isEmpty() ? UUID.randomUUID().toString() : viaje.id,
                viaje.destino,
                viaje.fecha,
                viaje.duracion,
                viaje.presupuesto,
                viaje.actividades,
                viaje.realizado);
        boolean reemplazado = false;
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).id.equals(guardado.id)) {
                viajes.set(i, guardado);
                reemplazado = true;
                break;
            }
        }
        if (!reemplazado) {
            viajes.add(0, guardado);
        }
        guardar(preferences(context), viajes);
        return guardado;
    }

    static void eliminarViaje(Context context, String id) {
        List<Viaje> viajes = getViajes(context);
        for (int i = viajes.size() - 1; i >= 0; i--) {
            if (viajes.get(i).id.equals(id)) {
                viajes.remove(i);
            }
        }
        guardar(preferences(context), viajes);
    }

    private static SharedPreferences preferences(Context context) {
        return context.getSharedPreferences(PREFERENCES, Context.MODE_PRIVATE);
    }

    private static List<Viaje> crearViajesDeEjemplo(Context context) {
        List<Viaje> viajes = new ArrayList<>();
        viajes.add(new Viaje(
                "ejemplo_valparaiso",
                context.getString(R.string.viaje_valparaiso),
                "20/12/2026",
                "4 días",
                "$250.000",
                "Paseo por el puerto\nTour por los cerros\nPlaya y gastronomía",
                false));
        viajes.add(new Viaje(
                "ejemplo_torres_paine",
                context.getString(R.string.viaje_torres_paine),
                "05/01/2027",
                "7 días",
                "$680.000",
                "Circuito de senderismo\nMiradores del parque",
                false));
        viajes.add(new Viaje(
                "ejemplo_atacama",
                context.getString(R.string.viaje_atacama),
                "18/02/2027",
                "5 días",
                "$520.000",
                "Valle de la Luna\nGéiseres del Tatio",
                false));
        return viajes;
    }

    private static List<Viaje> leer(String value) {
        List<Viaje> viajes = new ArrayList<>();
        try {
            JSONArray array = new JSONArray(value);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                viajes.add(Viaje.fromJson(object));
            }
        } catch (JSONException exception) {
            throw new IllegalStateException("No se pudieron cargar los itinerarios guardados.", exception);
        }
        return viajes;
    }

    private static void guardar(SharedPreferences preferences, List<Viaje> viajes) {
        JSONArray array = new JSONArray();
        try {
            for (Viaje viaje : viajes) {
                array.put(viaje.toJson());
            }
        } catch (JSONException exception) {
            throw new IllegalStateException("No se pudieron preparar los itinerarios.", exception);
        }
        if (!preferences.edit().putString(KEY_VIAJES, array.toString()).commit()) {
            throw new IllegalStateException("No se pudieron guardar los itinerarios en el dispositivo.");
        }
    }
}
