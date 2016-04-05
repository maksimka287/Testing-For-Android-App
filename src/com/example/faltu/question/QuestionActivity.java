package com.example.faltu.question;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.*;
import com.example.faltu.ProfileActivity;
import com.example.faltu.R;
import com.example.faltu.ResultActivity;
import com.example.faltu.Utility;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private static int number_test     = -1;      // номер теста
    private int number_question = 0;       // номер вопроса
    private Utility util = new Utility();
    private Map<String, String> list_new = new HashMap<String, String>();

    private LinearLayout layout_question;
    private String[]     tests_1;                                         // список названий тестов

    private boolean initialize = false;
    private String question ="";
    String[] requests = new String[100];
    private TextView testTitle;
    private MultiAutoCompleteTextView textQuestion;


    public String[] test_requests = {"Да", "Нет", "Возможно", "Как-то", "Как-то", "Как-то"}; // массив с вариантами ответов
    public int      quantity_request = -1;                               // количество ответов на данный вопрос

    public void setNumberTest(int numTest) {
        this.number_test = numTest;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        Button endTest = (Button) findViewById(R.id.end_test);
        endTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button skipQuestion = (Button) findViewById(R.id.skip_question);
        skipQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button nextQuestion = (Button) findViewById(R.id.next_question);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                util.getMessage(getApplicationContext(),"Переходим к следующему вопросу");
                number_question++;
                getNextQuestion();
                /* здесь мы не должны переходить в другое окно
                *  до ответа на последний вопрос данного теста
                *  и после этого момента переходим к окну результатов.
                */
                //Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                //intent.addFlags(1073741824);
                //startActivity(intent);
            }
        });

        testTitle = (TextView) findViewById(R.id.testTitle);
        textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);
/*        textQuestion.setText("Первый вопрос, как выдумаете зачем " +
                             "содается так много машин, заводов " +
                             "и разной другой живности. " +
                             "Наверно просто жывотные хотят что-то делать.");
*/
        getNextQuestion();

    }

    public void getNextQuestion() {

        // парсим xml ресурс с тестом
        list_new = util.parsingXML(getApplicationContext(),this);

        // загрузка списка тестов из new_tests.xml
        try {
            tests_1 = getResources().getStringArray(R.array.tests1);
            //util.getMessage(getApplicationContext(), "test = " + number_test);
            if (tests_1[number_test] != null) {
                testTitle.setText(tests_1[number_test]);
            }
        }catch (Exception e) {
            util.getMessage(getApplicationContext(), "load name test error = " + e.toString());
        }

        textQuestion.setText(question);

        QuestionContentLoad quest_cld = new QuestionContentLoad(getApplicationContext());

        // находим по хеш ключу все ответы для данного вопроса
        String question_key = "q"+Integer.toString(number_question);
        int index_req =0;
        String request_key = "r"+Integer.toString(index_req);
        String key = question_key + request_key;
        question = list_new.get(question_key);
        try {
            while ( list_new.get(key) != null ){
                request_key = "r"+Integer.toString(index_req);
                key = question_key + request_key;
                if ( list_new.get(key)!=null ) {
                    requests[index_req] = list_new.get(key);
                    util.getMessage(getApplicationContext(), list_new.get(key));
                }
                index_req++;
            }
        } catch (Exception e) {
            util.getMessage(getApplication(),"Get request exception =" + e.toString());
        }

        //отправляем ответы в спомогательный класс для отрисовки кнопок выбора
        quest_cld.setTestRequests(requests);

        //подгружаем варианты выбора на вопрос
        try{
            layout_question = (LinearLayout) findViewById(R.id.layout_question);
            layout_question.removeAllViews();
            layout_question.addView(quest_cld.generateContent(getApplicationContext()));
            layout_question.forceLayout();
        }catch (Exception e){
            util.getMessage(getApplication(), e.toString());
        }


    }

}
