package com.kahl.chatmart;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.kahl.chatmart.adapter.NavigationListAdapter;
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
    private final String HALO = "halo";
    private final String OPENING_RESP = "Ada yang dapat kami bantu?";
    private final String SHIPMENT = "barang dikirim";
    private final String CATEGORY = "tampilkan kategori";
    private final String CARI = "cari";
    private final String CARI_INDOMIE = "cari indomie";
    private final String ITEM_NOT_FOUND = "Barang tidak ditemukan";
    private final String KERANJANG = "lihat keranjang";

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle drawerToggle;
    private ListView drawerList;

    private boolean backPressedTwice = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
        setContentView(R.layout.activity_main);

        setupNavigationDrawer();

        bindObject();

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
    }

    private void setupNavigationDrawer() {
        drawerLayout = (DrawerLayout) findViewById(R.id.navigation_drawer_layout);
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

        drawerList = (ListView) findViewById(R.id.drawer_list);
        String[] itemText = {"Pengaturan"};
        Drawable[] itemIcon = {getResources().getDrawable(R.drawable.settings_icon)};
        drawerList.setAdapter(new NavigationListAdapter(itemText, itemIcon, this));
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private void runLogic() {
        String input = editTextInput.getText().toString().toLowerCase();

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
                    "sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa.");
            Category electrocnic = new Category("Gadget", getResources().getDrawable(R.drawable.ic_phone_android_white_24px),
                    "Nemo enim ipsam voluptatem quia voluptas sit aspernatur.");
            categories.add(health);
            categories.add(electrocnic);
            chats.add(new ChatEntity(ChatEntity.LIST_OF_CATEGORY, null, null, categories));
        } else if (input.equals(SHIPMENT)) {
            chats.add(addIntoChats(SHIPMENT, false));
            chats.add(new ChatEntity(ChatEntity.LIST_OF_SHIPMENT, null, products, null));
        } else if ((input.length() > 5) && (input.substring(0, 4).equals(CARI))) {
            if (input.equals(CARI_INDOMIE)) {
                products.add(new Product(getResources().getDrawable(R.drawable.indomie), "Indomie Goreng", "Mie instan enak", "100.000"));
                products.add(new Product(getResources().getDrawable(R.drawable.indomie), "Indomie Goreng Direbus", "Mie instan lezat", "120.000"));
                chats.add(addIntoChats(CARI_INDOMIE, false));
                chats.add(new ChatEntity(ChatEntity.LIST_OF_PRODUCT, null, products, null));
            } else {
                chats.add(addIntoChats(input, false));
                chats.add(addIntoChats(ITEM_NOT_FOUND, true));
            }
        } else if (input.equals(KERANJANG)) {
            chats.add(addIntoChats(KERANJANG, false));
            chats.add(new ChatEntity(ChatEntity.CART, null, products, null));
        } else {
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
        } else if (item.getItemId() == android.R.id.home) {
            if (drawerLayout.isDrawerOpen(GravityCompat.START))
                drawerLayout.closeDrawer(GravityCompat.START);
            else
                drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            if (backPressedTwice) {
                finish();
                System.exit(0);
            } else {
                backPressedTwice = true;
                Toast.makeText(this, "Sentuh sekali lagi untuk keluar", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(
                        new Runnable() {
                            @Override
                            public void run() {
                                backPressedTwice = false;
                            }
                        }, 3000);
            }
        }
    }
}
