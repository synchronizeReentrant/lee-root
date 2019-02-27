package com.lee.demo.base.search;

/**
 *
 */
public enum  Join {
    AND("ADN"),OR("OR");
    private String name;
    private Join(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
