package com.lee.demo.base.define;

import java.util.List;

/**
 * IBatisWithBase基本类，
 * 定义了Dao的通过方法
 * @param <T>
 */
public interface IBatisWithBase<T> {
     public int save(T t);

     int batchImport(List<T> list);

     int update(T t);

     List<T> findAll();

     List<T> findBySearch(Object search);

     int delete(T t);

}
