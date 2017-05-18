package com.xiaoma.designstudy;

import android.content.Context;
import android.content.Intent;

/**
 * Created by xiaoma on 2017/5/17.
 */

public interface UserState {
    
    /*
      定义用户登录、退出后两种状态 评论和跳转的不同转向
     */
    
    void forward(Context context, Intent intent);
    
    void comment(Context context);
    
}
