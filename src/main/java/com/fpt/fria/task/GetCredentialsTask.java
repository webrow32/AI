package com.fpt.fria.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Message;

import com.fpt.fria.entity.JSONGetEntity;
import com.fpt.fria.entity.JSONPostEntity;

import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by fealrone.alajas on 7/14/2016.
 */
public class GetCredentialsTask extends AsyncTask<Void, Void, JSONGetEntity[]> {
    private static final String TTS_CREDENTIAL_REQUEST_URL = "http://10.192.21.9:8080/ServiceBotServer/credential/texttospeech";
    private static final String STT_CREDENTIAL_REQUEST_URL = "http://10.192.21.9:8080/ServiceBotServer/credential/speechtotext";

    private JSONPostEntity jsonPostParam;
    private Handler mHandler;
    private Context context;
    private ProgressDialog progress;

    public GetCredentialsTask(Context context, Handler mHandler) {
        this.mHandler = mHandler;
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
        progress = new ProgressDialog(context);
        progress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progress.show();
        progress.setCanceledOnTouchOutside(false);
    }

    @Override
    protected JSONGetEntity[] doInBackground(Void... params) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());

            JSONGetEntity ttsCredentials = restTemplate.getForObject(TTS_CREDENTIAL_REQUEST_URL, JSONGetEntity.class);

            JSONGetEntity sttCredentials = restTemplate.getForObject(STT_CREDENTIAL_REQUEST_URL, JSONGetEntity.class);

            JSONGetEntity credentials[] = new JSONGetEntity[]{ttsCredentials, sttCredentials};
            System.out.println("DONE");
            return credentials;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(JSONGetEntity[] greeting) {
        progress.dismiss();

        Message completeMessage =
                mHandler.obtainMessage(0, greeting);
        mHandler.dispatchMessage(completeMessage);
    }

}

