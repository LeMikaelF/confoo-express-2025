package com.mikaelfrancoeur.confooexpress2025;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.mikaelfrancoeur.confooexpress2025.person.LoudPerson;
import com.mikaelfrancoeur.confooexpress2025.person.Person;

@SpringBootTest
class DemoTest implements WithAssertions {

    @Autowired
    private Person person;

    @Autowired
    private LoudPerson loudPerson;

    @Test
    void test() {
        assertThat(person.greet()).isEqualTo("bonjour!");
        assertThat(loudPerson.greet()).isEqualTo("BONJOUR!");
    }
}
