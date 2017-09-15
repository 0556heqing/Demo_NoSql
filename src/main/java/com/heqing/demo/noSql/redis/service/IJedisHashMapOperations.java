package com.heqing.demo.noSql.redis.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/7.
 *
 * 本接口主要操作Redis HashMap
 */
public interface IJedisHashMapOperations extends IJedisKeyOperations {

    /**
     * 该方法用于同时将多个 field-value (字段-值)对设置到哈希表中。
     * 此命令会覆盖哈希表中已存在的字段。
     * 如果哈希表不存在，会创建一个空哈希表，并执行 HMSET 操作。
     * @param key 主键名
     * @param hash 需要存储的Map值
     * @return 如果命令执行成功，返回 OK 。
     */
    public String set(String key, Map<String, String> hash);

    /**
     * 该方法用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略。
     * @param key 主键名
     * @param fields 多个字段
     * @return 被成功删除字段的数量，不包括被忽略的字段。
     */
    public Long deleteFileds(String key, String... fields);

    /**
     * 该方法用于查看哈希表的指定字段是否存在。
     * @param key 主键名
     * @param filed 字段名
     * @return 如果哈希表含有给定字段，返回 true 。 如果哈希表不含有给定字段，或 key 不存在，返回 false 。
     */
    public Boolean isExists(String key, String filed);

    /**
     * 该方法用于返回哈希表中指定字段的值。
     * @param key 主键名
     * @param filed 字段名
     * @return 返回给定字段的值。如果给定的字段或 key 不存在时，返回 null 。
     */
    public String getValueByFiled(String key, String filed);

    /**
     * 该方法用于返回哈希表中，所有的字段和值。
     * 在返回值里，紧跟每个字段名(field name)之后是字段的值(value)，所以返回值的长度是哈希表大小的两倍。
     * @param key 主键名
     * @return 以列表形式返回哈希表的字段及字段值。 若 key 不存在，返回空列表。
     */
    public Map<String, String> getMapByKey(String key);

    /**
     * 该方法用于为哈希表中的字段值加上指定增量值。
     * 增量也可以为负数，相当于对指定字段进行减法操作。
     * 如果哈希表的 key 不存在，一个新的哈希表被创建并执行 HINCRBY 命令。
     * 如果指定的字段不存在，那么在执行命令前，字段的值被初始化为 0 。
     * 对一个储存字符串值的字段执行 HINCRBY 命令将造成一个错误。
     * 本操作的值被限制在 64 位(bit)有符号数字表示之内。
     * @param key 主键名
     * @param filed 字段名
     * @param value 增量值
     * @return 执行 HINCRBY 命令之后，哈希表中字段的值。
     */
    public Long addLongValueByFiled(String key, String filed, long value);

    /**
     * 该方法用于为哈希表中的字段值加上指定浮点数增量值。
     * 如果指定的字段不存在，那么在执行命令前，字段的值被初始化为 0 。
     * @param key 主键名
     * @return 执行 Hincrbyfloat 命令之后，哈希表中字段的值。
     */
    public Double addDoubleValueByFiled(String key, String filed, double value);

    /**
     * 该方法用于获取哈希表中的所有域（field）。
     * @param key 主键名
     * @return 包含哈希表中所有域（field）列表。 当 key 不存在时，返回一个空列表。
     */
    public Set<String> getField(String key);

    /**
     * 该方法用于获取哈希表中字段的数量。
     * @param key 主键名
     * @return 哈希表中字段的数量。 当 key 不存在时，返回 0 。
     */
    public Long getCount(String key);

    /**
     * 该方法用于返回哈希表中，一个或多个给定字段的值。
     * 如果指定的字段不存在于哈希表，那么返回一个 null 值。
     * @param key 主键名
     * @param field 字段名
     * @return 一个包含多个给定字段关联值的表，表值的排列顺序和指定字段的请求顺序一样。
     */
    public List<String> getFiledAndValue(String key, String... field);

    /**
     * 该方法用于为哈希表中的字段赋值 。
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，旧值将被覆盖。
     * @param key 主键名
     * @param field 字段名
     * @param value 值
     * @return 如果字段是哈希表中的一个新建字段，并且值设置成功，返回 true 。
     *         如果哈希表中域字段已经存在且旧值已被新值覆盖，返回 false 。
     */
    public Boolean setFiledAndValue(String key, String field, String value);

    /**
     * 该方法用于为哈希表中不存在的的字段赋值 。
     * 如果哈希表不存在，一个新的哈希表被创建并进行 HSET 操作。
     * 如果字段已经存在于哈希表中，操作无效。
     * 如果 key 不存在，一个新哈希表被创建并执行 HSETNX 命令。
     * @param key 主键名
     * @param field 字段名
     * @param value 值
     * @return 设置成功，返回 true 。 如果给定字段已经存在且没有操作被执行，返回 false 。
     */
    public Boolean setFiledAndValueNX(String key, String field, String value);

    /**
     * 该方法用于返回哈希表所有域(field)的值。
     * @param key 主键名
     * @return 一个包含哈希表中所有域(field)值的列表。 当 key 不存在时，返回一个空表。
     */
    public List<String> getValue(String key);

}
