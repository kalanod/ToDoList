package com.calanco.todolist.adapters;

import com.calanco.todolist.model.ListItem;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class DataAdapter {

    String p;


    public DataAdapter(String p) {
        this.p = p;
    }

    public ListItem getLists() {
        try {
            ListItem item = deserialize();
            if (item == null) storeNew(new ListItem("root", -1, 0, null));
            return deserialize();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void storeNew(ListItem listItem) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("list.ser"));
        oos.writeObject(listItem);
        oos.close();
    }

    public ListItem deserialize() throws IOException {
        ObjectInputStream objectinputstream = null;
        ListItem readCase = null;
        try {
            FileInputStream streamIn = new FileInputStream("list.ser");
            objectinputstream = new ObjectInputStream(streamIn);
            readCase = (ListItem) objectinputstream.readObject();
        } catch (Exception e) {
            readCase = new ListItem("root", -1, 0, null);
        } finally {
            if (objectinputstream != null) {
                objectinputstream.close();
            }
        }
        return readCase;
    }

//    public ListItem readData() {
//        ListItem root = new ListItem("root", null);
//        Stack<ListItem> stack = new Stack<>();
//        stack.addElement(root);
//        String line;
//        int i = 0;
//        try (BufferedReader reader = new BufferedReader(new FileReader("list.txt"))) {
//            while ((line = reader.readLine()) != null) {
//                if (line.split("~")[0].equals("{")) {
//                    stack.addElement(new ListItem(line.split("~")[1], i, Integer.parseInt(line.split("~")[2])));
//                } else if (line.equals("}")) {
//                    ListItem tmp = stack.pop();
//                    stack.peek().getArrayList().add(tmp);
//                } else {
//                    stack.peek().getArrayList().add(new ListItem(line.split("~")[0], i, Integer.parseInt(line.split("~")[1])));
//                }
//                i += 1;
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try {
//            storeNew(root);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return root;
//    }

    public void swich(int i) throws IOException {
        ListItem all = getLists();
        ListItem item = getItem(all, i);
        item.swich();
        storeNew(getRoot(item));
    }

    //    public ListItem getItem(ListItem root, int id) {
//        if (root.getId() == id) {
//            return root;
//        } else {
//            for (int i = 0; i < root.getArrayList().size(); i++) {
//                if (((ListItem) root.getArrayList().get(i)).getId() == id)
//                    return (ListItem) root.getArrayList().get(i);
//                getItem((ListItem) root.getArrayList().get(i), id);
//            }
//        }
//        return null;
//    }
    public ListItem getItem(ListItem root, int id) {
        Stack<ListItem> stack = new Stack<>();
        stack.add(root);
        ListItem e;
        while (!stack.isEmpty()) {
            e = stack.pop();
            if (e.getId() == id) return e;
            for (ListItem l : e.getArrayList()) {
                if (l.getId() == id) return l;
                stack.add(l);
            }
        }
        return null;
    }

    public ListItem getRoot(ListItem elem) {
        if (elem.getParrent() == null) return elem;
        return getRoot(elem.getParrent());
    }

    public StringBuilder rprint(ListItem root, StringBuilder builder) {
        builder.append("<ol>");
        for (ListItem i: root.getArrayList()) {
            builder.append("<li><div class=\"form-check\">\n" +
                            "  <label class=\"form-check-label\" for=\"flexCheckDefault\">\n")
                    .append(i.getTitle())
                    .append("  </label>\n" +
                            "  <input class=\"form-check-input\" type=\"checkbox\"" +
                            " id=\"")
                    .append(i.getId())
                    .append(i.getIsChecked() == 0 ? "\"" : "\" checked")
                    .append("></div></li>");
            builder.append("<div style='display: inline-block'><a href='").append(p).append("/del?id=")
                    .append(i.getId()).append("'type=\"button\" class=\"btn btn-primary btn-sm\" style='display: inline-block'>удалить</a>")
                    .append("<button id='").append(i.getId()).append("'type=\"button\" class=\"btn btn-primary btnAdd btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" style='display: inline-block'>добавить</button>")
                    .append("</div>");
            rprint(i, builder);
        }
        builder.append("</ol>");
        return builder;
    }
    public String print(ListItem root) {
        return rprint(root, new StringBuilder()).append("<div style='display: inline-block'><a href='").append(p).append("/del?id=")
                .append(root.getId()).append("'type=\"button\" class=\"btn btn-primary btn-sm\" style='display: inline-block'>удалить</a>")
                .append("<button id='").append(root.getId()).append("'type=\"button\" class=\"btn btn-primary btnAdd btn-sm\" data-bs-toggle=\"modal\" data-bs-target=\"#exampleModal\" style='display: inline-block'>добавить</button>")
                .append("</div>").toString();
    }


    @Deprecated
    public String toText(ListItem root, StringBuilder builder) {
        if (root.getArrayList().size() == 0) {
            if (!builder.toString().equals(""))
                builder.append(root.getTitle()).append("~").append(root.getIsChecked());
        } else {
            builder.append("{~").append(root.getTitle()).append("~0\n");
            for (int i = 0; i < root.getArrayList().size(); i++) {
                toText((ListItem) root.getArrayList().get(i), builder);
                builder.append("\n");
            }
            builder.append("}");
        }
        return builder.substring(9, builder.toString().length() - 2);
    }

    public void delete(int id) throws IOException {
        ListItem item = getItem(getLists(), id);
        ListItem parrent = item.getParrent();
        parrent.getArrayList().remove(item);
        storeNew(getRoot(parrent));
    }

    public void add(String id, String title) throws IOException {
        ListItem item = getItem(getLists(), Integer.parseInt(id));
        item.getArrayList().add(new ListItem(title, item));
        ListItem lk = getRoot(item);
        storeNew(getRoot(item));
    }
}
