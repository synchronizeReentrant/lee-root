package com.lee.demo.base.extension;

import com.lee.demo.base.define.IBatisWithBase;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;

public abstract class IBatisWithExtension<T,V> {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    private static final String MAPPER_PACKAGE = "com.wd.question.dao.common.mapper";

    public SqlSessionFactory getSqlSessionFactory() {
        return sqlSessionFactory;
    }
    //获得实体类对应的Mapper.Class
    public V  getPoMapper(Class<? extends IBatisWithBase> DaoClass){
        Type superType = DaoClass.getGenericSuperclass();
        Type [] typeC =((ParameterizedType)superType).getActualTypeArguments();
        String typeName = typeC[1].getTypeName();
        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1];
        if(sqlSessionFactory == null) throw new RuntimeException("sqlSession is injected by spring container");
        //获得SqlSession
        SqlSession sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        MapperRegistry registry = configuration.getMapperRegistry();
        Collection<Class<?>> classCollection =  registry.getMappers();
        for(Class clazz : classCollection){
            String packageName = clazz.getPackage().getName();
            if(!packageName.equals(MAPPER_PACKAGE)){
                continue;
            }
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
