package com.vpfinance.stephen.androiduidemo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final PolygonView polygonView1 = (PolygonView) findViewById(R.id.polygon1);
        final PolygonView polygonView2 = (PolygonView) findViewById(R.id.polygon2);
        final PolygonView polygonView3 = (PolygonView) findViewById(R.id.polygon3);
        final PolygonView polygonView4 = (PolygonView) findViewById(R.id.polygon4);
        TextView click1 = (TextView) findViewById(R.id.tvClick1);
        click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                polygonView1.setIndeterminate(true);
                polygonView2.setIndeterminate(true);
                polygonView3.setIndeterminate(true);
                polygonView4.setIndeterminate(true);
            }
        });
        TextView click2 = (TextView) findViewById(R.id.tvClick2);
        click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                polygonView1.stopAnimate();
                polygonView2.stopAnimate();
                polygonView3.stopAnimate();
                polygonView4.stopAnimate();
            }
        });
    }

}