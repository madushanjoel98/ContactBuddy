package com.example.contactview.utils;

import android.app.Activity;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

public class ColorFun {

    private static ColorFun single_instance = null;

    private ColorFun() {
    }

    public static synchronized ColorFun getInstance() {
        if (single_instance == null)
            single_instance = new ColorFun();

        return single_instance;
    }

    public void changecorlorByLatter(View view, String letter) {
        letter = letter.toUpperCase();

        int color;

        switch (letter) {
            case "A":
                color = Color.rgb(247, 153, 146);
                break;
            case "B":
                color = Color.rgb(245, 232, 118);
                break;
            case "C":
                color = Color.rgb(123, 188, 146);
                break;
            case "D":
                color = Color.rgb(232, 206, 128);
                break;
            case "E":
                color = Color.rgb(221, 240, 158);
                break;
            case "F":
                color = Color.rgb(200, 240, 158);
                break;
            case "J":
                color = Color.rgb(198, 240, 158);
                break;
            case "K":
                color = Color.rgb(195, 240, 158);
                break;
            case "L":
                color = Color.rgb(192, 240, 158);
                break;
            case "M":
                color = Color.rgb(184, 224, 245);
                break;
            case "N":
                color = Color.rgb(255, 100, 66);
                break;
            case "0":
                color = Color.rgb(240, 214, 66);
                break;
            case "P":
                color = Color.rgb(200, 150, 66);
                break;
            case "Q":
                color = Color.rgb(249, 214, 66);
                break;
            case "S":
                color = Color.rgb(242, 214, 66);
                break;
            case "T":
                color = Color.rgb(243, 214, 66);
                break;
            case "U":
                color = Color.rgb(244, 214, 66);
                break;
            case "V":
                color = Color.rgb(245, 100, 150);
                break;
            case "W":
                color = Color.rgb(239, 214, 66);
                break;
            case "X":
                color = Color.rgb(150, 150, 66);
                break;
            case "Y":
                color = Color.rgb(40, 214, 66);
                break;
            case "Z":
                color = Color.rgb(100, 214, 66);
                break;
            default:
                color = Color.rgb(213, 179, 255);
                break;
        }

        // Create a ColorStateList and set it as the background tint
        view.setBackgroundTintList(ColorStateList.valueOf(color));
    }

    public void changeRandomColor(View view, Activity activity ) {
        String[] hasshs = {"#ffdb78", "#94cbff", "#def7be", "#bef7da",
                           "#548dff", "#e66d74", "#f36a6f", "#D88CF6"};
        Window window = activity.getWindow();

        // Generate a random index to pick a color from the array
        int randomIndex = new Random().nextInt(hasshs.length);

        // Get the color using the random index
        int color = Color.parseColor(hasshs[randomIndex]);

        // Set the background tint for the view
        window.setStatusBarColor(color);
        view.setBackgroundTintList(ColorStateList.valueOf(color));
    }


}
