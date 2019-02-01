package com.lee.demo.base.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.extension.IBatisWithExtension;

import java.util.List;

public class DefaultBaseDao extends IBatisWithExtension implements IBatisDaoWithSingleId {
    @Override
    public Object getById(Object p) {
        return null;
    }

    @Override
    public int deleteById(Object p) {
        return 0;
    }

    @Override
    public int save(Object o) {
        return 0;
    }

    @Override
    public int batchImport(List list) {
        return 0;
    }

    @Override
    public int update(Object o) {
        return 0;
    }

    @Override
    public List findAll() {
        return null;
    }

    @Override
    public List findBySearch(Object search) {
        return null;
    }

    @Override
    public int delete(Object o) {
        return 0;
    }
}
