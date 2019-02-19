package com.lee.demo.base.extension;

import com.github.abel533.mapper.Mapper;
import com.lee.demo.base.define.IBatisWithBase;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.Map;


public abstract class IBatisWithExtension<T,V extends Mapper> {
    @Resource
    private SqlSessionFactory sqlSessionFactory;


    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }

    /**
     * 初始化一些操作
     */
    public IBatisWithExtension(){

    }

    public V  getPoMapper(Class<? extends IBatisWithBase> DaoClass){

        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        if(sqlSessionFactory == null) throw new RuntimeException("sqlSession is injected by spring container");
        //获得SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        MapperRegistry registry = configuration.getMapperRegistry();
        Collection<Class<?>> classCollection =  registry.getMappers();
        for(Class clazz : classCollection){
            String packageName = clazz.getPackage().getName();
            Type [] type = clazz.getGenericInterfaces();
            Type first = type[0];
            if (first instanceof  ParameterizedType) {
                for (Type argType:((ParameterizedType)first).getActualTypeArguments()) {

                    if(argType.getTypeName().equals(entityClass.getName())){
                        return (V)sqlSession.getMapper(clazz);
                    }
                }
            }
        }
        throw new RuntimeException("attention : mapper is not registed");
    }

}
