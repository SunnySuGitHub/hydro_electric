package com.hust.hydroelectric_backend.Entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * Description:hydroelectric_backend
 * Created by Administrator on 2020/3/24
 */
@Component
public class TestBean {

    @Value("${TestBean.name}")
    private String name;

    @Value("${TestBean.age}")
    private Integer age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public TestBean() {

    }
}
