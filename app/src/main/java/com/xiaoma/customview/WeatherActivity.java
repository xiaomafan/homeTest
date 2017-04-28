package com.xiaoma.customview;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.jauker.widget.BadgeView;
import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/3/24.
 */

public class WeatherActivity extends BaseActivity {

    private Button mShow;
    private BadgeView badgeView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
       
        mShow = (Button) findViewById(R.id.btn_show);
        showNumber();
        findViewById(R.id.btn_circle_number).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ViewGroup)badgeView.getParent()).removeView(badgeView);
            }
        });
    }

    private void showNumber() {
        badgeView = new BadgeView(this);
        badgeView.setTargetView(mShow);
        badgeView.setPadding(0,8,8,0);
        badgeView.setBackgroundColor(Color.RED);
        badgeView.setTextColor(Color.WHITE);
        badgeView.setBadgeCount(12);
    }
}
