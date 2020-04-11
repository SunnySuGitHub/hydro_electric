package com.hust.hydroelectric_backend.config;

import javax.annotation.PostConstruct;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2020/4/5
 */
public class TestClass {


    public TestClass() {
        System.out.println("test class init");
    }

    @PostConstruct
    public void show() {
        System.out.println("this is testclass");
    }

}
