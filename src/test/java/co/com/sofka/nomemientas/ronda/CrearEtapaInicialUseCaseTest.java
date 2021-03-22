package co.com.sofka.nomemientas.ronda;

import co.com.sofka.business.generic.UseCase;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.TriggeredEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.ronda.events.DadosLanzados;
import co.com.sofka.nomemientas.domain.ronda.events.EtapaCreada;
import co.com.sofka.nomemientas.domain.ronda.events.RondaCreada;
import co.com.sofka.nomemientas.domain.ronda.events.RondaInicializada;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Cara;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.DadoId;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;
import co.com.sofka.nomemientas.usecase.ronda.CrearEtapaInicialUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CrearEtapaInicialUseCaseTest {

    private final Set<JugadorId> jugadoresIds = Set.of(
            JugadorId.of("gggg"), JugadorId.of("ttttt")
    );
    private final JuegoId juegoId = JuegoId.of("ffff");

    @Mock
    private DomainEventRepository repository;

    @Test
    void crearEtapaInicial(){
        var rondaId = RondaId.of("aaaaa");
        var event = createTriggeredEventWith(rondaId);
        var useCase = new CrearEtapaInicialUseCase();
        when(repository.getEventsBy(rondaId.value())).thenReturn(eventStored());
        useCase.addRepository(repository);

        var events = executer(rondaId, event, useCase);
        var etapaCreada = (EtapaCreada) events.get(0);

        Assertions.assertEquals(juegoId, etapaCreada.getJuegoId());
        Assertions.assertEquals(0, etapaCreada.getCarasVisibles().size());
        Assertions.assertTrue(Objects.nonNull(etapaCreada.getEtapaId()));
    }

    private DadosLanzados createTriggeredEventWith(RondaId rondaId) {
        List<Map<DadoId, List<Cara>>> listCara = new ArrayList<>();
        listCara.add(Map.of(
            DadoId.of(1), generateCaras(),
            DadoId.of(2), generateCaras(),
            DadoId.of(3), generateCaras(),
            DadoId.of(4), generateCaras(),
            DadoId.of(5), generateCaras(),
            DadoId.of(6), generateCaras()
        ));

        var event = new DadosLanzados(juegoId, listCara);
        event.setAggregateRootId(rondaId.value());
        return event;
    }

    private List<Cara> generateCaras() {
        List<Cara> caraList = new ArrayList<>();
        for(var i = 1; i <= 6; i ++){
            caraList.add(new Cara(i));
        }
        return caraList;
    }

    private List<DomainEvent> executer(RondaId rondaId, DadosLanzados event, CrearEtapaInicialUseCase useCase){
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
