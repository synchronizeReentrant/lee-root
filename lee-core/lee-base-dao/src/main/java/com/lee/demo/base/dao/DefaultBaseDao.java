package com.lee.demo.base.dao;

import com.lee.demo.base.define.IBatisDaoWithSingleId;
import com.lee.demo.base.extension.IBatisWithExtension;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public  abstract  class DefaultBaseDao<T,PK> extends IBatisWithExtension implements IBatisDaoWithSingleId<T,PK> {
    public static final Logger LOG = LoggerFactory.getLogger(DefaultBaseDao.class);

    private static int BATCH_DEAL_NUM = 200;

    protected static SqlSessionFactory batchSqlSessionFactory;

    public int batchInsert(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                try {
                    batchSession.insert(statement, list.get(i));
                } catch (Exception e) {
                    LOG.error(e.getMessage(), e);
                }

                if ((i + 1) % BATCH_DEAL_NUM == 0) { //BATCH_DEAL_NUM为批量提交的条数
                    batchSession.commit();
                }
            }
            batchSession.commit();
        } catch (Throwable ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    /**
     * 批量更新记录
     * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
     * @param statement
     * @param list
     * @return
     */
    public int batchUpdate(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                batchSession.update(statement, list.get(i));
                if ((i + 1) % BATCH_DEAL_NUM == 0) {
                    batchSession.commit();
                }
            }
            batchSession.commit();
        } catch (Throwable ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    /**
     * 批量删除记录
     * 默认500条，也可以通过修改BATCH_DEAL_NUM字段改变记录数量
     * @param statement
     * @param list
     * @return
     */
    public int batchDelete(String statement, List<?> list) {
        SqlSession batchSession = batchSqlSessionFactory.openSession(ExecutorType.BATCH, false);
        int i = 0;
        try {
            for (int cnt = list.size(); i < cnt; i++) {
                batchSession.delete(statement, list.get(i));
                if ((i + 1) % BATCH_DEAL_NUM == 0) {
                    batchSession.commit();
                }
            }
            batchSession.commit();
        } catch (Throwable ex) {
            LOG.error(ex.getMessage(), ex);
        } finally {
            batchSession.close();
        }
        return i;
    }

    public void setBatchSqlSessionFactory(SqlSessionFactory sqlSessionFactory) {
        batchSqlSessionFactory = sqlSessionFactory;
    }

    /**
     * 手动提交事务
     */
    public void commitTransaction(){
        SqlSession batchSession = batchSqlSessionFactory.openSession(true);
        try{
            batchSession.flushStatements();
            batchSession.commit();
        }catch(Exception e){
            e.printStackTrace();
            logger.error("----commit-----the error is:=" + e.getMessage(), e);
        }finally{
            if(batchSession!=null){
                batchSession.close();
            }
        }
    }

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
    public int  delete(T t) { return getPoMapper(getClass()).delete(t); }




}
