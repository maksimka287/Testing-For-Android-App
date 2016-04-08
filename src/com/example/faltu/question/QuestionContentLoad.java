package com.example.faltu.question;

import android.content.*;
import android.content.res.ColorStateList;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.faltu.R;
import com.example.faltu.Utility;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by MaKsa on 26.03.16.
 */
public class QuestionContentLoad extends LinearLayout {

    private Utility  util = new Utility();

    private QuestionActivity quest_act          = new QuestionActivity();   // указатель(ссылка) на страницу с вопросом
    private int              resultRequest      = -1;                       // индекс ответа на вопрос

    // конструктор от класса extends
    public QuestionContentLoad(Context context) {
        super(context);
    }

    public int getResultRequest() {
        return this.resultRequest;
    }

    public void setResultRequest(int request) {
        this.resultRequest = request;
    }

    //создание необходимого количества кнопок для ответа на вопрос
    // list_requests  контейнер с вариантами ответов
    public LinearLayout generateContent(Context context, List<String> list_requests) {
        //context.getApplicationContext().setTheme(R.style.DarkStyleGray);
        //context = quest_act.getBaseContext();

        // группа для кнопок выбора
        RadioGroup radio_requests = new RadioGroup(context);

        // макс. количество ответов, помещающееся
        final int MAX_REQUEST = 6;

        // количество ответов на данный вопрос
        int quantity_request = -1;
        quantity_request = list_requests.size()-1;
        //util.getMessage(context, "Quantity Requests = " + quantity_request);

        try{
            // массив кнопок выбора
            RadioButton[] radio_request_arr = new RadioButton[list_requests.size()];
            // в цикле создаем необходимое количество кнопок выбора
            for (int index_request = 0; index_request <= quantity_request; index_request++) {
                radio_request_arr[index_request] = new RadioButton(context);
                // помещаем варианты ответов в случайном порядке
                int random_request = (int) Math.round(Math.random()*quantity_request);
                radio_requests.addView(radio_request_arr[index_request], index_request);
                //radio_request_arr[index_request].setTextColor(0x10203f);
                //radio_request_arr[index_request].getContext().getApplicationContext().setTheme(R.style.DarkStyle);
                //radio_requests.setBackgroundColor(0xd3c9e7);
                radio_request_arr[index_request].setText(list_requests.get(index_request));
                final int index = index_request;
                // в событии нажатия получаем номер выбранного значения
                radio_request_arr[index_request].setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        resultRequest = index;
                    }
                });

            }
        }catch (Exception e){
            util.getMessage(context, e.toString());
        }

        try{
     //       LinearLayout layout_question;
     //       layout_question = (LinearLayout) findViewById(R.id.layout_question);
     //       layout_question.addView(radio_requests);
            return radio_requests;
        }catch (Exception e){
            util.getMessage(context, e.toString());
            return null;
        }

    }

}
