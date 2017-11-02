package app.test.com.testapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cunoraz.gifview.library.GifView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.callback.BitmapCallback;
import com.lzy.okgo.callback.FileCallback;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Progress;

import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class HttpTest extends BaseAct implements View.OnClickListener {
    private final String TAG = "HttpTest";
    private TextView textView;
    private ImageView imageView;
    private int index = 0;
    private String url = "";
    private GifView gifView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_http_test);
        Button btn = (Button) findViewById(R.id.btn_okgo);
        btn.setOnClickListener(this);
        btn = (Button) findViewById(R.id.btn_okHttp);
        btn.setOnClickListener(this);
        textView = (TextView) findViewById(R.id.tv_http);
        textView.setMovementMethod(ScrollingMovementMethod.getInstance());
        imageView = (ImageView) findViewById(R.id.iv_http);

        //        gifView = (GifView) findViewById(R.id.gifView);
        //        gifView.setGifResource(R.mipmap.gif);
        //        gifView.play();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelTag(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_okgo:
                okgo(index);
                index++;
                break;
            case R.id.btn_okHttp:
                okhttp();
                break;

        }
    }

    private void okhttp() {
        OkHttpClient okHttpClient = new OkHttpClient().newBuilder().connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS).build();

        Request request = new Request.Builder().url("http://192.168.100.120:8080/employeeServerTime.action").build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                final String msg = e.toString();
                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("okhttp data : " + msg);
                    }
                });

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String data = response.body().string();

                textView.post(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText("okhttp data : " + data);
                    }
                });
            }
        });
    }

    private void okgo(int type) {
        //        'com.lzy.net:okgo:2.1.4'
        //        OkGo.get("http://192.168.100.220:8080/employeeServerTime.action")
        //                .tag(this)
        //                .cacheKey("cacheKey")
        //                .cacheMode(CacheMode.DEFAULT)
        //                .execute(new StringCallback() {
        //                    @Override
        //                    public void onSuccess(String s, Call call, Response response) {
        //                        String data = s;
        //                        textView.setText("okgo data : " + data);
        //                    }
        //
        //                    @Override
        //                    public void onError(Call call, Response response, Exception e) {
        //                        super.onError(call, response, e);
        //                        textView.setText("okgo error : " + e.toString());
        //                    }
        //                });

        //'com.lzy.net:okgo:3.0.2'
        type %= 5;
        switch (type) {
            case 0:
                OkGo.<String>get("http://192.168.100.20:8080/employeeServerTime.action")
                        .tag(this)
                        .cacheKey(TAG)
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                String data = response.body();
                                textView.setText("OkGo Get String : " + data);
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<String> response) {
                                super.onError(response);
                                textView.setText("OkGo Get String error : " + response.body());
                            }
                        });
                break;
            case 1:
                //http://192.168.100.120:8080/employeeGetInfo.action?cardnum=1001
                OkGo.<String>get("http://192.168.100.20:8080/employeeGetInfo.action")
                        .tag(this)
                        .params("cardnum", "1001")
                        .cacheMode(CacheMode.DEFAULT)
                        .cacheKey(TAG)
                        .execute(new StringCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                                String data = response.body();
                                textView.setText("OkGo Get by params : " + data);
                            }
                        });

                break;
            case 2:
                String url1 = "http://pic.qjimage.com/pm0046/high/pm0046-2734vb.jpg";
                String url2 = "http://pic.qjimage.com/pm0050/high/pm0050-0384wd.jpg";
                if (url.equals(url1))
                    url = url2;
                else
                    url = url1;
                OkGo.<Bitmap>get(url)
                        .tag(this)
                        .cacheKey(TAG)
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new BitmapCallback() {
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<Bitmap> response) {
                                Bitmap bitmap = response.body();
                                imageView.setImageBitmap(bitmap);
                                textView.setText("OkGo Get Image!");
                            }

                            @Override
                            public void onError(com.lzy.okgo.model.Response<Bitmap> response) {
                                super.onError(response);
                                textView.setText("OkGo Get Image error!");
                            }
                        });

                //                Glide.with(this).load(url).into(imageView);

                break;

            case 3://http://down.360safe.com/setup.exe
                //http://a.ikafan.com/attachment/forum/201307/16/184001fbs5f6mq9onyz5vv.jpg
                String url3 = "http://img1.imgtn.bdimg.com/it/u=2522650831,2287600360&fm=214&gp=0.jpg";
                String url4 = "http://img.52z.com/upload/201607/28/1469691222.gif";
                OkGo.<File>get(url4)
                        .tag(this)
                        .cacheKey(TAG)
                        .cacheMode(CacheMode.DEFAULT)
                        .execute(new FileCallback(Common.APPDIR, null) {//1.gif
                            @Override
                            public void onSuccess(com.lzy.okgo.model.Response<File> response) {
                                File file = response.body();
                                String path = file.getAbsolutePath();
                                if (path.endsWith("jpg") || path.endsWith("gif")) {
                                    Glide.with(HttpTest.this).load(path).into(imageView);
                                }
                            }

                            @Override
                            public void downloadProgress(Progress progress) {
                                super.downloadProgress(progress);
                                textView.setText("OkGo download file : " + progress.filePath + " %" + progress.currentSize * 100 / progress.totalSize);
                            }
                        });
                break;


            case 4:
                textView.setText("No No No!");
                Map<String, String> params = new HashMap<>();
                params.put("a", "a");
                params.put("b", "b");
                JSONObject json = new JSONObject(params);


                break;

            default:

                break;
        }
    }
}
