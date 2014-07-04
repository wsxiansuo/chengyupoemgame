package com.sxs.chengyupoemgame.main;

import org.taptwo.android.widget.TitleFlowIndicator;
import org.taptwo.android.widget.ViewFlow;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.sxs.chengyupoemgame.data.DBManager;
import com.sxs.chengyupoemgame.data.UserDataModel;

public class DiffViewFlowActivity extends Activity implements  OnClickListener{
	
	private ViewFlow viewFlow;
	private DBManager mgr;  
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_flow);
        ViewUtils.inject(this);//记得要注入
        mgr = new DBManager(DiffViewFlowActivity.this);
       
		initConment();//初始化组件
    }
    
    private void initConment(){
    	 UserDataModel.instance().setContext(DiffViewFlowActivity.this);
 		 viewFlow = (ViewFlow) findViewById(R.id.viewflow);
         DiffAdapter adapter = new DiffAdapter(this);
         UserDataModel.instance().listData = mgr.queryGuankaArray();
         adapter.setList(UserDataModel.instance().listData);
         viewFlow.setAdapter(adapter);
 		 TitleFlowIndicator indicator = (TitleFlowIndicator) findViewById(R.id.viewflowindic);
 		 indicator.setTitleProvider(adapter);
 		 viewFlow.setFlowIndicator(indicator);
 		 viewFlow.setSelection(UserDataModel.instance().maxPointX);//设置默认选中项
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
	   protected void onActivityResult(int requestCode, int resultCode, Intent data) 
	   {   
		    if(requestCode == 0)
		    {   
		     if(resultCode == RESULT_OK)
		      {   
		    	 initConment();//初始化组件 主要是为了防止 关卡对应不上的问题 
		      } 
		    }   
	  }  
	   @Override  
	    protected void onDestroy() {  
	        super.onDestroy();  
	        //应用的最后一个Activity关闭时应释放DB  
	        mgr.closeDB();  
	    }
}
