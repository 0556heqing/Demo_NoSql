package com.heqing.demo.noSql.redis.service;

import java.util.Map;
import java.util.Set;

/**
 * Created by heqing on 2017/9/8.
 *
 *  本接口主要操作Redis Set(有序)
 */
public interface IJedisSortedSetOperations {

    /**
     * 该方法用于将一个或多个成员元素及其分数值加入到有序集当中。
     * 如果某个成员已经是有序集的成员，那么更新这个成员的分数值，并通过重新插入这个成员元素，来保证该成员在正确的位置上。
     * 分数值可以是整数值或双精度浮点数。
     * 如果有序集合 key 不存在，则创建一个空的有序集并执行 ZADD 操作。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * 注意： 在 Redis 2.4 版本以前， ZADD 每次只能添加一个元素。
     * @param key 主键名
     * @param scoreMembers Map类型（成员元素-分数值）
     * @return 被成功添加的新成员的数量，不包括那些被更新的、已经存在的成员。
     */
    public Long add(String key, Map<String, Double> scoreMembers);

    /**
     * 该方法用于计算集合中元素的数量。
     * @param key 主键名
     * @return 当 key 存在且是有序集类型时，返回有序集的基数。 当 key 不存在时，返回 0 。
     */
    public Long getcount(String key);

    /**
     * 该方法用于计算有序集合中指定分数区间的成员数量。
     * @param key 主键名
     * @param min 最小分数
     * @param max 最大分数
     * @return 集合的数量。 当集合 key 不存在时，返回 0 。
     */
    public Long getCountByRange(String key, double min, double max);

    /**
     * 该方法用于对有序集合中指定成员的分数加上增量 sroce
     * 可以通过传递一个负数值 sroce ，让分数减去相应的值，比如 ZINCRBY key -5 member ，就是让 member 的 score 值减去 5 。
     * 当 key 不存在，或分数不是 key 的成员时， ZINCRBY key sroce member 等同于 ZADD key sroce member 。
     * 当 key 不是有序集类型时，返回一个错误。
     * 分数值可以是整数值或双精度浮点数。
     * @param key 主键名
     * @param sroce 分数
     * @param member 成员
     * @return member 成员的新分数值，以字符串形式表示。
     */
    public Double addSroceByMember(String key, double sroce, String member);

    /**
     * 该方法用于计算给定的一个或多个有序集的交集，其中给定 key 的数量必须以 numkeys 参数指定，并将该交集(结果集)储存到 desKey 。
     * 默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和。
     * @param desKey 存入的有序集
     * @param sets 一个或多个有序集
     * @return 保存到目标结果集的的成员数量。
     */
    public Long intersectionMove(String desKey, String... sets);

    /**
     * 该方法用于返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递增(从小到大)来排序。
     * 具有相同分数值的成员按字典序(lexicographical order )来排列。
     * 如果你需要成员按
     * 值递减(从大到小)来排列，请使用 ZREVRANGE 命令。
     * 下标参数 start 和 end 都以 0 为底，也就是说，以 0 表示有序集第一个成员，以 1 表示有序集第二个成员，以此类推。
     * 你也可以使用负数下标，以 -1 表示最后一个成员， -2 表示倒数第二个成员，以此类推。
     * @param key 主键名
     * @param start 开始下标（包含）
     * @param end 结束下标（包含）
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<String> getRangeByIndex(String key, long start, long end);

    /**
     * 该方法用于返回有序集合中指定分数区间的成员列表。有序集成员按分数值递增(从小到大)次序排列。
     * 具有相同分数值的成员按字典序来排列(该属性是有序集提供的，不需要额外的计算)。
     * 默认情况下，区间的取值使用闭区间 (小于等于或大于等于)，你也可以通过给参数前增加 ( 符号来使用可选的开区间 (小于或大于)。
     * @param key 主键名
     * @param min 最小分数
     * @param max 最大分数
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<String> getRangeByScore(String key, double min, double max);

    /**
     * 该方法用于返回有序集中指定成员的排名。其中有序集成员按分数值递增(从小到大)顺序排列。
     * @param key 有序集主键名
     * @param member 有序集成员
     * @return 如果成员是有序集 key 的成员，返回 member 的排名。 如果成员不是有序集 key 的成员，返回 null 。
     */
    public Long getRankByMember(String key, String member) ;

    /**
     * 该方法用于移除有序集中的一个或多个成员，不存在的成员将被忽略。
     * 当 key 存在但不是有序集类型时，返回一个错误。
     * 注意： 在 Redis 2.4 版本以前， ZREM 每次只能删除一个元素。
     * @param key 主键名
     * @param member 一个或多个成员
     * @return 被成功移除的成员的数量，不包括被忽略的成员。
     */
    public Long remove(String key, String... member);

    /**
     * 该方法用于移除有序集中，指定排名(rank)区间内的所有成员。
     * @param key 主键名
     * @return 被移除成员的数量。
     */
    public Long removeByRank(String key, long start, long end);

    /**
     * 该方法用于移除有序集中，指定分数（score）区间内的所有成员。
     * @param key 主键名
     * @param min 最小分数
     * @param max 最大分数
     * @return 被移除成员的数量。
     */
    public Long removeByScore(String key, double min, double max);

    /**
     * 该方法用于返回有序集中，指定区间内的成员。
     * 其中成员的位置按分数值递减(从大到小)来排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order)排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGE 命令的其他方面和 ZRANGE 命令一样。
     * @param key 主键名
     * @param start 开始位置
     * @param end 结束位置
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<String> getRangeByIndexDesc(String key, long start, long end);

    /**
     * 该方法用于返回有序集中指定分数区间内的所有的成员。有序集成员按分数值递减(从大到小)的次序排列。
     * 具有相同分数值的成员按字典序的逆序(reverse lexicographical order )排列。
     * 除了成员按分数值递减的次序排列这一点外， ZREVRANGEBYSCORE 命令的其他方面和 ZRANGEBYSCORE 命令一样。
     * @param key 主键名
     * @param max 最大分数
     * @param min 最小分数
     * @return 指定区间内，带有分数值(可选)的有序集成员的列表。
     */
    public Set<String> getRangeByScoreDesc(String key, double max, double min);

    /**
     * 该方法用于返回有序集中成员的排名。其中有序集成员按分数值递减(从大到小)排序。
     * 排名以 0 为底，也就是说， 分数值最大的成员排名为 0 。
     * 使用 ZRANK 命令可以获得成员按分数值递增(从小到大)排列的排名。
     * @param key 主键名
     * @param member 有序集成员
     * @return 如果成员是有序集 key 的成员，返回成员的排名。 如果成员不是有序集 key 的成员，返回 null 。
     */
    public Long getRankDescByMember(String key, String member);

    /**
     * 该方法用于返回有序集中，成员的分数值。 如果成员元素不是有序集 key 的成员，或 key 不存在，返回 nil 。
     * @param key 主键名
     * @param member 有序集成员
     * @return 成员的分数值，以字符串形式表示。
     */
    public Double getScoreByMember(String key, String member);

    /**
     * 该方法用于计算给定的一个或多个有序集的并集，其中给定 key 的数量必须以 numkeys 参数指定，并将该并集(结果集)储存到 dstKey 。
     * 默认情况下，结果集中某个成员的分数值是所有给定集下该成员分数值之和 。
     * @param dstKey 存入集合名
     * @param sets 一个或多个有序集
     * @return 保存到 destination 的结果集的成员数量。
     */
    public Long getUnionAndMove(String dstKey, String... sets);

}
