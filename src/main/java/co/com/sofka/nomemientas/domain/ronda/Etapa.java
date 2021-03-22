package co.com.sofka.nomemientas.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Cara;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Case;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.EtapaId;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Etapa extends Entity<EtapaId> {
    private final List<Cara> carasVisibles;
    private final Map<JugadorId, Case> cases;

    public Etapa(EtapaId entityId, List<Cara> carasVisibles) {
        super(entityId);
        this.carasVisibles = carasVisibles;
        this.cases = new HashMap<>();
    }

    public void agregarCaraVisible(Cara cara) {
        carasVisibles.add(cara);
    }

    public void agregarCase(JugadorId jugadorId, Case aCase) {
        this.cases.put(jugadorId, aCase);
    }

    public Map<JugadorId, Case> cases() {
        return cases;
    }

    public List<Cara> carasVisibles() {
        return carasVisibles;
    }
}
