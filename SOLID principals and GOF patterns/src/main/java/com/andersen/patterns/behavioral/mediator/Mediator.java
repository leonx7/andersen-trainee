package com.andersen.patterns.behavioral.mediator;

public interface Mediator {
    void send(String message, Colleague sender);
}
