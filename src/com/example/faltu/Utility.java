package com.example.faltu;

import android.app.Activity;
import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * Created by MaKsa on 27.03.16.
 */
public class Utility {

    public void getMessage(Context context, String text) {
        //создаем и отображаем текстовое уведомление
        Toast toast = Toast.makeText(context, text, Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

}
