package com.example.lint.h;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.lint.h.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);// 设置左上角的返回的图标
            //actionBar.setHomeAsUpIndicator(R.drawable.ic_done);// 给左上角图标的左边加上
        }

        navView.setCheckedItem(R.id.nav_call);
        //下面设置navigationview的menu监听
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.nav_call: {
                        break;
                    }
                    case R.id.nav_friends:
                        //Toast.makeText(this, "You clicked nav_friends", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_location:
                       // Toast.makeText(this, "You clicked nav_locatione", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_mail:
                       // Toast.makeText(this, "You clicked nav_mail", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_task:
                        Intent intent = new Intent(MainActivity.this, com.example.lint.h.LoginActivity.class);
                        startActivity(intent);
                        break;
                    default:
                }

                mDrawerLayout.closeDrawers();
                return true;

            }
        });
    }

        public boolean onCreateOptionsMenu (Menu menu){//加载Toolbar 的menu文件
            getMenuInflater().inflate(R.menu.toolbar, menu);
            return true;
        }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//对Toolbar的menu进程监听
        switch (item.getItemId()) {
            case android.R.id.home://主键id 必须这样写
                mDrawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.backup:
                Toast.makeText(this, "You clicked Backup", Toast.LENGTH_SHORT).show();
                break;
            case R.id.delete:
                Toast.makeText(this, "You clicked Delete", Toast.LENGTH_SHORT).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "You clicked Settings", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }

}
