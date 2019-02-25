package com.lee.demo.base.search;

import com.lee.sys.constant.Constants;

public class SearchFilter {

    private String fieldName;

    private Operator operator;

    private Object value;

    private String filterStatement;

    public SearchFilter(String fieldName,Operator operator,String value){
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
        this.filterStatement = this.fieldName+ Constants.WHITE_SPACE + operator.getName()+ Constants.WHITE_SPACE + this.value;
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

    private void setFilterStatement(){
        this.filterStatement = this.fieldName + operator.getName() + this.value;
    }

    public String getFilterStatement() {
        return filterStatement;
    }

    public static void main(String[] args) {

        SearchFilter searchFilter = new SearchFilter("user",Operator.eq,"张三");
        System.out.println(searchFilter.getFilterStatement());
    }


}
