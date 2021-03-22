package co.com.sofka.nomemientas.domain.juego;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Capital;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JugadorId;
import co.com.sofka.nomemientas.domain.juego.valueObjects.Nombre;

public class Jugador extends Entity<JugadorId> {
    private final Nombre nombre;
    private Capital capital;

    public Jugador(JugadorId entityId, Nombre nombre, Capital capital) {
        super(entityId);
        this.nombre = nombre;
        this.capital = capital;
    }

    public void aumentarCapital(Integer value){
        this.capital = this.capital.aumentar(value);
    }

    public void disminuirCapital(Integer value){
        this.capital = this.capital.disminuir(value);
    }

    public Nombre getNombre() {
        return nombre;
    }

    public Capital getCapital() {
        return capital;
    }
}
