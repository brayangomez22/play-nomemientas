package co.com.sofka.nomemientas.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;

public class JuegoCreado extends DomainEvent {
    private final JuegoId juegoId;

    public JuegoCreado(JuegoId juegoId) {
        super("nomemientas.juego.creado");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
