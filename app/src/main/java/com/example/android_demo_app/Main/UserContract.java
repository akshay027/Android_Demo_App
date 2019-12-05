package com.example.android_demo_app.Main;

import com.example.android_demo_app.ModelClass.PlaceDetails;

import java.util.ArrayList;

public interface UserContract {
    interface View {
        
        void init();

        void showError();

        void loadDataInList(String title, ArrayList<PlaceDetails> placeDetailsArrayList);

        void success();

    }

    interface Presenter {

        void start();

        void loadUsers();

        void alertDialog();

        void downloadImage(String path, String subject);
    }

}