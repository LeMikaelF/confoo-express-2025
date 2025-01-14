package com.mikaelfrancoeur.confooexpress2025.person;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
public class LoudBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if ("loudPerson".equals(beanName) && bean instanceof Person person) {
            return new LoudPerson(person);
        }
        return bean;
    }

    @RequiredArgsConstructor
    private static class LoudPerson extends Person {
        private final Person delegate;

        @Override
        public String greet() {
            return delegate.greet().toUpperCase();
        }
    }
}
