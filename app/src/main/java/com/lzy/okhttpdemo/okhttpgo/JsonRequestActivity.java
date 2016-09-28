package com.lzy.okhttpdemo.okhttpgo;

import android.os.Bundle;
import android.view.View;

import com.lzy.okhttpdemo.R;
import com.lzy.okhttpdemo.base.BaseDetailActivity;
import com.lzy.okhttpdemo.callback.DialogCallback;
import com.lzy.okhttpdemo.model.ServerModel;
import com.lzy.okhttpdemo.utils.Urls;
import com.lzy.okhttpgo.OkHttpGo;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Response;

public class JsonRequestActivity extends BaseDetailActivity {

    @Override
    protected void onActivityCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_custom_request);
        ButterKnife.bind(this);
        actionBar.setTitle("自动解析Json对象");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //Activity销毁时，取消网络请求
        OkHttpGo.getInstance().cancelTag(this);
    }

    /**
     * 解析javabean对象
     */
    @OnClick(R.id.requestJson)
    public void requestJson(View view) {
        OkHttpGo.get(Urls.URL_JSONOBJECT)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new DialogCallback<ServerModel>(this) {
                    @Override
                    public void onSuccess(ServerModel serverModel, Call call, Response response) {
                        handleResponse(serverModel, call, response);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        handleError(call, response);
                    }
                });
    }

    /**
     * 解析集合对象
     */
    @OnClick(R.id.requestJsonArray)
    public void requestJsonArray(View view) {
        OkHttpGo.get(Urls.URL_JSONARRAY)//
                .tag(this)//
                .headers("header1", "headerValue1")//
                .params("param1", "paramValue1")//
                .execute(new DialogCallback<List<ServerModel>>(this) {
                    @Override
                    public void onSuccess(List<ServerModel> serverModels, Call call, Response response) {
                        handleResponse(serverModels, call, response);
                    }

                    @Override
                    public void onError(Call call, Response response, Exception e) {
                        super.onError(call, response, e);
                        handleError(call, response);
                    }
                });
    }
}
