package com.example.lab52;

import java.util.ArrayList;
import java.util.Arrays;

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
