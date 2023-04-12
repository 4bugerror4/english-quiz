package com.bug.error.handler.util;

public class Script {
	
	public static String back(String msg) {
		
		StringBuffer sb = new StringBuffer();
		sb.append("<h1>" + msg +"</h1>");
		sb.append("<a href='/'>문제 추가 홈으로 이동</a>");
		
		return sb.toString();
	}

}
