package co.com.sofka.nomemientas.domain.ronda;

import co.com.sofka.domain.generic.Entity;
import co.com.sofka.nomemientas.domain.juego.valueObjects.JuegoId;

public class Punto extends Entity<JuegoId> {
    private Integer value;

    public Punto(JuegoId entityId) {
        super(entityId);
        this.value = 0;
    }

    public void aumentarPuntos(Integer value){
        if (0 > value){
            throw new IllegalArgumentException("No se puede colocar puntos negativos");
        }
        this.value =+ value;
    }

    public Integer getValue() {
        return value;
    }
}
