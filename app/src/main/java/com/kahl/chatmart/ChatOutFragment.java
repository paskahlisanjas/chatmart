package com.kahl.chatmart;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by Paskahlis Anjas Prabowo on 10/11/2017.
 */

public class ChatOutFragment extends Fragment {
    private static String content;
    private static final String CONTENT = "content";

    public static ChatOutFragment newInstance(String content) {
        ChatOutFragment instance = new ChatOutFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CONTENT, content);
        instance.setArguments(bundle);
        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        content = getArguments().getString(CONTENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.chat_default_out, container, false);

        TextView textView = (TextView) view.findViewById(R.id.message_content_text_view);
        textView.setText(content);

        return view;
    }
}
