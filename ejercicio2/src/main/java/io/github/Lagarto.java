package io.github;

public class Lagarto implements Jugada{
    @Override
    public String jugarContra(Jugada otraJugada) {
        return otraJugada.jugarContraLagarto();
    }

    @Override
    public String jugarContraPiedra() {
        return "Lagarto vs Piedra, gana Piedra";
    }

    @Override
    public String jugarContraPapel() {
        return "Lagarto vs Papel, gana Lagarto";
    }

    @Override
    public String jugarContraTijera() {
        return "Lagarto vs Tijera, gana Tijera";
    }

    @Override
    public String jugarContraLagarto() {
        return "Lagarto vs Lagarto, empate";
    }

    @Override
    public String jugarContraSpock() {
        return "Lagarto vs Spock, gana Lagarto";
    }
}
