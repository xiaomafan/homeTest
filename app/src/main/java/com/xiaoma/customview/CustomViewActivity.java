package com.xiaoma.customview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.ViewConfiguration;
import android.widget.Button;

import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by deng on 17/3/26.
 */
public class CustomViewActivity extends BaseActivity {

    @Bind(R.id.btn_getattribute)
    Button btnGetattribute;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customview);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_getattribute)
    public void onClick() {
        int left = btnGetattribute.getLeft();
        int right = btnGetattribute.getRight();
        float x = btnGetattribute.getX();
        float y = btnGetattribute.getY();
        int top = btnGetattribute.getTop();
        int bottom = btnGetattribute.getBottom();
        float translationX = btnGetattribute.getTranslationX();
        float rotationY = btnGetattribute.getRotationY();
        int scaledTouchSlop = ViewConfiguration.get(this).getScaledTouchSlop();

    }
}
