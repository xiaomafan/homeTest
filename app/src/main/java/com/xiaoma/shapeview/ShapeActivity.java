package com.xiaoma.shapeview;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.xiaoma.hometest.BaseActivity;
import com.xiaoma.hometest.R;

/**
 * Created by xiaoma on 2017/3/30.
 */

public class ShapeActivity extends BaseActivity {

 

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape);
//        ButterKnife.bind(this);
    }

//    @OnClick(R.id.btn_shape)
//    public void onClick() {
//        Toast.makeText(this,"点击",Toast.LENGTH_SHORT).show();
//    }
}
