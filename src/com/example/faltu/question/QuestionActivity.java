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
                /* здесь мы не должны переходить в другое окно
                *  до ответа на последний вопрос данного теста
                *  и после этого момента переходим к окну результатов.
                */
                //Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
                //intent.addFlags(1073741824);
                //startActivity(intent);
            }
        });

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

        String result_question ="";
        // парсим xml ресурс с тестом
        try {
            // get a new XmlPullParser object from Factory
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser xpp = getResources().getXml(R.xml.test_new_1);//factory.newPullParser();

            String name_tag ="";
            String value_tag ="";
            String attribute_name_tag ="";
            String attribute_value_tag ="";
            String question_key ="";

            // get event type
            int eventType = xpp.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                int index_tests = 0;
                int index_question = 0;
                int index_request = 0;
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT: { // начало файла
                        util.getMessage(getApplicationContext(), "start read file = test_new_1");
                    }
                    break;
                    case XmlPullParser.START_TAG: { // начало тега
                        if (xpp.getName()!=null) {
                            name_tag = xpp.getName();
                        }
                        if ( name_tag.equals("requests") ) {
                        // запоминаем ответы на вопрос
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                                attribute_name_tag = xpp.getAttributeName(i);
                                attribute_value_tag = xpp.getAttributeValue(i);
                            //ответы в том же массиве что и вопрос только ниже вопроса
                            if ( attribute_name_tag.indexOf("request") != -1 ) {
                                try {
                                String request_key = "r"+Integer.toString(index_request);
                                list_new.put(request_key,attribute_value_tag);
                                index_request++;
                                } catch (Exception e) {
                                    util.getMessage(getApplicationContext(), "add attr exception = " + e.toString());
                                }
                            }
                        }
                        }
                    }
                    break;
                    case XmlPullParser.END_TAG: { // конец тега

                    }
                    break;
                    case XmlPullParser.TEXT: { // содержимое тега
                        if (xpp.getText()!=null) {
                            value_tag = xpp.getText();
                        }
                        if ( name_tag.equals("title") ) {
                            // запоминаем имя теста
                            list_new.put(name_tag,value_tag);
                            //index_tests++;
                        } else if ( name_tag.equals("question") ) {
                            // запоминаем вопросы
                            index_request = 0;
                            result_question += value_tag + "\n";
                            question_key = "q"+Integer.toString(index_question);
                            list_new.put(question_key,value_tag);
                            index_question++;
                        }
                    }
                    break;
                    default:

                    break;
                }
                // jump to next event
                eventType = xpp.next();
            }
        } catch (XmlPullParserException e) {
            util.getMessage(getApplicationContext(),"Read xml parse exception = " + e.toString());
        } catch (IOException e) {
            util.getMessage(getApplicationContext(),"Read xml IO exception = " + e.toString());
        } catch (Exception e) {
            util.getMessage(getApplicationContext(),"Read xml exception = " + e.toString());
        }

        // загрузка списка тестов из new_tests.xml
        try {
            tests_1 = getResources().getStringArray(R.array.tests1);
            TextView testTitle = (TextView) findViewById(R.id.testTitle);
            util.getMessage(getApplicationContext(), "test = " + number_test);
            if (tests_1[number_test] != null) {
                testTitle.setText(tests_1[number_test]);
            }
        }catch (Exception e) {
            util.getMessage(getApplicationContext(), "load name test error = " + e.toString());
        }

        MultiAutoCompleteTextView textQuestion = (MultiAutoCompleteTextView) findViewById(R.id.textQuestion);
        textQuestion.setText("Первый вопрос, как выдумаете зачем " +
                             "содается так много машин, заводов " +
                             "и разной другой живности. " +
                             "Наверно просто жывотные хотят что-то делать.");
        textQuestion.setText(result_question);

        QuestionContentLoad quest_cld = new QuestionContentLoad(getApplicationContext());

        //подгружаем варианты выбора на вопрос
        try{
            layout_question = (LinearLayout) findViewById(R.id.layout_question);
            layout_question.addView(quest_cld.generateContent(getApplicationContext()));
        }catch (Exception e){
            util.getMessage(getApplication(), e.toString());
        }

    }

}
