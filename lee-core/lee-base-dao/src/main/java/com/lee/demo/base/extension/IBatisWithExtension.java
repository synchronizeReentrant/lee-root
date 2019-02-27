package com.lee.demo.base.extension;

import com.github.abel533.mapper.Mapper;
import com.lee.demo.base.define.IBatisWithBase;
import org.apache.ibatis.binding.MapperRegistry;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Collection;


public abstract class IBatisWithExtension<T,V extends Mapper> extends SqlSessionDaoSupport {

    private  Collection<Class<?>> classCollection = null;

    private  SqlSession sqlSession = null;


    @Override
    @Resource
    public void setSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        super.setSqlSessionFactory(sqlSessionFactory);
    }
    /**
     * 初始化一些操作
     */
    @PostConstruct
    public void init(){
        sqlSession = getSqlSessionFactory().openSession();
        Configuration configuration = sqlSession.getConfiguration();
        MapperRegistry registry = configuration.getMapperRegistry();
        classCollection =  registry.getMappers();
    }

    protected  V  getPoMapper(Class<? extends IBatisWithBase> DaoClass){  //UserDaoImpl
        Class <T> entityClass = (Class <T>) ((ParameterizedType)DaoClass.getGenericSuperclass()).getActualTypeArguments()[0];
        for(Class clazz : classCollection){
            String className = clazz.getSimpleName();
            if(className.indexOf("Dao") > -1) continue;
            Type [] type = clazz.getGenericInterfaces();
            Type first = type[0];
            if (first instanceof  ParameterizedType) {
                for (Type argType:((ParameterizedType)first).getActualTypeArguments()) {
                    if(argType.getTypeName().equals(entityClass.getName())){
                        System.out.println("----"+clazz);
                        return (V)sqlSession.getMapper(clazz);
                    }
                }
            }
        }
        throw new RuntimeException("attention : mapper is not registed");
    }

}
