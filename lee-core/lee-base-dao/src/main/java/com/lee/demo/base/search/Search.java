package com.lee.demo.base.search;

import com.google.common.collect.Maps;
import sun.misc.JavaObjectInputStreamAccess;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.AbstractMap;
import java.util.HashMap;
import java.util.Map;

/**
 * 通用查询类
 * 适应简单的查询
 *
 */
public class Search implements Searchable {

     private Map<String,SearchFilter> searchFilterMap = Maps.newHashMap();

     private Operator operator;

     private SearchFilter searchFilter;

     private String whereSqlCustom = "";

     private final static String WHERE_SQL_CUSTOM = "WHERE_SQL_CUSTOM";

     public Search(){
         searchFilter = SearchFilter.newSearchFilter();
     }

     public static Search newSearch(){
         return new Search();
     }

     public  Search addSearchFilter(String fieldName,Operator operator,Object value){
         searchFilter.AndSearchFilter(fieldName,operator,value);
         searchFilterMap.put(fieldName,searchFilter);
         return this;
     }

     public  Search addOrSearchFilter(String fieldName,Operator operator,Object value){
        searchFilter.OrSearchFilter(fieldName,operator,value);
        return this;
     }

    public Operator getOperator() {
        return operator;
    }

    public void setOperator(Operator operator) {
        this.operator = operator;
    }

    public SearchFilter getSearchFilter() {
        return searchFilter;
    }

    public void setSearchFilter(SearchFilter searchFilter) {
        this.searchFilter = searchFilter;
    }

    public String getWhereSqlCustom() {
         whereSqlCustom = searchFilter.getFilterStatement().toString();
         if(whereSqlCustom.startsWith(Join.AND.name(),1) ){
             whereSqlCustom = whereSqlCustom.substring(4);
         }else if(whereSqlCustom.startsWith(Join.OR.name(),1)){
             whereSqlCustom = whereSqlCustom.substring(3);
         }
        return whereSqlCustom;
    }

    public void setWhereSqlCustom(String whereSqlCustom) {
        this.whereSqlCustom = whereSqlCustom;
    }
}
