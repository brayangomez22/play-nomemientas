package co.com.sofka.nomemientas.usecase.juego;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.nomemientas.domain.juego.Juego;
import co.com.sofka.nomemientas.domain.ronda.Ronda;
import co.com.sofka.nomemientas.domain.ronda.events.EtapaCreada;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;
import co.com.sofka.nomemientas.usecase.juego.service.CaseJugadorService;

public class SolicitarCaseDelJugadorUseCase extends UseCase<TriggeredEvent<EtapaCreada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<EtapaCreada> input) {
        var event = input.getDomainEvent();
        var juego = Juego.from(event.getJuegoId(), retrieveEvents());
        var service = getService(CaseJugadorService.class).orElseThrow();
        var rondaId = RondaId.of(event.aggregateRootId());

        event.getJugadorIds().forEach(jugadorId -> {
            var aCase = service.getCasePor(jugadorId);
            juego.deducirCapitalDelJugador(jugadorId, aCase.value().apuesta());
            juego.casarApuestaEnEtapa(
                    jugadorId, rondaId, event.getEtapaId(), aCase
            );
        });

        emit().onResponse(new ResponseEvents(juego.getUncommittedChanges()));
    }
}
