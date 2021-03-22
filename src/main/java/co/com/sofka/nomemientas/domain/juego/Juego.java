package co.com.sofka.nomemientas.domain.juego;

import co.com.sofka.domain.generic.AggregateEvent;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofka.nomemientas.domain.juego.events.*;
import co.com.sofka.nomemientas.domain.juego.factory.JugadorFactory;
import co.com.sofka.nomemientas.domain.juego.valueObjects.*;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Case;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.EtapaId;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.RondaId;

import java.util.List;
import java.util.Map;

public class Juego extends AggregateEvent<JuegoId> {

    protected Boolean juegoInicializado;
    protected Map<JugadorId, Jugador> jugadores;

    public Juego(JuegoId entityId, JugadorFactory jugadorFactory) {
        super(entityId);
        appendChange(new JuegoCreado(entityId)).apply();
        jugadorFactory.jugadores()
                .forEach(jugador -> adicionarJugador(jugador.identity(), jugador.getNombre(), jugador.getCapital()));
    }

    public Juego(JuegoId entityId) {
        super(entityId);
        subscribe(new JuegoChange(this));
    }

    public static Juego from(JuegoId entityId, List<DomainEvent> events){
        var juego = new Juego(entityId);
        events.forEach(juego::applyEvent);
        return juego;
    }

    public void adicionarJugador(JugadorId jugadorId, Nombre nombre, Capital capital) {
        appendChange(new JugadorAdicionado(jugadorId, nombre, capital)).apply();
    }

    public void iniciarJuego(){
        var jugadoresIds = jugadores.keySet();
        appendChange(new JuegoInicializado(jugadoresIds)).apply();
    }

    public void deducirCapitalDelJugador(JugadorId jugadorId, Apuesta apuesta){
        appendChange(new CapitalDeducidoDelJugador(jugadorId, apuesta)).apply();
    }

    public void casarApuestaEnEtapa(JugadorId jugadorId, RondaId rondaId, EtapaId etapaId, Case eCase){
        appendChange(new AputestaYAdivinanzaCasada(jugadorId, rondaId, etapaId, eCase)).apply();
    }

    public Boolean osJuegoInicializado() {
        return juegoInicializado;
    }
}
