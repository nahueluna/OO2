package io.github;

public class Spock implements Jugada{
    @Override
    public String jugarContra(Jugada otraJugada) {
        return otraJugada.jugarContraSpock();
    }

    @Override
    public String jugarContraPiedra() {
        return "Spock vs Piedra, gana Spock";
    }

    @Override
    public String jugarContraPapel() {
        return "Spock vs Papel, gana Papel";
    }

    @Override
    public String jugarContraTijera() {
        return "Spock vs Tijera, gana Spock";
    }

    @Override
    public String jugarContraLagarto() {
        return "Spock vs Lagarto, gana Lagarto";
    }

    @Override
    public String jugarContraSpock() {
        return "Spock vs Spock, empate";
    }
}
