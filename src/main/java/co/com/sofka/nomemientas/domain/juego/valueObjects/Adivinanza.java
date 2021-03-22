package co.com.sofka.nomemientas.domain.juego.valueObjects;

import co.com.sofka.domain.generic.ValueObject;

import java.util.Objects;

public class Adivinanza implements ValueObject<Adivinanza.Value> {
    private final Integer numero;
    private final Integer repeticiones;

    public Adivinanza(Integer numero, Integer repeticiones) {
        this.numero = Objects.requireNonNull(numero);
        this.repeticiones = Objects.requireNonNull(repeticiones);

        if (1 > numero || 6 > numero){
            throw new IllegalArgumentException("El número debe de estar entre 1-6");
        }

        if (3 > repeticiones || 6 > repeticiones){
            throw new IllegalArgumentException("El numero de repeticiones debe ser entre 3-6");
        }
    }

    @Override
    public Value value() {
        return new Value() {
            @Override
            public Integer numero() {
                return numero;
            }

            @Override
            public Integer repeticiones() {
                return repeticiones;
            }
        };
    }

    public interface Value {
        Integer numero();
        Integer repeticiones();
    }
}
