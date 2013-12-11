package com.charon.scrollingtabs;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.Button;
import android.widget.ImageView;

import com.charon.scrollingtabs.inter.TabAdapter;
import com.charon.scrollingtabs.view.ScrollingTabsView;

public class MainActivity extends Activity {

	private ViewPager mViewPager;

	private ScrollingTabsView mScrollingTabsView;

	private List<View> views = new ArrayList<View>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findView();
		initView();
	}

	private void findView() {
		mViewPager = (ViewPager) findViewById(R.id.vp);
		mScrollingTabsView = (ScrollingTabsView) findViewById(R.id.stv);
	}

	private void initView() {
		for (int i = 0; i < 6; i++) {
			Button button = new Button(this);
			button.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT,
					LayoutParams.MATCH_PARENT));
			button.setText("ViewPager:" + i);
			views.add(button);
		}
		mViewPager.setAdapter(new PagerAdapter() {

			public int getCount() {
				return views.size();
			}

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				return arg0 == arg1;
			}

			@Override
			public void destroyItem(ViewGroup container, int position,
					Object object) {
				container.removeView(views.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				container.addView(views.get(position));
				return views.get(position);
			}
		});
		mScrollingTabsView.setViewPager(mViewPager);
		mScrollingTabsView.setTabAdapter(new TabAdapter() {

			@Override
			public View getView(int position) {
				LayoutInflater inflater = MainActivity.this.getLayoutInflater();
				final Button tab = (Button) inflater.inflate(R.layout.tabs,
						null);
				final String[] mTitles = new String[] { "Button0", "Button1",
						"Button2", "Button3", "Button4", "Button5", };

				if (position < mTitles.length)
					tab.setText(mTitles[position]);

				return tab;
			}

			@Override
			public View getSeparator() {
				View view = new ImageView(MainActivity.this);
				view.setLayoutParams(new LayoutParams(1,
						LayoutParams.MATCH_PARENT));
				view.setBackgroundColor(Color.RED);
				return view;
			}
		});

	}

}
