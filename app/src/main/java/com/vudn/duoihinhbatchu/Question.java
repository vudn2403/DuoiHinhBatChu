package com.vudn.duoihinhbatchu;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Question {
    private int id;
    private String keyword;

    public int getId() {
        return id;
    }

    public String getKeyword() {
        return keyword;
    }

    public Question(int id, String keyword){
        this.id = id;
        this.keyword = keyword.toUpperCase();
    }

    public ArrayList<String> getChars() {
        ArrayList<String> chars = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < 16; i++) {
            if (i < keyword.length()) {
                chars.add(String.valueOf(keyword.charAt(i)));
            }else {
                char c = (char) ('A' + random.nextInt(26));
                chars.add(String.valueOf(c));
            }
        }
        Collections.shuffle(chars);
        return chars;
    }
}
