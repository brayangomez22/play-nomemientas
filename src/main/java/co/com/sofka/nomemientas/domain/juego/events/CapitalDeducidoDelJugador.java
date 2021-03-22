package co.com.sofka.nomemientas.domain.juego.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Apuesta;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;

public class CapitalDeducidoDelJugador extends DomainEvent {
    private final JugadorId jugadorId;
    private final Apuesta apuesta;

    public CapitalDeducidoDelJugador(JugadorId jugadorId, Apuesta apuesta) {
        super("nomemientan.juego.capitaldeducidodeljugador");
        this.jugadorId = jugadorId;
        this.apuesta = apuesta;
    }

    public JugadorId getJugadorId() {
        return jugadorId;
    }

    public Apuesta getApuesta() {
        return apuesta;
    }
}
