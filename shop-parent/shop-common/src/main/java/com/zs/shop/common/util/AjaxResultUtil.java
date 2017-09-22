package com.zs.shop.common.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

/**
 * Controller返回解决工具类
 *
 */
public class AjaxResultUtil {
		
	/**
	 * 返回操作的结果json
	 * @param result 是否操作成功
	 * @param tip 提示信息
	 * @return
	 */
	public static String assembleResult(boolean result,String tip){
		JSONObject object = new JSONObject();
		object.put("result", result);
		object.put("tip", tip);
		return object.toJSONString();
	}
	
	/**
	 * 返回json数据
	 * @param result
	 * @return
	 */
	public static String dumpResult(Object result){
		return JSON.toJSONString(result);
	}
	
	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(1, "a");
		map.put(2, "b");
		
		System.out.println(JSON.toJSONString(map, JSONUtil.getSerializerFeatures()));
	}
	
	/**
	 * 返回操作的结果json
	 * @param result 是否操作成功
	 * @param tip 提示信息
	 * @return
	 */
	public static String assembleResult(int ecode,String msg){
		JSONObject object = new JSONObject();
		object.put("ecode", ecode);
		object.put("msg", msg);
		return JSON.toJSONString(object, JSONUtil.getSerializerFeatures());
	}

	
	
}
