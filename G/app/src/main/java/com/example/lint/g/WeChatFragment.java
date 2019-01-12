package com.example.lint.g;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class WeChatFragment extends Fragment {

    public WeChatFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceSttate){
        return inflater.inflate(R.layout.fragment_wechat,container,false);
    }
}
