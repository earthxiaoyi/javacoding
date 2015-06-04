package com.javacoding.jedis;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
/**
 * jedis连接池
 * @author Administrator
 *
 */
public class JedisPools {
	//读写锁
	private static final Lock lock = new ReentrantLock();
	private static JedisPool pool = null;
	/**
	 * 返回jedis连接池
	 * @return
	 */
	public static void initPool() {
		lock.lock();
		CglibProxy proxy = new CglibProxy();
		try {
			if (pool == null) {
				JedisPoolConfig config = new JedisPoolConfig();
				config.setMaxTotal(100);
				config.setMaxIdle(5);
				config.setMaxWaitMillis(1000 * 20);
				String host = "192.168.233.128";
				pool = new JedisPool(config, "192.168.233.128");
				//代理pool
				pool = proxy.getInstance(pool, new Class[]{GenericObjectPoolConfig.class,String.class}, new Object[]{config,host});
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			lock.unlock();
		}
	}
	/**
	 * 返回jedis实例
	 * @return
	 * @throws Exception 
	 */
	public static Jedis getJedis() {
		Jedis jedis = null;
		//获取jedis连接池
		if(pool==null){
			initPool();
		}
		//获取jedis实例
		jedis = pool.getResource();
		return jedis;
	}
	
	/**
	 * 回收jedis实例
	 * @param jedis
	 */
	public static void recoveryJedis(Jedis jedis){
		if(pool==null){
			initPool();
		}
		pool.returnResource(jedis);
	}
}
