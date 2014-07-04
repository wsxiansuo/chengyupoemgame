package com.sxs.chengyupoemgame.data;

import android.content.Context;
import android.content.SharedPreferences;


public class UserDataModel {
	
	public String[][] listData;
	public int maxPointX = 0;
	public int maxPointY = 0;
	
	private String maxLevel;
	/**
	 * @return the maxLevel
	 */
	public String getMaxLevel() {
		return maxLevel;
	}
	/**
	 * @param maxLevel the maxLevel to set
	 */
	public void setMaxLevel(String maxLevel) {
		this.maxLevel = maxLevel;
		String [] arr = maxLevel.split("-");
		
		if(arr != null && arr.length == 2){
			this.maxPointX = Integer.parseInt(arr[0]);
			this.maxPointY = Integer.parseInt(arr[1]);
		}
		
	}
	private String level;
	public Context context;
	/**
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}
	/**
	 * @return the level
	 */
	public String getLevel() {
		return level;
	}
	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
		if(MapStringUtil.compareTwoLevel(level, maxLevel)){
			updateMaxLevel(level);
			setMaxLevel(level);
		}
	}
	public Boolean isMusic;
	private static UserDataModel _instance;
	public static UserDataModel instance()
	{
		if(_instance == null)
		{
			_instance = new UserDataModel();
		}
		return _instance;
	}
	public void updateMaxLevel(String lev)
	{
		SharedPreferences settings = context.getSharedPreferences("setting_game_infos",0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString("maxLevel", lev);
        editor.commit();
	}
	public void loadMaxLevel()
	{
        SharedPreferences settings = context.getSharedPreferences("setting_game_infos",0);
        setMaxLevel(settings.getString("maxLevel", "0-0"));
	}
	
	public String getIdByLevel(){
		String [] arr = level.split("-");
		try{
			return listData[Integer.parseInt(arr[0])][Integer.parseInt(arr[1])];
		}
		catch(Exception e){
		}
		return "";
	}
	
	public String getNextLevelId(){
		String [] arr = level.split("-");
		int x = Integer.parseInt(arr[0]);
		int y = Integer.parseInt(arr[1]);
		if(y < 31){
			y++;
		}
		else{
			if(x >= listData.length - 1){
				return "over";
			}
			else{
				x++;
				y = 0;
			}
		}
		setLevel(x+"-"+y);
		return getIdByLevel();
	}
	
}
