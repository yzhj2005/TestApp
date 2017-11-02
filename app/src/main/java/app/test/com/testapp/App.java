package app.test.com.testapp;

import android.app.Application;

import com.lzy.okgo.OkGo;

/**
 * Created by YZJ on 2017/7/5.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //        OkGo.getInstance()
        //                .init(this)
        //                .setRetryCount(3)
        //                .setCacheMode(CacheMode.DEFAULT)
        //                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE);
        OkGo.getInstance().init(this);


    }

}
