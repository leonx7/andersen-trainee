package com.andersen.patterns.behavioral.state;

//Concrete state #1
public class Rest implements Activity {
    int count = 0;

    @Override
    public void doSomething(Human context) {
        if (count < 3) {
            System.out.println("I'm chilling out");
            count++;
        } else {
            context.setState(new Work());
        }
    }
}
