package com.github.scormaq.cucumber.definitions;

import com.github.scormaq.spring.TestSession;
import io.cucumber.java.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class CucumberHooks {

    @Autowired
    protected ApplicationContext context;

    @After
    public void afterTest() {
        cleanTestSession();
    }

    private void cleanTestSession() {
        context.getBeansWithAnnotation(TestSession.class).forEach((beanName, bean) -> {
            ((GenericApplicationContext) context).removeBeanDefinition(beanName);
            ((GenericApplicationContext) context).registerBean(beanName, bean.getClass());
        });
    }
}
