package com.kahl.chatmart.adapter;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import com.kahl.chatmart.entity.ChatEntity;

import java.util.List;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class ListChatAdapter extends ListFragment implements AdapterView.OnItemClickListener {
    Context context;
    List<ChatEntity> chatEntityList;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

  /*  public ListChatAdapter(Context context, List<ChatEntity> chatEntityList) {
        this.context = context;
        this.chatEntityList = chatEntityList;
    }*/


}
