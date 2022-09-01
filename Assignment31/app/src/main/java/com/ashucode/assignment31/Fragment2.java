package com.ashucode.assignment31;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.CallLog;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class Fragment2 extends Fragment {

    private RecyclerView recyclerView;
    ArrayList<CallModel> arrayList1 = new ArrayList<CallModel>();
    private View v1;

    String str_call_date, str_call_time;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v1 = inflater.inflate(R.layout.recent_activity, container, false);
        recyclerView = v1.findViewById(R.id.call_RV);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView.LayoutManager layoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(layoutManager);

        CallAdapter adapter = new CallAdapter(getContext(), getCallList());

        recyclerView.setAdapter(adapter);

        return v1;
    }

    private ArrayList<CallModel> getCallList() {

        if(ContextCompat.checkSelfPermission(getContext()
                , Manifest.permission.READ_CALL_LOG)
                != PackageManager.PERMISSION_GRANTED)
        {
            //when permission not granted
            //Request permission
            ActivityCompat.requestPermissions(getActivity()
                    ,new String[]{Manifest.permission.READ_CALL_LOG},1);
        }

        //Initialize cursor

        Cursor cursor = getContext().getContentResolver().query(CallLog.Calls.CONTENT_URI, null,
                null, null, CallLog.Calls.DATE + " DESC");

        arrayList1.clear();

        while (cursor.moveToNext()) {


            //get contact name
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                    "name"
            ));
            name = name==null || name.equals("") ? "Unknown" : name;


            @SuppressLint("Range") String duration = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DURATION));

            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(CallLog.Calls.DATE));

            SimpleDateFormat dateFormatter = new SimpleDateFormat(
                    "dd MMM yyyy");
            str_call_date = dateFormatter.format(new Date(Long.parseLong(date)));

            SimpleDateFormat timeFormatter = new SimpleDateFormat(
                    "HH:mm:ss");
            str_call_time = dateFormatter.format(new Date(Long.parseLong(date)));

            duration = DurationFormat(duration);

            //initialize contact model
            CallModel model = new CallModel(name, duration, str_call_time);
            arrayList1.add(model);

        }
        //close phone cursor
        cursor.close();
        return arrayList1;
    }
    private String DurationFormat(String duration) {
        String durationFormatted=null;
        if(Integer.parseInt(duration) < 60){
            durationFormatted = duration+" sec";
        }
        else{
            int min = Integer.parseInt(duration)/60;
            int sec = Integer.parseInt(duration)%60;

            if(sec==0)
                durationFormatted = min + " min" ;
            else
                durationFormatted = min + " min " + sec + " sec";

        }
        return durationFormatted;
    }

}
