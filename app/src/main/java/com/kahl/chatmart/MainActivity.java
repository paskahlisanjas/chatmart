package com.kahl.chatmart;

import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.kahl.chatmart.adapter.RecyclerChatAdapter;
import com.kahl.chatmart.entity.ChatEntity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView chatList;
    EditText editTextInput;
    FloatingActionButton fabSend;

    List<ChatEntity> chats = new ArrayList<>();

    RecyclerChatAdapter chatAdapter;

    private final String WRONG_MESSAGE = "Tolong bantu kami memahami permintaan Anda dengan memperjelas permintaan Anda.";
    private final String HALO = "Halo";
    private final String OPENING_RESP = "Ada yang dapat kami bantu untuk Anda?";

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
            chatList.setAdapter(new RecyclerChatAdapter(this, chats));
            chatList.invalidate();
        } else {
            chats.add(addIntoChats(input, false));
            chats.add(addIntoChats(WRONG_MESSAGE, true));
            chatAdapter.notifyDataSetChanged();
        }

        int totalItem = chatList.getAdapter().getItemCount();
        chatList.smoothScrollToPosition(totalItem - 1);
    }


    private ChatEntity addIntoChats(String text, boolean in) {
        return in ? new ChatEntity(ChatEntity.DEFAULT_IN, text)
                : new ChatEntity(ChatEntity.DEFAULT_OUT, text);
    }

    private void bindObject() {
        chats.add(new ChatEntity(ChatEntity.DEFAULT_IN, "Initial message"));
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
}
