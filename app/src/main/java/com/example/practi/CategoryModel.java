package com.example.practi;

public class CategoryModel {

    private  String imgeUrl,title;

    public CategoryModel(String imgeUrl, String title) {
        this.imgeUrl = imgeUrl;
        this.title = title;
    }

    public String getImgeUrl() {
        return imgeUrl;
    }

    public void setImgeUrl(String imgeUrl) {
        this.imgeUrl = imgeUrl;
    }

    public String getTile() {
        return title;
    }

    public void setTile(String tile) {
        this.title = title;
    }
}
