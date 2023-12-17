package com.calanco.todolist.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class ListItem implements Serializable {
    int id;
    int isChecked;
    String title;
    ArrayList<ListItem> arrayList;
    ListItem parrent;

    public ListItem(String title, ListItem parrent) {
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = 0;
        id = new Random().nextInt(999999999);
        this.parrent = parrent;
    }
    public ListItem(String title, int id, int c, ListItem parrent) {
        this.id = id;
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = c;
        this.parrent = parrent;
    }

    public String getTitle() {
        return title;
    }

    public ArrayList<ListItem> getArrayList() {
        return arrayList;
    }

    public ListItem getParrent() {
        return parrent;
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
