package co.com.sofka.nomemientas.juego;

import co.com.sofka.business.generic.BusinessException;
import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.nomemientas.domain.juego.commands.CrearJuego;
import co.com.sofka.nomemientas.domain.juego.events.JuegoCreado;
import co.com.sofka.nomemientas.domain.juego.events.JugadorAdicionado;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Capital;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Nombre;
import co.com.sofka.nomemientas.usecase.juego.CrearJuegoUseCase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;

public class CrearJuegoUseCaseTest {

    @Test
    void crearUnJuego(){
        var nombres = Map.of(
                JugadorId.of("xxxxx"), new Nombre("Brayan Gomez"),
                JugadorId.of("ffff"), new Nombre("Alexander Gomez")
        );
        var capitales = Map.of(
                JugadorId.of("xxxxx"), new Capital(500),
                JugadorId.of("ffff"), new Capital(500)
        );

        var command = new CrearJuego(capitales, nombres, new JuegoId());
        var useCase = new CrearJuegoUseCase();

        var events = UseCaseHandler.getInstance()
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        var juegoCreado = (JuegoCreado) events.get(0);
        var jugadorAdicionadoParaBrayan = (JugadorAdicionado) events.get(2);
        var jugadorAdicionadoParaAlexander = (JugadorAdicionado) events.get(1);

        Assertions.assertTrue(Objects.nonNull(juegoCreado.getJuegoId().value()));

        Assertions.assertEquals("Brayan Gomez", jugadorAdicionadoParaBrayan.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaBrayan.getCapital().value());
        Assertions.assertEquals("xxxxx", jugadorAdicionadoParaBrayan.getJugadorId().value());

        Assertions.assertEquals("Alexander Gomez", jugadorAdicionadoParaAlexander.getNombre().value());
        Assertions.assertEquals(500, jugadorAdicionadoParaAlexander.getCapital().value());
        Assertions.assertEquals("ffff", jugadorAdicionadoParaAlexander.getJugadorId().value());
    }

    @Test
    void errorAlCrearJuego(){
        var nombres = Map.of(
                JugadorId.of("xxxxx"), new Nombre("Brayan Gomez")
        );
        var capitales = Map.of(
                JugadorId.of("xxxxx"), new Capital(500)
        );

        var command = new CrearJuego(capitales,nombres, new JuegoId());
        var useCase = new CrearJuegoUseCase();

        Assertions.assertThrows(BusinessException.class, () -> {
            UseCaseHandler.getInstance()
                    .syncExecutor(useCase, new RequestCommand<>(command))
                    .orElseThrow();
        }, "No se puede crear el juego porque no tiene la cantidad necesaria de jugadores");
    }
}
