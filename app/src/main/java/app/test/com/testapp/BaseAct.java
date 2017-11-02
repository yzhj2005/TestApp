package app.test.com.testapp;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;

/**
 * Created by YZJ on 2017/7/5.
 */

public class BaseAct extends Activity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
}
