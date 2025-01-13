package com.mikaelfrancoeur.confooexpress2025.person;

public class LoudPerson extends Person {

    @Loud
    @Override
    public String greet() {
        return super.greet();
    }
}
