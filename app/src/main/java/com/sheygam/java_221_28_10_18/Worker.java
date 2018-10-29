package com.sheygam.java_221_28_10_18;

import android.os.Handler;
import android.os.Message;

public class Worker extends Thread {
    public static final int START = 0x01;
    public static final int UPDATE = 0x02;
    public static final int DONE = 0x03;

    private Handler handler;

    public Worker(Handler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.sendEmptyMessage(START);
        for (int i = 0; i < 100; i++) {
            try {
                sleep(50);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Message msg = Message.obtain();
            msg.what = UPDATE;
            msg.arg1 = i+1;
            handler.sendMessageDelayed(msg,0);
        }
        String str  = "DONE!";
        Message msg = Message.obtain();
        msg.what = DONE;
        msg.obj = str;
        handler.sendMessage(msg);
    }
}
