package com.lee.demo.base.define;

/**
 * 单个ID
 * @param <T>
 * @param <PK>
 */
public interface IBatisDaoWithSingleId<T,PK> extends IBatisWithBase<T>{

    T getById(PK p);

     int deleteById(PK p);


}
