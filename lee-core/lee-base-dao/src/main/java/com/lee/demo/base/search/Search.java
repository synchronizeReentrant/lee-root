package com.lee.demo.base.search;

import com.google.common.collect.Maps;

import java.io.Serializable;
import java.util.Map;

/**
 * 通用查询类
 * 适应简单的查询
 *
 */
public class Search<T> implements Searchable<T> {

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
