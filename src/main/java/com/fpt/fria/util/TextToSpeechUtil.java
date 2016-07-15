package com.fpt.fria.util;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

import com.ibm.watson.developer_cloud.text_to_speech.v1.TextToSpeech;
import com.ibm.watson.developer_cloud.text_to_speech.v1.model.Voice;

import org.apache.commons.io.IOUtils;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Resty Louis Artiaga on 7/12/2016.
 */

public class TextToSpeechUtil {

    private String username;
    private String password;
    private String endpoint;
    TextToSpeech tts;
    private int sampleRate = 0;


    public TextToSpeechUtil(String username, String password){
        this.username = username;
        this.password = password;

    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void initTTS() {
        tts= new TextToSpeech();
        tts.setUsernameAndPassword(username, password);
        tts.setEndPoint(endpoint);
    }

    public void playTTS(final String text) throws JSONException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                InputStream result = tts.synthesize(text, Voice.EN_MICHAEL).execute();
                int minBufferSize = AudioTrack.getMinBufferSize(44100, AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT);

                byte[] data = null;
                data = analyzeWavData(result);

                AudioTrack track = new AudioTrack(AudioManager.STREAM_MUSIC,
                        sampleRate,
                        AudioFormat.CHANNEL_OUT_MONO,
                        AudioFormat.ENCODING_PCM_16BIT,
                        minBufferSize,
                        AudioTrack.MODE_STREAM);


                if (track != null)
                    track.play();

                track.write(data,0,data.length);
                try {
                    result.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }).start();
    }


    public byte[] analyzeWavData(InputStream i){
        try {
            int headSize=44, metaDataSize=48;
            byte[] data = IOUtils.toByteArray(i);

            if(data.length < headSize){
                throw new IOException("Wrong Wav header");
            }

            if(this.sampleRate == 0 && data.length > 28) {
                this.sampleRate = readInt(data, 24); // 24 is the position of sample rate in wav format
            }

            int destPos = headSize + metaDataSize;
            int rawLength = data.length - destPos;

            byte[] d = new byte[rawLength];
            System.arraycopy(data, destPos, d, 0, rawLength);
            return d;
        } catch (IOException e) {

        }
        return new byte[0];
    }

    protected static int readInt(final byte[] data, final int offset)
    {
        return (data[offset] & 0xff) |
                ((data[offset+1] & 0xff) <<  8) |
                ((data[offset+2] & 0xff) << 16) |
                (data[offset+3] << 24); // no 0xff on the last one to keep the sign
    }
}
