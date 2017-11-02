package app.test.com.testapp;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;

public class PullToRefresh extends BaseAct {

    private PtrClassicFrameLayout mPtrFrame;
    private TextView textview;
    private int i = 10000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pull_to_refresh);
        textview = (TextView) findViewById(R.id.tv_text);
        mPtrFrame = (PtrClassicFrameLayout) findViewById(R.id.store_house_ptr_frame);
        mPtrFrame.setResistance(1.7f);
        mPtrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        mPtrFrame.setDurationToClose(200);
        mPtrFrame.setDurationToCloseHeader(1000);
        // default is false
        mPtrFrame.setPullToRefresh(false);
        // default is true
        mPtrFrame.setKeepHeaderWhenRefresh(true);
        //        mPtrFrame.setLastUpdateTimeRelateObject(this);//上次刷新时间
        mPtrFrame.setPtrHandler(new PtrHandler() {
            @Override

            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }

        });

        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 1000);


    }

    private void updateData() {
        i++;
        textview.setText(i + "");
        mPtrFrame.refreshComplete();
    }


}

