package com.calanco.todolist.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ListItem implements Serializable {
    private static final long serialVersionUID = 6529685098267757690L;
    int id;
    int isChecked;
    String title;
    ArrayList<ListItem> arrayList;
    ListItem parrent;
    Calendar date;
    int type;

    public ListItem(String title, ListItem parrent) {
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = 0;
        id = new Random().nextInt(999999999);
        this.parrent = parrent;
    }
    public ListItem(String title, ListItem parrent, String date, String type) throws ParseException {
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = 0;
        id = new Random().nextInt(999999999);
        this.parrent = parrent;
        this.type = parrent.getParrent() != null? -1: Integer.parseInt(type);
        this.date = parrent.getParrent() != null? null: parseDate(date);
    }
    public ListItem(String title, ListItem parrent, Calendar date, int type) throws ParseException {
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = 0;
        id = new Random().nextInt(999999999);
        this.parrent = parrent;
        this.type = type;
        this.date = date;
    }
    public ListItem(String title, int id, int c, ListItem parrent) {
        this.id = id;
        this.title = title;
        arrayList = new ArrayList<>();
        isChecked = c;
        this.parrent = parrent;
    }

    public ListItem(int id, int isChecked, String title, ListItem parrent, String date, String type) throws ParseException {
        this.id = id;
        this.isChecked = isChecked;
        this.title = title;
        this.parrent = parrent;
        this.type = type==null? -1: Integer.parseInt(type);
        this.date = date==null? null: parseDate(date);
    }

    public ListItem(int id, int isChecked, String title, ArrayList<ListItem> arrayList, ListItem parrent, String date, String type) throws ParseException {
        this.id = id;
        this.isChecked = isChecked;
        this.title = title;
        this.arrayList = arrayList;
        this.parrent = parrent;
        this.type = type==null? -1: Integer.parseInt(type);
        this.date = date==null? null: parseDate(date);
    }
    public static GregorianCalendar parseDate(String dateString) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(dateString);
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListItem item = (ListItem) o;
        return isChecked == item.isChecked &&
                Objects.equals(title, item.title) &&
                arrayList.containsAll(item.arrayList) &&
                item.arrayList.containsAll(arrayList) &&
                Objects.equals(parrent, item.parrent);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isChecked, title, arrayList, parrent);
    }

    public Calendar getDate() {
        return date;
    }

    public int getType() {
        return type;
    }

    public void setId(int nextInt) {
        id = nextInt;
    }

    public void setIsChecked(int b) {
        isChecked = b;
    }
}
