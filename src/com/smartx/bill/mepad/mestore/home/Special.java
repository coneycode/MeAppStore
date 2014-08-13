package com.smartx.bill.mepad.mestore.home;

import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.Toast;

import com.smartx.bill.mepad.mestore.R;
import com.smartx.bill.mepad.mestore.adapter.SpecialGridviewAdapter;
import com.smartx.bill.mepad.mestore.iostream.DownLoadDatas;
import com.smartx.bill.mepad.mestore.matadata.IOStreamDatas;
import com.smartx.bill.mepad.mestore.uimgloader.AbsListViewBaseActivity;

public class Special extends AbsListViewBaseActivity {

//	private GridView myGridView;
	private SpecialGridviewAdapter mSpecialAdapter;
	private JSONArray jsonArraySpecial;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_special);
		initDatas();
		initGridView();
	}

	private void initDatas() {
		try {
			jsonArraySpecial = DownLoadDatas.getDatasFromServer(null, null, null,
					null, IOStreamDatas.SPECIAL_DATA);
		} catch (IOException | JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mSpecialAdapter = new SpecialGridviewAdapter(this, jsonArraySpecial,imageLoader);
		myGridView = (GridView) findViewById(R.id.special_gridView);
	}

	private void initGridView() {
		((GridView) myGridView).setNumColumns(1);
		myGridView.setAdapter(mSpecialAdapter);
		myGridView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {
			}
		});
	}

	@Override
	protected void onPause() {
		super.onPause();
	}

	/**
	 * 屏幕切换时进行处理 如果屏幕是竖屏，则显示3列，如果是横屏，则显示4列
	 */
	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		int imageCol = 1;
		try {

			super.onConfigurationChanged(newConfig);
			// 如果屏幕是竖屏，则显示3列，如果是横屏，则显示4列
			if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				imageCol = 1;
				Toast.makeText(Special.this, "现在是横屏", Toast.LENGTH_SHORT)
						.show();
			} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				imageCol = 1;
			}
			((GridView) myGridView).setNumColumns(imageCol);
			myGridView.setAdapter(mSpecialAdapter);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
