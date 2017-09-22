package com.zs.interceptors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;


public class PCInterceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		String attr = request.getRequestURI().toString();
		String targetIdStr = attr.substring(attr.lastIndexOf("/")+1,attr.length());
		long targetId = 0;
		try{
			if(StringUtils.isEmpty(targetIdStr)) {
				targetId = Long.parseLong(targetIdStr);
			}
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
		System.out.println("----");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
