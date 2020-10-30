package com.andersen.patterns.behavioral.commande;

//Receiver
public class Computer {

    public void start() {
        System.out.println("Start computer");
    }

    public void stop() {
        System.out.println("Stop computer");
    }

    public void reset() {
        System.out.println("Reset computer");
    }
}
