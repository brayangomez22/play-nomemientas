package co.com.sofka.nomemientas.domain.ronda.valueObjects;

import co.com.sofka.domain.generic.Identity;

public class DadoId extends Identity {
    private DadoId(Integer num) {
        super(num.toString());
    }

    public static DadoId of(Integer num) {
        return new DadoId(num);
    }
}
