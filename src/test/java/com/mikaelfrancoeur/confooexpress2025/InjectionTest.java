package com.mikaelfrancoeur.confooexpress2025;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

@SuppressWarnings({ "LombokSetterMayBeUsed", "FieldCanBeLocal", "unused", "CastCanBeRemovedNarrowingVariableType" })
public class InjectionTest implements WithAssertions {

    static class InjectedBean {
        private final String message = "allo!";
    }

    static class RequiresInjection {
        private InjectedBean injectedBean;

        public void setInjectedBean(InjectedBean injectedBean) {
            this.injectedBean = injectedBean;
        }
    }

    @Test
    void test() {
        BeanFactory beanFactory = new DefaultListableBeanFactory();

        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("injectedBean",
                BeanDefinitionBuilder.genericBeanDefinition(InjectedBean.class)
                        .getBeanDefinition());

        ((DefaultListableBeanFactory) beanFactory).registerBeanDefinition("requiresInjection",
                BeanDefinitionBuilder.genericBeanDefinition(RequiresInjection.class)
                        .addAutowiredProperty("injectedBean")
                        .getBeanDefinition());

        assertThat(beanFactory.getBean("requiresInjection", RequiresInjection.class))
                .extracting("injectedBean.message")
                .isEqualTo("allo!");
    }
}
