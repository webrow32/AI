package com.fpt.fria.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.fpt.fria.R;
import com.fpt.fria.entity.Chat;

import java.util.List;

/**
 * Created by Resty Louis Artiaga on 7/13/2016.
 */
public class ChatAdapter extends BaseAdapter{

    private List<Chat> chatList;
    private Context context;
    private LayoutInflater layoutInflater;

    public ChatAdapter(Context context, List<Chat> chatList){
       this.context = context;
        this.chatList = chatList;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return chatList.size();
    }

    @Override
    public Object getItem(int position) {
        return chatList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if (convertView == null) {
            if (chatList.get(position).getFrom() == Chat.FROM_YOU)
                convertView = layoutInflater.inflate(R.layout.bubble_right, null);
            else if (chatList.get(position).getFrom() == Chat.FROM_BOT) {
                convertView = layoutInflater.inflate(R.layout.bubble_left, null);
            }
        }

        TextView text = (TextView) convertView.findViewById(R.id.txt_msg);
        text.setText(chatList.get(position).getMessage());
        TextView timestamp = (TextView) convertView.findViewById(R.id.timestamp);
        timestamp.setText(chatList.get(position).getTimestamp());
        return convertView;
    }

}
