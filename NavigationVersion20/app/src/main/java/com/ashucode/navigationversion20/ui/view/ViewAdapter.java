package com.ashucode.navigationversion20.ui.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ashucode.navigationversion20.R;

import java.util.ArrayList;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.ViewHolder> {

   private final Context ccontext;
    private final ArrayList<ViewModel> carrayList;

    public ViewAdapter(Context context, ArrayList<ViewModel> arrayList) {
        ccontext = context;
        carrayList = arrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder( LayoutInflater.from(ccontext)
                .inflate(R.layout.student_viewlist,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize contact model
        ViewModel model = carrayList.get(position);
        //set name
        holder.StudentName.setText(model.getStuName());
        holder.marks.setText(model.getMarks());
    }

    @Override
    public int getItemCount() {
        //return arraylist size
        return carrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView StudentName;
        final TextView marks;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            marks= itemView.findViewById(R.id.tv_studentpercent);
            StudentName = itemView.findViewById(R.id.tv_studentname);
        }
    }
}