package com.example.android_demo_app.Main;

import android.app.AlertDialog;
import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.Environment;
import android.widget.Toast;

import com.example.android_demo_app.Api.APIClient;
import com.example.android_demo_app.Api.APIInterface;
import com.example.android_demo_app.Api.UIUtil;
import com.example.android_demo_app.ModelClass.PlaceDetails;
import com.example.android_demo_app.ModelClass.PlaceResponse;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserPresenter implements UserContract.Presenter {
    UserContract.View mView;
    String title;
    Context context;
    private DownloadManager downloadManager;
    private long reference;
    private APIInterface apiInterface;
    ArrayList<PlaceDetails> placeDetailsArrayList = new ArrayList<>();

    UserPresenter(UserContract.View mView, Context context) {
        this.mView = mView;
        this.context = context;
    }

    @Override
    public void loadUsers() {

        try {
            if (UIUtil.isInternetAvailable(context)) {
                UIUtil.startProgressDialog(context, "Please Wait.. ");
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<PlaceResponse> call = apiInterface.getPlaceDetails();
                call.enqueue(new Callback<PlaceResponse>() {
                    @Override
                    public void onResponse(Call<PlaceResponse> call, Response<PlaceResponse> response) {
                        UIUtil.stopProgressDialog(context);
                        PlaceResponse resource = response.body();

                        //clear the arraylist before adding details if you wont clear it will create duplicate record
                        title = resource.getTitle();
                        placeDetailsArrayList.clear();
                        placeDetailsArrayList.addAll(resource.getRows());
                        mView.loadDataInList(title, placeDetailsArrayList);
                    }

                    @Override
                    public void onFailure(Call<PlaceResponse> call, Throwable t) {
                        UIUtil.stopProgressDialog(context);
                        call.cancel();
                        mView.showError();
                    }
                });
            } else {
                mView.showError();
                Toast.makeText(context, "Please Connect to Internet", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void alertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        //Setting the title manually
        builder.setTitle("Confirmation");
        builder.setMessage("Do you want to download this image file ?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        mView.success();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //  Action for 'NO' Button
                        dialog.cancel();

                    }
                });
        //Creating dialog box
        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public void downloadImage(String path, String subject) {
        if (downloadManager == null)
            downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);

        Uri uri = Uri.parse(path);

// execute this when the downloader must be fired
        DownloadManager.Request request = new DownloadManager.Request(uri);
        request.setTitle(path);
        request.setDestinationInExternalFilesDir(context, Environment.DIRECTORY_DOWNLOADS, "Image" + subject + path);
        request.setVisibleInDownloadsUi(true);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        reference = downloadManager.enqueue(request);
        Toast.makeText(context, "Started Downloading ..", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void start() {
        mView.init();
    }


}