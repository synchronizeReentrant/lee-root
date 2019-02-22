package com.lee.demo.base.extension;

import com.github.abel533.mapper.Mapper;
import com.lee.demo.base.define.IBatisWithBase;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;


public abstract class IBatisWithExtension<T,V extends Mapper> {
    @Resource
    private SqlSessionFactory sqlSessionFactory;

    private  Collection<Class<?>> classCollection = null;

    private  SqlSession sqlSession = null;

    public SqlSessionFactory getSqlSessionFactory() {

        return sqlSessionFactory;
    }

    /**
     * 初始化一些操作
     */

    @PostConstruct
    public void init(){
        sqlSession = sqlSessionFactory.openSession();
        Configuration configuration = sqlSession.getConfiguration();
        MapperRegistry registry = configuration.getMapperRegistry();
        classCollection =  registry.getMappers();
    }
    protected  V  getPoMapper(Class<? extends IBatisWithBase> DaoClass){
        Class <T> entityClass = (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        for(Class clazz : classCollection){
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
