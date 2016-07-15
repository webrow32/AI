package com.fpt.fria.task;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fpt.fria.entity.JSONGetEntity;
import com.fpt.fria.entity.JSONPostEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.Date;

/**
 * Created by fealrone.alajas on 7/14/2016.
 */
public class HttpRequestTask extends AsyncTask<Void, Void, JSONGetEntity> {
    private static final String REQUEST_URL = "http://10.192.21.9:8080/ServiceBotServer/sendMessage";

    private JSONPostEntity jsonPostParam;
    private Handler mHandler;

    public HttpRequestTask(Handler mHandler) {
        this.mHandler = mHandler;
    }

    public void sendMessage(String user, String message){
        jsonPostParam = new JSONPostEntity();
        jsonPostParam.setUser(user);
        jsonPostParam.setMessage(message);
        jsonPostParam.setTimestamp(new Date().toString());
    }

    @Override
    protected JSONGetEntity doInBackground(Void... params) {
        try {
            ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
            String json = ow.writeValueAsString(jsonPostParam);

            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONGetEntity jsonGetParam = restTemplate.postForObject(REQUEST_URL, jsonPostParam, JSONGetEntity.class);
            return jsonGetParam;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONGetEntity greeting) {
        Message completeMessage =
                mHandler.obtainMessage(0,greeting);
        mHandler.dispatchMessage(completeMessage);
    }

}

