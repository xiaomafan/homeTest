package com.xiaoma.designstudy;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by xiaoma on 2017/5/17.
 */

public class LoginedState implements UserState {


    @Override
    public void forward(Context context,Intent intent) {
        context.startActivity(intent);
    }

    @Override
    public void comment(Context context) {
        Toast.makeText(context,"跳转到评论区",Toast.LENGTH_SHORT).show();
    }
}
