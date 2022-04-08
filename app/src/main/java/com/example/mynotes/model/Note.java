package com.example.mynotes.model;

import android.net.Uri;

import java.util.List;

public class Note {
    private String id,title,description;
    List<String> images;

    public Note(String id, String title, String description, List<String> images) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.images = images;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }
}
