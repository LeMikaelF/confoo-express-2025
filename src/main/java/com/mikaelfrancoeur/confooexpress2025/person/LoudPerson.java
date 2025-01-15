package com.mikaelfrancoeur.confooexpress2025.person;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class LoudPerson extends Person {
    private final Person delegate;

    @Override
    public String greet() {
        return delegate.greet().toUpperCase();
    }
}
