package com.example.practi;

public class ListPDFLoad {

    private String id,title,location;


    public ListPDFLoad(){

    }

    public ListPDFLoad(String id, String title, String location) {
        this.id = id;
        this.title = title;
        this.location = location;
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
