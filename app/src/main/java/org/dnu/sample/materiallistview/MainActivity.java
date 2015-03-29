package org.dnu.sample.materiallistview;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import android.widget.ProgressBar;


public class MainActivity extends ActionBarActivity
    implements Runnable {

    private ProfileListAdapter mProfileListAdapter;

    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final ListView lv = (ListView)findViewById(R.id.listView);
        mProfileListAdapter = new ProfileListAdapter(getApplicationContext());
        lv.setAdapter(mProfileListAdapter);
        lv.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    mProfileListAdapter.setOnScrollState(true);
                }
            }
            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
            }
        });

        mProgressBar = (ProgressBar)findViewById(R.id.progressbar);

        fetchData();
    }

    private void fetchData() {
        mProgressBar.setVisibility(View.VISIBLE);

        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
            Thread.sleep(1000);
            updateView();

        } catch (InterruptedException e) {
        }
    }

    private void updateView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                mProfileListAdapter.add("Alice");
                mProfileListAdapter.add("Bob");
                mProfileListAdapter.add("Alice's brother");
                mProfileListAdapter.add("Bob's brother");
                mProfileListAdapter.add("Alice's sister");
                mProfileListAdapter.add("Bob's sister");
                mProgressBar.setVisibility(View.GONE);
            }
        });
    }
}
