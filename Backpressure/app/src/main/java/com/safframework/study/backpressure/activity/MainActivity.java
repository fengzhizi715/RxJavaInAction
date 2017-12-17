package com.safframework.study.backpressure.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.safframework.injectview.annotations.InjectView;
import com.safframework.study.backpressure.R;
import com.safframework.study.backpressure.app.BaseActivity;

public class MainActivity extends BaseActivity {

    @InjectView(R.id.text1)
    TextView text1;

    @InjectView(R.id.text2)
    TextView text2;

    @InjectView(R.id.text3)
    TextView text3;

    @InjectView(R.id.text4)
    TextView text4;

    @InjectView(R.id.text5)
    TextView text5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,NoBackpressureActivity.class);
                startActivity(i);
            }
        });

        text2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Backpressure1Activity.class);
                startActivity(i);
            }
        });

        text3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,Backpressure2Activity.class);
                startActivity(i);
            }
        });

        text4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,ErrorActivity.class);
                startActivity(i);
            }
        });

        text5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(MainActivity.this,BufferActivity.class);
                startActivity(i);
            }
        });
    }
}
