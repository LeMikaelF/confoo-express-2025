package com.mikaelfrancoeur.confooexpress2025;

import org.assertj.core.api.WithAssertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;

@SuppressWarnings({ "LombokSetterMayBeUsed", "FieldCanBeLocal", "unused" })
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

        ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("injectedBean",
                BeanDefinitionBuilder.genericBeanDefinition(InjectedBean.class)
                        .getBeanDefinition());

        ((BeanDefinitionRegistry) beanFactory).registerBeanDefinition("requiresInjection",
                BeanDefinitionBuilder.genericBeanDefinition(RequiresInjection.class)
                        .addAutowiredProperty("injectedBean")
                        .getBeanDefinition());

        RequiresInjection bean = beanFactory.getBean("requiresInjection", RequiresInjection.class);

        assertThat(bean)
                .extracting("injectedBean.message")
                .isEqualTo("allo!");
    }
}
