package com.example.faltu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private static long number_test = -1;
    private Utility util = new Utility();

    private LinearLayout layout_question;

    private RadioGroup radio_requests;                                    // группа для кнопок выбора

    private RadioButton radio_request;                                    // кнопка выбора

    public String[] test_requests = {"Да", "Нет", "Возможно", "Как-то", "Как-то", "Как-то"}; // массив с вариантами ответов
    public int      quantity_request = -1;                               // количество ответов на данный вопрос

    public void setNumberTest(long numTest) {
        this.number_test = numTest;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        Button end_test = (Button) findViewById(R.id.end_test);
        end_test.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button skip_question = (Button) findViewById(R.id.skip_question);
        skip_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        TextView getNumTest = (TextView) findViewById(R.id.getNumTest);
        getNumTest.setText("Выбран тест №" + number_test);

        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);
        textQuestion.setText("Первый вопрос, как выдумаете зачем " +
                             "\n содается так много машин, заводов " +
                             "\n и разной другой живности. " +
                             "\n Наверно просто жывотные хотят что-то делать.");

        QuestionContentLoad quest_cld = new QuestionContentLoad(getApplicationContext());

        //подгружаем варианты выбора на вопрос

        try{
            layout_question = (LinearLayout) findViewById(R.id.layout_question);
            layout_question.addView(quest_cld.generateContent(getApplicationContext()));
        }catch (Exception e){
            util.getMessage(getApplication(), e.toString());
        }

    }

    public void getRequest(int id) {
        try {
        String message = "";

        switch (id) {
            case 1 : {
                util.getMessage(getApplicationContext(), "Ответ ДА");
            }
            break;
            case 2 : {
                util.getMessage(getApplicationContext(), "Ответ НЕТ");
            }
            break;
            case 3 : {
                util.getMessage(getApplicationContext(), "Ответ ВОЗМОЖНО");
            }
            break;
            default: {
                util.getMessage(getApplicationContext(), "Ответ не ясен");
            }
            break;
        }

        //main.getMessage(message);
        } catch (Exception e) {
            util.getMessage(getApplicationContext(), e.toString());
        }

    }

}
