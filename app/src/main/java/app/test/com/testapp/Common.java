package app.test.com.testapp;

import android.os.Environment;

/**
 * Created by YZJ on 2017/7/6.
 */

public class Common {

    public static final String SDCARD = Environment.getExternalStorageDirectory().getAbsolutePath();
    public static final String APPDIR = SDCARD + "/TestApp/";


}
