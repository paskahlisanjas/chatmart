package com.kahl.chatmart.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kahl.chatmart.R;
import com.kahl.chatmart.entity.Product;

import java.util.List;

/**
 * Created by ZackiZulfikarFauzi on 12/11/2017.
 */

public class ProductListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public ProductListViewAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }

    @Override
    public int getCount() {
        return products.size();
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
        View item = LayoutInflater.from(context).inflate(R.layout.product_list_item, parent, false);
        Product product = products.get(position);

        ImageView illustration = (ImageView) item.findViewById(R.id.product_illustration);
        TextView productName = (TextView) item.findViewById(R.id.product_list_name);
        TextView productPrice = (TextView) item.findViewById(R.id.product_list_price);

        illustration.setImageDrawable(product.getImage());
        productName.setText(product.getName());
        productPrice.setText("Price : Rp "+product.getPrice());

        return item;
    }
}
