package com.kahl.chatmart;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TextView;

import com.kahl.chatmart.adapter.RecyclerChatAdapter;

/**
 * Created by ZackiZulfikarFauzi on 12/11/2017.
 */

public class ItemActivity extends AppCompatActivity {
    TabHost tabHost;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.item_activity);
        TabHost host = (TabHost)findViewById(R.id.tabHost);
        host.setup();

        //Tab 1
        TabHost.TabSpec spec = host.newTabSpec("Description");
        spec.setContent(R.id.Description);
        spec.setIndicator("Description");
        host.addTab(spec);

        //Tab 2
        spec = host.newTabSpec("Comment");
        spec.setContent(R.id.Comments);
        spec.setIndicator("Comment");
        host.addTab(spec);
    }
}
