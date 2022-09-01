package com.ashucode.navigationversion20.ui.home;

import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ashucode.navigationversion20.DBHelper;
import com.ashucode.navigationversion20.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    Button addStudent;
    View v;
    final ArrayList<HomeModel> list = new ArrayList<>();

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v= inflater.inflate(R.layout.fragment_home, container, false);

        addStudent = v.findViewById(R.id.add_student_btn);
        RecyclerView recyclerView = v.findViewById(R.id.home_RV);
        DBHelper DB = new DBHelper(getActivity());
        Cursor cursor = DB.getdata();

        String str1,str2,str3;

        str3 = "06/04/2022";
        while (cursor.moveToNext())
        {
            str1=(cursor.getString(0));
            str2=(cursor.getString(1));
            list.add(new HomeModel(str1,str2,str3));
        }
        HomeAdapter adapter = new HomeAdapter(getActivity(),list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);


        addStudent.setOnClickListener(view -> Navigation.findNavController(view).navigate(R.id.action_nav_home_to_addStudentFragment));
        return v;
    }
}