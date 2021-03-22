package co.com.sofka.nomemientas.domain.ronda;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.nomemientas.domain.ronda.events.CaseRealizadoDelJugador;
import co.com.sofka.nomemientas.domain.ronda.events.DadosLanzados;
import co.com.sofka.nomemientas.domain.ronda.events.EtapaCreada;
import co.com.sofka.nomemientas.domain.ronda.events.RondaCreada;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.DadoId;

import java.util.HashMap;

public class RondaChange extends EventChange {
    public RondaChange(Ronda ronda) {
        apply((RondaCreada event) -> {
            ronda.juegoId = event.getJuegoId();
            ronda.dados = new HashMap<>();
            ronda.etapas = new HashMap<>();
            ronda.puntaje = new HashMap<>();
            ronda.jugadorIds = event.getJugadorIds();

            for (var i = 1; i <= 6; i++) {//inicializar dados
                ronda.dados.put(DadoId.of(i), new Dado(DadoId.of(i)));
            }
        });

        apply((DadosLanzados event) -> {
            ronda.dados.values().forEach(Dado::lanzarDado);
        });

        apply((EtapaCreada event) -> {
            ronda.etapas.put(event.getEtapaId(),
                    new Etapa(event.getEtapaId(), event.getCarasVisibles())
            );
        });

        apply((CaseRealizadoDelJugador event) -> {
            ronda.etapas.get(event.getEtapaId())
                    .agregarCase(event.getJugadorId(), event.getCase());
        });
    }
}
