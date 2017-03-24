package cn.yky.realm;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.realm.Realm;
import io.realm.RealmResults;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.tv_show)
    TextView tvShow;
    private Realm realm;
    int i = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //初始化Realm
        realm = Realm.getDefaultInstance();
    }

    @OnClick({R.id.bt_add, R.id.bt_delete, R.id.bt_updata, R.id.bt_query})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_add:
                add();
                break;
            case R.id.bt_delete:
                delete();
                break;
            case R.id.bt_updata:
                updata();
                break;
            case R.id.bt_query:
                query();
                break;
        }
    }

    /**
     * 这是一个添加一条数据的方法
     */
    public void add() {
        i = i + 1;
        realm.beginTransaction();
        User user = realm.createObject(User.class); // Create a new object
        user.Name = "王祖贤";
        user.Age = 23 + i;
        user.sex = 0;
        realm.commitTransaction();
    }

    /**
     * 这是一个删除一条数据的方法
     */
    public void delete() {
        realm.beginTransaction();
        RealmResults<User> guests = realm.where(User.class).equalTo("sex", 0).findAll();
        for (User guest : guests) {
            if (guest.Age > 28) {
                guest.deleteFromRealm();
            }
        }
        realm.commitTransaction();
    }

    /**
     * 这是一条更新的方法
     */
    public void updata() {
        realm.beginTransaction();
        RealmResults<User> guests = realm.where(User.class).equalTo("sex", 0).findAll();
        for (User guest : guests) {
            guest.Age = 48;
        }
        realm.commitTransaction();
    }

    /**
     * 这是一个一个查询的方法
     */
    public void query() {
        realm.beginTransaction();
        RealmResults<User> guests = realm.where(User.class).equalTo("sex", 0).findAll();
        realm.commitTransaction();
        tvShow.setText("");
        String show = "";
        for (User guest : guests) {
            show = show + "/\n" + guest.Name + "**" + guest.Age + "**" + guest.sex;
        }
        tvShow.setText(show);
    }
}
