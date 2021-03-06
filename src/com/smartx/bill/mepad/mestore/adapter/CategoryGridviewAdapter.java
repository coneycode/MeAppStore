package com.smartx.bill.mepad.mestore.adapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.smartx.bill.mepad.mestore.R;
import com.smartx.bill.mepad.mestore.matadata.IOStreamDatas;

public class CategoryGridviewAdapter extends MyBaseAdapter {
	private Activity activity;
	private JSONArray categorysInfo;
	private ImageLoader imageLoader;
	private int[] studyImages = { R.drawable.study, R.drawable.story,
			R.drawable.manga, R.drawable.social, R.drawable.instrument,
			R.drawable.news, R.drawable.dance, R.drawable.sport };
	private int[] gameImages = { R.drawable.game1, R.drawable.game2,
			R.drawable.game3, R.drawable.game4, R.drawable.game5,
			R.drawable.game6, R.drawable.game7, R.drawable.game8,
			R.drawable.game9, R.drawable.game10, R.drawable.game11 };
	private int[] toolsImages = { R.drawable.system, R.drawable.tools };

	public CategoryGridviewAdapter(Activity activity, JSONArray categorysInfo,
			ImageLoader imageLoader) throws JSONException {
		super();
		this.categorysInfo = categorysInfo;
		this.imageLoader = imageLoader;
		this.activity = activity;
	}

	private String getItemDatas(String key, int position) {
		try {
			return this.getItem(position).getString(key);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return categorysInfo.length();
	}

	@Override
	public JSONObject getItem(int position) {
		// TODO Auto-generated method stub
		try {
			return categorysInfo.getJSONObject(position);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	public static class CategoryViewHolder {
		public ImageView imgViewFlag;
		public TextView txtViewTitle;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		CategoryViewHolder view;
		LayoutInflater inflator = activity.getLayoutInflater();

		if (convertView == null) {
			view = new CategoryViewHolder();
			convertView = inflator.inflate(R.layout.category_gridview_item,
					null);
			view.txtViewTitle = (TextView) convertView
					.findViewById(R.id.category_name);
			view.imgViewFlag = (ImageView) convertView
					.findViewById(R.id.category_pic);
			convertView.setTag(view);
		} else {
			view = (CategoryViewHolder) convertView.getTag();
		}
		view.txtViewTitle.setText(getItemDatas("name", position));
		if(getCount() == 8){
			view.imgViewFlag.setImageResource(studyImages[position]);
		}else if(getCount() == 11){
			view.imgViewFlag.setImageResource(gameImages[position]);
		}else if(getCount() == 2){
			view.imgViewFlag.setImageResource(toolsImages[position]);
		}else{
		imageLoader.displayImage(getImageUrl(getItemDatas("image", position)),
				view.imgViewFlag, options);
		}
		return convertView;
	}

}
