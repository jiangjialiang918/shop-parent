package com.zs.controller;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zs.shop.common.util.AjaxResultUtil;
import com.zs.shop.order.dto.OrderDTO;
import com.zs.shop.order.service.IOrderService;

import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;

@Controller
@RequestMapping("/test")
public class TestController {
	
	@Autowired
	private IOrderService orderService;

	@Autowired
	private JedisPool jedisPool;
	
	@Autowired
	private JedisCluster jedisCluster;

	@RequestMapping("/dbTest/{id}")
	@ResponseBody
	public String dbTest(@PathVariable(value="id") long id){
		OrderDTO orderDTO = orderService.getByProductId(id);
		return AjaxResultUtil.dumpResult(orderDTO);
	}
	

	@RequestMapping("/dbTest1/{name}")
	public String jsonTest1(@PathVariable(value="name") String name){
		System.out.println(name);
		return name;
	}
	
	@RequestMapping("/dbTest1/views")
	public String jsonTest3(){
		return "/index/index1";
	}

	@RequestMapping("/dbTest2")
	public String jsonTest2(
			@RequestParam(value = "name")  
			String name){
	
		System.out.println(name);
		return name;
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public String add(){
		OrderDTO orderDTO1 = new OrderDTO();
		orderDTO1.setName("测试添加");
		boolean result1 = orderService.add(orderDTO1);
		boolean result2 = orderService.add(new OrderDTO());
		
		return AjaxResultUtil.dumpResult(result1);
	}
	
	/**
	 * jedis
	 * @author ZhangShuai
	 * @date 创建时间：2017年4月21日 上午10:26:55
	 * @return
	 */
	@RequestMapping("/test4")
	public String test4(){
		Jedis jedis = new Jedis("192.168.137.150",6379);
		jedis.set("jedisKey1", "jedisValue1");
		String result = jedis.get("jedisKey1");
		System.out.println(result);
		jedis.close();
		return "index";
	}
	
	/**
	 * jedisPool
	 * @author ZhangShuai
	 * @date 创建时间：2017年4月21日 上午10:26:41
	 */
	@RequestMapping("/test5")
	@ResponseBody
	public void test5(){
		//从连接池中获取jedis
		Jedis jedis = jedisPool.getResource();
		//设置String类型数据类型，单键值
		jedis.set("Key1", "Value1");
		//获取单键值
		jedis.get("Key1");
		//设置多键值
		jedis.mset("Key2", "Value2", "Key3", "Value3", "Key4", "Value4");
		//获取多键值
		jedis.mget("Key2","Key3");
		//取值并赋值,值会改变
		jedis.getSet("Key2", "V2");
		//删除,删除成功返回值为1
		jedis.del("Key2");
		//设置失效时间，单位为秒，默认永不失效
		jedis.expire("Key1", 1000);
		//查看失效时间,返回还有几秒失效。redis默认为永不失效，返回-1。当键不存在或者被删除时，返回-2
		jedis.ttl("Key1");
		//查看是否包含指定的Key,如果存在返回1，不存在返回0
		jedis.exists("Key1");
		//当存储的字符串为整数时，incr方法为该值递增，递增1
		jedis.incr("Key1");
		//incrBy方法为该值递增，递增3
		jedis.incrBy("Key1", 3);
		//decr方法为该值递减，递减1
		jedis.decr("Key1");
		//decrBy方法为该值递减，递减2
		jedis.decrBy("Key1",2);
		//user代表对象名，username代表属性名，第三个参数代表属性值
		jedis.hset("user", "username", "张三");
		//设置多个属性和属性值
		jedis.hmget("user", "username","张三","age","20","pwd", "123");
		//取值
		jedis.hmget("user","username","age");
		//获取所有字段
		jedis.hgetAll("user");
		//删除
		jedis.hdel("user", "username","age");
		//判断是否存在
		jedis.hexists("user", "username");
		//获取字段数量
		jedis.hlen("user");
		//list 
		jedis.rpush("items", "comment","age");
		//获取指定索引之间的元素 -1 代表最后一个元素
		jedis.lrange("lrange ", 0, 1);
		//从列表左边弹出一个元素
		jedis.lpop("items");
		//获取元素个数
		jedis.llen("items");
		
		//添加元素
		jedis.sadd("a", "1","2");
		//是否存在指定的元素
		jedis.sismember("a", "1");
		//获取集合个数
		jedis.scard("a");
		//重命名
		jedis.rename("a", "b");
		//获取key的数据类型
		jedis.type("a");
		
		String result = jedis.get("jedisKey1");
		System.out.println(result);
		jedis.close();
		jedisPool.close();
	}
	
	@RequestMapping("/test6")
	@ResponseBody
	public void test6(){
		//创建一连接，JedisCluster对象,在系统中是单例存在
		Set<HostAndPort> nodes = new HashSet<>();
		nodes.add(new HostAndPort("192.168.137.150", 7001));
		nodes.add(new HostAndPort("192.168.137.150", 7002));
		nodes.add(new HostAndPort("192.168.137.150", 7003));
		nodes.add(new HostAndPort("192.168.137.150", 7004));
		nodes.add(new HostAndPort("192.168.137.150", 7005));
		nodes.add(new HostAndPort("192.168.137.150", 7006));
		JedisCluster cluster = new JedisCluster(nodes);
		//执行JedisCluster对象中的方法，方法和redis一一对应。
		cluster.set("cluster-test", "my jedis cluster test");
		String result = cluster.get("cluster-test");
		System.out.println(result);
		//程序结束时需要关闭JedisCluster对象
		cluster.close();
	}

	@RequestMapping("/test7")
	@ResponseBody
	public void test7(@PathVariable(value="id") long id){
		jedisCluster.set("cluster", "clusterValue");
		jedisCluster.expire("cluster", 500);
		boolean exists = jedisCluster.exists("cluster");
		if(exists){
		String result =	jedisCluster.get("cluster");
		System.out.println(result);
		jedisCluster.ttl("cluster");
 		}
		
		jedisCluster.zadd("cluster", 90, "name");
		//定义一个字段/分数集合
		Map<String, Double> scoreMembers = new HashMap<String, Double>();
		scoreMembers.put("name", (double) 90);
		scoreMembers.put("age", (double) 89);
		scoreMembers.put("address", (double) 70);
		jedisCluster.zadd("cluster",scoreMembers);
		//获取指定集合中元素的分数
		jedisCluster.zscore("cluster", "name");
		//删除指定元素，若成功返回1，不成功或者元素不存在返回一个错误
		jedisCluster.zrem("cluster", "name");
		//获取分数区间的元素
		jedisCluster.zrange("cluster", 70, 99);
		
		//获取指定元素的排名
		jedisCluster.zrank("cluster", "name");
		
		
		OrderDTO orderDTO = orderService.getByProductId(id);
		jedisCluster.set(String.valueOf(orderDTO.getId()), 
				AjaxResultUtil.dumpResult(orderDTO));
		//存储字符串类型，并设置存活时间，单位秒
		jedisCluster.setex(String.valueOf(orderDTO.getId()), 
				100, AjaxResultUtil.dumpResult(orderDTO));
	}
	

	
	
}
