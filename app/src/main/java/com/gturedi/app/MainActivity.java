package com.gturedi.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.gturedi.views.StatefulLayout;

/**
 * Created by gturedi on 17.02.2017.
 */
public class MainActivity
        extends AppCompatActivity {

    private StatefulLayout stateful;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        stateful = (StatefulLayout) findViewById(R.id.stateful);
    }

    public void content(View view) {
        stateful.showContent();
    }

    public void loading(View view) {
        stateful.showLoading();
    }

    public void empty(View view) {
        stateful.showEmpty();
    }

    public void error(View view) {
        stateful.showError(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void offline(View view) {
        stateful.showOffline(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void locationOff(View view) {
        stateful.showLocationOff(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "click!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
