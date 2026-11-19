package com.example.itinerarioviaje;

import org.json.JSONException;
import org.json.JSONObject;

final class Viaje {
    final String id;
    final String destino;
    final String fecha;
    final String duracion;
    final String presupuesto;
    final String actividades;
    final boolean realizado;

    Viaje(String id, String destino, String fecha, String duracion, String presupuesto,
          String actividades, boolean realizado) {
        this.id = id;
        this.destino = destino;
        this.fecha = fecha;
        this.duracion = duracion;
        this.presupuesto = presupuesto;
        this.actividades = actividades;
        this.realizado = realizado;
    }

    JSONObject toJson() throws JSONException {
        return new JSONObject()
                .put("id", id)
                .put("destino", destino)
                .put("fecha", fecha)
                .put("duracion", duracion)
                .put("presupuesto", presupuesto)
                .put("actividades", actividades)
                .put("realizado", realizado);
    }

    static Viaje fromJson(JSONObject json) throws JSONException {
        return new Viaje(
                json.getString("id"),
                json.getString("destino"),
                json.getString("fecha"),
                json.getString("duracion"),
                json.getString("presupuesto"),
                json.optString("actividades", ""),
                json.optBoolean("realizado", false));
    }

    String serialize() {
        try {
            return toJson().toString();
        } catch (JSONException exception) {
            throw new IllegalStateException("No se pudo serializar el itinerario.", exception);
        }
    }

    static Viaje deserialize(String value) {
        try {
            return fromJson(new JSONObject(value));
        } catch (JSONException exception) {
            throw new IllegalStateException("No se pudo leer el itinerario.", exception);
        }
    }
}
