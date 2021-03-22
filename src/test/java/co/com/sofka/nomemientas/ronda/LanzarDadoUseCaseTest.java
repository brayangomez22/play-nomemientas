package co.com.sofka.nomemientas.ronda;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.ronda.events.DadosLanzados;
import co.com.sofka.nomemientas.domain.ronda.events.RondaCreada;
import co.com.sofka.nomemientas.domain.ronda.events.RondaInicializada;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Cara;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;
import co.com.sofka.nomemientas.usecase.ronda.LanzarDadoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Set;

import static org.mockito.Mockito.when;

@ExtendWith({MockitoExtension.class})
public class LanzarDadoUseCaseTest {

    private final Set<JugadorId> jugadoresIds = Set.of(
            JugadorId.of("gggg"), JugadorId.of("ttttt")
    );
    private final JuegoId juegoId = JuegoId.of("ffff");

    @Mock
    private DomainEventRepository repository;

    @Test
    void lanzarDadosDeLaRonda(){
        var rondaId = RondaId.of("aaaaa");
        var event = createTriggeredEventWith(rondaId);
        var useCase = new LanzarDadoUseCase();

        when(repository.getEventsBy(rondaId.value())).thenReturn(eventStored());
        useCase.addRepository(repository);

        var events = executer(rondaId, event, useCase);
        var dadosLanzados = (DadosLanzados) events.get(0);

        Assertions.assertEquals(juegoId, dadosLanzados.getJuegoId());
        Assertions.assertEquals(6, dadosLanzados.getCarasList().size());
        dadosLanzados.getCarasList().forEach(dadoIdListMap -> {
            List<Cara> list = ((List<Cara>) dadoIdListMap.values().toArray()[0]);
            Assertions.assertEquals(6, list.size(), "No se tienen las seis caras del dado");
            for (Cara cara : list){
                Assertions.assertTrue(() -> cara.value() > 0 && 6 >= cara.value(), "El valor de la cara no está entre uno y seis");
            }
        });
    }

    private RondaInicializada createTriggeredEventWith(RondaId rondaId) {
        var event = new RondaInicializada(juegoId, jugadoresIds);
        event.setAggregateRootId(rondaId.value());
        return event;
    }

    private List<DomainEvent> executer(RondaId rondaId, RondaInicializada event, LanzarDadoUseCase useCase){
        return UseCaseHandler
                .getInstance()
                .setIdentifyExecutor(rondaId.toString())
                .syncExecutor(useCase, new TriggeredEvent<>(event))
                .orElseThrow()
                .getDomainEvents();
    }

    private List<DomainEvent> eventStored() {
        return List.of(
                new RondaCreada(jugadoresIds, juegoId),
                new RondaInicializada(juegoId, jugadoresIds)
        );
    }
}
