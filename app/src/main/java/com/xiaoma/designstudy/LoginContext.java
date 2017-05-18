package com.xiaoma.designstudy;

import android.content.Context;
import android.content.Intent;

/**
 * Created by xiaoma on 2017/5/17.
 */

public class LoginContext {

    private LoginContext() {
    }

    private static LoginContext mLoginContext;
    
    public static LoginContext getInstance() {
        if (mLoginContext == null) {
            synchronized (LoginContext.class) {
                if (mLoginContext == null) {
                    mLoginContext = new LoginContext();
                }
            }
        }
        return mLoginContext;
    }

    private UserState mUserState;

    public void setUserState(UserState state) {
        mUserState = state;
    }
    
    private void checkLogin(){
        String token = PreferenceUtil.getInstance().getToken("token");
        if (null != token) {
            //已登录
            setUserState(new LoginedState());
//            mUserState = new LoginedState();
        } else {
            //未登录
//            mUserState = new LogoutState();
            setUserState(new LogoutState());
        }
    }
    
    public void forward(Context context, Intent intent){
        checkLogin();
        mUserState.forward(context,intent);
    }
    
    public void comment(Context context){
        checkLogin();
        mUserState.comment(context);
    } 
}
