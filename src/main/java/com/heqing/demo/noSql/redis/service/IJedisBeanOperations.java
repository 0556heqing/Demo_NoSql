package com.heqing.demo.noSql.redis.service;

import java.util.List;

/**
 * Created by heqing on 2017/9/13.
 */
public interface IJedisBeanOperations {

    //String
    public String set(String key, String value);

    public String setValueBySecond(String key, int secods, String value);

    public String setValueByMillisecond(String key, long millisecods, String value);

    public Boolean setnx(String key, String value);

    public String get(String key);


    //HashMap
    public Boolean setFiledAndValue(String key, String field, String value);

    public Boolean setFiledAndValueNX(String key, String field, String value);

    public List<String> getFiledAndValue(String key, String... field);

    public String getValueByFiled(String key, String filed);


    //List
    public String getValueByIndex(String key, long index);

    public Long insert(String key, int position, String pivot, String value);
}
