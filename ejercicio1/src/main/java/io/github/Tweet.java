package io.github;

import java.util.List;

public class Tweet implements Post{
    private String textoAsociado;

    public Tweet(String textoAsociado) {
        this.textoAsociado = textoAsociado;
    }

    @Override
    public String getTextoAsociado() {
        return this.textoAsociado;
    }

    @Override
    public boolean esRetweet() {
        return false;
    }

    @Override
    public boolean publicacionDebeEliminarse(Usuario user) {
        return user.contienePost(this);
    }

}
