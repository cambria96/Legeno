package com.example.billage.frontend.ui.quest.subView.daily;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.billage.R;
import com.example.billage.backend.common.AppData;
import com.example.billage.frontend.adapter.QuestAdapter;
import com.example.billage.frontend.data.QuestList;

import java.util.ArrayList;


public class Daily extends Fragment {

    public Daily() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.quest_daily, container, false);
        // Inflate the layout for this fragment

     // ArrayList<UsageList> items = AppData.mdb.getTransColumns();
        ArrayList<QuestList> items = new ArrayList<>();
        items.add(new QuestList(1,"계획 소비","일일 소비량을 10000원 이내로 사용한다.","진행중","1200","일간"));
        items.add(new QuestList(1,"계획 소비","일일 소비량을 10000원 이내로 사용한다.","진행중","1200","일간"));
        items.add(new QuestList(1,"계획 소비","일일 소비량을 10000원 이내로 사용한다.","진행중","1200","일간"));
        items.add(new QuestList(1,"계획 소비","일일 소비량을 10000원 이내로 사용한다.","진행중","1200","일간"));

        ListView listview = (ListView) root.findViewById(R.id.daliy_list) ;
        // listview.setAdapter(adapter) ;

        QuestAdapter usageAdapter = new QuestAdapter(getActivity(),items,listview,getActivity());
        listview.setAdapter(usageAdapter);
        return root;
    }
}
