package com.matto.model;

import android.util.Log;

import com.matto.model.http.MainService;
import com.matto.model.http.ServiceFactory;
import com.matto.pojo.Gank;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * author meikoz on 2016/4/13.
 * email  meikoz@126.com
 */
public class LoginLogicImpl implements LoginLogic {

    MainService mainService;
    LoginView mView;

    @Override
    public void login(String name, String passwrod) {
        if (name.equals("zhangsan") && passwrod.equals("123")) {
            Log.d("cs", "登录成功,通知Activity");
            mainService.getMainAndroid(1, 20).enqueue(new Callback<Gank>() {
                @Override
                public void onResponse(Call<Gank> call, Response<Gank> response) {
                    Log.d("cs", "请求成功" + response.body().toString());
                    mView.onLoginSuccess();
                }

                @Override
                public void onFailure(Call<Gank> call, Throwable t) {
                    Log.d("cs", "登录失败,通知UI");
                    mView.onLoginFail();
                }
            });
        }
    }

    @Override
    public void attachView(LoginView mvpView) {
        this.mView = mvpView;
        this.mainService = ServiceFactory.getMainIns();
    }

}
