package com.andersen.patterns.behavioral.commande;

//Invoker
public class User {
    Command start;
    Command stop;
    Command reset;

    public User(Command start, Command stop, Command reset) {
        this.start = start;
        this.stop = stop;
        this.reset = reset;
    }

    public void start() {
        start.execute();
    }

    public void stop() {
        stop.execute();
    }

    public void reset() {
        reset.execute();
    }
}
