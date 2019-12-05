package com.example.android_demo_app.Main;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.android_demo_app.Adapter.CustomAdapter;
import com.example.android_demo_app.ModelClass.PlaceDetails;
import com.example.android_demo_app.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements UserContract.View {

    private UserContract.Presenter mPresenter;

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

        placeDetailsArrayList = new ArrayList<>();
        mPresenter = new UserPresenter(this, context);
        mPresenter.start();

    }

    @Override
    public void init() {

        mPresenter.loadUsers();

        refreshView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mPresenter.loadUsers();

            }
        });
        //setting an setOnRefreshListener on the SwipeDownLayout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                mPresenter.loadUsers();

                swipeRefreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void loadDataInList(String title, final ArrayList<PlaceDetails> placeDetailsArrayList) {
        if (TextUtils.isEmpty(title)) {
            actionBar.setTitle("");
        } else {
            actionBar.setTitle(title);
        }
        if (placeDetailsArrayList.size() <= 0) {
            refreshView.setVisibility(View.VISIBLE);
            placeListView.setVisibility(View.GONE);
        } else {
            refreshView.setVisibility(View.GONE);
            placeListView.setVisibility(View.VISIBLE);
        }
        customAdapter = new CustomAdapter(this, placeDetailsArrayList);
        placeListView.setAdapter(customAdapter);


        customAdapter.SetOnItemClickListener(new CustomAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                itemPosition = position;
                imagefile = placeDetailsArrayList.get(itemPosition).getImageHref();
                mPresenter.alertDialog();
            }
        });

    }

    @Override
    public void success() {
        mPresenter.downloadImage(imagefile, "Image");
    }

    @Override
    public void showError() {
        refreshView.setVisibility(View.VISIBLE);
        placeListView.setVisibility(View.GONE);
    }

}