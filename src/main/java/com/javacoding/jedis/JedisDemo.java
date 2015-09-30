package com.javacoding.jedis;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Test;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.SortingParams;

/**
 * 使用redis java client 操作 redis
 * 
 * redis包含的数据类型: int、String、lists、 sets、hashes、sorted sets
 * 
 * @author JM
 */
public class JedisDemo {

	private Logger log = Logger.getLogger(JedisDemo.class);
	Jedis jedis = JedisPools.getJedis();

	@Test
	public void redisTest() {

		jedis.set("a", "nimei");
		String value = jedis.get("a");
		log.info(value);
	}

	/**
	 * redis 操作String 类型数据示例
	 * 
	 * @throws Exception
	 */
	@Test
	public void testString() throws Exception {

		// 存放string
		jedis.set("sex", "1");
		System.out.println(jedis.get("sex"));

		// 覆盖数据,如果设置key相同的数据,后设置的会覆盖之前设计的数据
		jedis.set("bookName", "java编程思想");
		jedis.set("bookName", "设计模式");
		System.out.println(jedis.get("bookName"));

		// 若name不存在 则保存
		jedis.setnx("name", "小明");
		jedis.setnx("name", "小黑");
		System.out.println(jedis.get("name"));

		// 追加数据
		jedis.append("remark", "啊!小明");
		jedis.append("remark", ",你的书掉地上了!");
		System.out.println(jedis.get("remark"));

		// 设置key的有效期
		jedis.setex("user", 10, "张三,年龄10岁");
		System.out.println(jedis.get("user"));
		// Thread.sleep(1000*10);
		System.out.println(jedis.get("user"));
		// Thread.sleep(1000*5);
		System.out.println(jedis.get("user"));

		// 获取并更改数据
		jedis.set("foo", "18元");
		// getset 设置并返回旧值
		System.out.println(jedis.getSet("foo", "19元"));
		System.out.println(jedis.get("foo"));

		// 设置多个值,返回数组
		jedis.mset("key1", "value1", "key2", "value2");
		System.out.println(jedis.mget("key1", "key2"));
		// 清空数据
		jedis.flushDB();
	}

	@Test
	public void testProxy(){
		Jedis jedis1 = JedisPools.getJedis();
		jedis1.set("a", "b");
		jedis1.set("c", "c");
	}
	
	/**
	 * redis list 数据类型示例
	 * 
	 * @throws Exception
	 */
	@Test
	public void testList() throws Exception {

		// 首先先删除掉数据
		jedis.del("message");
		jedis.rpush("message", "你妹啊");
		jedis.rpush("message", "把我的袋子给我");
		jedis.rpush("message", "不给我就干掉你");

		// jedis.llen 获取数组长度 -1 表示获取所有数据
		List<String> messages = jedis.lrange("message", 0,
				jedis.llen("message"));

		System.out.println(messages.toString());

		// 清空数据
		jedis.flushDB();
		SortingParams sortingParameters = new SortingParams();
		sortingParameters.alpha();
		sortingParameters.desc();

		jedis.lpush("lists", "1ArrayList");
		jedis.lpush("lists", "5linkedList");
		jedis.lpush("lists", "3VecotorList");
		// //数组长度
		// System.out.println(jedis.llen("lists"));
		// //排序
		// System.out.println(jedis.sort("lists",sortingParameters));
		// //按范围取出
		// System.out.println(jedis.lrange("lists", 0, 1));
		// //修改列表单个值
		// jedis.lset("lists", 0, "你妹啊");
		// System.out.println(jedis.lrange("lists", 0, 3));
		//
		// //删除列表指定下标值
		// System.out.println(jedis.lrem("lists", 1, "1ArrayList"));
		// System.out.println(jedis.lrange("lists", 0, -1));

		// 列表出栈
		// 这个可以做消息队列
		for (int i = 0; i < 10; i++) {
			System.out.println(jedis.lpop("lists"));
		}
	}

