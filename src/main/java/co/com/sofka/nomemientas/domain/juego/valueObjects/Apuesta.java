package co.com.sofka.nomemientas.domain.juego.valueObjects;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Apuesta implements ValueObject<Integer> {
    private final Integer value;

    public Apuesta(Integer value) {
        this.value = Objects.requireNonNull(value);
    }

    @Override
    public Integer value() {
        return null;
    }
}
