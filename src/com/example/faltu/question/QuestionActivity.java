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
import java.util.*;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private static int                number_test     = -1;                 // номер теста
    private int                       number_question = 0;                  // номер вопроса
    private Utility                   util            = new Utility();
    private Map<String, String>       list_new        = new HashMap<String, String>();   //контейнер с содержимым теста
    private static Map<Integer, Map<String, String>> list_tests
         = new HashMap<Integer, Map<String, String>>();                     //контейнер с тестами
    private Map<Integer, Integer>     list_result_request
         = new HashMap<Integer, Integer>();                                 //контейнер с результатами ответов
    private List<String>              list_requests
         = new LinkedList<String>();                                        //контейнер с вариантами ответов
    private LinearLayout              layout_question;                      //визуальный контейнер для вариантов ответа
    private TextView                  testTitle;                            //объект для названия теста
    private MultiAutoCompleteTextView textQuestion;                         //объект для вопроса
    private QuestionContentLoad       quest_cld;                            //указатель(ссылка) на вспомогательный класс


    public void setNumberTest(int numTest) {
        this.number_test = numTest;
    }

    public void setListTests(Map<Integer, Map<String, String>> list_tests) {
        this.list_tests = list_tests;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.question_activity);

        quest_cld = new QuestionContentLoad(getApplicationContext());

        Button endTest = (Button) findViewById(R.id.end_test);
        endTest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // завершение теста на текущем вопросе и переход к профилю
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Button skipQuestion = (Button) findViewById(R.id.skip_question);
        skipQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переход к следующему вопросу теста
                // без проверки что ответ выбран
                // закончились вопросы переходим к окну результатов
                number_question++;
                getNextQuestion();
            }
        });

        Button nextQuestion = (Button) findViewById(R.id.next_question);
        nextQuestion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // переход к следующему вопросу теста
                // закончились вопросы переходим к окну результатов
            if (quest_cld.getResultRequest()!=-1) {
                list_result_request.put( number_question, quest_cld.getResultRequest() );
                number_question++;
                getNextQuestion();
                quest_cld.setResultRequest(-1);
            } else{
                util.getMessage(getApplicationContext(),"Не выбран вариант ответа.");
            }
            }
        });

        testTitle = (TextView) findViewById(R.id.testTitle);

        // парсим xml ресурс с тестом
        //list_tests = util.parsingXML(getApplicationContext(),this);
        //достаем выбранный тест
        list_new = list_tests.get(number_test);
        // достаем название теста
        if ( list_new.get("title") != null ) {
            testTitle.setText(list_new.get("title"));
        }

        textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);

        getNextQuestion();

    }

    public void getNextQuestion() {

        // находим по хеш ключу все ответы для данного вопроса
        String question_key = "q"+Integer.toString(number_question);
        int index_req =0;
        String request_key = "r"+Integer.toString(index_req);
        String key = question_key + request_key;

        if ( list_new.get(question_key) == null ) {
            ResultActivity result_act = new ResultActivity();
            //передача вариантов ответов в класс результата
            result_act.setListRequest(list_result_request);
            Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
            intent.addFlags(1073741824);
            startActivity(intent);
        }
        textQuestion.setText(list_new.get(question_key));
        try {
            list_requests.clear();
            while ( list_new.get(key) != null ){
                request_key = "r"+Integer.toString(index_req);
                key = question_key + request_key;
                if ( list_new.get(key)!=null ) {
                    list_requests.add(list_new.get(key));
                    //util.getMessage(getApplicationContext(), list_new.get(key));
                }
                index_req++;
            }
        } catch (Exception e) {
            util.getMessage(getApplication(),"Get request exception = " + e.toString());
        }

        //подгружаем варианты ответа на вопрос
        try{
            layout_question = (LinearLayout) findViewById(R.id.layout_question);
            layout_question.removeAllViews();
            layout_question.addView( quest_cld.generateContent( getApplicationContext(), list_requests ) );
            layout_question.forceLayout();
        }catch (Exception e){
            util.getMessage(getApplication(), e.toString());
        }


    }

}
