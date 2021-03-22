package co.com.sofka.nomemientas.domain.juego;

import co.com.sofka.domain.generic.EventChange;
import co.com.sofka.nomemientas.domain.juego.events.CapitalDeducidoDelJugador;
import co.com.sofka.nomemientas.domain.juego.events.JuegoCreado;
import co.com.sofka.nomemientas.domain.juego.events.JuegoInicializado;
import co.com.sofka.nomemientas.domain.juego.events.JugadorAdicionado;

import java.util.HashMap;

public class JuegoChange extends EventChange {
    public JuegoChange(Juego juego) {
        apply((JuegoCreado event) -> {
            juego.juegoInicializado = Boolean.FALSE;
            juego.jugadores = new HashMap<>();
        });

        apply((JuegoInicializado event) -> {
            juego.juegoInicializado = Boolean.TRUE;
        });

        apply((JugadorAdicionado event) -> {
            if (juego.juegoInicializado.equals(Boolean.TRUE)) {
                throw new IllegalArgumentException("No se puede crear un nuevo jugador si el juego esta en marcha");
            }
            juego.jugadores.put(event.getJugadorId(),
                    new Jugador(
                            event.getJugadorId(),
                            event.getNombre(),
                            event.getCapital()
                    )
            );
        });

        apply((CapitalDeducidoDelJugador event) -> {
            juego.jugadores.get(event.getJugadorId())
                    .disminuirCapital(event.getApuesta().value());
        });
    }
}
