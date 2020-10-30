package com.andersen.patterns.behavioral.chainofresponsibility;

//Concrete handler #1
public class FileLogger extends Logger {
    public FileLogger(int priority) {
        super(priority);
    }

    @Override
    void write(String message) {
        System.out.println("Writing in file: " + message);
    }
}
