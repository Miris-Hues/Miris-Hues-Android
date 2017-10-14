package com.miris.hues.miris_hues_android.home;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.miris.hues.miris_hues_android.R;

import java.util.Set;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements HomeContract.View {
    private HomePresenter mHomePresenter;
    private Button dataGetBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        boolean isPermissionAllowed = isNotiPermissionAllowed();

        if (!isPermissionAllowed) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        }
    }

    private boolean isNotiPermissionAllowed() {
        Set<String> notiListenerSet = NotificationManagerCompat.getEnabledListenerPackages(this);
        String myPackageName = getPackageName();

        for (String packageName : notiListenerSet) {
            if (packageName == null) {
                continue;
            }
            if (packageName.equals(myPackageName)) {
                return true;
            }
        }

        return false;
    }

    @Override
    public void initView() {
        ButterKnife.bind(this);

        mHomePresenter = new HomePresenter(this);

        dataGetBtn = (Button) findViewById(R.id.main_dataget);
    }

    @OnClick(R.id.main_dataget)
    public void dataGetBtnClicked() {
        mHomePresenter.jsonDataGetClicked();
    }
}
