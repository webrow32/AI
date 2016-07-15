package com.fpt.fria.activities;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.fpt.fria.R;
import com.fpt.fria.adapters.ChatAdapter;
import com.fpt.fria.entity.Chat;
import com.fpt.fria.entity.JSONGetEntity;
import com.fpt.fria.entity.ServiceCredential;
import com.fpt.fria.task.GetCredentialsTask;
import com.fpt.fria.task.HttpRequestTask;
import com.fpt.fria.util.TextToSpeechUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {

    ListView chatListview;
    EditText chatField;
    Button sendBtn;

    List<Chat> chatList;
    ChatAdapter adapter;

    private boolean isSpeechOn;
    DrawerLayout dLayout;
    HttpRequestTask task;
    String user;

    Handler botMessageHandler = new Handler(new Handler.Callback(){

        @Override
        public boolean handleMessage(Message msg) {
            JSONGetEntity entity = (JSONGetEntity) msg.obj;

            Chat chat2 = new Chat();
            chat2.setMessage(entity.getEntity().get(JSONGetEntity.MESSAGE));
            chat2.setTimestamp(entity.getEntity().get(JSONGetEntity.TIMESTAMP));
            chat2.setFrom(Chat.FROM_BOT);
            chatList.add(chat2);
            adapter.notifyDataSetChanged();
            if (isSpeechOn) {
                try {
                    ttsUtil.playTTS(chat2.getMessage());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            return false;
        }
    });
    Handler getCredentialsHandler = new Handler(new Handler.Callback(){
        @Override
        public boolean handleMessage(Message msg) {
            JSONGetEntity entity[] = (JSONGetEntity[]) msg.obj;
            ttsServiceCredential = new ServiceCredential();
            sttServiceCredential = new ServiceCredential();

            ttsServiceCredential.setUsername(entity[0].getEntity().get(ServiceCredential.USERNAME));
            ttsServiceCredential.setPassword(entity[0].getEntity().get(ServiceCredential.PASSWORD));
            ttsServiceCredential.setEndpoint(entity[0].getEntity().get(ServiceCredential.ENDPOINT));

            sttServiceCredential.setUsername(entity[1].getEntity().get(ServiceCredential.USERNAME));
            sttServiceCredential.setPassword(entity[1].getEntity().get(ServiceCredential.PASSWORD));
            sttServiceCredential.setEndpoint(entity[1].getEntity().get(ServiceCredential.ENDPOINT));

            ttsUtil = new TextToSpeechUtil(ttsServiceCredential.getUsername(), ttsServiceCredential.getPassword());
            ttsUtil.setEndpoint(ttsServiceCredential.getEndpoint());
            ttsUtil.initTTS();
            return false;
        }
    });
    TextToSpeechUtil ttsUtil;
    ServiceCredential ttsServiceCredential;
    ServiceCredential sttServiceCredential;

    GetCredentialsTask credentialsTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_chat);

        setToolBar();
        setNavigationDrawer();

        user = getIntent().getStringExtra("user");

        chatListview = (ListView) findViewById(R.id.chatListview);
        chatField = (EditText) findViewById(R.id.chatField);
        sendBtn = (Button) findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(this);

        isSpeechOn = false;
        chatList = new ArrayList();
        adapter = new ChatAdapter(this, chatList);

        chatListview.setAdapter(adapter);

        getCredentials();
        //temporaryCredentials();
        initializeBotMessageHandler();


    }

    private void getCredentials() {


        credentialsTask = new GetCredentialsTask(this, getCredentialsHandler);
        credentialsTask.execute();
    }


    private void initializeBotMessageHandler() {

    }


    private void setToolBar() {
        Toolbar tb = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(tb);
        ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.ic_drawer);
        ab.setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    private void setNavigationDrawer() {
        dLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navView = (NavigationView) findViewById(R.id.navigation);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            case android.R.id.home: {
                dLayout.openDrawer(GravityCompat.START);
                return true;
            }
            case R.id.speech:
                if (isSpeechOn) {
                    item.setIcon(android.R.drawable.ic_lock_silent_mode);
                    isSpeechOn = false;
                } else {
                    item.setIcon(android.R.drawable.ic_lock_silent_mode_off);
                    isSpeechOn = true;
                }
            default:
                break;
        }

        return true;
    }

    @Override
    public void onClick(View v) {
        int viewId = v.getId();

        switch (viewId) {
            case R.id.sendBtn:
                task = new HttpRequestTask(botMessageHandler);

                Chat chat = new Chat();
                chat.setMessage(chatField.getText().toString());
                chat.setTimestamp(new Date().toString());
                chat.setFrom(Chat.FROM_YOU);

                chatField.setText("");
                chatList.add(chat);
                adapter.notifyDataSetChanged();

                task.sendMessage(user, chat.getMessage());
                task.execute();
                break;
            default:
                break;
        }
    }
}
