package com.lee.demo.base.search;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用查询类
 */
public class Search implements Searchable {

     private Map<String,SearchFilter> searcherMap = Maps.newHashMap();

     private Operator operator;

     private SearchFilter searchFilter;

     public Search addSearchFilter(String fieldName,Operator operator,Object value){
         searchFilter = searchFilter.newSearchFilter();
         searchFilter.setFieldName(fieldName);
         searchFilter.setOperator(operator);
         searchFilter.setValue(value);
         searcherMap.put(fieldName,searchFilter);
         return this;
     }
}
