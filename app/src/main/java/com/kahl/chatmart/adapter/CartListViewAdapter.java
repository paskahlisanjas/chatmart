package com.kahl.chatmart.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
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

public class CartListViewAdapter extends RecyclerView.Adapter<CartListViewAdapter.MyViewHolder>{
    private Context context;
    private List<Product> products;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView productName;
        public TextView productPrice;

        public MyViewHolder(View view) {
            super(view);
            productName = (TextView) view.findViewById(R.id.cart_product_name);
            productPrice = (TextView) view.findViewById(R.id.cart_product_price);
        }
    }

    public CartListViewAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
    }
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Product product = products.get(position);
        holder.productName.setText(product.getName());
        holder.productPrice.setText("Harga : Rp "+product.getPrice());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context)
                .inflate(R.layout.cart_list_item,parent, false);
        return new MyViewHolder(v);
    }

}
