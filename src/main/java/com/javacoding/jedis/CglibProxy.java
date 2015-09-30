package com.javacoding.jedis;

import java.lang.reflect.Method;

import redis.clients.jedis.Jedis;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class CglibProxy implements MethodInterceptor{

	
	public <T> T getInstance(T target,Class[] args,Object[] argsValue){
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(target.getClass());
		hancer.setCallback(this);
		return (T)hancer.create(args,argsValue);
	}
	
	public <T> T getInstance(T target){
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(target.getClass());
		hancer.setCallback(this);
		return (T)hancer.create();
	}
	
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Jedis result=null;
		System.out.println("执行了!");
		try {
			result = (Jedis)proxy.invokeSuper(obj, args);
			if(result!=null){
				//回收连接
				JedisPools.recoveryJedis(result);
				//System.out.println("回收了");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
}