package co.com.sofka.nomemientas.domain.ronda.events;

import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Cara;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.EtapaId;

import java.util.List;
import java.util.Set;

public class EtapaCreada extends DomainEvent {
    private final JuegoId juegoId;
    private final Set<JugadorId> jugadorIds;
    private final EtapaId etapaId;
    private final List<Cara> carasVisibles;

    public EtapaCreada(JuegoId juegoId, Set<JugadorId> jugadorIds, EtapaId etapaId, List<Cara> carasVisibles) {
        super("nomemientan.ronda.etapacreada");
        this.juegoId = juegoId;
        this.jugadorIds = jugadorIds;
        this.etapaId = etapaId;
        this.carasVisibles = carasVisibles;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }

    public EtapaId getEtapaId() {
        return etapaId;
    }

    public List<Cara> getCarasVisibles() {
        return carasVisibles;
    }

    public Set<JugadorId> getJugadorIds() {
        return jugadorIds;
    }
}
