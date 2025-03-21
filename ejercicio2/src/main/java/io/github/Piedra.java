package io.github;

public class Piedra implements Jugada{
    @Override
    public String jugarContra(Jugada otraJugada) {
        return otraJugada.jugarContraPiedra();
    }

    @Override
    public String jugarContraPiedra() {
        return "Piedra vs Piedra es empate";
    }

    @Override
    public String jugarContraPapel() {
        return "Piedra vs Papel, gana Papel";
    }

    @Override
    public String jugarContraTijera() {
        return "Piedra vs Tijera, gana Tijera";
    }

    @Override
    public String jugarContraLagarto() {
        return "Piedra vs Lagarto, gana Piedra";
    }

    @Override
    public String jugarContraSpock() {
        return "Piedra vs Spock, gana Spock";
    }
}
