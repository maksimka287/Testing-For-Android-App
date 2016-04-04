package com.example.faltu.question;

import android.content.*;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import com.example.faltu.Utility;

/**
 * Created by MaKsa on 26.03.16.
 */
public class QuestionContentLoad extends LinearLayout {

    private Utility  util = new Utility();
    private String[] test_questions_requests;                             // массив с данными выбранного теста
    private String   test_title = "";                                     // строка в названием теста
    private String   test_question = "";                                  // строка с вопросом

    public  String[] test_requests = {"Да", "Нет", "Возможно", "Как-то", "Как-то", "Как-то"}; // массив с вариантами ответов
    public  int      quantity_request = -1;                               // количество ответов на данный вопрос

    private int      max_requests = 6;                                    // макс. количество ответов, помещающееся
    private QuestionActivity quest_act = new QuestionActivity();          // указатель(ссылка) на страницу с вопросом

    //private Context context;                                            // контекст указывает на страницу с вопросом

    private LinearLayout layout_question;

    private RadioGroup    radio_requests;                                 // группа для кнопок выбора
    private RadioButton[] radio_request_arr;                              // массив кнопок выбора
    private int           resultRequest      = -1;                        // индекс ответа на вопрос

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
        //util.getMessage(context, "Quantity Requests = " + quantity_request);

        try{
            radio_request_arr = new RadioButton[100];
            // в цикле создаем необходимое количество кнопок выбора
            for (int index_request = 0; index_request <= quantity_request; index_request++) {
                radio_request_arr[index_request] = new RadioButton(context);
                // помещаем варианты ответов в случайном порядке
                int random_request = (int) Math.round(Math.random()*quantity_request);
                radio_requests.addView(radio_request_arr[index_request], index_request);
                radio_request_arr[index_request].setText(test_requests[index_request]);
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
        /*radio_request.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                quest_act.getRequest(radio_requests.getCheckedRadioButtonId());
                //util.getMessage(context, "Button checked = " + radio_requests.getCheckedRadioButtonId());
            }
        });*/

        radio_requests.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //quest_act.getRequest(radio_requests.getCheckedRadioButtonId());
            }
        });
        }catch (Exception e){
            util.getMessage(context, e.toString());
        }

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

    public void getRequest(int id) {
        try {
            String message = "";
            util.getMessage(quest_act.getApplication(), "Button checked id = "+ Integer.toString(id));
            switch (id) {
                case 1 : {
                    util.getMessage(quest_act.getApplication(), "Ответ ДА");
                }
                break;
                case 2 : {
                    util.getMessage(quest_act.getApplicationContext(), "Ответ НЕТ");
                }
                break;
                case 3 : {
                    util.getMessage(quest_act.getApplicationContext(), "Ответ ВОЗМОЖНО");
                }
                break;
                default: {
                    util.getMessage(quest_act.getApplicationContext(), "Ответ не ясен");
                }
                break;
            }
            //main.getMessage(message);
        } catch (Exception e) {
            util.getMessage(quest_act.getApplicationContext(), e.toString());
        }

    }

}
