package com.mikaelfrancoeur.confooexpress2025;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

import lombok.Getter;
import lombok.Setter;

@SuppressWarnings({ "FieldCanBeLocal", "unused" })
public class InjectionTest implements WithAssertions {

    @Getter
    static class InjectedBean {
        private final String message = "allo!";
    }

    @Getter @Setter
    static class RequiresInjection {
        private InjectedBean injectedField;
    }

    @Test
    void test() {
        BeanFactory beanFactory = new DefaultListableBeanFactory();

        ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("injectedBean",
                BeanDefinitionBuilder.genericBeanDefinition(InjectedBean.class)
                        .getBeanDefinition());

        ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("requiresInjection",
                BeanDefinitionBuilder.genericBeanDefinition(RequiresInjection.class)
                        .addAutowiredProperty("injectedField")
                        .getBeanDefinition());

        RequiresInjection bean = beanFactory.getBean("requiresInjection", RequiresInjection.class);

        assertThat(bean.getInjectedField().getMessage())
                .isEqualTo("allo!");
    }
}
