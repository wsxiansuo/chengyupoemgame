package com.sxs.chengyupoemgame.main;

import java.util.List;
import java.util.Map;

import net.doujin.android.DJManager;
import net.doujin.android.djp.DJPushManager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.UserManager;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sxs.chengyupoemgame.data.DBManager;
import com.sxs.chengyupoemgame.data.UserDataModel;
import com.umeng.analytics.MobclickAgent;
import com.umeng.update.UmengUpdateAgent;

public class MainActivity extends Activity {

	@ViewInject(R.id.main_start_btn) 		private Button startBtn; 
	@ViewInject(R.id.mian_music_img) 		private ImageView musicBtn; 
	
	
	private DBManager mgr;  
	private int dbVersion = 1;	
	
	private Boolean isUseMusic = true;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		UmengUpdateAgent.update(this);
		setContentView(R.layout.activity_main);
		ViewUtils.inject(this);
		new Handler().postDelayed(new Runnable(){
            @Override
            public void run(){
            	mgr = new DBManager(MainActivity.this);
            	if(mgr.getDbVersion() < dbVersion){
            		mgr.upDatabase();  
            		Log.i("update", "数据库执行了更新---------------");
            	}
            	mgr.queryGuankaArray();
            }
        }, 1000);
		UserDataModel.instance().setContext(MainActivity.this);
		UserDataModel.instance().loadMaxLevel();
//		DJManager.getInstance(MainActivity.this).init("9a64736158fd3a15b6875823475711df", false);
//		DJPushManager.startDoujinPush(this);
	}
	
	
	
	@OnClick(R.id.main_start_btn)
	public void onStartClick(View v){
		Intent intent = new Intent();
		intent.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
		intent.setClass(MainActivity.this, DiffViewFlowActivity.class);
		startActivity(intent);
	}
	@OnClick(R.id.mian_music_img)
	public void onMusicClick(View v){
		isUseMusic = !isUseMusic;
		musicBtn.setImageResource(isUseMusic?R.drawable.shengyin_on:R.drawable.shengyin_off);
	}

	public void onResume() {
    	super.onResume();
    	MobclickAgent.onResume(this);
    	}
	public void onPause() {
		super.onPause();
		MobclickAgent.onPause(this);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	 @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //应用的最后一个Activity关闭时应释放DB  
        mgr.closeDB();  
    }

}
