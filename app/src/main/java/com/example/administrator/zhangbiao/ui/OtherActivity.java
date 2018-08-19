package com.example.administrator.zhangbiao.ui;

import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.os.Message;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

import com.example.administrator.zhangbiao.Adapter.MyAdapter;
import com.example.administrator.zhangbiao.Beans.MyBean;
import com.example.administrator.zhangbiao.R;
import com.example.administrator.zhangbiao.Utils.HttpUtils;
import com.google.gson.Gson;

import java.io.IOException;
import java.lang.ref.WeakReference;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class OtherActivity extends AppCompatActivity {
    class MyHandler extends android.os.Handler {
        // 虚引用的引用
        // 参考 https://www.jianshu.com/p/d65d7030fb3a
        private WeakReference<Activity> mactivity;


        public MyHandler(Activity activity) {
            mactivity = new WeakReference<Activity>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            MyBean myBean = (MyBean) msg.obj;
            Activity activity = mactivity.get();
            if (activity != null) {
                MyAdapter adapter = new MyAdapter(OtherActivity.this,myBean.getResults());
                recyclerView.setAdapter(adapter);
            }
        }
    }
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private MyHandler myHandler = new MyHandler(this);



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_other);
        //使标题栏延伸到状态栏
        View docerView = getWindow().getDecorView();
        int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
        docerView.setSystemUiVisibility(option);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        initViews();
        initData();
    }

    private void initViews() {
        toolbar = findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        //支持toobar的操作  （注：清单文件中吧actionbar去掉）
        setSupportActionBar(toolbar);
    }

    private void initData() {
        String Url = "https://gank.io/api/data/福利/10/1";
        //callback 回调运行在子线程中的 不能更新UI的
        HttpUtils.OkHttpAsyncGetRequest(Url, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Gson gson = new Gson();
                MyBean myBean = gson.fromJson(response.body().string(), MyBean.class);
                Log.i("1234", "集合大小" + myBean.getResults().size());
                Message msg = Message.obtain();// 获得对象的方式比较好  相对new (自行百度)
                msg.what = 0x11;
                msg.obj = myBean;
                myHandler.sendMessage(msg);
            }
        });

        // 设置布局管理  默认动画
        GridLayoutManager manager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(manager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());


    }
}
