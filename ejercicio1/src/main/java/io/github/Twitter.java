package io.github;

import java.util.ArrayList;
import java.util.List;

public class Twitter {
    private List<Usuario> usuarios;

    public Twitter() {
        this.usuarios = new ArrayList<>();
    }

    public Usuario crearUsuario(String screenName) {
        Usuario user = new Usuario(screenName);
        usuarios.add(user);
        return user;
    }

    private boolean existeUsuario(Usuario user) {
        return this.usuarios.stream().anyMatch(u -> u.getScreenName().equals(user.getScreenName()));
    }

    public void eliminarUsuario(Usuario user) {
        if(user != null && this.existeUsuario(user)) {
            this.usuarios.stream().forEach(u -> u.eliminarPosts(user));
            this.usuarios.remove(user);
        }
    }
}
