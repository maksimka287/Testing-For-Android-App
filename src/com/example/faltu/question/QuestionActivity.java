package com.example.faltu.question;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import com.example.faltu.ProfileActivity;
import com.example.faltu.R;
import com.example.faltu.Utility;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by MaKsa on 24.03.16.
 */
public class QuestionActivity extends Activity {

    private long number_test     = -1;      // номер теста
    private int number_question = 0;       // номер вопроса
    private Utility util = new Utility();
    private String test_name = "";
    private String[] listTests;
    private String[] test_questions;
    private String[] test_questions_requests;

    private LinearLayout layout_question;
    private String[]     tests_1;                                         // список названий тестов
    private ArrayList test_1 = new ArrayList();

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
                Intent intent = new Intent(QuestionActivity.this, ProfileActivity.class);
                intent.addFlags(1073741824);
                startActivity(intent);
            }
        });

        Activity activity = new Activity();
        Resources resource = activity.getResources();
        XmlPullParser xpp = resource.getXml(R.xml.test_new_1);

        /*<contact>                // имя тега contact начало
        <firstname>Кот</firstname> // имя тега firstname содержимое тега Кот
        <lastname>Рыжик</lastname> // имя тега lastname содержимое тега Рыжик
        <phone                     // имя тега phone содержит 2 атрибута
            home="4516585"         // имя атрибута home содержимое атрибута текст 4516585
            work="1111" />         // имя атрибута work содержимое атрибута текст 1111
        </contact>*/               // конец тега contact

        //xpp.getName();            // имя тега
        //xpp.getAttributeCount();  // число атрибутов тега
        //xpp.getDepth();           // уровень вложенности
        //xpp.getText();            // содержимое тега
        //xpp.getAttributeName(1);  // имя первого атрибута
        //xpp.getAttributeValue(1); // значение первого атрибута

        // парсим xml ресурс с тестом
        try {
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: {
                        String tmp_name = "";
                        String tmp_value = "";
                        int index_tests = 0;
                        int index_question = 0;
                        int index_request = 0;

                        if ( xpp.getName() == "title" ) {
                            // ззапоминаем имя теста
                            listTests[index_tests] = xpp.getText();
                            index_tests++;
                        } else if ( xpp.getName() == "question" ) {
                            //запоминаем вопросы
                            test_questions_requests[index_request] = xpp.getText();
                            listTests[index_tests] = test_questions_requests[index_request];
                            index_request++;
                        } else if ( xpp.getName() == "requests") {
                            //запоминаем ответы на вопрос
                            for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                tmp_name += xpp.getAttributeName(i);
                                tmp_value += xpp.getAttributeValue(i);
                            }
                            //ответы в том же массиве что и вопрос только ниже вопроса
                            if ( tmp_name.indexOf("request") != -1 ) {
                                test_questions_requests[index_request] = tmp_value;
                                index_request++;
                            }
                        }
                    }
                    break;
                    case XmlPullParser.START_TAG: { // начало тега

                    }
                    break;
                    case XmlPullParser.END_TAG: { // конец тега

                    }
                    break;
                    case XmlPullParser.TEXT: { // содержимое тега

                    }
                    break;
                    default:

                    break;
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            util.getMessage(getApplicationContext(), e.toString());
        } catch (IOException e) {
            util.getMessage(getApplicationContext(), e.toString());
        }

        TextView testTitle = (TextView) findViewById(R.id.testTitle);
        testTitle.setText(tests_1[(int)number_test]);

        test_1.add(getResources().getStringArray(R.array.test1)) ;
        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);
        textQuestion.setText("Первый вопрос, как выдумаете зачем " +
                             "содается так много машин, заводов " +
                             "и разной другой живности. " +
                             "Наверно просто жывотные хотят что-то делать.");

        for (int index=0;index<=2;index++) {
            util.getMessage(getApplicationContext(), test_1.get(index).toString());
        }
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
            util.getMessage(getApplicationContext(), "Button checked id = "+ Integer.toString(id));
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
