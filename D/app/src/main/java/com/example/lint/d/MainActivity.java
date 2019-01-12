package com.example.lint.d;

import android.Manifest;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.provider.CallLog;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    TextView tv;
    Button btn_all,btn_find,btn_delete,btn_add;
    ContentResolver resolver;
    String[] projection;
    private static int requestCode=2;
    final static Uri CALLLOGURI= CallLog.Calls.CONTENT_URI;
    SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd:hh:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv=findViewById(R.id.textView);
        btn_all=findViewById(R.id.btn_all);
        btn_find=findViewById(R.id.btn_find);
        btn_delete=findViewById(R.id.btn_delete);
        btn_add=findViewById(R.id.btn_add);

        btn_all.setOnClickListener(this);
        btn_find.setOnClickListener(this);
        btn_delete.setOnClickListener(this);
        btn_add.setOnClickListener(this);

        resolver=getContentResolver();

        projection=new String[]{CallLog.Calls.CACHED_NAME,
                                CallLog.Calls.NUMBER,
                                CallLog.Calls.DATE,
                                CallLog.Calls.TYPE,
                                CallLog.Calls.DURATION};
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[]permissions,@NonNull int[]grantResults){
        super.onRequestPermissionsResult(requestCode,permissions,grantResults);

        if(grantResults.length>0){
            for(int i=0;i<grantResults.length;i++){
                int grantResult=grantResults[i];
                if(grantResult==PackageManager.PERMISSION_DENIED){
                    Toast.makeText(this,"你拒绝了授权",Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this,"你接受了授权",Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onClick(View v){
        if(ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CALL_LOG)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(MainActivity.this,new String[]{
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.WRITE_CALL_LOG},requestCode);
        }else{
            if(v==btn_add){
                fun_add();
            }else if(v==btn_all){
                fun_all();
            }else if(v==btn_delete){
                fun_delect();
            }else if(v==btn_find){
                fun_find();
            }
        }
    }

    protected void fun_add(){
        ContentValues values=new ContentValues();
        values.put(CallLog.Calls.CACHED_NAME,"移动");
        values.put(CallLog.Calls.NUMBER,"10086");
        values.put(CallLog.Calls.DATE,System.currentTimeMillis());
        values.put(CallLog.Calls.TYPE, CallLog.Calls.INCOMING_TYPE);
        Uri uri=resolver.insert(CALLLOGURI,values);

        Toast.makeText(getApplicationContext(),uri.toString(),Toast.LENGTH_LONG).show();

    }
    protected void fun_all(){
        /**
         * Cursor query(final @RequiresPermission.Read @NonNull Uri uri,
         @Nullable String[] projection, @Nullable Bundle queryArgs,
         @Nullable CancellationSignal cancellationSignal)*/
        Cursor cursor=resolver.query(CALLLOGURI,projection,null,null);
        startManagingCursor(cursor);
        tv.setText(convertToCalls(cursor));
    }
    protected void fun_delect(){
        int cnt=resolver.delete(CALLLOGURI, CallLog.Calls.CACHED_NAME+"=?",new String[]{"移动"});
        if(cnt>0){
            Toast.makeText(getApplicationContext(),"删除成功！",Toast.LENGTH_LONG).show();
        }
    }
    protected void fun_find(){
        Cursor cursor=resolver.query(CALLLOGURI,projection,CallLog.Calls.CACHED_NAME+"=?",new String[]{"移动"},null);
        tv.setText(convertToCalls(cursor));
    }

    protected String convertToCalls(Cursor cursor){
        String result="";

        if(cursor!=null){
            int cnt=cursor.getCount();
            cursor.moveToFirst();
            for(int i=0;i<cnt;i++){
                result+=cursor.getString(cursor.getColumnIndex(CallLog.Calls.CACHED_NAME))+"; ";
                result+=cursor.getString(cursor.getColumnIndex(CallLog.Calls.NUMBER))+"; ";

                String timeStr=cursor.getString(cursor.getColumnIndexOrThrow(CallLog.Calls.DATE));
                Long lg=Long.valueOf(timeStr);
                Date date=new Date(lg);
                String time=dateFormat.format(date);
                result+="; ";
            }
            int type= Integer.parseInt(cursor.getString(cursor.getColumnIndex(CallLog.Calls.TYPE)));
            switch(type){
                case CallLog.Calls.INCOMING_TYPE:
                    result+="已接来电； ";
                    break;
                case CallLog.Calls.OUTGOING_TYPE:
                    result+="已拨打； ";
                    break;
                case CallLog.Calls.MISSED_TYPE:
                    result+="未处理； ";
                    break;
            }
            result+=cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION))+"; ";
            result+="\n\r";
            cursor.moveToNext();
        }
        return result;
    }



}
