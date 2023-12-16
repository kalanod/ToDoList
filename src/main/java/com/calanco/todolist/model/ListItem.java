package com.calanco.todolist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListItem implements Serializable {
    int id;
    int isChecked;
    String title;
    ArrayList<Object> arrayList;

    public ListItem(String title) {
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = 0;
        id = new Random().nextInt(999999999);
    }
    public ListItem(String title, int id, int c) {
        this.id = id;
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = c;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<Object> getArrayList() {
        return arrayList;
    }

    public int getId() {
        return id;
    }

    public int getIsChecked() {
        return isChecked;
    }

    public void swich() {
        isChecked = (isChecked + 1) % 2;
    }

    @Override
    public String toString() {
        return "ListItem{" +
                "id=" + id +
                ", isChecked=" + isChecked +
                ", title='" + title + '\'' +
                ", arrayList=" + arrayList +
                '}';
    }
}
