package com.ashucode.navigationversion20.ui.home;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashucode.navigationversion20.R;

import java.util.ArrayList;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {

    private final Context ccontext;
    private final ArrayList<HomeModel> carrayList;

    public HomeAdapter(Context context, ArrayList<HomeModel> arrayList) {
        ccontext = context;
        carrayList = arrayList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(ccontext)
                .inflate(R.layout.student_list,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize contact model
        HomeModel model = carrayList.get(position);
        //set name
        holder.RollNo.setText(model.getRollNO());
        holder.StudentName.setText(model.getName());
        holder.date.setText(model.getDate());
    }

    @Override
    public int getItemCount() {
        //return arraylist size
        return carrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView RollNo;
        final TextView StudentName;
        final TextView date;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            RollNo= itemView.findViewById(R.id.RollNo);
            StudentName = itemView.findViewById(R.id.Student_name);
            date = itemView.findViewById(R.id.date);
        }
    }
}
