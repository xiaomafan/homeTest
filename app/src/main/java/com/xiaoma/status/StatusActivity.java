package com.xiaoma.status;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/5/10.
 */

public class StatusActivity extends BaseActivity {

    private Toolbar mToolBar;
    private TextView mTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        setTitleBg(R.color.colorPrimary);
        setTitle("标题栏");
    }
}
