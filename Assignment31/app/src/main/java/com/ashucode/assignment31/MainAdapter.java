package com.ashucode.assignment31;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements Filterable {

    Context mcontext;
    ArrayList<ContactModel> backup;
    ArrayList<ContactModel> marrayList;

    public MainAdapter(Context context, ArrayList<ContactModel> arrayList) {
        mcontext = context;
        marrayList = arrayList;
        backup = new ArrayList<>(marrayList);
        notifyDataSetChanged();
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mcontext)
                .inflate(R.layout.item_contact,parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        //Initialize contact model
        TextView tvName, tvNumber;
        ContactModel model = marrayList.get(position);

        //set name
        holder.tvName.setText(model.getName());
        //setnumber
        holder.tvNumber.setText(model.getNumber());

        holder.itemView.setOnClickListener(v ->{
            Intent intent = new Intent(mcontext, MainActivity.class);
            intent.putExtra("Name", model.getName() );
            mcontext.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {

        //return arraylist size

        return marrayList.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence keyword)
        {

            ArrayList<ContactModel> filterlist = new ArrayList<>();

            if(keyword.toString().isEmpty())
                filterlist.addAll(backup);
            else
            {
                for (ContactModel items : backup)
                {
                    if(items.getName().toString().toLowerCase().contains(keyword.toString().toLowerCase()))
                    {
                        filterlist.add(items);
                    }
                }

            }
            FilterResults results = new FilterResults();
            results.values= filterlist;
            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results)
        {
            marrayList.clear();
            marrayList.addAll((ArrayList) results.values);
            notifyDataSetChanged();
        }
    };

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvNumber;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            //Assign variable
            tvName= itemView.findViewById(R.id.tv_name);
            tvNumber = itemView.findViewById(R.id.tv_number);
        }
    }
}
