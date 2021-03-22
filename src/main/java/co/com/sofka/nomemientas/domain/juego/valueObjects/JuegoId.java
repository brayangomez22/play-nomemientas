package co.com.sofka.nomemientas.domain.juego.valueObjects;

import co.com.sofka.domain.generic.Identity;

public class JuegoId extends Identity {
    private JuegoId(String uid) {
        super(uid);
    }

    public JuegoId() {
    }

    public static JuegoId of(String uid) {
        return new JuegoId(uid);
    }
}