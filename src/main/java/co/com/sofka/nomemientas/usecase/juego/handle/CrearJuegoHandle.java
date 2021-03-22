package co.com.sofka.nomemientas.usecase.juego.handle;

import co.com.sofka.business.annotation.CommandHandles;
import co.com.sofka.business.annotation.CommandType;
import co.com.sofka.business.asyn.UseCaseExecutor;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.nomemientas.domain.juego.commands.CrearJuego;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Capital;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Nombre;
import co.com.sofka.nomemientas.usecase.juego.CrearJuegoUseCase;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@CommandHandles
@CommandType(name = "nomemientas.juego.crear", aggregate = "juego")
public class CrearJuegoHandle extends UseCaseExecutor {
    private RequestCommand<CrearJuego> request;

    @Override
    public void run() {
        runUseCase(new CrearJuegoUseCase(), request);
    }

    @Override
    public void accept(Map<String, String> args) {
        Map<JugadorId, Nombre> nombreMap = new HashMap<>();
        Map<JugadorId, Capital> capitalesMap = new HashMap<>();

        var ids = Objects.requireNonNull(args.get("jugadoresIds")).split(",");
        var nombres = Objects.requireNonNull(args.get("nombres")).split(",");
        var capitales = Objects.requireNonNull(args.get("capitales")).split(",");

        for (var i=0; i<ids.length; i++){
            nombreMap.put(JugadorId.of(ids[i]), new Nombre(nombres[i]));
            capitalesMap.put(JugadorId.of(ids[i]), new Capital(Integer.parseInt(capitales[i])));
        }

        request = new RequestCommand<>(new CrearJuego(capitalesMap, nombreMap, JuegoId.of(aggregateId())));
    }
}
