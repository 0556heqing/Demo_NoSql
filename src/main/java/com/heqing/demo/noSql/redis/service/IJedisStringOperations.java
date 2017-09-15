package com.heqing.demo.noSql.redis.service;

import java.util.List;

/**
 * Created by heqing on 2017/9/6.
 *
 *  本接口主要操作Redis String
 */
public interface IJedisStringOperations extends IJedisKeyOperations {

    /**
     * 该方法用于设置给定 key 的值。如果 key 已经存储其他值，SET 就覆写旧值，且无视类型。
     * @param key 主键名
     * @param value 值
     * @return 在 Redis 2.6.12 以前版本， SET 命令总是返回 OK 。
     *         从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
     */
    public String set(String key, String value);

    /**
     * 该方法用于设置给定 key 的值。如果 key 已经存储其他值，SET 就覆写旧值，且无视类型。
     * @param key 主键名
     * @param bean 对象
     * @return 在 Redis 2.6.12 以前版本， SET 命令总是返回 OK 。
     *         从 Redis 2.6.12 版本开始， SET 在设置操作成功完成时，才返回 OK 。
     */
    public String set(String key, Object bean);

    /**
     * 该方法用于获取指定 key 的值。如果 key 不存在，返回 null 。如果 key 储存的值不是字符串类型，返回一个错误。
     * @param key 主键名
     * @return 返回 key 的值，如果 key 不存在时，返回 null。如果 key 不是字符串类型，那么返回一个错误。
     */
    public String get(String key);

    /**
     * 该方法用于获取指定 key 的值。如果 key 不存在，返回 null 。如果 key 储存的值不是字符串类型，返回一个错误。
     * @param key 主键名
     * @return 返回 key 的值，如果 key 不存在时，返回 null。如果 key 不是字符串类型，那么返回一个错误。
     */
    public <T> T get(String key, Class<T> clazz);

    /**
     * 该方法用于获取存储在指定 key 中字符串的子字符串。字符串的截取范围由 start 和 end 两个偏移量决定(包括 start 和 end 在内)。
     * @param key 主键名
     * @param startOffset 开始坐标
     * @param endOffset 结束坐标
     * @return 截取得到的子字符串。
     */
    public String getRange(String key, long startOffset, long endOffset);

    /**
     * 该方法用于设置指定 key 的值，并返回 key 的旧值。
     * @param key 主键名
     * @param newValue 新值
     * @return 返回给定 key 的旧值。 当 key 没有旧值时，即 key 不存在时，返回 null。
     *         当 key 存在但不是字符串类型时，返回一个错误。
     */
    public String getSet(String key, String newValue);

    /**
     * 该方法用于同时设置一个或多个 key-value 对。
     * @param key_value 多个字段，key 与 value
     * @return 总是返回 OK 。
     */
    public String setK_V(String... key_value);

    /**
     * 该方法用于返回所有(一个或多个)给定 key 的值。 如果给定的 key 里面，有某个 key 不存在，那么这个 key 返回特殊值 null 。
     * @param key_value 多个字段，key 与 value
     * @return 一个包含所有给定 key 的值的列表。
     */
    public List<String> getK_V(String... key_value);

    /**
     * 该方法用于在指定的 key 不存在时，为 key 设置指定的值。
     * @param key 主键名
     * @param value 字符串值
     * @return 设置成功，返回 true 。 设置失败，返回 false 。
     */
    public Boolean setnx(String key, String value);

    /**
     * 该方法用于在指定的 key 不存在时，为 key 设置指定的值。
     * @param key 主键名
     * @param bean 对象
     * @return 设置成功，返回 true 。 设置失败，返回 false 。
     */
    public Boolean setnx(String key, Object bean);

    /**
     * 该方法用于指定的字符串覆盖给定 key 所储存的字符串值，覆盖的位置从偏移量 offset 开始。
     * @param key 主键名
     * @param offset 覆盖的位置
     * @param value 字符串值
     * @return 被修改后的字符串长度。
     */
    public Long setrange(String key, long offset, String value);

