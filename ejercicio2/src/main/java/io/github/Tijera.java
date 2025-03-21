package io.github;

public class Tijera implements Jugada{
    @Override
    public String jugarContra(Jugada otraJugada) {
        return otraJugada.jugarContraTijera();
    }

    @Override
    public String jugarContraPiedra() {
        return "Tijera vs Piedra, gana Piedra";
    }

    @Override
    public String jugarContraPapel() {
        return "Tijera vs Papel, gana Papel";
    }

    @Override
    public String jugarContraTijera() {
        return "Tijera vs Tijera, empate";
    }

    @Override
    public String jugarContraLagarto() {
        return "Tijera vs Lagarto, gana Tijera";
    }

    @Override
    public String jugarContraSpock() {
        return "Tijera vs Spock, gana Spock";
    }
}
