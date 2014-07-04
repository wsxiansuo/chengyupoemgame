package com.sxs.chengyupoemgame.data;

public class MapStringUtil {
	
	public static String getTimeStr(long time)
	{
		long allmm = time / 1000;
		long mm = allmm % 60;
		return allmm/60 +":"+ (mm < 10 ? "0"+mm : mm);
	}
	
	public static Boolean compareTwoLevel(String level , String maxLevel){
		Boolean result = false;
		if(level != null && maxLevel != null){
			String [] arr1 = level.split("-");
			String [] arr2 = maxLevel.split("-");
			if(arr1.length == arr2.length && arr1.length == 2){
				if(Integer.parseInt(arr2[0]) < Integer.parseInt(arr1[0])){
					result = true;
				}else if(Integer.parseInt(arr2[0]) == Integer.parseInt(arr1[0])){
					if(Integer.parseInt(arr2[1]) < Integer.parseInt(arr1[1])){
						result = true;
					}
				}
			}
		}
		return result;
	}

}

