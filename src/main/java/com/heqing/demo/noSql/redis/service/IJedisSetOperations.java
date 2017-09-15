package com.heqing.demo.noSql.redis.service;

import java.util.List;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 *
 * 本接口主要操作Redis Set(无序)
 */
public interface IJedisSetOperations {

    /**
     * 该方法用于将一个或多个成员元素加入到集合中，已经存在于集合的成员元素将被忽略。
     * 假如集合 key 不存在，则创建一个只包含添加的元素作成员的集合。
     * 当集合 key 不是集合类型时，返回一个错误。
     * 注意：在Redis2.4版本以前， SADD 只接受单个成员值。
     * @param key 主键名
     * @param members 加入集合的成员元素。（一个或多个）
     * @return 被添加到集合中的新元素的数量，不包括被忽略的元素。
     */
    public Long add(String key, String... members);

    /**
     * 该方法用于返回集合中元素的数量。
     * @param key 主键名
     * @return 集合的数量。 当集合 key 不存在时，返回 0 。
     */
    public Long getCount(String key);

    /**
     * 该方法用于返回给定集合之间的差集。不存在的集合 key 将视为空集。
     * @param keys 主键名（一个或多个）
     * @return 包含差集成员的列表。（第一个集合中存在而其他集合不存在）
     */
    public Set<String> getDiff(String... keys);

    /**
     * 该方法用于将给定集合之间的差集存储在指定的集合中。如果指定的集合 dstKey 已存在，则会被覆盖。
     * @param dstKey 存入的集合
     * @param keys 多个集合以比较差集
     * @return 结果集中的元素数量。（差集第一个集合中存在而其他集合不存在）
     */
    public Long getDiffAndMove(String dstKey, String... keys);

    /**
     * 该方法用于返回给定所有给定集合的交集。 不存在的集合 key 被视为空集。 当给定集合当中有一个空集时，结果也为空集(根据集合运算定律)。
     * @param keys 集合主键名
     * @return 交集成员的列表。
     */
    public Set<String> getIntersection(String... keys);

    /**
     * 该方法用于将给定集合之间的交集存储在指定的集合中。如果指定的集合已经存在，则将其覆盖。
     * @param dstKey 存入的集合
     * @param keys 主键名
     * @return 交集中的元素数量。
     */
    public Long getIntersectionAndMove(String dstKey, String... keys);

    /**
     * 该方法用于断成员元素是否是集合的成员。
     * @param key 主键名
     * @param member 元素
     * @return 如果成员元素是集合的成员，返回 true 。 如果成员元素不是集合的成员，或 key 不存在，返回 false 。
     */
    public Boolean isMember(String key, String member);

    /**
     * 该方法用于返回集合中的所有的成员。 不存在的集合 key 被视为空集合。
     * @param key 主键名
     * @return 集合中的所有成员。
     */
    public Set<String> getMemberByKey(String key);

    /**
     * 该方法用于将指定成员 member 元素从 srcKey 集合移动到 destKey 集合。
     * SMOVE 是原子性操作。
     * 如果 source 集合不存在或不包含指定的 member 元素，则 SMOVE 命令不执行任何操作，仅返回 0 。否则， member 元素从 source 集合中被移除，并添加到 destination 集合中去。
     * 当 destination 集合已经包含 member 元素时， SMOVE 命令只是简单地将 source 集合中的 member 元素删除。
     * 当 source 或 destination 不是集合类型时，返回一个错误。
     * @param srcKey 移出集合
     * @param destKey 移入集合
     * @param member 成员元素
     * @return 如果成员元素被成功移除，返回 true 。 如果成员元素不是 source 集合的成员，并且没有任何操作对 destination 集合执行，那么返回 false 。
     */
    public Boolean move(String srcKey, String destKey, String member);

    /**
     * 该方法用于移除并返回集合中的一个随机元素。
     * @param key 主键名
     * @return 被移除的随机元素。 当集合不存在或是空集时，返回 null 。
     */
    public String removeRandomMember(String key);

    /**
     * 该方法用于返回集合中的一个随机元素。
     * 从 Redis 2.6 版本开始， Srandmember 命令接受可选的 count 参数：
     *      如果 count 为正数，且小于集合基数，那么命令返回一个包含 count 个元素的数组，数组中的元素各不相同。如果 count 大于等于集合基数，那么返回整个集合。
     *      如果 count 为负数，那么命令返回一个数组，数组中的元素可能会重复出现多次，而数组的长度为 count 的绝对值。
     * 该操作和 SPOP 相似，但 SPOP 将随机元素从集合中移除并返回，而 Srandmember 则仅仅返回随机元素，而不对集合进行任何改动。
     * @param key 主键名
     * @param count 参数
     * @return 只提供集合 key 参数时，返回一个元素；如果集合为空，返回 null 。 如果提供了 count 参数，那么返回一个数组；如果集合为空，返回空数组。
     */
    public List<String> getRandomMember(String key, int count);

    /**
     * 该方法用于移除集合中的一个或多个成员元素，不存在的成员元素会被忽略。
     * 当 key 不是集合类型，返回一个错误。
     * 在 Redis 2.4 版本以前， SREM 只接受单个成员值。
     * @param key 主键名
     * @param members 一个或多个成员元素
     * @return 被成功移除的元素的数量，不包括被忽略的元素。
     */
    public Long remove(String key, String... members);

    /**
     * 该方法用于返回给定集合的并集。不存在的集合 key 被视为空集。
     * @param keys 主键名
     * @return 并集成员的列表。
     */
    public Set<String> getUnion(String... keys);

    /**
     * 该方法用于将给定集合的并集存储在指定的集合 dstKey 中。如果 dstKey 已经存在，则将其覆盖。
     * @param dstKey 主键名
     * @param keys 多个集合
     * @return 结果集中的元素数量。
     */
    public Long getUnionAndMove(String dstKey, String... keys);

}
