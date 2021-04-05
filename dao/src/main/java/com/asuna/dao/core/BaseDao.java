package com.asuna.dao.core;

import com.google.common.collect.Maps;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

/**
 * Created by liunian on 2021-04-04
 */
@SuppressWarnings("unchecked")
public class BaseDao {
    public static final Map<String, AsunaBaseMapper> mapperManager = Maps.newHashMap();


    /**
     * set mapper to mapperManager
     */
    static void setMapper(AsunaBaseMapper asunaBaseMapper) {
        //获取asunaBaseMapper的类
        Type[] genericInterfaces = asunaBaseMapper.getClass().getGenericInterfaces();
        Type[] actualTypeArguments = ((ParameterizedType) genericInterfaces[0]).getActualTypeArguments();
        mapperManager.put(actualTypeArguments[0].getTypeName(), asunaBaseMapper);
    }


    public static int delete(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.delete(o);
    }




    public static int insert(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.insert(o);
    }


    public static int insertSelective(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.insertSelective(o);
    }


    public static <T> List<T> select(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.select(o);
    }


    public static <T> T selectOne(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return (T)relateMapper.selectOne(o);
    }


    public static int updateByPrimaryKey(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.updateByPrimaryKey(o);
    }


    public static int updateByPrimaryKeySelective(Object o) {
        AsunaBaseMapper relateMapper = getRelateMapper(o);
        return relateMapper.updateByPrimaryKeySelective(o);
    }

    /**
     * 获取对应的Mapper
     * @param o
     * @return
     */
    private static AsunaBaseMapper getRelateMapper(Object o) {
        AsunaBaseMapper asunaBaseMapper = mapperManager.get(o.getClass().getName());
        if (asunaBaseMapper == null) {
            throw new RuntimeException("没有找到" + o.getClass() + "对应的Mapper");
        }
        return asunaBaseMapper;
    }
}
