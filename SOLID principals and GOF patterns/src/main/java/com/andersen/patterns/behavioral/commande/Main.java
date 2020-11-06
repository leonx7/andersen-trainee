package com.andersen.patterns.behavioral.commande;

public class Main {
    public static void main(String[] args) {
        Computer computer = new Computer();
        User user = new User(new StartCommand(computer), new StopCommand(computer), new ResetCommand(computer));

        user.start();
        user.stop();
        user.reset();
    }
}
