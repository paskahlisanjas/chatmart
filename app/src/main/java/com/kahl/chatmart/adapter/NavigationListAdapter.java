package com.kahl.chatmart.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kahl.chatmart.R;

/**
 * Created by Paskahlis Anjas Prabowo on 03/12/2017.
 */

public class NavigationListAdapter extends BaseAdapter {
    private String[] text;
    private Drawable[] icon;
    private Context context;

    public NavigationListAdapter(String[] text, Drawable[] icon, Context context) {
        this.text = text;
        this.icon = icon;
        this.context = context;
    }

    @Override
    public int getCount() {
        return text.length;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.drawer_list_item, parent, false);
        TextView itemText = (TextView) itemView.findViewById(R.id.item_text);
        ImageView itemIcon = (ImageView) itemView.findViewById(R.id.item_icon);
        itemText.setText(text[position]);
        itemIcon.setImageDrawable(icon[position]);
        return itemView;
    }
}
