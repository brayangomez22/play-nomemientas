package co.com.sofka.nomemientas.usecase.ronda;

import co.com.sofka.business.annotation.EventListener;
import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.nomemientas.domain.juego.events.JuegoInicializado;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.ronda.Ronda;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;

@EventListener(eventType = "nomemientan.juego.juegoinicializado")
public class CrearRondaInicialUseCase extends UseCase<TriggeredEvent<JuegoInicializado>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<JuegoInicializado> input) {
        var event = input.getDomainEvent();
        var rondaId = new RondaId();

        if (event.getJugadoresIds().size() < 2){
            throw new BusinessException(rondaId.value(), "No se puede crear la ronda por falta de jugadores");
        }

        var juegoId = JuegoId.of(event.aggregateRootId());
        var ronda = new Ronda(rondaId, juegoId, event.getJugadoresIds());
        ronda.inicializarRonda();

        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }
}
