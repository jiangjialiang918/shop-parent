package com.zs.shop.common.util;

public class TransformUtil {
	
	public static void main(String[] args) {
		System.out.println(countStr(138183819)); //1.38亿
		System.out.println(countStr(1000555)); //4

	}
	
	public static String countStr(int count){
		   String countStr = Integer.toString(count);
		   int remian = count%100000000;
		   int division = count/100000000;
		   //如果大于亿且千万位百万位都大于0，小数点保留两位，千万位百万位为0,只取亿位,千万位大于0百万位为0,小数点后保留一位
	       if(division > 0){
	    	   if(remian >= 10000000){
	    		   if(remian/1000000%10 > 0){
	    			   countStr = division + "." + remian/1000000 + "亿" ; 
	    		   }else{
	    			   countStr = division + "." + remian/10000000 + "亿" ; 
	    		   }
	    	   }else if(remian < 10000000){
	    		   if(remian/1000000%10 > 0){
	    			   countStr = division + ".0" +remian/1000000 + "亿" ; 
	    		   }else{
	    			   countStr = division + "亿" ;   
	    		   }
	    	   }
	        }
	       //数字过万不过亿,千位为0只取万位,否则保留一位,数字不过万保留原数据
	       if(count/10000 > 0 && division <= 0){
	    	   if(count%10000 >= 1000){
	    		   countStr = count/10000 + "." + count%10000/1000 + "W";
	    	   }else{
	    		   countStr = count/10000 + "W";
			   }
	       }
	   return countStr;
	}

}
