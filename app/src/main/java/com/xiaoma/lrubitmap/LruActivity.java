package com.xiaoma.lrubitmap;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.ImageView;

import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/5/13.
 */

public class LruActivity extends AppCompatActivity {

    @Bind(R.id.btn_handler)
    Button btnHandler;
    @Bind(R.id.iv_img)
    ImageView ivImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru);
        ButterKnife.bind(this);
        imgStudy();
    }

    private void imgStudy() {
        ivImg.getHeight();
        ivImg.getWidth();
        BitmapFactory.Options option=new BitmapFactory.Options();
        //设置取样率
        option.inJustDecodeBounds=true;
    }

    @OnClick(R.id.btn_handler)
    public void onViewClicked() {
    }
}
