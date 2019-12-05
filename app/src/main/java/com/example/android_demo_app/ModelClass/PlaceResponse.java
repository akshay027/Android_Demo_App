package com.example.android_demo_app.ModelClass;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlaceResponse {
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("rows")
    @Expose
    private List<PlaceDetails> rows = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<PlaceDetails> getRows() {
        return rows;
    }

    public void setRows(List<PlaceDetails> rows) {
        this.rows = rows;
    }
}
