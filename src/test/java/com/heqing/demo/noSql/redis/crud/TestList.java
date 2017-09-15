package com.heqing.demo.noSql.redis.crud;

import com.heqing.demo.noSql.redis.RedisUtil;
import org.junit.Before;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

/** 
 * jedis操作List 
 */  
public class TestList {

	private Jedis jedis; // 获取数据库的连接,非切片客户端连接
	    
    @Before
    public void setup() {
        jedis = RedisUtil.getJedisPool().getResource();
    }
    
	/**
     * Lpush 命令将一个或多个值插入到列表头部(最左边)。
     *        如果 key 不存在，一个空列表会被创建并执行 LPUSH 操作。 当 key 存在但不是列表类型时，返回一个错误
     * Rpush 命令用于将一个或多个值插入到列表的尾部(最右边)。 
     * lrange 是按范围取出第一个是key，第二个是起始位置，第三个是结束位置，jedis.llen获取长度 -1表示取得所有  
     **/
    @Test
    public void testAdd() {
    	System.out.println("=============增=============");
        jedis.lpush("java","1");  
        jedis.lpush("java","2");  
        jedis.lpush("java","3"); 
        System.out.println("所有元素-java："+jedis.lrange("java", 0, -1));
        jedis.rpush("j2ee","test1");  
        jedis.rpush("j2ee","test2");  
        jedis.rpush("j2ee","test3"); 
        System.out.println("所有元素-j2ee："+jedis.lrange("j2ee", 0, -1));
    }
    
    @Test
    public void testUpdate() {
    	System.out.println("=============改=============");
    	System.out.println("j2ee修改前---->"+jedis.lrange("j2ee", 0, -1));
        // 修改列表中指定下标的值 
        jedis.lset("j2ee", 0, "test0"); 
        System.out.println("j2ee修改后---->"+jedis.lrange("j2ee", 0, -1));
    }
	    
    @Test
    public void testFind() {
    	System.out.println("=============查=============");
    	System.out.println("所有元素-java："+jedis.lrange("java", 0, -1));
        // 数组长度 
        System.out.println("java的长度："+jedis.llen("java"));
    }
    
    /*
     * list中存字符串时必须指定参数为alpha，如果不使用SortingParams，而是直接使用sort("list")，
     * 会出现"ERR One or more scores can't be converted into double"
     * 
     * 排序之后返回元素的数量可以通过limit修饰符进行限制，修饰符接受offset和count两个参数。
     * 	 offset：指定要跳过的元素数量，即起始位置。count：指定跳过offset个指定的元素之后，要返回多少个对象。
     * sort命令默认排序对象为数字，当需要对字符串进行排序时，需要显式地在sort命令之后添加alpha修饰符。
     * ace返回键值从小到大排序的结果。      desc：返回键值从大到小排序的结果
     * lrange 子串：  start为元素下标，end也为元素下标；-1代表倒数一个元素，-2代表倒数第二个元素
     * lindex 获取列表指定下标的值 
     */
    @Test
    public void testOrder() {
    	System.out.println("=============排序=============");
        System.out.println("Java原始排序："+jedis.lrange("java", 0, -1));
        SortingParams sortingParameters = new SortingParams();
        sortingParameters.alpha();
        sortingParameters.limit(1, -1);
        sortingParameters.desc();
        System.out.println("返回排序后的结果："+jedis.sort("java",sortingParameters)); 
        System.out.println("返回排序后的结果-java："+jedis.sort("java"));
        System.out.println("子串-第二个开始到结束："+jedis.lrange("java", 1, -1));
        System.out.println("获取下标为1的元素："+jedis.lindex("java", 1)+"\n");
    }

    /**
     * lrem 根据参数 COUNT 的值，移除列表中与参数 VALUE 相等的元素 。
     *      count>0:从表头开始向表尾搜索，count<0:从表尾开始向表头搜索，count = 0:移除表中所有与 VALUE 相等的值
     * Ltrim 让列表只保留指定区间内的元素，不在指定区间之内的元素都将被删除。
     *       0 表示列表的第一个元素，以 1 表示列表的第二个元素    -1表示列表的最后一个元素， -2 表示列表的倒数第二个元素
     * lpop 命令删除，并返回保存列表在key的第一个元素  
     **/
    @Test
    public void testDelete(){  
        System.out.println("=============删=============");
        System.out.println("所有元素-j2ee："+jedis.lrange("j2ee", 0, -1));
        // 删除列表指定的值 ，第二个参数为删除的个数（有重复时），后add进去的值先被删，类似于出栈
        System.out.println("成功删除指定元素个数-java："+jedis.lrem("j2ee", 1, "test0")); 
        System.out.println("删除指定元素之后-java："+jedis.lrange("j2ee", 0, -1));
        // 删除区间以外的数据 
        System.out.println("删除下标0-1区间之外的元素："+jedis.ltrim("j2ee", 0, 1));
        System.out.println("删除指定区间之外元素后-java："+jedis.lrange("j2ee", 0, -1));
        // 列表元素出栈 
        System.out.println("出栈元素："+jedis.lpop("j2ee")); 
        System.out.println("元素出栈后-java："+jedis.lrange("j2ee", 0, -1));
        // 删除key 
        System.out.println("删除j2ee键值对："+jedis.del("j2ee"));  
    }      
}
