package org.dnu.sample.materiallistview.widgets;

import android.view.View;
import android.widget.AbsListView;

/**
 * Created by isskj on 3/29/15.
 */
public class MaterialListViewOnClickListener implements View.OnClickListener {

    private OnScrollListener mScrollListener;

    public static interface OnScrollListener {
        public void onScroll();
    }

    @Override
    public void onClick(View v) {
    }

    public void setOnScrollListener(OnScrollListener listener) {
        mScrollListener = listener;
    }
}
