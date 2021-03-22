package co.com.sofka.nomemientas.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;

public class RondaFinalizada extends DomainEvent {
    private final JuegoId juegoId;

    public RondaFinalizada(String type, JuegoId juegoId) {
        super("nomemientan.ronda.rondafinalizada");
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
