package com.sxs.chengyupoemgame.main;

import net.doujin.android.DJManager;
import net.doujin.android.djp.DJPushManager;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sxs.chengyupoemgame.data.DBManager;
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
            }
        }, 1000);
		DJManager.getInstance(MainActivity.this).init("73922e402a9e04d37aaa50f5c1823a76", false);
		DJPushManager.startDoujinPush(this);
	}
	@OnClick(R.id.main_start_btn)
	public void onStartClick(View v){
		
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
