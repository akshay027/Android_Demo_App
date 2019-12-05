package com.example.android_demo_app.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.android_demo_app.Adapter.CustomAdapter;
import com.example.android_demo_app.ModelClass.PlaceDetails;
import com.example.android_demo_app.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ListView placeListView;

    private SwipeRefreshLayout swipeRefreshLayout;
    private CustomAdapter customAdapter;

    private ArrayList<PlaceDetails> placeDetailsArrayList;

    private ActionBar actionBar;
    private int itemPosition;

    private Button refreshView;
    private Context context = MainActivity.this;
    private String imagefile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        actionBar = getSupportActionBar();
        actionBar.setTitle("");

        placeListView = findViewById(R.id.list_item_view);
        swipeRefreshLayout = findViewById(R.id.swipeToRefresh);
        refreshView = findViewById(R.id.refreshView);


    }
}
