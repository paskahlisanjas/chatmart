package com.kahl.chatmart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kahl.chatmart.R;
import com.kahl.chatmart.entity.Category;
import com.kahl.chatmart.entity.Product;

import java.util.List;

/**
 * Created by Paskahlis Anjas Prabowo on 12/11/2017.
 */

public class CategoryListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Category> categories;

    public CategoryListViewAdapter(Context context, List<Category> categories) {
        this.context = context;
        this.categories = categories;
    }

    @Override
    public int getCount() {
        return categories.size();
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
        View item = LayoutInflater.from(context).inflate(R.layout.category_list_item, parent, false);
        Category category = categories.get(position);

        ImageView illustration = (ImageView) item.findViewById(R.id.illustration);
        TextView categoryName = (TextView) item.findViewById(R.id.category_name);
        TextView categoryDescription = (TextView) item.findViewById(R.id.category_description);

        illustration.setImageDrawable(category.getIllustration());
        categoryName.setText(category.getName());
        categoryDescription.setText(category.getDescription());

        return item;
    }
}
