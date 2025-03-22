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
    }

    public boolean contienePost(Post publicacion) {
        return this.publicaciones.contains(publicacion);
    }

    void eliminarPosts(Usuario user) {
        for(Post publicacion: this.publicaciones) {
            if(publicacion.publicacionDebeEliminarse(user)) {
                this.publicaciones.remove(publicacion);
            }
        }
    }
}
