package com.miris.hues.miris_hues_android.home;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.miris.hues.miris_hues_android.R;

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
