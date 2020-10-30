package com.andersen.patterns.behavioral.chainofresponsibility;

//Concrete handler #3
public class SMSLogger extends Logger {
    public SMSLogger(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("SMS: " + message);
    }
}
