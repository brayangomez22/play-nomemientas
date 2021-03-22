package co.com.sofka.nomemientas.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.nomemientas.domain.juego.commands.InicializarJuego;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.usecase.juego.InicializarJuegoUseCase;

import java.util.Map;

@CommandHandles
@CommandType(name = "nomemientas.juego.inicializar", aggregate = "juego")
public class InicializarJuegoHandle extends UseCaseExecutor {
    private RequestCommand<InicializarJuego> request;

    @Override
    public void run() {
        runUseCase(new InicializarJuegoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        var command = new InicializarJuego(JuegoId.of(aggregateId()));
        request = new RequestCommand<>(command);
    }
}
