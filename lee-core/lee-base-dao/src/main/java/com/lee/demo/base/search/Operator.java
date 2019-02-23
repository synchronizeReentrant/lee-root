package com.lee.demo.base.search;

public  enum  Operator {
    eq("="),mt(">"),or("or"),like("like");
    private String operatorSymbol;
    private Operator(String operatorSymbol){
        this.operatorSymbol = operatorSymbol;
    }
}
