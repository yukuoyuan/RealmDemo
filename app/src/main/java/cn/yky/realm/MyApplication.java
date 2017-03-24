package cn.yky.realm;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by yukuoyuan on 2017/3/24.
 */

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
