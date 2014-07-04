package com.sxs.chengyupoemgame.main;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sxs.chengyupoemgame.data.DBManager;
import com.sxs.chengyupoemgame.data.QuestionVO;
import com.sxs.chengyupoemgame.data.UserDataModel;

public class GameAnswerActivity extends Activity implements  OnClickListener{
	
	@ViewInject(R.id.tv_game_title)  private TextView tv_game_title;
	@ViewInject(R.id.tv_game_score)  private TextView tv_game_score;
	@ViewInject(R.id.rl_question_view)  private RelativeLayout rl_question_view;
	@ViewInject(R.id.rl_answer_layout)  private RelativeLayout rl_answer_layout;
	@ViewInject(R.id.ll_answer_view)  private LinearLayout ll_answer_view;
	
	private DisplayMetrics dm;//屏幕分辨率容器
	private UserDataModel model;
	private Typeface tf;
	private DBManager mgr; 
	private QuestionVO item;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_answer_view);
		ViewUtils.inject(this);//记得要注入
		mgr = new DBManager(GameAnswerActivity.this);
		model = UserDataModel.instance();
		tf = Typeface.createFromAsset(this.getAssets(),  
	              "fonts/mygame.ttf");  
		model.setContext(GameAnswerActivity.this);
		initConment();//初始化组件
	}
	private void initConment(){
		updateTitle();
		tv_game_score.setTypeface(tf);
		String id = model.getIdByLevel();
		if(id != null && id.length() > 0){
			item = mgr.queryQuestionById(id);
			initQuestion();
		}
		dm = new DisplayMetrics();
		this.getWindowManager().getDefaultDisplay().getMetrics(dm);
		
	}
	private void initQuestion(){
		if(item != null){
			ll_answer_view.removeAllViews();
			int asId = 1000;
			int qsId = 2000;
			int btnWd = (rl_answer_layout.getWidth() - 200)/4;
			for(int i = 0;i<item.answer.length();i++){
				Button btn = (Button)LayoutInflater.from(this).inflate(R.layout.layout_answer_btn, null);
				btn.setId(asId + i);
				btn.setOnClickListener(this);  
				btn.setTag(item.answer.substring(i, i+1));
				btn.setTextColor(Color.YELLOW);
			
				LinearLayout.LayoutParams layout = new LinearLayout.LayoutParams(  
						btnWd, btnWd);  
				layout.setMargins(10, 0, 10, 0);  
				btn.setLayoutParams(layout);
				ll_answer_view.addView(btn);
			}
			
			
		}
	}
	
	private void updateTitle(){
		String [] arr = model.getLevel().split("-");
		tv_game_title.setText((Integer.parseInt(arr[0]) + 1)+"-"+(Integer.parseInt(arr[1]) + 1));
	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
	
	
	@OnClick(R.id.back_btn)
	public void backClick(View v){
		setResult(RESULT_OK,(new Intent()).setAction("100"));   
		finish();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
	}
	 @Override  
    protected void onDestroy() {  
        super.onDestroy();  
        //应用的最后一个Activity关闭时应释放DB  
        mgr.closeDB();  
    }
}