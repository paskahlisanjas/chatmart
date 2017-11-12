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
 * Created by Paskahlis Anjas Prabowo on 12/11/2017.
 */

public class ShipmentListViewAdapter extends BaseAdapter {
    private Context context;
    private List<Product> products;

    public ShipmentListViewAdapter(Context context, List<Product> products) {
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
        View item = LayoutInflater.from(context).inflate(R.layout.shipment_list_item, parent, false);
        Product product = products.get(position);

        ImageView illustration = (ImageView) item.findViewById(R.id.illustration);
        TextView productName = (TextView) item.findViewById(R.id.product_name);

        illustration.setImageDrawable(product.getImage());
        productName.setText(product.getName());

        return item;
    }
}
