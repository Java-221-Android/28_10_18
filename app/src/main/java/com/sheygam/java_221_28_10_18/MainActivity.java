package com.sheygam.java_221_28_10_18;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private ProgressBar myProgress;
    private Button startBtn;
    private Handler handler;
    private TextView resultTxt;
    private FrameLayout progressFrame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        handler = new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what) {
                    case Worker.START:
//                        myProgress.setVisibility(View.VISIBLE);
                        progressFrame.setVisibility(View.VISIBLE);
//                        startBtn.setEnabled(false);
                        break;
                    case Worker.UPDATE:
                            resultTxt.setText(String.valueOf(msg.arg1));
                        break;
                    case Worker.DONE:
//                        myProgress.setVisibility(View.INVISIBLE);
                        progressFrame.setVisibility(View.GONE);
//                        startBtn.setEnabled(true);
                        resultTxt.setText(msg.obj.toString());
                        break;
                }
                return true;
            }
        });
        resultTxt = findViewById(R.id.result_txt);
        progressFrame = findViewById(R.id.progress_frame);
        myProgress = findViewById(R.id.my_progress);
        progressFrame.setOnClickListener(null);
        startBtn = findViewById(R.id.start_btn);
        startBtn.setOnClickListener(this);

        myProgress.setMax(200);
        myProgress.setProgress(100);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.start_btn) {
            Worker w = new Worker(handler);
            w.start();

        }
    }
}
