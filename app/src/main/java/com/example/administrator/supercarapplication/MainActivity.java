package com.example.administrator.supercarapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.administrator.supercarapplication.fragment.FirstFragment;
import com.example.administrator.supercarapplication.fragment.SecondFragement;
import com.example.administrator.supercarapplication.fragment.ThirdFragment;
import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ViewInject;

public class MainActivity extends FragmentActivity {

    @ViewInject(R.id.framelayout_main)
    private FrameLayout mFrameLayout;//主界面上添加Fragment的FrameLayout
    @ViewInject(R.id.radiogroup_main)
    private RadioGroup mRadioGroupMain;//主界面上放置底部三个RadioButton的RadioGroup
    @ViewInject(R.id.radiobutton_home)
    private RadioButton mRadioButtonHome;//主界面底部的首页按钮
    @ViewInject(R.id.radiobutton_home_near)
    private RadioButton mRadioButtonHomeNear;//主界面底部的周边按钮
    @ViewInject(R.id.radiobutton_home_user)
    private RadioButton mRadioButtonHomeUser;//主界面底部的我的按钮
    //三个Fragment
    private FirstFragment mFirstFragment;
    private SecondFragement mSecondFragment;
    private ThirdFragment mThirdFragment;
    //Fragment管理器
    private FragmentManager mFragmentManager;
    private FragmentTransaction mFragmentTransaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewUtils.inject(this);
        //得到三个Fragment
        mFirstFragment=new FirstFragment();
        mSecondFragment=new SecondFragement();
        mThirdFragment=new ThirdFragment();

        mFragmentManager=getSupportFragmentManager();
        mFragmentTransaction=mFragmentManager.beginTransaction();
        //默认选择第一个fragment
        mFragmentTransaction.replace(R.id.framelayout_main,mFirstFragment);
        mFragmentTransaction.commit();
        mRadioGroupMain.check(R.id.radiobutton_home);
        //点击选择不同的fragment
        mRadioGroupMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                mFragmentTransaction=mFragmentManager.beginTransaction();
                switch (checkedId){
                    case R.id.radiobutton_home:
                        mFragmentTransaction.replace(R.id.framelayout_main,mFirstFragment);
                        mFragmentTransaction.commit();
                        break;
                    case R.id.radiobutton_home_near:
                        mFragmentTransaction.replace(R.id.framelayout_main,mSecondFragment);
                        mFragmentTransaction.commit();
                        break;
                    case R.id.radiobutton_home_user:
                        mFragmentTransaction.replace(R.id.framelayout_main,mThirdFragment);
                        mFragmentTransaction.commit();
                        break;
                }
            }
        });
    }
}
