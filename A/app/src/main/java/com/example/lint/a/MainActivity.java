package com.example.lint.a;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    String []users;
    Button btn_login,btn_to;
    EditText username,email_from,email_to,email_theme,email_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_login=findViewById(R.id.btn_login);
        btn_to=findViewById(R.id.btn_to);

        username=findViewById(R.id.username);
        email_from=findViewById(R.id.email_from);
        email_to=findViewById(R.id.email_to);
        email_theme=findViewById(R.id.email_theme);
        email_msg=findViewById(R.id.email_msg);

        btn_to.setEnabled(true);//先禁用发送

        users=MainActivity.this.getResources().getStringArray(R.array.users);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean hasUser=false;
                for (String user:users) {
                    if(user.equals(username.getText().toString())) {
                        email_from.setText(user + " " + "460852283@qq.com");
                        hasUser = true;
                        btn_to.setEnabled(true);
                    }
                }
                if(!hasUser){
                    Toast.makeText(MainActivity.this,username.getText()+" 用户不存在！",Toast.LENGTH_LONG).show();
                    btn_to.setEnabled(false);
                }
            }
        });

        btn_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(email_to.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"请先填写收件人信息！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(email_msg.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"请先填写邮件内容！",Toast.LENGTH_LONG).show();
                    return;
                }
                if(email_theme.getText().toString().equals("")){
                    Toast.makeText(MainActivity.this,"由于你没有填写邮件主题，已为你自动选择默认主题！",Toast.LENGTH_SHORT).show();
                    email_theme.setText("默认主题");
                }

                Toast.makeText(MainActivity.this,"正在邮件添加基本信息",Toast.LENGTH_SHORT).show();


                email_msg.append("\r\n\r\n\t\t\t\t"+email_from.getText().toString());

                Toast.makeText(MainActivity.this,"正在向 "+email_to.getText()+" 发送信息！",Toast.LENGTH_SHORT).show();

                Toast.makeText(MainActivity.this,"向 "+email_to.getText()+" 发送信息成功！",Toast.LENGTH_LONG).show();
            }
        });
    }
}
