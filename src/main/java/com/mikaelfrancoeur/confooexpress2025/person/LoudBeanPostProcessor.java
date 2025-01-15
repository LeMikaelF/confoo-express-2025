package com.mikaelfrancoeur.confooexpress2025.person;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

@Component
public class LoudBeanPostProcessor implements BeanPostProcessor {

    @Override
    public Object postProcessAfterInitialization(@NonNull Object bean, @NonNull String beanName) throws BeansException {
        if (beanName.startsWith("loud") && bean instanceof Person person) {
            return Enhancer.create(Person.class,
                    (MethodInterceptor) (obj, method, args, proxy) -> {
                        Object result = method.invoke(person, args);
                        if (method.getName().equals("greet") && result instanceof String string) {
                            return (string).toUpperCase();
                        }
                        return result;
                    });
        }
        return bean;
    }

}
