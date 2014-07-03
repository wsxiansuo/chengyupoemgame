package com.sxs.chengyupoemgame.main;

import org.taptwo.android.widget.TitleProvider;

import android.content.Context;
import android.graphics.Typeface;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;

public class DiffAdapter extends BaseAdapter implements TitleProvider {

        private static final int VIEW_MAX_COUNT = 20;
    	private final String[] names = {"第1波","第2波","第3波","第4波","第5波","第6波","第7波","第8波","第9波","第10波","第11波","第12波","第13波","第14波","第15波","第16波","第17波","第18波","第19波","第20波"};

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
        return 20;
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
    			Button btn = (Button) mInflater.inflate(R.layout.activity_lock_on, null);
    			btn.setId(i+1 + pCount);
//    			btn.setOnClickListener(); 
    			btn.setTypeface(tf);
    			btn.setTag((pCount+i+1)+"");
    			btn.setText((i+1)+"");
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



    /* (non-Javadoc)
	 * @see org.taptwo.android.widget.TitleProvider#getTitle(int)
	 */
	public String getTitle(int position) {
		return names[position];
	}

}
