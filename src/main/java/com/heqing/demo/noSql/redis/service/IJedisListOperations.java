package com.heqing.demo.noSql.redis.service;

import java.util.List;

/**
 * Created by heqing on 2017/9/7.
 */
public interface IJedisListOperations extends IJedisKeyOperations {

    /**
     * 该方法用于移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout 以秒为单位的等待超时时间
     * @param key 主键名
     * @return 如果列表为空，返回一个 null 。
     *         否则，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     *         注意：弹出的元素会从列表中移除，不可用来仅仅获取第一个元素
     */
    public List<String> removeAndGetFirstIndex(int timeout, String key);

    /**
     * 该方法用于移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止。
     * @param timeout 以秒为单位的等待超时时间
     * @param key 主键名
     * @return 假如在指定时间内没有任何元素被弹出，则返回一个 null 和等待时长。
     *         反之，返回一个含有两个元素的列表，第一个元素是被弹出元素所属的 key ，第二个元素是被弹出元素的值。
     *          注意：弹出的元素会从列表中移除，不可用来仅仅获取最后一个元素
     */
    public List<String> removeAndGetLastValue(int timeout, String key);

    /**
     * 该方法用于通过索引获取列表中的元素。你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param key 主键名
     * @param index 元素下标
     * @return 列表中下标为指定索引值的元素。 如果指定索引值不在列表的区间范围内，返回 null 。
     */
    public String getValueByIndex(String key, long index);

    /**
     * 该方法用于在列表的元素前或者后插入元素。 当指定元素不存在于列表中时，不执行任何操作。
     * 当列表不存在时，被视为空列表，不执行任何操作。 如果 key 不是列表类型，返回一个错误。
     * @param key 主键名
     * @param position 只能为 -1 或 1；-1 代表之前，1代表之后。
     * @param pivot 列表中存在的元素
     * @param value 将要加入的元素
     * @return 如果命令执行成功，返回插入操作完成之后，列表的长度。
     *         如果 position 不为 -1 或 1 ，返回 -2 。如果没有找到指定元素 ，返回 -1 。 如果 key 不存在或为空列表，返回 0 。
     */
    public Long insert(String key, int position, String pivot, String value);

    /**
     * 该方法用于返回列表的长度。 如果列表 key 不存在，则 key 被解释为一个空列表，返回 0 。 如果 key 不是列表类型，返回一个错误。
     * @param key 主键名
     * @return 列表的长度。
     */
    public Long getCount(String key);

    /**
     * 该方法用于移除并返回列表的第一个元素。
     * @param key 主键名
     * @return 列表的第一个元素。 当列表 key 不存在时，返回 null 。
     */
    public String removeAndGetFirstValue(String key);

    /**
     * 该方法用于将一个或多个值插入到列表头部。
     * 如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。
     * 当 key 存在但不是列表类型时，返回一个错误。
     * 注意：在Redis 2.4版本以前的 LPUSH 命令，都只接受单个 value 值。
     * @param key 主键名
     * @param values 列表值
     * @return 执行 LPUSH 命令后，列表的长度。
     */
    public Long addBeforeValue(String key, String... values);

    /**
     * 该方法用于将一个值插入到已存在的列表头部，列表不存在时操作无效。
     * @param key 主键名
     * @param value 多个列的值
     * @return LPUSHX 命令执行之后，列表的长度。
     */
    public Long addBeforeValueNX(String key, String value);

    /**
     * 该方法用于返回列表中指定区间内的元素，区间以偏移量 START 和 END 指定。
     * 其中 0 表示列表的第一个元素， 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param key 主键名
     * @param start 开始位置(包括此元素)
     * @param end 结束位置(包括此元素)
     * @return 指定区间内的元素。
     */
    public List<String> getValueByRange(String key, Long start, Long end);

    /**
     * 该方法用于根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素。
     * COUNT 的值可以是以下几种：
     *      count > 0 : 从表头开始向表尾搜索，移除与 VALUE 相等的元素，数量为 COUNT 。
     *      count < 0 : 从表尾开始向表头搜索，移除与 VALUE 相等的元素，数量为 COUNT 的绝对值。
     *      count = 0 : 移除表中所有与 VALUE 相等的值。
     * @param key 主键名
     * @param count 参数值
     * @param value 相等的元素值
     * @return 被移除元素的数量。 列表不存在时返回 0 。
     */
    public Long removeByValue(String key, Long count, String value);

    /**
     * 该方法用于通过索引来设置元素的值，将替换原来索引所在的值。
     * 当索引参数超出范围，或对一个空列表进行 LSET 时，返回一个错误。
     * @param key 主键名
     * @param index 元素下标
     * @param value 新增的值
     * @return 哈希表中字段的数量。 当 key 不存在时，返回 0 。
     */
    public String setValueByIndex(String key, Long index, String value);

    /**
     * 该方法用于 对一个列表进行修剪(trim)，就是说，让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     * 下标 0 表示列表的第一个元素，以 1 表示列表的第二个元素，以此类推。
     * 你也可以使用负数下标，以 -1 表示列表的最后一个元素， -2 表示列表的倒数第二个元素，以此类推。
     * @param key 主键名
     * @param start 开始下标(包括此元素)
     * @param end 结束下标(包括此元素)
     * @return 命令执行成功时，返回 ok 。
     */
    public String getValueByTrim(String key, Long start, Long end);

    /**
     * 该方法用于移除并返回列表的最后一个元素。
     * @param key 主键名
     * @return 列表的最后一个元素。 当列表不存在时，返回 null 。
     */
    public String removeAndGetLastIndex(String key);

    /**
     * 该方法用于移除列表的最后一个元素，并将该元素添加到另一个列表并返回。
     * @param srcKey 移除的主键名
     * @param dstKey 移入的主键名
     * @return 被弹出的元素。
     */
    public String getLastIndexAndMove(String srcKey, String dstKey);

    /**
     * 该方法用于将一个或多个值插入到列表的尾部(最右边)。
     * 如果列表不存在，一个空列表会被创建并执行 RPUSH 操作。 当列表存在但不是列表类型时，返回一个错误。
     * 注意：在 Redis 2.4 版本以前的 RPUSH 命令，都只接受单个 value 值。
     * @param key 主键名
     * @param values 多个添加的值
     * @return 执行 RPUSH 操作后，列表的长度 。
     */
    public Long addAfterValue(String key, String... values);

    /**
     * 该方法用于将一个值插入到已存在的列表尾部(最右边)。如果列表不存在，操作无效。
     * @param key 主键名
     * @param value 值
     * @return 执行 Rpushx 操作后，列表的长度。
     */
    public Long addAfterValueNX(String key, String value);

}
