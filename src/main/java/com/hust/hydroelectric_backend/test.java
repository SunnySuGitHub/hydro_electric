package com.hust.hydroelectric_backend;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2020/3/23
 */
public class test {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext();
        String[] beanDefinitionNames = context.getBeanDefinitionNames();
        for(String s : beanDefinitionNames) {
            System.out.println(s);
        }
    }
}
