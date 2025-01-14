package com.mikaelfrancoeur.confooexpress2025.person;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class LoudBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if ("loudPerson".equals(beanName)) {
            return new LoudPerson();
        }
        return bean;
    }

    private static class LoudPerson extends Person {
        @Override
        public String greet() {
            return super.greet().toUpperCase();
        }
    }
}
