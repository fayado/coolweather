package com.example.coolweather.util;

import java.util.List;

/**
 * Created by admin on 2015/4/23.
 */
public interface HttpCallbackListener {
    void onFinish(List<weather> weathers);
    void onError(Exception e);
}
