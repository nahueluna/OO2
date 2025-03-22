package io.github;

public interface Post {
    String getTextoAsociado();
    boolean esRetweet();
    boolean publicacionDebeEliminarse(Usuario user);
}
