package com.example.faltu;

import android.app.Activity;
import android.content.*;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import java.io.*;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MaKsa on 26.03.16.
 */
public class QuestionContentLoad extends LinearLayout {

    private Utility util = new Utility();
    private String[] test_questions_requests;                             // массив с данными выбранного теста
    private String   test_title = "";                                     // строка в названием теста
    private String   test_question = "";                                  // строка с вопросом

    public String[] test_requests = {"Да", "Нет", "Возможно", "Как-то", "Как-то", "Как-то"}; // массив с вариантами ответов
    public int      quantity_request = -1;                               // количество ответов на данный вопрос

    private int      max_requests = 4;                                    // макс. количество ответов, помещающееся
    private QuestionActivity quest_act = new QuestionActivity();          // указатель(ссылка) на страницу с вопросом

    //private Context context;                                              // контекст указывает на страницу с вопросом

    private LinearLayout layout_question;

    private RadioGroup radio_requests;                                    // группа для кнопок выбора

    private RadioButton radio_request;                                    // кнопка выбора

    private int         resultRequest   = -1;

    // конструктор от класса extends
    public QuestionContentLoad(Context context) {
        super(context);
    }

    //создание необходимого количества кнопок для ответа на вопрос
    public LinearLayout generateContent(Context context) {
        //context = quest_act.getBaseContext();

        try{
            radio_requests = new RadioGroup(context);
        }catch (Exception e){
            util.getMessage(context, e.toString());
        }

        quantity_request = test_requests.length-1;
        util.getMessage(context, "Quantity Requests = " + quantity_request);

        try{
            for (int index_request = 0; index_request <= quantity_request; index_request++) {

                    radio_request = new RadioButton(context);
                    radio_requests.addView(radio_request, index_request);
                    radio_request.setText(test_requests[index_request]);
                    util.getMessage(context, "Request = " + test_requests[index_request]);

            }
        }catch (Exception e){
            util.getMessage(context, e.toString());
        }

        radio_request.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                quest_act.getRequest(radio_requests.getCheckedRadioButtonId());
                //util.getMessage(context, "Button checked = " + radio_requests.getCheckedRadioButtonId());
            }
        });

        util.getMessage(context, "Button checked = " + radio_requests.getCheckedRadioButtonId());

        try{
     //       layout_question = (LinearLayout) findViewById(R.id.layout_question);
     //       layout_question.addView(radio_requests);
            return radio_requests;
        }catch (Exception e){
            util.getMessage(context, e.toString());
            return null;
        }

        //test_questions_requests = getResources().getStringArray(R.array.test1);

     //   int index_request = 0;
       /* do{

        } while (true);*/

     //   while (index_request<test_requests.length) {
     //       util.getMessage(quest_act.getBaseContext(), test_questions_requests[index_request] + " " + Integer.toString(index_request));
     //       index_request++;
     //   }

    }

}
