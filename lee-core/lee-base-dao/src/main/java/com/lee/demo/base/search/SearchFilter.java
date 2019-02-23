package com.lee.demo.base.search;

public class SearchFilter {
    private String fieldName;

    private Operator operator;

    private Object value;

    private String filter;

    public SearchFilter(String fieldName,Operator operator,String value){
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }
    public SearchFilter(){

    }

    public SearchFilter newSearchFilter(){
      return new SearchFilter();
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    public String getFilter() {
        return fieldName + operator + value;
    }


}
