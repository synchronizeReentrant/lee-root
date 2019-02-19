package com.lee.demo.base.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.extension.IBatisWithExtension;

import java.util.List;

public abstract   class DefaultBaseDao<T,PK> extends IBatisWithExtension implements IBatisDaoWithSingleId<T,PK> {

    @Override
    public T getById(PK p) {
        return (T)getPoMapper(getClass()).selectByPrimaryKey(p);
    }

    @Override
    public int deleteById(PK p) {
        return getPoMapper(getClass()).deleteByPrimaryKey(p);
    }

    @Override
    public int save(T t) {
        return getPoMapper(getClass()).insert(t);
    }

    @Override
    public int batchImport(List<T> list) {
        return 0;
    }

    @Override
    public int update(T t) {
        return getPoMapper(getClass()).updateByPrimaryKey(t);
    }

    @Override
    public List<T> findAll() {
        return getPoMapper(getClass()).select(null);
    }

    @Override
    public List<T> findBySearch(Object search) {
        return null;
    }

    @Override
    public int delete(T t) {
        return getPoMapper(getClass()).delete(t);
    }
}
