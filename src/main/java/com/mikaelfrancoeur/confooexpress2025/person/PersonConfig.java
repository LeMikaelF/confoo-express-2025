package com.mikaelfrancoeur.confooexpress2025.person;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PersonConfig {

    @Bean
    Person person() {
        return new Person();
    }

    @Bean
    LoudPerson loudPerson() {
        return new LoudPerson();
    }
}
