package io.github;

import java.util.List;

public class Tweet implements Post{
    private String textoAsociado;
    private List<Retweet> retweetsAlPosteo;

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

    public void agregarReposteo(Retweet reposteo) {
        this.retweetsAlPosteo.add(reposteo);
    }

    public void eliminarReposteos() {
        this.retweetsAlPosteo.clear();
    }

}
