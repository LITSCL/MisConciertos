package cl.inacap.misconciertos.dao;

import java.util.ArrayList;
import java.util.List;

import cl.inacap.misconciertos.dto.Evento;

public class EventoDAO {
    private static List<Evento> eventos = new ArrayList<Evento>();

    public void create(Evento e) {
        eventos.add(e);
    }

    public List<Evento> getAll() {
        return eventos;
    }
}
