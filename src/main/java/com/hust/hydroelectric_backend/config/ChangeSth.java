package com.hust.hydroelectric_backend.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2020/4/5
 */
@Component
public class ChangeSth implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition abd = beanFactory.getBeanDefinition("adminController");
        System.out.println("adminController bean class name" + abd.getBeanClassName());
        abd.setBeanClassName("com.hust.hydroelectric_backend.config.TestClass");
    }
}
