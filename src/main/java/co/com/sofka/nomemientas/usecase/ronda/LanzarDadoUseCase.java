package co.com.sofka.nomemientas.usecase.ronda;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.support.ResponseEvents;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.nomemientas.domain.ronda.Ronda;
import co.com.sofka.nomemientas.domain.ronda.events.RondaInicializada;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;

public class LanzarDadoUseCase extends UseCase<TriggeredEvent<RondaInicializada>, ResponseEvents> {
    @Override
    public void executeUseCase(TriggeredEvent<RondaInicializada> input) {
        var event = input.getDomainEvent();
        var ronda = Ronda.from(RondaId.of(event.aggregateRootId()), retrieveEvents());

        ronda.tirarDados();

        emit().onResponse(new ResponseEvents(ronda.getUncommittedChanges()));
    }
}