    /**
     * 该方法用于获取指定 key 所储存的字符串值的长度。当 key 储存的不是字符串值时，返回一个错误。
     * @param key 主键名
     * @return 字符串值的长度。 当 key 不存在时，返回 0。
     */
    public Long getLength(String key);

    /**
     * 该方法用于所有给定 key 都不存在时，同时设置一个或多个 key-value 对。
     * @param key_value 多个字段，key 与 value
     * @return 当所有 key 都成功设置，返回 true 。 如果所有给定 key 都设置失败(至少有一个 key 已经存在)，那么返回 false 。
     */
    public Boolean setK_Vnx(String... key_value);

    /**
     * 该方法用于为指定的 key 设置值及其过期时间（以秒为单位）。如果 key 已经存在， SETEX 命令将会替换旧的值。
     * @param key 主键名
     * @param secods 以秒为单位过期时间
     * @param value 新值
     * @return 设置成功时返回 OK 。
     */
    public String setValueBySecond(String key, int secods, String value);

    /**
     * 该方法用于为指定的 key 设置值及其过期时间（以秒为单位）。如果 key 已经存在， SETEX 命令将会替换旧的值。
     * @param key 主键名
     * @param secods 以秒为单位过期时间
     * @param bean 对象
     * @return 设置成功时返回 OK 。
     */
    public String setValueBySecond(String key, int secods, Object bean);

    /**
     * 该方法用于为指定的 key 设置值及其过期时间（以毫秒为单位）。如果 key 已经存在， PSETEX 命令将会替换旧的值。
     * @param key 主键名
     * @param millisecods 毫秒
     * @param value 值
     * @return 设置成功时返回 OK 。
     */
    public String setValueByMillisecond(String key, long millisecods, String value);

    /**
     * 该方法用于为指定的 key 设置值及其过期时间（以毫秒为单位）。如果 key 已经存在， PSETEX 命令将会替换旧的值。
     * @param key 主键名
     * @param millisecods 毫秒
     * @param bean 值
     * @return 设置成功时返回 OK 。
     */
    public String setValueByMillisecond(String key, long millisecods, Object bean);

    /**
     * 该方法用于 key 中储存的数字值增一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key 主键名
     * @return 执行 INCR 命令之后 key 的值。
     */
    public Long incr(String key);

    /**
     * 该方法用于将 key 中储存的数字加上指定的增量值。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCRBY 命令。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key 主键名
     * @param value 需要增加的值
     * @return 加上指定的增量值之后， key 的值。
     */
    public Long incrByLong(String key, long value);

    /**
     * 该方法用于为 key 中所储存的值加上指定的浮点数增量值。
     * 如果 key 不存在，那么 INCRBYFLOAT 会先将 key 的值设为 0 ，再执行加法操作。
     * @param key 主键名
     * @param value 需要增加的值,浮点型
     * @return 执行命令之后 key 的值。
     */
    public Double incrByDouble(String key, double value);

    /**
     * 该方法用于将 key 中储存的数字值减一。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECR 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key 主键名
     * @return 执行命令之后 key 的值。
     */
    public Long decr(String key);

    /**
     * 该方法用于将 key 所储存的值减去指定的减量值。
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 DECRBY 操作。
     * 如果值包含错误的类型，或字符串类型的值不能表示为数字，那么返回一个错误。
     * 本操作的值限制在 64 位(bit)有符号数字表示之内。
     * @param key 主键名
     * @param value 整数类型新值
     * @return 减去指定减量值之后， key 的值。
     */
    public Long decrByLong(String key, long value);

    /**
     * 该方法用于为指定的 key 追加值。
     * 如果 key 已经存在并且是一个字符串， APPEND 命令将 value 追加到 key 原来的值的末尾。
     * 如果 key 不存在， APPEND 就简单地将给定 key 设为 value ，就像执行 SET key value 一样。
     * @param key 主键名
     * @param value 追加的值
     * @return 追加指定值之后， key 中字符串的长度。
     */
    public Long append(String key, String value);

    /**
     * 该方法用于获取 key 的子串，由索引 start 开始至索引 end 结束。
     * @param key 主键名
     * @param start 开始的索引值
     * @param end 结束的索引值
     * @return key的string的value的子串
     */
    public String substr(String key, int start, int end);
}
