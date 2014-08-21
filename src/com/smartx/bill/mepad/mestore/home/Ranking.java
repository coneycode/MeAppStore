package com.smartx.bill.mepad.mestore.home;

import org.json.JSONArray;

import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridView;
import android.widget.Toast;

import com.loopj.android.http.JsonHttpResponseHandler;
import com.smartx.bill.mepad.mestore.R;
import com.smartx.bill.mepad.mestore.adapter.RankingGridviewAdapter;
import com.smartx.bill.mepad.mestore.listener.ItemClickListener;
import com.smartx.bill.mepad.mestore.listener.MyGestureListener;
import com.smartx.bill.mepad.mestore.matadata.IOStreamDatas;
import com.smartx.bill.mepad.mestore.myview.MyGridView;
import com.smartx.bill.mepad.mestore.uimgloader.AbsListViewBaseActivity;
import com.smartx.bill.mepad.mestore.util.HttpUtil;

public class Ranking extends AbsListViewBaseActivity {

	// private MyGridView mRankingGridView;
	private RankingGridviewAdapter mRankingAdapter;
	private JSONArray jsonArrayTop;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_ranking);
		initCommonDatas(this,this,savedInstanceState);
		HttpUtil.get(getDataUrl(IOStreamDatas.APP_DATA),
				getParams(null, null, null, null, null, null),
				new JsonHttpResponseHandler() {

					@Override
					public void onSuccess(JSONArray response) {
						initDatas(response);
					}

					@Override
					public void onFailure(Throwable e, JSONArray errorResponse) {
					}
				});
		// initDatas();
		// initGridView();
	}

	private void initDatas(JSONArray response) {
		jsonArrayTop = response;
		mRankingAdapter = new RankingGridviewAdapter(this, jsonArrayTop,
				imageLoader);
		// mRankingGridView = (MyGridView) findViewById(R.id.ranking_gridView);
		myGridView = (GridView) findViewById(R.id.ranking_gridView);
		((GridView) myGridView).setNumColumns(2);
		myGridView.setAdapter(mRankingAdapter);
		myGridView.setOnItemClickListener(new ItemClickListener(mContext,
				mActivity, savedInstanceState, response));
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
		int imageCol = 3;
		try {

			super.onConfigurationChanged(newConfig);
			// 如果屏幕是竖屏，则显示3列，如果是横屏，则显示4列
			if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
				imageCol = 4;
				Toast.makeText(Ranking.this, "现在是横屏", Toast.LENGTH_SHORT)
						.show();
			} else if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
				imageCol = 3;
			}
			((GridView) myGridView).setNumColumns(imageCol);
			myGridView.setAdapter(mRankingAdapter);
			// ia.notifyDataSetChanged();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
