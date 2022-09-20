package com.example.lab5;

import java.util.ArrayList;

public class Word {
    public ArrayList<String> badWords;
    public ArrayList<String> goodWords;

    public Word(){
        this.badWords = new ArrayList<String>();
        this.goodWords = new ArrayList<String>();

        badWords.add("fuck");
        badWords.add("olo");

        goodWords.add("happy");
        goodWords.add("enjoy");
        goodWords.add("like");
    }
}
