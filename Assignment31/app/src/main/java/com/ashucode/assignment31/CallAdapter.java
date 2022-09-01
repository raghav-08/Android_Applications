package com.ashucode.assignment31;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CallAdapter extends RecyclerView.Adapter<CallAdapter.ViewHolder> {

    Context ccontext;
    ArrayList<CallModel> carrayList;

    public CallAdapter(Context context, ArrayList<CallModel> arrayList) {
        ccontext = context;
        carrayList = arrayList;
        notifyDataSetChanged();
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(ccontext)
                .inflate(R.layout.item_call,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize contact model
        CallModel model = carrayList.get(position);

        //set name
        holder.callname.setText(model.getName());
        //setduration
        holder.callduration.setText(model.getDuration());
        //setdate
        holder.calldate.setText(model.getDate());

    }

    @Override
    public int getItemCount() {

        //return arraylist size

        return carrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView callname, callduration,calldate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            callname= itemView.findViewById(R.id.call_name);
            callduration = itemView.findViewById(R.id.call_duration);
            calldate = itemView.findViewById(R.id.call_date);
        }
    }
}
