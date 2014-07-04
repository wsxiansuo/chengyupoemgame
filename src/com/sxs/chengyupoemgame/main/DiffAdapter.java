package com.sxs.chengyupoemgame.main;

import org.taptwo.android.widget.TitleProvider;

import com.sxs.chengyupoemgame.data.MapStringUtil;
import com.sxs.chengyupoemgame.data.UserDataModel;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class DiffAdapter extends BaseAdapter implements TitleProvider {

    private int VIEW_MAX_COUNT = 20;
	private String[] names;
    private String[][] list;	
    /**
	 * @return the list
	 */
	public String[][] getList() {
		return list;
	}

	/**
	 * @param list the list to set
	 */
	public void setList(String[][] list) {
		this.list = list;
		names = new String[list.length];
		VIEW_MAX_COUNT = list.length;
		for(int i =0;i < list.length;i++){
			names[i] = "第"+(i+1)+"波";
		}
		
	}

	private LayoutInflater mInflater;
    private Context mContext;
	private DisplayMetrics dm;//屏幕分辨率容器
    public DiffAdapter(Context context) {
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mContext = context;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getViewTypeCount() {
        return VIEW_MAX_COUNT;
    }

    @Override
    public int getCount() {
        return names.length;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        int view = getItemViewType(position);
        if (convertView == null) {
           
           convertView = mInflater.inflate(R.layout.activity_list_view, null);
           Typeface tf = Typeface.createFromAsset(mContext.getAssets(),  
	              "fonts/mygame.ttf");  
            dm =mContext.getResources().getDisplayMetrics();  
            
            RelativeLayout rl_guanka_view = (RelativeLayout) convertView.findViewById(R.id.rl_guanka_view);
    		rl_guanka_view.removeAllViews();
    		int itemWd = dm.widthPixels / 4;
    		int pCount = position * 100;
    		int j = -1;
    		Log.i("Postion:", position + " -- " +pCount);
    		for(int i = 0;i < 32;i++){
    			Boolean isLocked = isLock(position,i);
    			Button btn = (Button) mInflater.inflate(isLocked?R.layout.activity_lock_off:R.layout.activity_lock_on, null);
    			btn.setId(i + pCount);
    			btn.setOnClickListener(btnClickListener); 
    			btn.setTypeface(tf);
    			btn.setTag(position+"-" +i);
    			btn.setText(isLocked ? "" : ((i+1)+""));
    			RelativeLayout.LayoutParams layout = new RelativeLayout.LayoutParams(itemWd, itemWd);
    			 if (i%4 == 0) {
    				 j++;
    	        }
    			layout.leftMargin = itemWd*(i%4);   //横坐标定位        
    			layout.topMargin = itemWd*j;   //纵坐标定位       
//    			btn.setLayoutParams(layout);
    			rl_guanka_view.addView(btn,layout);
    		}
              
        }
        return convertView;
    }
    private Boolean isLock(int x , int y){
    	Boolean reslut = false;
    	if(x > UserDataModel.instance().maxPointX){
    		reslut = true;
    	}else if(x == UserDataModel.instance().maxPointX && y > UserDataModel.instance().maxPointY){
    		reslut = true;
    	}
    	return reslut;
    }
    
    private OnClickListener btnClickListener = new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			if(MapStringUtil.compareTwoLevel(v.getTag().toString(), UserDataModel.instance().getMaxLevel())){
				Toast.makeText(mContext, "当前关卡未开启", Toast.LENGTH_SHORT).show();
			}else
			{
				UserDataModel.instance().setLevel(v.getTag().toString());
				Intent intent = new Intent();
			    intent.setClass(mContext, GameAnswerActivity.class);
			    Bundle bundle = new Bundle();
			    bundle.putString("uid", "100");
			    intent.putExtras(bundle);
			    ((Activity) mContext).startActivityForResult(intent,0);
			}
			
		}
	};


    /* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	public String getTitle(int position) {
		return names[position];
	}

}
