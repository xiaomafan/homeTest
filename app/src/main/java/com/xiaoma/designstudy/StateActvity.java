package com.xiaoma.designstudy;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.xiaoma.hometest.MainActivity;
import com.xiaoma.hometest.R;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xiaoma on 2017/5/17.
 */

public class StateActvity extends AppCompatActivity {

    @Bind(R.id.btn_one)
    Button btnOne;
    @Bind(R.id.btn_two)
    Button btnTwo;
    @Bind(R.id.btn_three)
    Button btnThree;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_one, R.id.btn_two})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_one:
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("key","你好，真不错呢啊");
                LoginContext.getInstance().forward(this,intent);
                break;
            case R.id.btn_two:
                LoginContext.getInstance().comment(this);
                break;
        }
    }

    @OnClick(R.id.btn_three)
    public void onViewClicked() {
        PreferenceUtil.getInstance().clearToken();
    }
}
