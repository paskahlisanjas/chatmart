package com.kahl.chatmart.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.kahl.chatmart.ItemActivity;
import com.kahl.chatmart.MainActivity;
import com.kahl.chatmart.R;
import com.kahl.chatmart.entity.ChatEntity;
import com.kahl.chatmart.entity.Product;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class RecyclerChatAdapter extends RecyclerView.Adapter<RecyclerChatAdapter.ChatHolder> {
    private Context context;
    private List<ChatEntity> chatEntityList;

    public RecyclerChatAdapter(Context context, List<ChatEntity> chatEntityList) {
        this.context = context;
        this.chatEntityList = chatEntityList;
    }

    public List<ChatEntity> getData() {
        return chatEntityList;
    }

    @Override
    public ChatHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chat_view_holder, parent, false);
        return new ChatHolder(view);
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(ChatHolder holder, int position) {
        ChatEntity chat = chatEntityList.get(position);

        switch (chat.getType()) {
            case ChatEntity.DEFAULT_IN:
                holder.messageInContainer.setVisibility(View.VISIBLE);
                holder.messageInContent.setText(chat.getMessage());
                break;
            case ChatEntity.DEFAULT_OUT:
                holder.messageOutContainer.setVisibility(View.VISIBLE);
                holder.messageOutContent.setText(chat.getMessage());
                break;
            case ChatEntity.LIST_OF_CATEGORY:
                holder.categoryListContainer.setVisibility(View.VISIBLE);
                holder.categoryList.setAdapter(new CategoryListViewAdapter(context, chat.getCategoryList()));
                holder.seeMoreButtonCategory.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(context, "Button clicked", Toast.LENGTH_SHORT).show();
                    }
                });
                holder.categoryList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        List<Product> products = new ArrayList<>();
                        products.add(new Product(context.getResources().getDrawable(R.drawable.indomie), "Indomie Goreng", "Mie instan enak","100.000"));
                        products.add(new Product(context.getResources().getDrawable(R.drawable.indomie), "Indomie Goreng Direbus", "Mie instan lezat","120.000"));
                        MainActivity.addToChatList(new ChatEntity(ChatEntity.LIST_OF_PRODUCT, null, products, null));
                    }
                });
                break;
            case ChatEntity.LIST_OF_SHIPMENT:
                holder.shipmentListContainer.setVisibility(View.VISIBLE);
                holder.shipmentList.setAdapter(new ShipmentListViewAdapter(context,
                        chat.getProductList()));
                holder.shipmentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MainActivity.addToChatList(new ChatEntity(ChatEntity.PRODUCT_SHIPMENT_STATUS, null, null, null));
                    }
                });
                break;
            case ChatEntity.PRODUCT_SHIPMENT_STATUS:
                holder.shipmentContainer.setVisibility(View.VISIBLE);
                break;
            case ChatEntity.CART:
                Log.d("CHATMART", "chat.getProductList().size() = " + chat.getProductList().size());
                if (chat.getProductList().size() == 0) {
                    MainActivity.addToChatList(new ChatEntity(ChatEntity.DEFAULT_IN, "Keranjang Anda kosong", null, null));
                } else {
                    holder.cartContainer.setVisibility(View.VISIBLE);
                    holder.totalPrice.setText("Total : Rp " + chat.getProductList().get(0).getPrice());
                    holder.cartList.setAdapter(new CartListViewAdapter(context, chat.getProductList()));
                    LinearLayoutManager llm = new LinearLayoutManager(context);
                    llm.setOrientation(LinearLayoutManager.VERTICAL);
                    holder.cartList.setLayoutManager(llm);
                    holder.checkOutButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            MainActivity.addToChatList(new ChatEntity(ChatEntity.PAYMENT_METHOD, null, null, null));
                        }
                    });
                }
                break;
            case ChatEntity.PAYMENT_METHOD:
                holder.paymentMethodContainer.setVisibility(View.VISIBLE);
                holder.chooseCreditCardButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.addToChatList(new ChatEntity(ChatEntity.DEFAULT_IN, "Pembayaran berhasil!", null, null));
                    }
                });
                holder.chooseTransferBankButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        MainActivity.addToChatList(new ChatEntity(ChatEntity.DEFAULT_IN, "Silakan lakukan pembayaran ke " +
                                "14045 (BCA) atas nama PT. Chatmart Nusantara dengan nominal Rp 221.352,00. Pembayaran akan otomatis +" +
                                "terverifikasi oleh sistem saat transaksi transfer anda berhasil.", null, null));
                    }
                });
                break;
            case ChatEntity.LIST_OF_PRODUCT:
                holder.productListContainer.setVisibility(View.VISIBLE);
                holder.productList.setAdapter(new ProductListViewAdapter(context, chat.getProductList()));
                holder.seeMoreButtonProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        context.startActivity(new Intent(context, ItemActivity.class));
                    }
                });
                break;
        }
    }

    @Override
    public int getItemCount() {
        return chatEntityList.size();
    }

    public static class ChatHolder extends RecyclerView.ViewHolder {
        protected LinearLayout messageOutContainer;
        protected TextView messageOutContent;

        protected LinearLayout messageInContainer;
        protected TextView messageInContent;

        protected LinearLayout productListContainer;
        protected ListView productList;
        protected TextView totalPrice;
        protected Button seeMoreButtonProduct;

        protected LinearLayout cartContainer;
        protected RecyclerView cartList;
        protected Button checkOutButton;

        protected LinearLayout paymentMethodContainer;
        protected Button chooseTransferBankButton;
        protected Button chooseCreditCardButton;

        protected LinearLayout categoryListContainer;
        protected ListView categoryList;
        protected Button seeMoreButtonCategory;

        protected LinearLayout shipmentListContainer;
        protected ListView shipmentList;

        protected LinearLayout shipmentContainer;

        public ChatHolder(View view) {
            super(view);

            messageOutContainer = (LinearLayout) view.findViewById(R.id.message_out_container);
            messageOutContent = (TextView) view.findViewById(R.id.message_content_text_view_out);

            messageInContainer = (LinearLayout) view.findViewById(R.id.message_in_container);
            messageInContent = (TextView) view.findViewById(R.id.message_content_text_view_in);

            productListContainer = (LinearLayout) view.findViewById(R.id.list_of_product_container);
            productList = (ListView) view.findViewById(R.id.list_of_product);
            totalPrice = (TextView) view.findViewById(R.id.total_price_cart);
            seeMoreButtonProduct = (Button) view.findViewById(R.id.see_more_button_product);

            cartContainer = (LinearLayout) view.findViewById(R.id.cart_container);
            cartList = (RecyclerView) view.findViewById(R.id.item_list_cart);
            checkOutButton = (Button) view.findViewById(R.id.check_out_button);

            paymentMethodContainer = (LinearLayout) view.findViewById(R.id.payment_method_container);
            chooseCreditCardButton = (Button) view.findViewById(R.id.button_choose_credit_card);
            chooseTransferBankButton = (Button) view.findViewById(R.id.button_choose_transfer_bank);

            categoryListContainer = (LinearLayout) view.findViewById(R.id.category_list_view_container);
            categoryList = (ListView) view.findViewById(R.id.category_list_view);
            seeMoreButtonCategory = (Button) view.findViewById(R.id.see_more_button_category);

            shipmentListContainer = (LinearLayout) view.findViewById(R.id.shipment_list_container);
            shipmentList = (ListView) view.findViewById(R.id.shipment_list_view);

            shipmentContainer = (LinearLayout) view.findViewById(R.id.shipment_container);
        }
    }
}

