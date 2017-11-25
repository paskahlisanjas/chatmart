package com.kahl.chatmart;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.kahl.chatmart.adapter.RecyclerChatAdapter;
import com.kahl.chatmart.entity.Category;
import com.kahl.chatmart.entity.ChatEntity;
import com.kahl.chatmart.entity.Product;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static RecyclerView chatList;
    private EditText editTextInput;
    private FloatingActionButton fabSend;
    private List<Product> products = new ArrayList<>();

    private static List<ChatEntity> chats = new ArrayList<>();

    private static RecyclerChatAdapter chatAdapter;

    private final String WRONG_MESSAGE = "Tolong bantu kami memahami permintaan Anda dengan memperjelas permintaan Anda.";
    private final String HALO = "Halo";
    private final String OPENING_RESP = "Ada yang dapat kami bantu?";
    private final String SHIPMENT = "Barang dikirim";
    private final String CATEGORY = "Tampilkan kategori";
    private final String CARI = "Cari";
    private final String CARI_INDOMIE = "Cari Indomie";
    private final String ITEM_NOT_FOUND = "Barang tidak ditemukan";
    private final String KERANJANG = "Lihat keranjang";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindObject();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    private void runLogic() {
        String input = editTextInput.getText().toString();

        if (input.isEmpty()) {
            return;
        }

        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        editTextInput.setText("");

        if (input.equals(HALO)) {
            chats.add(addIntoChats(HALO, false));
            chats.add(addIntoChats(OPENING_RESP, true));
        } else if (input.equals(CATEGORY)) {
            chats.add(addIntoChats(CATEGORY, false));
            List<Category> categories = new ArrayList<>();
            Category health = new Category("Kesehatan", getResources().getDrawable(R.drawable.ic_accessibility_white_24px),
                    "sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa .");
            Category electrocnic = new Category("Gadget", getResources().getDrawable(R.drawable.ic_phone_android_white_24px),
                    "Nemo enim ipsam voluptatem quia voluptas sit aspernatur.");
            categories.add(health);
            categories.add(electrocnic);
            chats.add(new ChatEntity(ChatEntity.LIST_OF_CATEGORY, null, null, categories));
        } else if (input.equals(SHIPMENT)) {
            chats.add(addIntoChats(SHIPMENT, false));
            chats.add(new ChatEntity(ChatEntity.LIST_OF_SHIPMENT, null, products, null));
        } else if ((input.length()>5)&&(input.substring(0,4).equals(CARI))) {
            if(input.equals(CARI_INDOMIE)){
                products.add(new Product(getResources().getDrawable(R.drawable.indomie), "Indomie Goreng", "Mie instan enak","100.000"));
                products.add(new Product(getResources().getDrawable(R.drawable.indomie), "Indomie Goreng Direbus", "Mie instan lezat","120.000"));
                chats.add(addIntoChats(CARI_INDOMIE, false));
                chats.add(new ChatEntity(ChatEntity.LIST_OF_PRODUCT, null, products, null));
            } else{
                chats.add(addIntoChats(input, false));
                chats.add(addIntoChats(ITEM_NOT_FOUND, true));
            }
        } else if (input.equals(KERANJANG)){
            chats.add(addIntoChats(KERANJANG, false));
            chats.add(new ChatEntity(ChatEntity.CART, null, products, null));
        }else {
                chats.add(addIntoChats(input, false));
                chats.add(addIntoChats(WRONG_MESSAGE, true));
        }

        chatAdapter.notifyDataSetChanged();

        int totalItem = chatList.getAdapter().getItemCount();
        chatList.smoothScrollToPosition(totalItem - 1);
    }

    private ChatEntity addIntoChats(String text, boolean in) {
        return in ? new ChatEntity(ChatEntity.DEFAULT_IN, text, null, null)
                : new ChatEntity(ChatEntity.DEFAULT_OUT, text, null, null);
    }

    private void bindObject() {
        chats.add(addIntoChats("Hi, Smitty Werbenjagermanjensen", true));
        chatAdapter = new RecyclerChatAdapter(this, chats);

        chatList = (RecyclerView) findViewById(R.id.list_view_chats);
        editTextInput = (EditText) findViewById(R.id.edit_text_user_input);
        fabSend = (FloatingActionButton) findViewById(R.id.fab_send);

        chatList.setAdapter(chatAdapter);
        chatList.setLayoutManager(new LinearLayoutManager(this));

        fabSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                runLogic();
            }
        });
    }
    
    public static void addToChatList(ChatEntity chatEntity) {
        chats.add(chatEntity);
        chatAdapter.notifyDataSetChanged();
        int totalItem = chatList.getAdapter().getItemCount();
        chatList.smoothScrollToPosition(totalItem - 1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.help_menu) {
            Intent intent = new Intent(this, HelpActivity.class);
            startActivity(intent);
            return true;
        }
        return false;
    }
}
