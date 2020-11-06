package com.andersen.patterns.behavioral.state;

//Concrete state #2
public class Work implements Activity {
    @Override
    public void doSomething(Human context) {
        System.out.println("I'm working");
        context.setState(new Rest());
    }
}
