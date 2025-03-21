package io.github;

import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private String screenName;
    private List<Post> publicaciones;

    public Usuario(String screenName) {
        this.screenName = screenName;
        this.publicaciones = new ArrayList<>();
    }

    public String getScreenName() {
        return this.screenName;
    }

    public Post crearTweet(String texto) {
        if(this.evaluarLongitudTexto(texto)) {
            Post tweet = new Tweet(texto);
            this.publicaciones.add(tweet);
            return tweet;
        }
        return null;
    }

    private boolean evaluarLongitudTexto(String texto) {
        return !texto.isEmpty() && texto.length() <= 280;
    }

    public void repostearTweet(Tweet tweet) {
        Retweet retweet = new Retweet(tweet);
        this.publicaciones.add(retweet);
        tweet.agregarReposteo(retweet);
    }

    void eliminarPosts() {
        this.publicaciones.stream()
                .filter(p -> !p.esRetweet())
                .forEach(t -> ((Tweet) t).eliminarReposteos()); // Downcasting seguro por booleano
        this.publicaciones.clear();
    }
}
