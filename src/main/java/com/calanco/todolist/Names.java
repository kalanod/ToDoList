package com.calanco.todolist;

import java.io.*;
import java.util.*;

public class Names {
    private ArrayList<String> names = new ArrayList<>();

    public synchronized void add(String name) {
        names.add(name);
    }



    public synchronized void saveToFile(String filename) {
        try {
            FileWriter writer = new FileWriter(filename);
            for (String name : names) {
                writer.write(name + "\n");
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void loadFromFile(String filename) {
        try {
            FileReader reader = new FileReader(filename);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                names.add(line);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public synchronized void remove(String name) {
        if (names.contains(name)) {
            names.remove(name);
        }
    }

    public synchronized String[] getNamesStrings() {
        String[] array = new String[names.size()];
        for (int i = 0; i < names.size(); i++) {
            array[i] = names.get(i);
        }
        return array;
    }

    public synchronized void reset() {
        names.clear();
    }
}