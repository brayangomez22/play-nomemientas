package co.com.sofka.nomemientas.domain.juego.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Capital;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Nombre;

import java.util.Map;

public class CrearJuego implements Command {
    private final Map<JugadorId, Capital> capitales;
    private final Map<JugadorId, Nombre> nombres;
    private final JuegoId juegoId;

    public CrearJuego(Map<JugadorId, Capital> capitales, Map<JugadorId, Nombre> nombres, JuegoId juegoId) {
        this.capitales = capitales;
        this.nombres = nombres;
        this.juegoId = juegoId;
    }

    public Map<JugadorId, Capital> getCapitales() {
        return capitales;
    }

    public Map<JugadorId, Nombre> getNombres() {
        return nombres;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
