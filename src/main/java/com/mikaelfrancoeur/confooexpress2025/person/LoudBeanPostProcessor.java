package com.mikaelfrancoeur.confooexpress2025.person;

import java.lang.reflect.Method;
import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

@Component
public class LoudBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessAfterInitialization(Object bean, @NonNull String beanName) throws BeansException {
        Method[] declaredMethods = ReflectionUtils.getAllDeclaredMethods(bean.getClass());
        if (Arrays.stream(declaredMethods).anyMatch(method -> method.getAnnotation(Loud.class) != null)) {
            return Enhancer.create(bean.getClass(),
                    (MethodInterceptor) (obj, method, args, proxy) -> {
                        Object result = method.invoke(bean, args);

                        if (result instanceof String string && method.getAnnotation(Loud.class) != null) {
                            return string.toUpperCase();
                        }

                        return result;
                    });
        }

        return bean;
    }
}
