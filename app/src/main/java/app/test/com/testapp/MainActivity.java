package app.test.com.testapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.File;

public class MainActivity extends BaseAct implements View.OnClickListener {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Button btn = (Button) findViewById(R.id.btn_eventbus);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_pull_to_refresh);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_http_test);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_showbar);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_hidebar);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_chart);
        btn.setOnClickListener(this);

        EventBus.getDefault().register(this);

        File file = new File(Common.APPDIR);
        if (!file.exists()) {
            file.mkdirs();
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_eventbus:
                EventBus.getDefault().post(new MessageEvent(1, "Hello EventBus!"));
                break;
            case R.id.btn_pull_to_refresh:
                startActivity(new Intent(MainActivity.this, PullToRefresh.class));
                break;
            case R.id.btn_http_test:
                startActivity(new Intent(MainActivity.this, HttpTest.class));
                break;
            case R.id.btn_showbar:
                sendBroadcast(new Intent("android.intent.action.SHOW_NAVIGATION_BAR"));
                break;
            case R.id.btn_hidebar:
                sendBroadcast(new Intent("android.intent.action.HIDE_NAVIGATION_BAR"));
                break;
            case R.id.btn_chart:
                startActivity(new Intent(MainActivity.this, ChartAct.class));
                break;
            default:
                break;
        }
    }

    public class MessageEvent {
        public int iEvent;
        public Object msg;

        MessageEvent(int event, Object obj) {
            this.iEvent = event;
            this.msg = obj;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMainThread(MessageEvent event) {
        String msg = "onMainThread收到了消息：" + event.msg.toString();
        //        Log.d(TAG, msg);
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }
}
