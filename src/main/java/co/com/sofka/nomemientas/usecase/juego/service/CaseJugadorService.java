package co.com.sofka.nomemientas.usecase.juego.service;

import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.ronda.valueObjects.Case;

public interface CaseJugadorService {
    Case getCasePor(JugadorId jugadorId);
}
