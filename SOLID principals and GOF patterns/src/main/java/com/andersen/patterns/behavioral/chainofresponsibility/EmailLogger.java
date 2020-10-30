package com.andersen.patterns.behavioral.chainofresponsibility;

//Concrete handler #2
public class EmailLogger extends Logger {
    public EmailLogger(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("Email: " + message);
    }
}
