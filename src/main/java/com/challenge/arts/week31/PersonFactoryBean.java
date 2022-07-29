package com.challenge.arts.week31;

import org.springframework.beans.factory.FactoryBean;

public class PersonFactoryBean implements FactoryBean {
    @Override
    public Object getObject() throws Exception {
        return new Person();
    }

    @Override
    public Class<?> getObjectType() {
        return Person.class;
    }
}
