package com.andersen.patterns.behavioral.state;

public class Main {
    public static void main(String[] args) {
        Human human = new Human();
        human.setState(new Work());
        for (int i = 0; i < 30; i++) {
            human.doSomething();
        }
    }
}
