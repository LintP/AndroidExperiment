package com.example.lint.g;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private FragmentPagerAdapter pagerAdapter;
    private List<Fragment> fragments;

    private LinearLayout wechat_tab,friends_tab,contact_tab,setting_tab;
    private ImageButton btn_wechat,btn_friends,btn_contact,btn_setting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initViews();
        initEvent();
        initDates();
    }

    private void initDates(){
        fragments=new ArrayList<>();
        fragments.add(new WeChatFragment());
        fragments.add(new FriendFragment());
        fragments.add(new ContactFragment());
        fragments.add(new SettingFragment());

        pagerAdapter=new FragmentPagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int i) {
                return fragments.get(i);
            }

            @Override
            public int getCount() {
                return fragments.size();
            }
        };
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                viewPager.setCurrentItem(i);
                resetImgs();
                selectTab(i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private void initEvent(){
        btn_wechat.setOnClickListener(onClickListener);
        btn_contact.setOnClickListener(onClickListener);
        btn_setting.setOnClickListener(onClickListener);
        btn_friends.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener=new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            resetImgs();

            switch (v.getId()){
                case R.id.btn_chat:selectTab(0);break;
                case R.id.btn_friends:selectTab(1);break;
                case R.id.btn_task:selectTab(2);break;
                case R.id.btn_set:selectTab(3);break;
            }
        }
    };

    //初始化
    private void initViews(){
        viewPager=findViewById(R.id.viewpager);
        wechat_tab=findViewById(R.id.tab_chat);
        friends_tab=findViewById(R.id.tab_friends);
        contact_tab=findViewById(R.id.tab_task);
        setting_tab=findViewById(R.id.tab_setting);

        btn_wechat=findViewById(R.id.btn_chat);
        btn_friends=findViewById(R.id.btn_friends);
        btn_contact=findViewById(R.id.btn_task);
        btn_setting=findViewById(R.id.btn_set);
    }

    private void selectTab(int i){
        switch (i){
            case 0:btn_wechat.setImageResource(R.mipmap.chat_a);break;
            case 1:btn_friends.setImageResource(R.mipmap.friends_a);break;
            case 2:btn_contact.setImageResource(R.mipmap.task_a);break;
            case 3:btn_setting.setImageResource(R.mipmap.set_a);
        }

        viewPager.setCurrentItem(i);
    }

    private void resetImgs(){
        btn_wechat.setImageResource(R.mipmap.chat);
        btn_friends.setImageResource(R.mipmap.friends);
        btn_contact.setImageResource(R.mipmap.task);
        btn_setting.setImageResource(R.mipmap.set);
    }
}
