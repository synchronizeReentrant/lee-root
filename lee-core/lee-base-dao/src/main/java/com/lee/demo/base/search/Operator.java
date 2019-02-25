package com.lee.demo.base.search;

public  enum  Operator {

    eq("="),mt(">"),or("or"),like("like");

    private String name;

    private Operator(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
