package com.lee.demo.base.search;

import com.lee.sys.constant.Constants;

public class SearchFilter {


    private String fieldName;

    private Operator operator;

    private Object value;

    private StringBuilder filterStatement = new StringBuilder();



    public SearchFilter(){}

    public SearchFilter(String fieldName,Operator operator,String value){
        this.fieldName = fieldName;
        this.operator = operator;
        this.value = value;
    }

    public static SearchFilter newSearchFilter(){
        return new SearchFilter();
    }

    private StringBuilder concatFilterStatement(){

        filterStatement.append(this.fieldName);
        filterStatement.append(Constants.WHITE_SPACE);
        filterStatement.append(operator.getName());
        filterStatement.append(Constants.WHITE_SPACE);
        filterStatement.append(this.value);
      return filterStatement;
    }

    private StringBuilder concatAndFilterStatement(){
        filterStatement.append(Constants.WHITE_SPACE+Join.AND.name()+Constants.WHITE_SPACE);
        return concatFilterStatement();
    }

    private StringBuilder concatOrFilterStatement(){
        filterStatement.append(Constants.WHITE_SPACE+Join.OR.name()+Constants.WHITE_SPACE);
        return concatFilterStatement();
    }

    public SearchFilter AndSearchFilter(String name,Operator operator ,Object value){
        this.fieldName = name;
        this.operator = operator;
        this.value = value;
        concatAndFilterStatement();
        return this;
    }

    public SearchFilter OrSearchFilter(String name,Operator operator ,Object value){
        this.fieldName = name;
        this.operator = operator;
        this.value = value;
        concatOrFilterStatement();
        return this;
    }

    public StringBuilder getFilterStatement() {
        return filterStatement;
    }

    public void setFilterStatement(StringBuilder filterStatement) {
        this.filterStatement = filterStatement;
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

}
