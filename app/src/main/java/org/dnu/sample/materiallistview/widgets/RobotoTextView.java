package org.dnu.platform.unointerface.widget;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by isskj on 3/28/15.
 */
public class RobotoTextView extends TextView {

    public RobotoTextView(Context context) {
        super(context);
        init();
    }

    public RobotoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public RobotoTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        super.setTypeface(Typeface.createFromAsset(getContext().getAssets(), "Roboto-Thin.ttf"));
    }
}