	/**
	 * redis seted数据类型测试
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSeted() throws Exception {

		// SortingParams sortingParameters = new SortingParams();
		// sortingParameters.alpha();
		// sortingParameters.desc();
		// 清空数据
		jedis.flushDB();
		// 设置数据
		for (int i = 1; i < 1 * 10; i++) {
			jedis.sadd("mysets", i + "");
		}
		// jedis.sort("mysets",sortingParameters);
		// 获取数据
		System.out.println(jedis.smembers("mysets"));
		// 返回元素个数
		System.out.println(jedis.scard("mysets"));
		// 判断是否是myset集合的元素
		System.out.println(jedis.sismember("mysets", "11100"));
		// 删除指定元素
		System.out.println(jedis.srem("mysets", "2"));

		System.out.println(jedis.smembers("mysets"));

		for (int i = 5; i < 1 * 10; i++) {
			jedis.sadd("mysets1", i + "");
		}

		// 交集
		System.out.println(jedis.sinter("mysets", "mysets1"));
		// 并集
		System.out.println(jedis.sunion("mysets", "mysets1"));
		// 差集
		System.out.println(jedis.sdiff("mysets", "mysets1"));
	}

	/**
	 * redis sortset 数据结构使用示例
	 * 
	 * @throws Exception
	 */
	@Test
	public void testSortedSet() throws Exception {
		jedis.flushDB();

		for (int i = 0; i < 100; i++) {
			jedis.zadd("set", i, "book" + i);
		}
		// 获取集合元素个数
		System.out.println(jedis.zcard("set"));
		// 获取所有元素
		System.out.println(jedis.zrange("set", 0, -1));
		// 查询score范围的数据
		System.out.println(jedis.zrangeByScore("set", 50, 60));

		// 从小到大排序,取前十个
		System.out.println(jedis.zrange("set", 0, 10));
		// 从大到小排序,取前十个
		System.out.println(jedis.zrevrange("set", 0, 10));
	}

	/**
	 * redis hash 使用示例
	 * 
	 * @throws Exception
	 */
	@Test
	public void testHash() throws Exception {
		jedis.flushDB();
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "张庆阳");
		map.put("age", "18");
		map.put("sex", "男");
		map.put("id", "00");
		// 插入hash数据
		jedis.hmset("hash", map);
		System.out.println(jedis.hmget("hash", "name"));
		//返回所有的key
		System.out.println(jedis.hkeys("hash"));
		//返回所有的value
		System.out.println(jedis.hvals("hash"));
		//查询是否存在hash
		System.out.println(jedis.exists("hash1"));
		
		//迭代hash
		Iterator<String> it = jedis.hkeys("hash").iterator();
		while(it.hasNext()){
			String key = it.next();
			System.out.println(key+" : "+jedis.hmget("hash", key));
		}
		//获取整个hash
		System.out.println("all:"+jedis.hgetAll("hash"));
	}
	
	
	/**
	 * 分页查询
	 * @throws Exception
	 */
	@Test
	public void testPage() throws Exception{
		jedis.flushDB();
		for(int i=1;i<=100;i++){
			jedis.lpush("list", "java编程思想"+i);
		}
		
		SortingParams params = new SortingParams();
		params.alpha();
		params.asc();
		params.limit(10, 20);
		System.out.println(jedis.sort("list",params));
	}
	
	@Test
	public void testSort() throws Exception{
		jedis.flushDB();
		jedis.sadd("tom:friend:list", "123"); // tom的好友列表  
        jedis.sadd("tom:friend:list", "456");  
        jedis.sadd("tom:friend:list", "789");  
        jedis.sadd("tom:friend:list", "101");  
  
        jedis.set("score:uid:123", "1000"); // 好友对应的成绩  
        jedis.set("score:uid:456", "6000");  
        jedis.set("score:uid:789", "100");  
        jedis.set("score:uid:101", "5999");  
  
        jedis.set("uid:123", "{'uid':123,'name':'lucy'}"); // 好友的详细信息  
        jedis.set("uid:456", "{'uid':456,'name':'jack'}");  
        jedis.set("uid:789", "{'uid':789,'name':'jay'}");  
        jedis.set("uid:101", "{'uid':101,'name':'jolin'}");  
  
        SortingParams params= new SortingParams();  
        // sortingParameters.desc();  
        params.get("#");
        // GET 还有一个特殊的规则—— "GET #"  
        // ，用于获取被排序对象(我们这里的例子是 user_id )的当前元素。  
        params.by("score:uid:*");  
        jedis.sort("tom:friend:list", params, "tom:friend:list");  
        List<String> result = jedis.lrange("tom:friend:list", 0, -1); 
        System.out.println(result);
	}
	
	
	/**
	 * redis 实际 案例之一
	 */
	@Test
	public void actual() throws Exception{
		jedis.flushAll();
		String title = "太阳能是绿色能源";
		String url="http://www.baidu.com/news/123123";
		//通过ad:adInfo 获得一个自增的id
		Long l = jedis.incr("ad:adInfo:next.id");
		jedis.set("ad:adInfo:"+l + ":title",title);
		jedis.set("ad:adInfo:"+l+":url", url);
		jedis.lpush("ad:adInfo", String.valueOf(l));
		
		String resultTitle = jedis.get("ad:adInfo:"+l+":title");
		String resultUrl = jedis.get("ad:adInfo:"+l+":url");
		System.out.println(resultTitle+"  "+resultUrl);
		
		System.out.println(jedis.lrange("ad:adInfo", 0, -1));
	}
}