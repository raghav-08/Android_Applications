package com.ashucode.navigationversion20.ui.marks;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.ashucode.navigationversion20.DBHelper;
import com.ashucode.navigationversion20.R;


public class MarksFragment extends Fragment {

    Button savemarks;
    View v;
    private DBHelper DB;
    private EditText english,math,science,comApp,SS;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_marks, container, false);
        savemarks = v.findViewById(R.id.save_marks_btn);
        english =v.findViewById(R.id.et_English);
        math =v.findViewById(R.id.et_Maths);
        science =v.findViewById(R.id.et_Science);
        comApp =v.findViewById(R.id.et_CA);
        SS =v.findViewById(R.id.et_SS);
        savemarks.setOnClickListener(view -> {
            DB= new DBHelper(getActivity());
            DB.insertMarksdetails(english.getText().toString(),math.getText().toString()
                    ,science.getText().toString(),comApp.getText().toString(),SS.getText().toString());
            Navigation.findNavController(view).navigate(R.id.action_marksFragment_to_nav_home2);
        });
        return v;
    }
}