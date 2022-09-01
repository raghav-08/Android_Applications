package com.ashucode.assignment31;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.SearchView;

import androidx.annotation.ContentView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Fragment3 extends Fragment {

    private RecyclerView recyclerView;
    ArrayList<ContactModel> arrayList = new ArrayList<ContactModel>();
    private SearchView searchbar;
    MainAdapter adapter;
    private View v;



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.contact_activity, container, false);

        searchbar = (SearchView)v.findViewById(R.id.searchview);

        recyclerView = v.findViewById(R.id.recycler_view);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());

        RecyclerView.LayoutManager layoutManager = linearLayoutManager;

        recyclerView.setLayoutManager(layoutManager);

        adapter = new MainAdapter(getContext(), getContactList());

        recyclerView.setAdapter(adapter);


        searchbar.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {

                adapter.getFilter().filter(s);
                return false;
            }
        });

        return v;

    }


    @SuppressLint("Range")
    private ArrayList<ContactModel> getContactList() {

        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

        //sort by ascending

        String sort = ContactsContract.Contacts.DISPLAY_NAME+" ASC";

        //Initialize cursor

        Cursor cursor = getContext().getContentResolver().query(
                uri,null,null,null,sort);


            if(cursor.getCount()>0)
            {
                while(cursor.moveToNext()){

                    @SuppressLint("Range") String id = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts._ID));

                    //get contact name
                    @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(
                            ContactsContract.Contacts.DISPLAY_NAME
                    ));

                    //initialize phone uri
                    Uri uriphone = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;

                    //initialize selection
                    String selection = ContactsContract.CommonDataKinds.Phone.CONTACT_ID
                            +" =?";

                    //initialize phone cursor
                    Cursor phnCursor = getContext().getContentResolver().query(
                            uriphone,null,selection
                            ,new String[]{id}, null
                    );

                    //cursor.moveToFirst();
                    if (phnCursor.moveToNext()) {

                        //when phone cursor move to next
                        @SuppressLint("Range") String number = phnCursor.getString(phnCursor.getColumnIndex(
                                ContactsContract.CommonDataKinds.Phone.NUMBER
                        ));
                        //initialize contact model
                        ContactModel model = new ContactModel(name,number);
                        //set name
                        model.setName(name);

                        //set Number
                        model.setNumber(number);

                        //add model in array list
                        arrayList.add(model);

                        //close phone cursor
                        phnCursor.close();

                    }

                }
                //close cursor
                cursor.close();
            }

        return arrayList;
    }

}
