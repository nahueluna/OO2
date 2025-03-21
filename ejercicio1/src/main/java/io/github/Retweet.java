package io.github;

public class Retweet implements Post{
    private Post tweetDeOrigen;

    public Retweet(Post tweetDeOrigen) {
        this.tweetDeOrigen = tweetDeOrigen;
    }

    @Override
    public String getTextoAsociado() {
        return this.tweetDeOrigen.getTextoAsociado();
    }

    @Override
    public boolean esRetweet() {
        return true;
    }

}
