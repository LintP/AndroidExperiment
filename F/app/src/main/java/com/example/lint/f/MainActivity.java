package com.example.lint.f;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private ListView list;
    private List<Map<String,Object>> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (getSupportActionBar() != null){
            getSupportActionBar().hide();
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=findViewById(R.id.list);
        mapList=getData();
        Mydapter mydapter=new Mydapter(this);
        list.setAdapter(mydapter);
    }

    private List<Map<String,Object>> getData(){
        List<Map<String,Object>> list=new ArrayList<Map<String,Object>>();
        Map<String,Object> map=new HashMap<String, Object>();

        map.put("title","江户川柯南");
        map.put("info","外表看似小孩，其真实身份是高中生侦探——工藤新一。和青梅竹马的同学毛利兰一起去游乐园玩，目击到黑衣男子的可疑交易现场，被灌下开发中的药物，变成了小学生的身体。那天以后，为了隐藏真实身份，化名江户川柯南，在青梅竹马的毛利兰家寄住的同时，日复一日解决了许多案件。一切都是为了恢复自己的身体。");
        map.put("thumb",R.raw.kn);
        list.add(map);

        map=new HashMap<String, Object>();

        map.put("title","工藤新一 ");
        map.put("info","崇拜夏洛克·福尔摩斯的高中生侦探。5月4日出生，17岁。帝丹高中2年级B班。还是高中1年级学生的时候，在去往洛杉矶的飞机上解决了杀人案，在那里和目暮警官等人相识。此后，作为高中生侦探活跃起来。特长是足球，头脑清醒出众。");
        map.put("thumb",R.raw.gt);
        list.add(map);

        map=new HashMap<String, Object>();

        map.put("title","毛利兰");
        map.put("info","新一的青梅竹马，帝丹高中2年级B班，空手道部女主将，都大会中有头号实力的文武双全的女生。一直喜欢着新一，在他突然消失后等待着他的归来。有时柯南的名推理会与新一的身影重叠，她开始像母亲一般温柔地照顾着柯南和少年侦探团。此外，和铃木园子是青梅竹马的挚友，从以前开始便无话不谈。");
        map.put("thumb",R.raw.ml);
        list.add(map);

        map=new HashMap<String, Object>();

        map.put("title","毛利小五郎");
        map.put("info","小兰的父亲，前警视厅搜查一课刑警，与目暮警部是故交。现在在毛利侦探事务所营业，多亏了柯南的名推理，以“沉睡的小五郎”闻名。经过小兰的拼命努力，经常与分居中的妻子——妃英理见面，有好色的一面妨碍着，很难坦率地重修旧好。");
        map.put("thumb",R.raw.xwl);
        list.add(map);

        map=new HashMap<String, Object>();

        map.put("title","阿笠博士");
        map.put("info","住在新一隔壁的古怪发明家。知道柯南真实身份的少数人物之一。是发明了领结型变声器、脚力增强鞋、侦探徽章和足球射出腰带等许多侦探产品的理解者，也照料着少年侦探团。在灰原哀逃离组织之后保护着她。");
        map.put("thumb",R.raw.al);
        list.add(map);

        map=new HashMap<String, Object>();

        map.put("title","灰原哀");
        map.put("info","外表是小孩，但其实是黑衣组织的一员，新一被灌下的毒药（APTX4869）的开发者，代号雪莉。姐姐被杀，背叛了组织，为了逃脱而服下了药物，变成小学生的样子。现在藏在阿笠博士家，和柯南一起在帝丹小学上学，另一边研究着解药。柯南是这世上唯一能与她分享相同境遇的对象。");
        map.put("thumb",R.raw.hya);
        list.add(map);

        return list;
    }

    public final class ViewHolder{
        public ImageView thumb;
        public TextView title,info;
        public ImageButton ibtn;
    }

    public class Mydapter extends BaseAdapter{

        private LayoutInflater mInflater;

        public Mydapter(Context context){
            this.mInflater=LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return mapList.size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            ViewHolder holder=null;

            if(convertView==null){
                holder=new ViewHolder();
                convertView=mInflater.inflate(R.layout.item,null);
                holder.thumb=convertView.findViewById(R.id.thumb);
                holder.title=convertView.findViewById(R.id.title);
                holder.info=convertView.findViewById(R.id.info);
                holder.ibtn=convertView.findViewById(R.id.ibt);
                convertView.setTag(holder);
            }else{
                holder=(ViewHolder)convertView.getTag();
            }

            holder.thumb.setBackgroundResource((Integer)mapList.get(position).get("thumb"));
            holder.title.setText((String)mapList.get(position).get("title"));
            holder.info.setText((String)mapList.get(position).get("info"));
            holder.ibtn.setTag(position);
            holder.ibtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showInfo(position);
                }
            });
            return convertView;
        }
    }

    public void showInfo(int position){
        new AlertDialog.Builder(this)
                .setTitle(mapList.get(position).get("title").toString())
                .setMessage(mapList.get(position).get("info").toString())
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                }).show();
    }
}
