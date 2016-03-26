package com.example.faltu;

import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;
import android.widget.LinearLayout;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MaKsa on 26.03.16.
 */
public class QuestionContentLoad extends LinearLayout {

    private String[] test_questions_requests;
    private String   test_title = "";
    private String   test_question = "";
    private String[] test_requests = {"", "", "", ""};
    private int      max_requests = 4;

    private MainActivity main = new MainActivity();

    // конструктор от класса extends
    public QuestionContentLoad(Context context) {
        super(context);
    }

    public void generateContent() {
        test_questions_requests = getResources().getStringArray(R.array.test1);

        int index_request = 1;
       /* do{

        } while (true);*/

        while (index_request<test_requests.length) {
            main.getMessage( test_questions_requests[index_request] + " " + Integer.toString(index_request) );
            index_request++;
        }

    }

}
