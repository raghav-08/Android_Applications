package com.ashucode.assignment31;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ashucode.assignment31.ui.main.SectionsPagerAdapter;
import com.ashucode.assignment31.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    TabLayout tabLayout;
    ViewPager viewPager;
    String name = "Maa";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tabs);
        viewPager = findViewById(R.id.view_pager);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this
                , getSupportFragmentManager());
        viewPager.setAdapter(sectionsPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);

        //Check permission
        checkPermission();

        if (getIntent().getStringExtra("Name") != null)
            name = getIntent().getStringExtra("Name");


    }

    private void checkPermission() {

        //Check Condition
        if(ContextCompat.checkSelfPermission(MainActivity.this
                , Manifest.permission.READ_CONTACTS)
        != PackageManager.PERMISSION_GRANTED)
        {
            //when permission not granted
            //Request permission
            ActivityCompat.requestPermissions(MainActivity.this
                    ,new String[]{Manifest.permission.READ_CONTACTS},1);
        }


    }



    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //check condition
        if(requestCode == 1 && grantResults.length >0 && grantResults[0]
                == PackageManager.PERMISSION_GRANTED )
        {
            //when permission is granted
            //call methode
            Toast.makeText(this, "Permission Granted.", Toast.LENGTH_SHORT).show();
        }
        else
        {
            //when permission is denied
            //display toast
            Toast.makeText(this, "Permission Denied.", Toast.LENGTH_SHORT).show();

            //call check permission method
            checkPermission();
        }
    }
}