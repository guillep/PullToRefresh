package com.pullToRefresh;

import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.pull.R;

public class Main extends ListActivity {
	private PullToRefreshComponent pullToRefresh;
	private ArrayAdapter<String> adapter;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.main);
		ViewGroup upperButton = (ViewGroup) this
				.findViewById(R.id.refresh_upper_button);
		ViewGroup lowerButton = (ViewGroup) this
				.findViewById(R.id.refresh_lower_button);
		this.adapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1);
		this.getListView().setAdapter(this.adapter);

		this.pullToRefresh = new PullToRefreshComponent(upperButton,
				lowerButton, this.getListView(), new Handler());
		this.pullToRefresh.setOnPullDownRefreshAction(new RefreshListener() {

			@Override
			public void refreshFinished() {
				Main.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Main.this.adapter.add("itemm from pull down");
					}
				});
			}

			@Override
			public void doRefresh() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});

		this.pullToRefresh.setOnPullUpRefreshAction(new RefreshListener() {

			@Override
			public void refreshFinished() {
				Main.this.runOnUiThread(new Runnable() {

					@Override
					public void run() {
						Main.this.adapter.add("itemm from the up");
					}
				});
			}

			@Override
			public void doRefresh() {
				try {
					Thread.sleep(1500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}