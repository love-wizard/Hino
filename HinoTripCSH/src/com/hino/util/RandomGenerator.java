package com.hino.util;

import java.util.Random;
import java.util.UUID;

public class RandomGenerator {
	
	static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ";
	static Random rnd = new Random();

	public static String randomString( int len ) 
	{
	  StringBuilder sb = new StringBuilder(len);
	  for( int i = 0; i < len; i++ ) 
	  {
		  sb.append(AB.charAt(rnd.nextInt(AB.length())));
	  }
	  return sb.toString();
	}

	public static String randomnNumberString(int length)
	{
		//i should less than certain length perfered 10
		double d = Math.random();
		//System.out.println(d);
		int id = (int)(Math.pow(10, length)*d);
		//System.out.println(id);
		return Integer.toString(id);
	}
	
	/*
	public static String getRandomStr(int length) {
        String uuid = UUID.randomUUID().toString();
        return uuid.substring((uuid.length()-length)<0?0:(uuid.length()-length));
    }
	*/
}
