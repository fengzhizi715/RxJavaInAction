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

                Intent i = new Intent(MainActivity.this,BackpressureActivity.class);
                startActivity(i);
            }
        });
    }
}
