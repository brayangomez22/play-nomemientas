package co.com.sofka.nomemientas.domain.ronda.valueObjects;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Cara implements ValueObject<Integer> {
    private final Integer value;

    public Cara(Integer value) {
        this.value = Objects.requireNonNull(value, "El valor de la cara es requerido");
        if (value <= 0 || 6 < value){
            throw new IllegalArgumentException("Ojo es necesario que este entre 1-6 la cara del dado");
        }
    }

    @Override
    public Integer value() {
        return value;
    }
}
