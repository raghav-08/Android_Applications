package com.ashucode.navigationversion20.ui.view;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ashucode.navigationversion20.DBHelper;
import com.ashucode.navigationversion20.R;

import java.util.ArrayList;


public class ViewFragment extends Fragment {

    View v;
    final ArrayList<ViewModel> list = new ArrayList<>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
         v=inflater.inflate(R.layout.fragment_view, container, false);
        RecyclerView recyclerView = v.findViewById(R.id.view_RV);
        DBHelper DB = new DBHelper(getActivity());
         Cursor cursor = DB.getdata();
         Cursor cursor2 = DB.getMarksData();
         int english,math,science,compApp,SS,avg;
         String s1,s2;

        while(cursor2.moveToNext() && cursor.moveToNext())
        {
            english = new Integer(cursor2.getString(1));
            math = new Integer(cursor2.getString(2));
            science = new Integer(cursor2.getString(3));
            compApp = new Integer(cursor2.getString(4));
            SS = new Integer(cursor2.getString(5));
            avg = (english+math+science+compApp+SS)/5;
            s2 = String.valueOf(avg);
            s1=(cursor.getString(1));
            list.add(new ViewModel(s1,s2));
        }

         ViewAdapter adapter = new ViewAdapter(getActivity(),list);
         recyclerView.setAdapter(adapter);
         LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
         recyclerView.setLayoutManager(layoutManager);
         return v;
    }
}