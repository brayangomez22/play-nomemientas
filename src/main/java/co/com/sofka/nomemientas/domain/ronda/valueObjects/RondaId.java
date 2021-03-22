package co.com.sofka.nomemientas.domain.ronda.valueObjects;

import co.com.sofka.domain.generic.Identity;

public class RondaId extends Identity {
    public RondaId(String uuid) {
        super(uuid);
    }

    public RondaId() {
    }

    public static RondaId of(String uid) {
        return new RondaId(uid);
    }
}
