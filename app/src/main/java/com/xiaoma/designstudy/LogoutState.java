package com.xiaoma.designstudy;

import android.content.Context;
import android.content.Intent;

/**
 * Created by xiaoma on 2017/5/17.
 */

public class LogoutState implements UserState {


    @Override
    public void forward(Context context, Intent intent) {
        goToLoginActivity(context);
    }

    @Override
    public void comment(Context context) {
        goToLoginActivity(context);
    }

    private void goToLoginActivity(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);
    }
}
