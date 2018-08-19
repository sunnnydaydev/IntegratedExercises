package com.example.administrator.zhangbiao.Utils;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * Created by 晴天 on 2018/8/19.
 */

public class HttpUtils {
    // get 异步请求   注： callback回调在子线程中执行
   public static void OkHttpAsyncGetRequest(String url,okhttp3.Callback callback){
       OkHttpClient client = new OkHttpClient();
       Request request = new Request.Builder()
               .url(url)
               .get()
               .build();
       client.newCall(request).enqueue(callback);
   }
}
