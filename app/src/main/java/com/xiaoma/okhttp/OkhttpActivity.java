package com.xiaoma.okhttp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewConfiguration;
import android.widget.Button;

import com.xiaoma.base.BaseActivity;
import com.xiaoma.hometest.LogHelp;
import com.xiaoma.hometest.R;

import java.io.IOException;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by xiaoma on 2017/5/3.
 */

public class OkhttpActivity extends BaseActivity {

    @Bind(R.id.btn_get)
    Button btnGet;
    @Bind(R.id.btn_post)
    Button btnPost;
    
    private final String TAGGET="get";
    private final String TAGPOST="post";
    private OkHttpClient client;
    private String mUrl="http://456.sheyuan.com/v3/h5/goods/hotSaleSectionGoods";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_okhttp);
        ButterKnife.bind(this);
        client = new OkHttpClient();
        //系统滑动的最小距离
        int touchSlop = ViewConfiguration.get(this).getScaledTouchSlop();
    }

    @OnClick({R.id.btn_get, R.id.btn_post})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get:
                LogHelp.e(TAGGET,"get");
                getData();
                break;
            case R.id.btn_post:
                LogHelp.e(TAGPOST,"post");
                postData();
                break;
        }
    }

    private void postData() {
        FormBody.Builder builder = new FormBody.Builder();
        builder.add("section","jiudianjiuyuan");
        FormBody body = builder.build();
        Request request = new Request.Builder().url(mUrl).post(body).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogHelp.e(TAGPOST,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                LogHelp.e(TAGPOST,response.body().string());
            }
        });
        
        
    }

    private void getData() {
       
        Request request = new Request.Builder().url("https://github.com/hongyangAndroid").
                build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                LogHelp.e(TAGGET,e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                //非UI线程
                LogHelp.e(TAGGET,"onResponse"+Thread.currentThread().getName());
            }
        });
    }
}
