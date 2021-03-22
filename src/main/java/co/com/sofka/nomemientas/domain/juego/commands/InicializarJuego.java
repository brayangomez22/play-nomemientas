package co.com.sofka.nomemientas.domain.juego.commands;

import co.com.sofka.domain.generic.Command;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;

public class InicializarJuego implements Command {
    private final JuegoId juegoId;

    public InicializarJuego(JuegoId juegoId) {
        this.juegoId = juegoId;
    }

    public JuegoId getJuegoId() {
        return juegoId;
    }
}
