package org.dnu.sample.materiallistview;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.dnu.sample.materiallistview.widgets.MaterialBallView;

/**
 * Created by isskj on 3/29/15.
 */
public class ProfileListAdapter extends ArrayAdapter<String>
    implements View.OnClickListener {

    private boolean mScrollState;

    private LayoutInflater mInflater;

    public ProfileListAdapter(Context context) {
        super(context, 0);
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        Holder holder;
        if (view == null) {
            view = mInflater.inflate(R.layout.listview_item, parent, false);
            holder = new Holder();
            holder.profileName = (TextView)view.findViewById(R.id.text_name);
            holder.viewPane = (MaterialBallView)view.findViewById(R.id.button_view);
            holder.viewPane.setOnClickEndAnimationListener(this);
            holder.viewPane.setOnScrolling(mScrollState);
            view.setTag(holder);
        } else {
            holder = (Holder)view.getTag();
        }

        String name = super.getItem(position);

        holder.profileName.setText(name);
        holder.viewPane.setTag(name);

        return view;
    }

    public void setOnScrollState(boolean scrolling) {
        mScrollState = scrolling;
    }

    private static class Holder {
        TextView profileName;
        MaterialBallView viewPane;
    }

    @Override
    public void onClick(View v) {
        String name = (String)v.getTag();
        Toast.makeText(mInflater.getContext(), "Item Clicked:"+name, Toast.LENGTH_SHORT).show();
    }

}
