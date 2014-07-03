package com.sxs.chengyupoemgame.main;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;

public class ChengyuGuanKaActivity extends Activity implements  OnClickListener{
	
	@ViewInject(R.id.tv_guanka_text)  private TextView tv_guanka_title;
	@ViewInject(R.id.rl_guanka_view)  private RelativeLayout rl_guanka_view;
	private DisplayMetrics dm;//屏幕分辨率容器
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guanka);
		ViewUtils.inject(this);//记得要注入
		initConment();//初始化组件
	}
	private void initConment(){
		Typeface tf = Typeface.createFromAsset(this.getAssets(),  
	              "fonts/mygame.ttf");  
		dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		rl_guanka_view.removeAllViews();
		tv_guanka_title.setTypeface(tf);
		tv_guanka_title.setText("第1波");
		int itemWd = dm.widthPixels / 4;

		int j = -1;
		for(int i = 0;i < 32;i++){
			
			Button btn = (Button)LayoutInflater.from(this).inflate(R.layout.activity_lock_on, null);
			btn.setId(i+1);
			btn.setOnClickListener(this); 
			btn.setTypeface(tf);
			btn.setTag((i+1)+"");
			btn.setText((i+1)+"");
			RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(itemWd, itemWd);
			 if (i%4 == 0) {
				 j++;
	        }
			layout.leftMargin = itemWd*(i%4);   //横坐标定位        
			layout.topMargin = itemWd*j;   //纵坐标定位       
//			btn.setLayoutParams(layout);
			rl_guanka_view.addView(btn,layout);
			
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	
	@OnClick(R.id.back_btn)
	public void backClick(View v){
		finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}

}
