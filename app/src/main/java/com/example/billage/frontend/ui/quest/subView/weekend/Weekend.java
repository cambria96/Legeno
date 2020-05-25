package com.example.billage.frontend.ui.quest.subView.weekend;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.billage.R;
import com.example.billage.backend.QuestChecker;
import com.example.billage.frontend.MainActivity;
import com.example.billage.frontend.adapter.QuestAdapter;
import com.example.billage.frontend.data.QuestList;

import org.json.JSONException;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Weekend extends Fragment {


    public Weekend() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        int complete_rate = 25;
        // Inflate the layout for this fragment\
        View root = inflater.inflate(R.layout.quest_weekend, container, false);

        MainActivity mainActivity = new MainActivity();
        QuestChecker questChecker = mainActivity.getQuestChecker();

        ArrayList<QuestList> items = null;
        try {
            items = questChecker.parseQuestList();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        int size = items.size();
        for(int i=0;i<size;i++){
            if(!(items.get(i).getType().equals("weekly"))){
                items.remove(i);
                size--;
                i--;
            }
        }

        ListView listview = (ListView) root.findViewById(R.id.weekend_list);

        QuestAdapter questAdapter = new QuestAdapter(getActivity(),items,listview,getActivity());
        listview.setAdapter(questAdapter);

        ProgressBar progressBar = root.findViewById(R.id.quest_progress);
        progressBar.setProgress(complete_rate);

        TextView progress_text = root.findViewById(R.id.progress_text);
        progress_text.setText(complete_rate+"%");
        return root;
    }
}
