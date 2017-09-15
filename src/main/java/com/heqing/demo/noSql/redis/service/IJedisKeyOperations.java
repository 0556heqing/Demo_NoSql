package com.heqing.demo.noSql.redis.service;

import java.util.Set;

/**
 * Created by heqing on 2017/9/6.
 *
 * 本接口主要操作Redis Key
 */
public interface IJedisKeyOperations {

    /**
     * 该方法用于删除已存在的键。不存在的 key 会被忽略。
     * @param keys 需要删除的主键名
     * @return 被删除 key 的数量
     */
    public Long delete(String... keys);

    /**
     * 该方法用于序列化给定 key 并返回被序列化的值。
     * @param key 主键名
     * @return 如果 key 不存在，那么返回 null 。否则，返回序列化之后的值。
     */
    public String serialize(String key);

    /**
     * 该方法用于检查给定 key 是否存在。
     * @param key 主键名
     * @return 若 key 存在返回 true ，否则返回 false 。
     */
    public Boolean isExists(String key);

    /**
     * 该方法用于设置 key 的过期时间。key 过期后将不再可用。
     * @param key 主键名
     * @param seconds 过期时间（已秒为单位）
     * @return 设置成功返回 true 。当 key 不存在或者不能为 key 设置过期时间时返回 false 。
     */
    public Boolean setExpireBySecond(String key, int seconds);

    /**
     * 该方法用于设置 key 的过期时间，以毫秒计。key 过期后将不再可用。
     * @param key 主键名
     * @param millseconds 过期时间（已毫秒为单位）
     * @return 设置成功返回 true 。当 key 不存在或者不能为 key 设置过期时间时返回 false 。
     */
    public Boolean setExpireByMillisecond(String key, long millseconds);

    /**
     * 该方法用于查找所有符合给定模式 pattern 的 key 。
     * @param pattern 条件
     * @return 设符合给定模式的 key 列表 (Array)。
     */
    public Set<String> getKeyByPattern(String pattern);

    /**
     * 该方法用于将当前数据库的 key 移动到给定的数据库db当中。
     * @param key 主键名
     * @param dbIndex 移动到的数据库下标
     * @return 移动成功返回 true ，失败则返回 false 。
     */
    public Boolean moveToOtherDB(String key, int dbIndex);

    /**
     * 该方法用于移除给定 key 的过期时间，使得 key 永不过期
     * @param key 主键名
     * @return 当过期时间移除成功时，返回 true 。如果 key 不存在或 key 没有设置过期时间，返回 false 。
     */
    public Boolean persist(String key);

    /**
     * 该方法用于以秒为单位返回key的剩余过期时间。
     * @param key 主键名
     * @return 当 key不存在时，返回 -2。当key存在但没有设置剩余生存时间时，返回 -1。否则，以秒为单位返回 key 的剩余生存时间。
     *         注意：在Redis2.8以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
     */
    public Long getRemainingSecond(String key);

    /**
     * 该方法用于以毫秒为单位返回 key 的剩余过期时间。
     * @param key 主键名
     * @return 当 key 不存在时，返回 -2 。当key存在但没有设置剩余生存时间时，返回 -1 。否则，以毫秒为单位返回 key 的剩余生存时间。
     *         注意：在Redis2.8以前，当 key 不存在，或者 key 没有设置剩余生存时间时，命令都返回 -1 。
     */
    public Long getRemainingMillisecond(String key);

    /**
     * 该方法用于从当前数据库中随机返回一个 key 。
     * @return 当数据库不为空时，返回一个 key 。当数据库为空时，返回 null 。
     */
    public String getRandomKey();

    /**
     * 该方法用于修改 key 的名称。
     * @param oldName 原来的主键名
     * @param newName 新的主键名
     * @return 改名成功时提示 OK ，失败时候返回一个错误。
     *         当OLD_KEY_NAME和NEW_KEY_NAME相同，或者OLD_KEY_NAME不存在时，返回一个错误。当NEW_KEY_NAME已经存在时，RENAME命令将覆盖旧值。
     */
    public String rename(String oldName, String newName);

    /**
     * 该方法用于在新的 key 不存在时修改 key 的名称。
     * @param oldName 原来的主键名
     * @param newName 新的主键名
     * @return 修改成功时，返回 true 。如果NEW_KEY_NAME已经存在，返回 false 。
     */
    public Boolean renameNX(String oldName, String newName);

    /**
     * 该方法用于返回 key 所储存的值的类型。
     * @param key 主键名
     * @return 返回 key 的数据类型，数据类型有：
     *         none (key不存在)
     *         string (字符串)
     *         list (列表)
     *         set (集合)
     *         zset (有序集)
     *         hash (哈希表)
     */
    public String type(String key);

    /**
     * 该方法用于返回当前数据库中key的数目
     * @return 当前数据库中key的数目
     */
    public Long getKeySize();

}
