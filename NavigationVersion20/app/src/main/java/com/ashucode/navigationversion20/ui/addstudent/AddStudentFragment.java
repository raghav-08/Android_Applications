package com.ashucode.navigationversion20.ui.addstudent;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.ashucode.navigationversion20.DBHelper;
import com.ashucode.navigationversion20.R;

public class AddStudentFragment extends Fragment {
    Button savebtn;
    View v;
    DBHelper DB;
    EditText name,age,address;
    RadioGroup radioGroup;
    RadioButton radioButton;
    String sex;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
            v =inflater.inflate(R.layout.fragment_add_student, container, false);
            savebtn = v.findViewById(R.id.savebtn);
            name=v.findViewById(R.id.stuName);
            age=v.findViewById(R.id.stuAge);
            radioGroup=v.findViewById(R.id.radio);
            address=v.findViewById(R.id.stuAddress);

            savebtn.setOnClickListener(view -> {
                String nameTXT = name.getText().toString();
                String ageTXT = age.getText().toString();
                String addressTXT = address.getText().toString();
                int radioId = radioGroup.getCheckedRadioButtonId();
                radioButton = v.findViewById(radioId);
                sex = radioButton.toString();
                DB= new DBHelper(getActivity());
                DB.insertdata(nameTXT,ageTXT,sex,addressTXT);
                Navigation.findNavController(view).navigate(R.id.action_addStudentFragment_to_marksFragment);
            });
        return v;

    }
}
