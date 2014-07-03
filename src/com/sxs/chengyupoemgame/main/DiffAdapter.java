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
    	private final String[] names = {"��1��","��2��","��3��","��4��","��5��","��6��","��7��","��8��","��9��","��10��","��11��","��12��","��13��","��14��","��15��","��16��","��17��","��18��","��19��","��20��"};

    private LayoutInflater mInflater;
    private Context mContext;
	private DisplayMetrics dm;//��Ļ�ֱ�������
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
    			layout.leftMargin = itemWd*(i%4);   //�����궨λ        
    			layout.topMargin = itemWd*j;   //�����궨λ       
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
