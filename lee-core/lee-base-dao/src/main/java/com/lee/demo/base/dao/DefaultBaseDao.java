package com.lee.demo.base.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.extension.IBatisWithExtension;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public  abstract  class DefaultBaseDao<T,PK> extends BaseDao<T> implements IBatisDaoWithSingleId<T,PK> {


    @Override
    public T getById(PK p) {
        return (T)getPoMapper(getClass()).selectByPrimaryKey(p);
    }

    @Override
    public int deleteById(PK p) {
        return getPoMapper(getClass()).deleteByPrimaryKey(p);
    }






}
